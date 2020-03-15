package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

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
			out.println("");
			String id = in.readLine();
			if (id != null) {
				out.println("Entrez le chemin de la classe à upload :##");
				String line = in.readLine();
				System.out.println("Connexion  <-- "+line);
				
				out.println();
				System.out.println("Connexion --> ");
				
				
			}
			in.close();
			out.close();
			
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				in.close();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}

}
