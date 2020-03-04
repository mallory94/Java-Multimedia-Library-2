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
	
	static {
		servicesClasses = new ArrayList<Class<? extends bri.Service>>();
	}
	private static List<Class<? extends bri.Service>> servicesClasses;

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
						
					}
				}
				if (!isNorm) {
					throw new Exception("omg tu n'implemente pas bri.Service");
				}


				
				if (!Modifier.isAbstract(classe.getModifiers())) {
					throw new Exception("La classe est abstract!");
				}
				
				if (!Modifier.isPublic((classe.getModifiers()))){
					throw new Exception("La classe n'est pas publique");
				}
				try {
					classe.getConstructor();
				}
				catch (NoSuchMethodException e) {
					throw new Exception("Le service ne possède pas de constructeur");
				}
				catch (Exception e) {
					e.printStackTrace();
				}
				
				if (!Modifier.isPublic(classe.getConstructor().getModifiers())) {
					throw new Exception("Le constructeur du service n'est pas public"); 
				}
				
				if (classe.getConstructor().getExceptionTypes().length != 0) {
					throw new Exception("Le constructeur du service renvoit des exceptions");
				}
				
				
				Field[] fields = classe.getDeclaredFields();
				Field field = null;
				boolean socketExiste = false;
				for (Field tmpField : fields) {
					if (tmpField.getClass().getSimpleName().equals(Socket.class.getSimpleName())) {
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
				for (Method tmpMethod : methods) {
					if (method.getName().equals("toStringue"))  {
							possedeToStringue = true;
							method = tmpMethod;
					}
				}
				if (!possedeToStringue) {
					throw new Exception("La méthode ToStringue du service n'existe pas");
				}
				
				if (!Modifier.isPublic(method.getModifiers())) {
					throw new Exception("La méthode ToStringue du service n'est pas publique");
				}
				
				if (!Modifier.isStatic(method.getModifiers())) {
					throw new Exception("La méthode ToStringue du service n'est pas statique");
				}
				
				if (!method.getReturnType().getName().equals(String.class.getName())) {
					throw new Exception("La méthode ToStringue du service ne retourne pas un objet de type string");
				}
				
				if (method.getExceptionTypes().length != 0) {
					throw new Exception("La méthode ToStringue du service ne doit pas retourner d'exception");
				}
				
				servicesClasses.add((Class<? extends bri.Service>) classe);
				
					

			} // si non conforme --> exception avec message clair
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
		
		
	}
	
// renvoie la classe de service (numService -1)	
	public static Class<? extends Service> getServiceClass(int numService) {
		return(servicesClasses.get(numService-1));
	}
	
// liste les activités présentes
	public static String toStringue() {
		String result = "Activités présentes :##";
		// todo
		return result;
	}

}
