package utilisateur;

public class Programmeur {

	private String mdp;
	private String url;
	
	public Programmeur(String mdp, String url) {
		this.mdp = mdp;
		this.url = url;
	}
	
	public String getMdp() {
		return mdp;
	}
	
	public String getUrl() {
		return url;
	}

}
