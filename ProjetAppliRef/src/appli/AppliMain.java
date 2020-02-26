package appli;

import bri.ServeurBRiLaunch;

public class AppliMain {

	public static void main(String[] args) {
		
		final int portAmateur = 2600;
		final int portProgrammeur = 2700;
		
		new Thread(new ServeurBRiLaunch(portAmateur)).start();
		//new Thread(new ServeurBRiLaunch(portProgrammeur)).start();
		
	}

}
