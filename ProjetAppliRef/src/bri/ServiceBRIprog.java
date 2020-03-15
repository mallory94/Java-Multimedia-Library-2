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
		BufferedReader in = null;
		PrintWriter out = null;
		try {
			 in = new BufferedReader (new InputStreamReader(client.getInputStream ( )));
			 out = new PrintWriter (client.getOutputStream ( ), true);
			
			String line = "";
			System.out.println("ok");
			out.println("Que souhaitez vous faire ?##1 : Fournir un nouveau service##"
					+ "2 : Mettre-�-jour un service##"
					+ "3 : D�clarer un changement d'adresse de votre serveur ftp##"
					+ "4 : Quitter l'application##"
					+ "Saisissez le num�ro de l'option voulue");
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
				e1.printStackTrace();
				out.println("Erreur de saisie.## Veuillez saisir un chiffre parmi ceux propos�s ");
			}
			
			
			System.out.println(line);

		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			out.close();
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
