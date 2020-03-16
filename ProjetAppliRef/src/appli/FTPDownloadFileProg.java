package appli;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import bri.Service;
import utilisateur.Amateur;
import utilisateur.Programmeur;

public class FTPDownloadFileProg implements Runnable{
	private Programmeur programmeur;
	private String nomFichier;
	
	public FTPDownloadFileProg(Programmeur programmeur, String nomFichier){
		this.programmeur = programmeur;
		this.nomFichier = nomFichier;
	}
	
	@Override
	public void run() {
		telecharge();
	}
	
	public void telecharge(){
		String destination = programmeur.getUrl();
		String host = null;
		Integer port = null;
		String directoryPath = null;
		String chemin = null;
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
        new File("./classesProgrammeurs/" + programmeur.getPseudo()).mkdir();
        try ( OutputStream os = new FileOutputStream("classesProgrammeurs/" + programmeur.getPseudo() + "/" + nomFichier)) {
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
	
	
	public void start() {
		new Thread(this).start();
	}
	
}
