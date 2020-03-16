package appli;

import org.apache.commons.net.ftp.FTPClient;

import utilisateur.Amateur;
import utilisateur.Programmeur;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;

import org.apache.commons.io.FilenameUtils;

public class TestFTP {
	

	public static void main(String[] args) {
		Programmeur programmeur = new Programmeur("mallory", "mallory", "ftp://localhost:2121/ohoh/", "anonymous", "brette");
		try{
			String nomFichierClasse = "ServiceInversionBis.class";
		    URL urlDeMonFichierLocal =  new URL("./classesProgrammeurs/" + programmeur.getPseudo() + "/" + nomFichierClasse);
		    ClassLoader loader = new URLClassLoader(new URL[] {urlDeMonFichierLocal}); 
		    Class theLoadedClass = Class.forName( nomFichierClasse ,true, loader);
		 
		    theLoadedClass.newInstance();
		    System.out.println("classe chargée");
		 
		}catch(MalformedURLException ex){System.out.println("MalformedURLException");
		}catch(ClassNotFoundException ex){System.out.println("ClassNotFoundException");
		}catch(InstantiationException ex){System.out.println("InstantiationException");
		}catch(IllegalAccessException ex){System.out.println("IllegalAccessException");}
		
        
    }

}


