package utilisateur;

public class Message {
	private Amateur éméteur;// le mec qui a envoyé le message
	private String message; // le message
	
	public Message(String message, Amateur émé) {
		this.message = message;
		this.éméteur = émé;
	}
	
	public String getMessage() {
		return this.message;
	}
	
	public Amateur getEmeteur() {
		return this.éméteur;
	}
}
