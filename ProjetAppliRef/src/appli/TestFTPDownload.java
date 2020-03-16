package appli;

import org.apache.commons.net.ftp.FTPClient;

import utilisateur.Amateur;
import utilisateur.Programmeur;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import org.apache.commons.io.FilenameUtils;

public class TestFTPDownload {
	

	public static void main(String[] args) {
		Programmeur programmeur = new Programmeur("mallory", "mallory", "ftp://localhost:2121/ohoh/", "anonymous", "brette");
		String destination = programmeur.getUrl();
		String host = null;
		Integer port = null;
		String directoryPath = null;
		String chemin = null;
		String nomFichier = "test.class";
		URI uri;
		try {
			uri = new URI(destination);
			host = uri.getHost();
			System.out.println("host = " + host);
			port = uri.getPort();
			System.out.println("port = " + port);
			directoryPath = uri.getPath().substring(1);
			System.out.println(directoryPath);
			System.out.println("nomFichier = " + nomFichier);
			chemin =  FilenameUtils.getFullPathNoEndSeparator(uri.getPath());
			System.out.println("chemin = " + chemin);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
        FTPClient client = new FTPClient();
        new File("./" + programmeur.getPseudo()).mkdir();
        try ( OutputStream os = new FileOutputStream("mallory/" + nomFichier)) {
            client.connect(host,port);
            client.login(programmeur.getPseudoFtp(), programmeur.getMdpFtp());
            client.changeWorkingDirectory(programmeur.getPseudo());
            boolean status = client.retrieveFile(nomFichier, os);
            System.out.println("status = " + status);
            System.out.println("reply  = " + client.getReplyString());
        }
        catch (IOException e) {
            e.printStackTrace();
        } 
        finally {
            try {
                client.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
		
        
    }

}


