package utilisateur;

public class Message {
	private Amateur �m�teur;// le mec qui a envoy� le message
	private String message; // le message
	
	public Message(String message, Amateur �m�) {
		this.message = message;
		this.�m�teur = �m�;
	}
	
	public String getMessage() {
		return this.message;
	}
	
	public Amateur getEmeteur() {
		return this.�m�teur;
	}
}
