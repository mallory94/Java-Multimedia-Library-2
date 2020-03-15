package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import bri.Service;
import utilisateur.ListeUtilisateur;

public class ServiceMessagerieInterne implements Runnable, Service{
	private final Socket client;
	
	public ServiceMessagerieInterne(Socket socket) {
		this.client = socket;
	}

	@Override
	public void run() {
		BufferedReader in = null;
		PrintWriter out = null;
		try {
			in = new BufferedReader(new InputStreamReader(this.getSocket().getInputStream()));
			out = new PrintWriter (this.getSocket().getOutputStream ( ), true);
			out.println("Entrez votre identifiant d'amateur");
			String id = in.readLine();
			if (id != null) {
				out.println("Entrez votre mot de passe d'amateur");
				String mdp = in.readLine();
				if (connectionValide(id,mdp)) {
					this.ModeMessagerie(in, out);
				}
				else {
					out.println("les informations données sont invalides, veuillez les vérifier");
				}
				
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

	private boolean connectionValide(String id, String mdp) {
		try {
			ListeUtilisateur.getAmateur(id, mdp);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	private void ModeMessagerie(BufferedReader in, PrintWriter out) {
		out.println("vous êtes bien dans la messagerie interne mon copain");
	}
	
	protected Socket getSocket() {
		return this.client;
	}

	public static String toStringue() {
		return("Service Messagerie Interne");
	}
}
