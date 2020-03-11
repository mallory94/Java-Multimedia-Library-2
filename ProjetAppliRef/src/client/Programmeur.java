package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Programmeur {
	private final static int PORT = 2700;
	private final static String HOST = "localhost"; 

	public static void main(String[] args) {
		Socket socket = null;
		try {
			socket = new Socket(HOST, PORT);
			BufferedReader sin = new BufferedReader (new InputStreamReader(socket.getInputStream ( )));
			PrintWriter sout = new PrintWriter (socket.getOutputStream ( ), true);
			BufferedReader clavier = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Connecté au serveur " + socket.getInetAddress() + ":"+ socket.getPort());
			
			String line;
			
			while(!sin.ready()) {
				System.out.println(sin.readLine()); //on écrit ce que le serveur veut
				line = clavier.readLine(); //on lit la réponse au clavier
				if (line.equals("exit")) {
					break;
				}
				else {
					sout.println(line);
				}
				 
			}
			
			
			socket.close();
		}
		catch (IOException e) { System.err.println(e); }
		// Refermer dans tous les cas la socket
		try { if (socket != null) socket.close(); } 
		catch (IOException e2) { ; }		
	}
}
