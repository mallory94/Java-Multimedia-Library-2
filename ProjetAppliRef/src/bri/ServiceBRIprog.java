package bri;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;

import services.ServiceUploadService;

public class ServiceBRIprog implements Runnable {

private Socket client;
	
	public ServiceBRIprog(Socket socket) {
		client = socket;
	}
	

	@Override
	public void run() {
		try {
			BufferedReader in = new BufferedReader (new InputStreamReader(client.getInputStream ( )));
			PrintWriter out = new PrintWriter (client.getOutputStream ( ), true);
			
			String line = "";
			while(!in.ready()) {
				out.println("Que souhaitez vous faire ?##1 : Fournir un nouveau service##"
						+ "2 : Mettre-à-jour un service##"
						+ "3 : Déclarer un changement d'adresse de votre serveur ftp##"
						+ "4 : Quitter l'application##"
						+ "Saisissez le numéro de l'option voulue");
				Integer choix = null;
				try {
					choix = Integer.parseInt(in.readLine());
					switch (choix) {
					case 1:
						new ServiceUploadService(client).start();
						break;
					case 2:
						break;
					case 3:
						break;
					case 4:
						break;
					}
				}
				catch (NumberFormatException e1) {
					out.println("Erreur de saisie.## Veuillez saisir un chiffre parmi ceux proposés ");
				}
				
				
				System.out.println(line);
			}

		}
		catch (IOException e) {
			//Fin du service
		}


	}
	
	protected void finalize() throws Throwable {
		 client.close(); 
	}

	// lancement du service
	public void start() {
		(new Thread(this)).start();		
	}

}
