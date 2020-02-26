package services;

import java.net.Socket;

public abstract class Service {
	

	private final Socket client;
	
	public Service(Socket socket) {
		this.client = socket;

	}
	
	protected Socket getSocket() {
		return this.client;
	}
	
}