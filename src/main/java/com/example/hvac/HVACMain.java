package com.example.hvac;

import java.io.IOException;

import com.example.hvac.router.CommandInterpreter;
import com.example.hvac.socket.ServerSocketWrapper;

public class HVACMain {

	public static void main(String[] args) throws IOException {
		int minTemp = 65;
		int maxTemp = 75;
		if (args.length == 2) {
			minTemp = Integer.parseInt(args[0]);
			maxTemp = Integer.parseInt(args[1]);
		} else {
			System.out.println("Invalid input. Sample input => 65 75");
		}
		new CommandInterpreter().setTheTemperatureRangeOnHVAC(minTemp, maxTemp);
		new ServerSocketWrapper().startAndAccept();
	}
}
