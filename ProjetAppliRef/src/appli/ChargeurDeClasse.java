package appli;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

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
		    URL urlDeMonFichierLocal =  new URL("./" + programmeur.getPseudo() + "/" + nomFichierClasse);
		    ClassLoader loader = new URLClassLoader(new URL[] {urlDeMonFichierLocal}); 
		    Class theLoadedClass = Class.forName( nomFichierClasse ,true, loader);
		 
		    theLoadedClass.newInstance();
		    System.out.println("classe chargée");
		 
		}catch(MalformedURLException ex){System.out.println("MalformedURLException");
		}catch(ClassNotFoundException ex){System.out.println("ClassNotFoundException");
		}catch(InstantiationException ex){System.out.println("InstantiationException");
		}catch(IllegalAccessException ex){System.out.println("IllegalAccessException");}
	}
	
	public void start() {
		new Thread(this).start();
	}
	
}
