package utilisateur;

import java.util.HashMap;

public class ListeUtilisateur {
	private static HashMap<String, Programmeur> programmeurs = new HashMap<String, Programmeur>();
	private static HashMap<String, Amateur> amateurs = new HashMap<String, Amateur>();
	
	public static Amateur getAmateur(String login, String mdp) throws Exception {
		try {
			if(mdp.equals(amateurs.get(login).getMdp())) {
				return amateurs.get(login);
			}
			else {
				throw new Exception("Les informations sont inexactes ou l'amateur demande n'existe pas.");
			}
		}catch(NullPointerException n) {
			throw new Exception("L'utilisateur que vous demandez n'existe pas !");
		}
		catch(Exception e) {
			throw e;
		}
	}
	
	public static Programmeur getProgrammeur(String login, String mdp) throws Exception {
		try {
			if(mdp.equals(programmeurs.get(login).getMdp())) {
				return programmeurs.get(login);
			}
			else {
				throw new Exception("Les informations sont inexactes ou le programmeur demande n'existe pas.");
			}
		}catch(Exception e) {
			throw e;
		}
	}
	
	public static void initialisation() {
		programmeurs.put("test",new Programmeur("test", "test", "ftp://localhost:2121/ohoh/", "anonymous", "brette"));
		programmeurs.put("mal",new Programmeur("mal", "mmm","ftp://localhost:2121/classes", "anonymous", "brette"));
		
		// si un système d'inscription était mise en place alors l'inscription d'un programmeur créerait forcément un compte amateur pour qu'il puisse utiliser ses services
		amateurs.put("test",new Amateur("test", "test", "anonymous", "brette"));
		amateurs.put("mal",new Amateur("mal", "mmm", "anonymous", "brette"));
		
		amateurs.put("mallory", new Amateur("mallory","mallory", "anonymous", "brette"));
		amateurs.put("vad", new Amateur("vad","vvv", "vad", "vad"));
		
	}
	
	public static Amateur getAmateurByLogin(String login) {
		
		return amateurs.get(login);
	}

}
