package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import bri.ServiceBRi;
import utilisateur.ListeUtilisateur;

public class ServiceAuthentificationAmateur extends ServiceStandard implements Runnable{

	public ServiceAuthentificationAmateur(Socket socket) {
		super(socket);
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
			//System.out.println("l'id : " + id);
			//System.out.println("le truc fantome : ");
			if (id != null) {
				out.println("Entrez votre mot de passe d'amateur");
				String mdp = in.readLine();
				if (connectionValide(id,mdp)) {
					//out.println("félicitation vous êtes réel"); // ligne de test doit être effacer une fois son utilité accomplie
					new ServiceBRi(this.getSocket()).start();
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

}
