package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import bri.Service;
import bri.ServiceRegistry;
import utilisateur.Programmeur;

public class ServiceArreterDemarrer implements Service {
private static int cpt = 1;
	
	private final Socket client;
	//private Programmeur prog;
	
	public ServiceArreterDemarrer(Socket socket) {
		this.client = socket;
		//this.prog = prog;
	}

	public void run() {
		System.out.println("*********Connexion démarrée");
		BufferedReader in = null;
		PrintWriter out = null;
		try {
			in = new BufferedReader (new InputStreamReader(client.getInputStream()));
			out = new PrintWriter (client.getOutputStream(), true);
			String line = "Quelle action voulez-vous effectuer sur vos services ? :##(0) - Démarrer un service##(1) - Arrêter un service##";
			out.println(line);
			line = in.readLine();
			if(line.equals("0")) {
				ArrayList<Class<? extends bri.Service>> listA =  ServiceRegistry.getServiceArreter();
				line = "Voici tout les services arrêtés choisissez en un ou marquez 'exit' pour quitter :";
				for (int i = 0; i < listA.size(); i++) {
					line += "## - " + listA.get(i).getName();
				}
				out.println(line);
				line = in.readLine();
				out.println(ServiceRegistry.DemarrerService(line));
				
			}
			else if (line.equals("1")) {
				ArrayList<Class<? extends bri.Service>> listD =  ServiceRegistry.getServiceDemarrer();
				line = "Voici tout les services démarrés choisissez en un ou marquez 'exit' pour quitter :";
				for (int i = 0; i < listD.size(); i++) {
					line += "## - " + listD.get(i).getName();
				}
				out.println(line);
				line = in.readLine();
				out.println(ServiceRegistry.ArreterService(line));
			}
			else {
				out.println("action invalide.");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//Fin du service d'inversion
		System.out.println("*********Connexion terminée");
		try {
			client.close();
			out.close();
			in.close();
		} catch (IOException e2) {e2.printStackTrace();}
	}
	
	protected void finalize() throws Throwable {
		 client.close(); 
	}

	public static String toStringue() {
		return "Changement d'url";
	}
	
	public void start() {
		new Thread(this).start();
	}

}
