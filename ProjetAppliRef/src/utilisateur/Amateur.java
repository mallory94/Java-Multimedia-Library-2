package utilisateur;

public class Amateur {

	private String mdp;
	private String pseudo;
	
	public Amateur(String pseudo, String mdp) {
		this.pseudo = pseudo;
		this.mdp = mdp;
	}
	
	public String getMdp() {
		return mdp;
	}
	
	public String getPseudo() {
		return pseudo;
	}
	

}
