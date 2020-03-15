package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;

import bri.ServiceBRIprog;
import utilisateur.ListeUtilisateur;
import utilisateur.Programmeur;

public class ServiceAuthentification extends ServiceStandard implements Runnable {
	
	public ServiceAuthentification(Socket accept) {
		super(accept);
	}
	
	@Override
	public void run() {
		BufferedReader in = null;
		PrintWriter out = null;
		try {
			in = new BufferedReader(new InputStreamReader(this.getSocket().getInputStream()));
			out = new PrintWriter (this.getSocket().getOutputStream ( ), true);
			out.println("Entrez votre identifiant");
			String id = in.readLine();
			if (id != null) {
				out.println("Entrez votre mot de passe");
				String mdp = in.readLine();
				Programmeur programmeur = connectionValide(id,mdp);
				if (programmeur != null) {
					new ServiceBRIprog(this.getSocket(), programmeur).start();
				}
				else {
					out.println("les informations données sont invalides, veuillez les vérifier");
				}
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

	private Programmeur connectionValide(String id, String mdp) {
		try {
			return ListeUtilisateur.getProgrammeur(id, mdp);
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
}
