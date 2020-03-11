package utilisateur;

public class Amateur {

	private String mdp;
	private String url;
	private String email;
	
	public Amateur(String mdp, String url) {
		this.mdp = mdp;
		this.url = url;
	}
	
	public String getMdp() {
		return mdp;
	}
	
	public String getUrl() {
		return url;
	}
	
	public String getEmail() {
		return email;
	}

}
