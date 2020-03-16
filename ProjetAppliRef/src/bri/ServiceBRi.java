package bri;


import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.*;


public class ServiceBRi implements Runnable {
	
	private Socket client;
	
	public ServiceBRi(Socket socket) {
		client = socket;
	}
	@Override
	public void run() {
		try {
			BufferedReader in = new BufferedReader (new InputStreamReader(client.getInputStream ( )));
			PrintWriter out = new PrintWriter(client.getOutputStream(),true);
			out.println("Liste des services : ##" + ServiceRegistry.toStringue() + "##Tapez le numéro de service désiré : ");
			Integer choix = null;
			while (true) {
				if (in.ready()) {
					choix = Integer.parseInt(in.readLine());
					break;
				}
			}
			// instancier le service numéro "choix" en lui passant la socket "client"
			// invoquer run() pour cette instance ou la lancer dans un thread à part
			try {
				new Thread(ServiceRegistry.getServiceClass(choix).getConstructor(Class.forName(Socket.class.getName())).newInstance(client)).start();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			//rajouter un if pour ajouter un nouveau service, je précise que le service bri ne doit jamais close la socket.
				
			}
		catch (IOException e) {
			e.printStackTrace();
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
