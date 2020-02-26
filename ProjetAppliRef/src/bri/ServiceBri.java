package bri;


import java.io.*;
import java.net.*;

import services.ServiceRegistry;
import services.*;

class ServiceBRi implements Runnable {
	
	private Socket client;
	
	ServiceBRi(Socket socket) {
		client = socket;
	}

	public void run() {
		try {BufferedReader in = new BufferedReader (new InputStreamReader(client.getInputStream ( )));
			PrintWriter out = new PrintWriter (client.getOutputStream ( ), true);
			out.println(ServiceRegistry.toStringue()+"##Tapez le numéro de service désiré : (pour l'instant vous ne pouvez qu'inverser une ligne de texte)");
			int choix = Integer.parseInt(in.readLine());
			// instancier le service numéro "choix" en lui passant la socket "client"
			// invoquer run() pour cette instance ou la lancer dans un thread à part 
			String line;
			if (true) {
				line = "vous avez choisi le service d'inversion de texte. Veuillez taper votre texte à inverser :";
				out.println(line);
				new Thread(new ServiceInversion(this.client)).start();
			}
			//rajouter un if pour ajouter un nouveau service, je précise que le service bri ne doit jamais close la socket.
				
			}
		catch (IOException e) {
			//Fin du service
		}

		//try {client.close();} catch (IOException e2) {}
	}
	
	protected void finalize() throws Throwable {
		 client.close(); 
	}

	// lancement du service
	public void start() {
		(new Thread(this)).start();		
	}

}
