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
				throw new Exception("Les informations sont inexactes ou l'amateur demandé n'existe pas.");
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
				throw new Exception("Les informations sont inexactes ou le programmeur demandé n'existe pas.");
			}
		}catch(Exception e) {
			throw e;
		}
	}
	
	public static void initialisation() {
		programmeurs.put("test",new Programmeur("test", "test", "url"));
		programmeurs.put("mal",new Programmeur("mal", "mmm","malurl"));
		
		amateurs.put("vad", new Amateur("vad","vvv"));
		amateurs.put("mec", new Amateur("mec","mec"));
		
		amateurs.get("vad").AddMessage(new Message("salut mec j'aime bien les bananes au comcombres.", amateurs.get("mec")));
		amateurs.get("vad").AddMessage(new Message("je suis pas ton père mec", amateurs.get("mec")));
		amateurs.get("vad").AddMessage(new Message("si jamais tu est mort tu ne pourras pas me lire :'(", amateurs.get("mec")));
		
	}
	
	public static Amateur getAmateurByLogin(String login) {
		return amateurs.get(login);
	}

}
