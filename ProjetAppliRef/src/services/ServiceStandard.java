package services;

import java.net.Socket;

public abstract class ServiceStandard {
	

	private final Socket client;
	
	public ServiceStandard(Socket socket) {
		this.client = socket;

	}
	
	protected Socket getSocket() {
		return this.client;
	}
}