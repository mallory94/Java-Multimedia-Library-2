package client;

import java.io.*;
import java.net.Socket;

public class Amateur {
	private final static int PORT = 2600;
	private final static String HOST = "localhost"; 

	public static void main(String[] args) {
		Socket socket = null;		
		try {
			// Cree une socket pour communiquer avec le service se trouvant sur la
			// machine host au port PORT
			socket = new Socket(HOST, PORT);
			// Cree les streams pour lire et ecrire du texte dans cette socket
			BufferedReader sin = new BufferedReader (new InputStreamReader(socket.getInputStream ( )));
			PrintWriter sout = new PrintWriter (socket.getOutputStream ( ), true);
			// Cree le stream pour lire du texte a partir du clavier 
			// (on pourrait aussi utiliser Scanner)
			BufferedReader clavier = new BufferedReader(new InputStreamReader(System.in));			
			// Informe l'utilisateur de la connection
			System.out.println("Connecté au serveur " + socket.getInetAddress() + ":"+ socket.getPort());

			String line;
			System.out.println(sin.readLine()); //on écrit ce que le serveur veut
			line = clavier.readLine(); //on lit la réponse au clavier
			// envoie au serveur
			sout.println(line); 
			// lit la réponse provenant du serveur
			line = sin.readLine();
			System.out.println("test");
			// Ecrit la ligne envoyee par le serveur
			System.out.println(line);
			
			line = clavier.readLine();
			sout.println(line);
			
			System.out.println(sin.readLine());
			
			socket.close();
		}
		catch (IOException e) { System.err.println(e); }
		// Refermer dans tous les cas la socket
		try { if (socket != null) socket.close(); } 
		catch (IOException e2) { ; }		
	}
}
