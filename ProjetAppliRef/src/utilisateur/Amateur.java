package utilisateur;

import java.util.ArrayList;

public class Amateur {

	private String mdp;
	private String pseudo;
	private ArrayList<Message> messagesRecu; // tous les messages reçu d'un amateur
	
	public Amateur(String pseudo, String mdp) {
		this.pseudo = pseudo;
		this.mdp = mdp;
		this.messagesRecu = new ArrayList<>();
	}
	
	public String getMdp() {
		return mdp;
	}
	
	public String getPseudo() {
		return pseudo;
	}
	
	public ArrayList<Message> getMessagesRecu() {
		return new ArrayList<Message>(this.messagesRecu); // on retourne une nouvelle liste de messages.
	}
	
	public void AddMessage(Message m) {
		this.messagesRecu.add(m);
	}

}
