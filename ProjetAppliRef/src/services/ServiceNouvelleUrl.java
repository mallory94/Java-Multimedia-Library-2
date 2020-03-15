package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import utilisateur.Programmeur;

public class ServiceNouvelleUrl implements Runnable {
	
	private static int cpt = 1;
	
	private final Socket client;
	private Programmeur prog;
	
	public ServiceNouvelleUrl(Socket socket, Programmeur prog) {
		this.client = socket;
		this.prog = prog;
	}

	public void run() {
		System.out.println("*********Connexion démarrée");
		try {
			BufferedReader in = new BufferedReader (new InputStreamReader(client.getInputStream()));
			PrintWriter out = new PrintWriter (client.getOutputStream(), true);
			out.println("quelle est votre nouvelle URL ? : ");
			String line = in.readLine();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//Fin du service d'inversion
		System.out.println("*********Connexion terminée");
		try {client.close();} catch (IOException e2) {}
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
