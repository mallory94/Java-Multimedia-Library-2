package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;

public class ServiceAuthentification extends ServiceStandard implements Runnable {
	
	private static HashMap<String, String> loginMdp = new HashMap<String, String>();
	
	public ServiceAuthentification(Socket accept) {
		super(accept);
	}

	@Override
	public void run() {
		BufferedReader in = null;
		PrintWriter out = null;
		try {
			in = new BufferedReader(new InputStreamReader(this.getSocket().getInputStream()));
			out = new PrintWriter (this.getSocket().getOutputStream ( ), true);
			out.println("Entrez votre identifiant");
			String id = in.readLine();
			if (id != null) {
				out.println("Entrez votre mot de passe");
				String mdp = in.readLine();
				if (connectionValide(id,mdp)) {
					//connecte ?
				}
				
			}
			in.close();
			out.close();
			
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				in.close();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
	}

	private boolean connectionValide(String id, String mdp) {
		booleanconnexionValide = 
		synchronized(loginMdp) {
			try {
				mdp.equals(loginMdp.get(id));
			}
			
		}
		return ;
	}
	
	
	
}
