package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import appli.FTPDownloadFileProg;
import utilisateur.Amateur;
import utilisateur.Programmeur;

public class ServiceNouveauService extends ServiceStandard implements Runnable {
	private Programmeur programmeur;
	
	public ServiceNouveauService(Socket socket, Programmeur programmeur) {
		super(socket);
		this.programmeur = programmeur;
	}

	@Override
	public void run() {
		BufferedReader in = null;
		PrintWriter out = null;
		try {
			in = new BufferedReader(new InputStreamReader(getSocket().getInputStream()));
			out = new PrintWriter (getSocket().getOutputStream ( ), true);
			out.println("##Votre url ftp est " + programmeur.getUrl() + "##Entrez le nom de la classe à upload (exemple : VotreClasse.class):##");
			String nomFichier = in.readLine();
			if (nomFichier != null) {
				new FTPDownloadFileProg(programmeur, nomFichier).start();
				out.println("##fichier telecharge avec succes");
			}
			
			
			
		}
		catch (IOException e) {
			e.printStackTrace();
			try {
				in.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			out.close();
		}
		finally {
		}
	}

}
