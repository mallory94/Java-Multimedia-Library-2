package bri;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;

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
			
			while(true) {
				out.println("wesh mec tu peux écrire un truc stp. ##ici : ");
				
				line = in.readLine();
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
