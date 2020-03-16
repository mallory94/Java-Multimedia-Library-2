package appli;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.net.ftp.FTPClient;

import bri.Service;
import utilisateur.Amateur;

public class FTPDownloadFileClient implements Service{
	String destination;
	
	
	public FTPDownloadFileClient(String destination){
		this.destination = destination;
	}
	
	@Override
	public void run() {
		telecharge();
	}
	
	public void telecharge(){
	
		Amateur amateur = new Amateur("mallory","mallory", "anonymous", "brette");
		String destination = "ftp://localhost:2121/ohoh/ok/fichierATelecharger.xml";
		String host = null;
		Integer port = null;
		String directoryPath = null;
		String chemin =  null;
		String nomFichier = null;
		URI uri;
		try {
			uri = new URI(destination);
			host = uri.getHost();
			System.out.println(host);
			port = uri.getPort();
			System.out.println(port);
			directoryPath = uri.getPath().substring(1);
			System.out.println(directoryPath);
			nomFichier=  FilenameUtils.getName(uri.getPath());
			chemin =  FilenameUtils.getFullPathNoEndSeparator(uri.getPath());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		String filename = "fichierATelecharger.xml";
        String pseudoAmateur;
        //new File("./" + pseudoAmateur).mkdir( );
        FTPClient client = new FTPClient();
        try (OutputStream os = new FileOutputStream(nomFichier)) {
            client.connect(host,port);
            client.login(amateur.getPseudoFtp(), amateur.getMdpFtp());
            client.changeWorkingDirectory(chemin);
            boolean status = client.retrieveFile(filename, os);
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
	
	
	public void start() {
		new Thread(this).start();
	}
	
}
