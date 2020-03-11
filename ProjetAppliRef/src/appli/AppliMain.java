package appli;

import bri.ServeurBRiLaunch;
import utilisateur.ListeUtilisateur;

public class AppliMain {

	public static void main(String[] args) {
		
		final int portAmateur = 2600;
		final int portProgrammeur = 2700;
		
		ListeUtilisateur.initialisation();
		
		new Thread(new ServeurBRiLaunch(portAmateur)).start();
		new Thread(new ServeurBRiLaunch(portProgrammeur)).start();
		
	}

}
