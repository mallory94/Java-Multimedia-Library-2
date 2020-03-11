package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;

import bri.ServiceBRIprog;
import utilisateur.Programmeur;

public class ServiceAuthentification extends ServiceStandard implements Runnable {
	
	private static HashMap<String, Programmeur> loginMdp = new HashMap<String, Programmeur>();
	
	
	
	public ServiceAuthentification(Socket accept) {
		super(accept);
		this.initialisation();
	}
	
	private void initialisation() {
		loginMdp.put("test",new Programmeur("test", "url"));
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
				if (connectionValide(id,mdp)) {
					out.println("gg mec t'es co");
					new ServiceBRIprog(this.getSocket()).start();
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
		synchronized(loginMdp) {
			try {
				
					return true;
				else {
					return false;
				}
			}
			catch(Exception e) {
				return false;
			}
			
		}
	}
	
	
	
}
