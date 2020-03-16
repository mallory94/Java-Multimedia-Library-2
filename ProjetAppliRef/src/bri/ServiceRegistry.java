package bri;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServiceRegistry {
	// cette classe est un registre de services
	// partagée en concurrence par les clients et les "ajouteurs" de services,
	// un Vector pour cette gestion est pratique
	
	
	
	private static List<Class<? extends bri.Service>> servicesClasses;
	private static List<Class<? extends bri.Service>> servicesDemarrer; // contient tous les services démarés
	
	static {
		servicesClasses = new ArrayList<Class<? extends bri.Service>>();
		servicesDemarrer = new ArrayList<Class<? extends bri.Service>>();
		try {

			ServiceRegistry.addService((Class<? extends bri.Service>) Class.forName("services.ServiceInversion"));
			ServiceRegistry.addService((Class<? extends bri.Service>) Class.forName("services.ServiceXmlAnalyse"));
			ServiceRegistry.addService((Class<? extends bri.Service>) Class.forName("services.ServiceMessagerieInterne"));
			
			for (int i = 0; i < servicesClasses.size(); i++) { // par défault tous les services sont démarrés
				servicesDemarrer.add(servicesClasses.get(i));
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

// ajoute une classe de service après contrôle de la norme BLTi
	@SuppressWarnings("unchecked")
	public static void addService(Class<?> classe) {
		synchronized (servicesClasses) {
			
			// vérifier la conformité par introspection
			try {
				Class<?>[] tabI = classe.getInterfaces();
				boolean isNorm = false;
				for (Class<?> c : tabI) {
					if (c.getSimpleName().equals("Service")) {
						isNorm = true;
					}
				}
				if (!isNorm) {
					throw new Exception("omg tu n'implemente pas bri.Service");
				}
				
				if (Modifier.isAbstract(classe.getModifiers())) {
					throw new Exception("La classe est abstract!");
				}
				
				if (!Modifier.isPublic((classe.getModifiers()))){
					throw new Exception("La classe n'est pas publique");
				}
				
				try {
					if (classe.getConstructors().length == 0) {
						throw new Exception("Le service ne possède pas de constructeur");
					}
				}
				catch (Exception e) {
					e.printStackTrace();
					
				}
				
				try {
					classe.getConstructor(Socket.class);
				}
				catch (Exception e) {
					throw new Exception("Le constructeur ne prend pas de socket en argument"); 
				}
					
				
				if (!Modifier.isPublic(classe.getConstructor(Socket.class).getModifiers())) {
					throw new Exception("Le constructeur du service n'est pas public"); 
				}
				
				if (classe.getConstructor(Socket.class).getExceptionTypes().length != 0) {
					throw new Exception("Le constructeur du service renvoit des exceptions");
				}
				
				
				Field[] fields = classe.getDeclaredFields();
				Field field = null;
				boolean socketExiste = false;
				for (Field tmpField : fields) {
					if (tmpField.getType().getSimpleName().equals(Socket.class.getSimpleName())) {
						socketExiste = true;
						field = tmpField;
					}
				}
				if (!socketExiste) {
					throw new Exception("Le service ne dispose pas d'une Socket");
				}
				
				if (!Modifier.isPrivate(field.getModifiers())) {
					throw new Exception("La socket du service ne possède pas la visibilité private");
				}
				if (!Modifier.isFinal(field.getModifiers())) {
					throw new Exception("La socket du service n'est pas déclarée final");
				}
				Method[] methods = classe.getMethods();
				boolean possedeToStringue = false;
				Method method = null;
				String toStr = "toStringue";
				for (Method tmpMethod : methods) {
					if (tmpMethod.getName().equals(toStr))  {
							possedeToStringue = true;
							method = tmpMethod;
					}
				}
				if (!possedeToStringue) {
					throw new Exception("La méthode toStringue du service n'existe pas");
				}
				
				if (!Modifier.isPublic(method.getModifiers())) {
					throw new Exception("La méthode toStringue du service n'est pas publique");
				}
				
				if (!Modifier.isStatic(method.getModifiers())) {
					throw new Exception("La méthode toStringue du service n'est pas statique");
				}
				
				if (!method.getReturnType().getName().equals(String.class.getName())) {
					throw new Exception("La méthode toStringue du service ne retourne pas un objet de type string");
				}
				
				if (method.getExceptionTypes().length != 0) {
					throw new Exception("La méthode toStringue du service ne doit pas retourner d'exception");
				}
				
				
				servicesClasses.add((Class<? extends bri.Service>) classe);
				
				
				
					

			} // si non conforme --> exception avec message clair
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
		
		
	}
	
// renvoie la classe de service (numService -1)	
	public static Class<? extends Service> getServiceDemarrer(int numService) {
		return(servicesDemarrer.get(numService));
	}
	
// liste les activités présentes
	public static String toStringue() {
		String result = "";
		try {
			int compteur = 0;
			for (Class<? extends bri.Service> serviceClass : servicesDemarrer ) {
				result = result + " " + compteur++ +" "  + serviceClass.getName() + " |##";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static String ArreterService(String nomService) {
		String reussite = "Le service demandée n'existe pas.";
		if(isServiceExist(nomService)) {
			reussite = "Le service demande est deja arrete.";
			for (int i = 0; i < servicesDemarrer.size(); i++) {
				if(servicesDemarrer.get(i).getName().equals(nomService)) {
					servicesDemarrer.remove(i);
					reussite = "Le service a bien ete arrete.";
				}
			}
		}
		return reussite;
	}
	
	public static String DemarrerService(String nomService) {
		String reussite = "Le service demande n'existe pas.";
		if(isServiceExist(nomService)) {
			if(!isDejaDemarre(nomService)) {
				try {
					servicesDemarrer.add((Class<? extends bri.Service>) Class.forName(nomService));
					reussite = "Le service a bien ete demarre";
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
					reussite = "Le service demande n'existe pas.";
				}
			}else {
				reussite = "La classe est deja demarre.";
			}
		}
		return reussite;
	}
	
	private static boolean isServiceExist(String nomService) { // teste si le service existe bien
		boolean exist = false;
		for (int i = 0; i < servicesClasses.size(); i++) {
			if(servicesClasses.get(i).getName().equals(nomService)) {
				exist = true;
			}
		}
		return exist;
	}
	
	private static boolean isDejaDemarre(String nomService) { // teste si le service n'est pas déjà démarré
		boolean exist = false;
		for (int i = 0; i < servicesDemarrer.size(); i++) {
			if(servicesDemarrer.get(i).getName().equals(nomService)) {
				exist = true;
			}
		}
		return exist;
	}
	
	public static ArrayList<Class<? extends bri.Service>> getServiceDemarrer() {
		return new ArrayList<Class<? extends bri.Service>>(servicesDemarrer);
		
	}
	
	public static ArrayList<Class<? extends bri.Service>> getServiceArreter() {
		ArrayList<Class<? extends bri.Service>> a = new ArrayList<Class<? extends bri.Service>>();
		for (int i = 0; i < servicesClasses.size(); i++) {
			if(!isDejaDemarre(servicesClasses.get(i).getName())) {
				a.add(servicesClasses.get(i));
			}
		}
		 return a;
		
	}
	
	
	public static void actualiserClasse() {
		
	}

}
