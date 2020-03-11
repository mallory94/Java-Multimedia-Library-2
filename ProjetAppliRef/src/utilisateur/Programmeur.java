package utilisateur;

public class Programmeur {
	
	private String pseudo;
	private String mdp;
	private String url;
	
	public Programmeur(String pseudo, String mdp,String url) {
		this.pseudo = pseudo;
		this.mdp = mdp;
		this.url = url;
	}
	
	public String getMdp() {
		return mdp;
	}
	
	public String getUrl() {
		return url;
	}
	
	public String getPseudo() {
		return pseudo;
	}
}
