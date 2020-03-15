package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import bri.Service;

public class ServiceUploadService implements Service{
	
	private static int cpt = 1;
	

	private final Socket client;
	
	public ServiceUploadService(Socket socket) {
		this.client = socket;
	}

	public void run() {
		System.out.println("*********Connexion démarrée");
		try {
			BufferedReader in = new BufferedReader (new InputStreamReader(client.getInputStream ( )));
			PrintWriter out = new PrintWriter (client.getOutputStream ( ), true);
			out.println("Entrez le chemin de la classe à upload :##");
			String line = in.readLine();
			System.out.println("Connexion  <-- "+line);
			
			out.println();
			System.out.println("Connexion --> ");
		}
		catch (Exception e) {
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
		return "telechargement de service";
	}
	
	public void start() {
		new Thread(this).start();
	}
}
