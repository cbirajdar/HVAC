package com.example.hvac.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class CommonUtils {

	public static String readFromSocket(Socket socket) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		String data = in.readLine();
		in.close();
		return data;
	}

	public static void readFromAndWriteToSocket(Socket socket) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		String data = in.readLine();
		if (data != null) {
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			out.write(data);
			out.flush();
		}
		in.close();
	}

	public static void writeToSocket(Socket socket, String data) throws IOException {
		PrintWriter out = new PrintWriter(socket.getOutputStream());
		out.println(data);
		out.flush();
	}
}
