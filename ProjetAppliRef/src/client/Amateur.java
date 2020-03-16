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
			System.out.println("Connect� au serveur " + socket.getInetAddress() + ":"+ socket.getPort());
			
			String line;
			
			while(true) {
				if(sin.ready()) {
					System.out.println("rentre");
					System.out.println(sin.readLine().replaceAll("##", "\n")); //on �crit ce que le serveur veut
					line = clavier.readLine(); //on lit la r�ponse au clavier
					if (line.equals("exit")) {
						break;
					}
					else {
						sout.println(line);
					}
				 }
			}
			
			
			socket.close();
		}
		catch (IOException e) { e.printStackTrace(); }
		catch (Exception e3) {
			e3.printStackTrace();
		}
		// Refermer dans tous les cas la socket
		try { if (socket != null) socket.close(); } 
		catch (IOException e2) { e2.printStackTrace(); }		
	}
	
}
