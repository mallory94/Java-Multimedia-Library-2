package services;

import java.io.*;
import java.net.*;

import bri.Service;


public class ServiceInversion implements Service{
	
	private static int cpt = 1;
	
	private final int numero;
	private final Socket client;
	
	public ServiceInversion(Socket socket) {
		this.numero = cpt ++;
		this.client = socket;
	}

	public void run() {
		System.out.println("*********Connexion "+this.numero+" démarrée");
		try {
			BufferedReader in = new BufferedReader (new InputStreamReader(client.getInputStream ( )));
			PrintWriter out = new PrintWriter (client.getOutputStream ( ), true);
			out.println("Bienvenue sur le service inversion. Ecrivez la chaine à inverser :##");
			String line = in.readLine();
			System.out.println("Connexion "+this.numero+" <-- "+line);
			String invLine = new String (new StringBuffer(line).reverse());
			out.println(invLine);
			System.out.println("Connexion "+this.numero+" --> "+invLine);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		//Fin du service d'inversion
		System.out.println("*********Connexion "+this.numero+" terminée");
		try {client.close();} catch (IOException e2) {e2.printStackTrace();}
	}
	
	protected void finalize() throws Throwable {
		 client.close(); 
	}

	public static String toStringue() {
		return "Inversion de texte";
	}
}
