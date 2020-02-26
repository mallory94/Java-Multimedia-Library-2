package bri;

import java.io.IOException;
import java.net.ServerSocket;

import services.ServiceAuthentification;

public class ServeurBRiLaunch implements Runnable{
	
private ServerSocket listen_socket;
	private int portAmateur = 1600;
	private int portProgrammeur = 1700;
	

	// Cree un serveur TCP - objet de la classe ServerSocket
	public ServeurBRiLaunch(int port) {
		try {
			listen_socket = new ServerSocket(port);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	// Le serveur ecoute et accepte les connections.
	// pour chaque connection, il cree un ServiceInversion, 
	// qui va la traiter.
	@Override
	public void run() {
		try {
			if (listen_socket.getLocalPort() == portAmateur) {
				while(true) {
					new ServiceBRi(listen_socket.accept()).start();
				}
			}
			else if (listen_socket.getLocalPort() == portProgrammeur) {
				while(true) {
					new Thread( new ServiceAuthentification(listen_socket.accept())).start();
				}
			}
		}
		catch (IOException e) { 
			try {this.listen_socket.close();} catch (IOException e1) {}
			System.err.println("Pb sur le port d'écoute :"+e);
		}
	}

	 // restituer les ressources --> finalize
	protected void finalize() throws Throwable {
		try {this.listen_socket.close();} catch (IOException e1) {}
	}

	// lancement du serveur
	public void lancer() {
		(new Thread(this)).start();		
	}
	
	
	

}
