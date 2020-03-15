package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;


import bri.Service;
import utilisateur.Amateur;
import utilisateur.ListeUtilisateur;
import utilisateur.Message;

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
			in = new BufferedReader(new InputStreamReader(this.client.getInputStream()));
			out = new PrintWriter (this.client.getOutputStream ( ), true);
			out.println("Entrez votre identifiant d'amateur");
			String id = in.readLine();
			if (id != null) {
				out.println("Entrez votre mot de passe d'amateur");
				String mdp = in.readLine();
				Amateur ama = connectionValide(id,mdp);
				if (ama != null) {
					this.ModeMessagerie(in, out, ama);
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

	private Amateur connectionValide(String id, String mdp) {
		try {
			Amateur ama;
			ama = ListeUtilisateur.getAmateur(id, mdp);
			return ama;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private void ModeMessagerie(BufferedReader in, PrintWriter out, Amateur amateurActuel) {
		out.println("Bienvenu dans la messagerie interne ##(0) - pour lire les messages reçus##(1) - pour écrire à un autre Amateur");
		try {
			String sin = in.readLine();
			if(sin == "0") {
				out.println("Messages reçu : ");
			}else if (sin == "1") {
				out.println("à qui voulez-vous écrire ?");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static String toStringue() {
		return "Messagerie interne";
	}
	
}
