package utilisateur;

public class Programmeur {
	
	private String pseudo;
	private String mdp;
	private String url;
	private String pseudoFtp;
	private String mdpFtp;
	
	
	public Programmeur(String pseudo, String mdp, String url, String pseudoFtp, String mdpFtp) {
		this.pseudo = pseudo;
		this.mdp = mdp;
		this.url = url;
		this.pseudoFtp = pseudoFtp;
		this.mdpFtp = mdpFtp;
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
	
	public String getPseudoFtp() {
		return pseudoFtp;	
	}
	
	public String getMdpFtp() {
		return mdpFtp;	
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
}
