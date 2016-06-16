package com.example.hvac.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.example.hvac.utils.CommonUtils;

public class ServerSocketWrapper {

	private final int PORT = 9999;

	private boolean accepted = true;

	private ServerSocket serverSocket;

	public void startAndAccept() throws IOException {
		serverSocket = new ServerSocket(PORT);
		accept();
	}

	public boolean isRunning() {
		return !isClosed();
	}

	public boolean isClosed() {
		return serverSocket.isClosed();
	}

	private void accept() throws IOException {
		while (accepted) {
			Socket socket = serverSocket.accept();
			CommonUtils.readFromAndWriteToSocket(socket);
		}
	}

	public void stop() throws IOException {
		if (!isClosed()) {
			accepted = false;
			serverSocket.close();
		}
	}

}
