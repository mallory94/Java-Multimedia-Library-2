package appli;

import bri.ServiceRegistry;
import utilisateur.Programmeur;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class TestFTP {
	

	public static void main(String[] args) {
		String nomFichierClasse = "ServiceBonjour.class";
		Programmeur programmeur = new Programmeur("test", "test", "ftp://localhost:2121/ohoh/", "anonymous", "brette");
		try{
			
		    URL[] urlDeMonFichierLocal =  new URL[] {new URL("file:/classesProgrammeurs/" + programmeur.getPseudo() + "/" + nomFichierClasse)};
		    
		    String fileNameURL = "file:" + "/classesProgrammeurs/" + programmeur.getPseudo() + "/" + nomFichierClasse;  // ou file:///c:/etc
			
			URLClassLoader urlcl = URLClassLoader.newInstance(new URL[] {new URL("file:///C:/Users/mallory/Documents/Code/ProjetAppliRef/ProjetAppliRef/test/ServiceBonjour.class"), 
					new URL("file://ServiceBonjour.class")});
			//String nomClasseSansExtension = new StringBuffer(new String (new StringBuffer(nomFichierClasse).reverse()).substring(6)).reverse().toString();
			try {
				//System.out.println(programmeur.getPseudo() + "." + nomClasseSansExtension);
				ServiceRegistry.addService(urlcl.loadClass("test.ServiceBonjour"));
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
}


