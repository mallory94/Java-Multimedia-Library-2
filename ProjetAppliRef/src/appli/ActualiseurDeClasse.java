package appli;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import utilisateur.Programmeur;

public class ActualiseurDeClasse implements Runnable{
	
	private String nomFichierClasse;
	private Programmeur programmeur;
	public ActualiseurDeClasse( Programmeur programmeur, String nomFichierClasse) {
		this.nomFichierClasse = nomFichierClasse;
		this.programmeur = programmeur;
	}
	
	@Override
	public void run() {
		 
		try{

		    URL urlDeMonFichierLocal =  new URL(new URL("file:"), "./classesProgrammeurs/" + programmeur.getPseudo() + "/" + nomFichierClasse);
		    ClassLoader loader = new URLClassLoader(new URL[] {urlDeMonFichierLocal}); 
		    String nomClasseSansExtension = new StringBuffer(new String (new StringBuffer(nomFichierClasse).reverse()).substring(6)).reverse().toString();
		    System.out.println(nomClasseSansExtension);
		    
		    ClassLoader cl = this.getClass().getClassLoader(); 
		    while (cl.getParent() != null ) {
		    	cl = cl.getParent();
		    }
		    Class theClass = cl.loadClass(nomClasseSansExtension);

		    
		    System.out.println("classe chargée");
		 
		}catch(MalformedURLException ex){ex.printStackTrace();
		}catch(ClassNotFoundException ex){ex.printStackTrace();
		}
	}
	
	public void start() {
		new Thread(this).start();
	}
	
}
