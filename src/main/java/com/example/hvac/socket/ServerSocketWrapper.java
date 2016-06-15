package com.example.hvac.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketWrapper {

	private final int PORT = 9999;
	
	private ServerSocket serverSocket;
	private Socket socket;
	
	public void start() throws IOException {
		serverSocket = new ServerSocket(PORT);
	}
	
	public boolean isRunning() {
		return true;
	}
	
	public boolean isClosed() {
		return true;
	}
	
	public void accept() throws IOException {
		
	}
	
	public void stop() {
		
	}
	
}
