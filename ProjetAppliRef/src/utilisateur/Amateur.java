package utilisateur;

import java.util.ArrayList;

public class Amateur {

	private String mdp;
	private String pseudo;
	private String pseudoFtp;
	private String mdpFtp;
	private ArrayList<Message> messagesRecu; // tous les messages reçu d'un amateur
	
	public Amateur(String pseudo, String mdp, String pseudoFtp, String mdpFtp) {
		this.pseudo = pseudo;
		this.mdp = mdp;
		this.pseudoFtp = pseudoFtp;
		this.mdpFtp = mdpFtp;
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

	public String getPseudoFtp() {
		return pseudoFtp;
	}

	public String getMdpFtp() {
		return mdpFtp;
	}

}
