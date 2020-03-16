package appli;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import bri.ServiceRegistry;
import utilisateur.Programmeur;

public class ChargeurDeClasse implements Runnable{
	
	private String nomFichierClasse;
	private Programmeur programmeur;
	public ChargeurDeClasse( Programmeur programmeur, String nomFichierClasse) {
		this.nomFichierClasse = nomFichierClasse;
		this.programmeur = programmeur;
	}
	
	@Override
	public void run() {
		 
		try{

		    URL urlDeMonFichierLocal =  new URL(new URL("file:"), "classesProgrammeurs/" + programmeur.getPseudo() + "/" + nomFichierClasse);
		    
		    String fileNameURL = "file:" + "classesProgrammeurs/" + programmeur.getPseudo() + "/" + nomFichierClasse;  // ou file:///c:/etc
			
			URLClassLoader urlcl = URLClassLoader.newInstance(new URL[] {new URL("file:///C:/Users/mallory/Documents/Code/ProjetAppliRef/ProjetAppliRef/classesProgrammeurs/test/")});
			String nomClasseSansExtension = new StringBuffer(new String (new StringBuffer(nomFichierClasse).reverse()).substring(6)).reverse().toString();
			try {
				ServiceRegistry.addService(urlcl.loadClass(programmeur.getPseudo() + "." + nomClasseSansExtension));
			}
			catch (Exception e) {
				e.printStackTrace();
			}
//			
//		    System.out.println(urlDeMonFichierLocal.getPath());
//		    System.out.println(programmeur.getPseudo());
//		    ClassLoader loader = new URLClassLoader(new URL[] {urlDeMonFichierLocal}); 
		    
//		    System.out.println(nomClasseSansExtension);
//		    Class theLoadedClass = Class.forName(programmeur.getPseudo() + "." + nomClasseSansExtension ,true, loader);
//		    
//		    ServiceRegistry.addService(theLoadedClass);
//		    
//		    System.out.println("classe chargée");
		 
		}catch(MalformedURLException ex){ex.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void start() {
		new Thread(this).start();
	}
	
}
