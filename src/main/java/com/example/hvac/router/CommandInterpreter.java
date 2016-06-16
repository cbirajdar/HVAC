package com.example.hvac.router;

import com.example.hvac.Constants;
import com.example.hvac.EnvironmentController;

public class CommandInterpreter {

	private EnvironmentController controller;

	public CommandInterpreter() {
		this.controller = EnvironmentController.getInstance();
	}

	public void setTheTemperatureRangeOnHVAC(int minTemp, int maxTemp) {
		if (maxTemp - minTemp < 5) {
			throw new IllegalArgumentException(Constants.INVALID_TEMP_RANGE_MESSAGE);
		}
		this.controller.setTempratureRanges(minTemp, maxTemp);
	}

	public void setTheHighTemperatureOnHVAC(int maxTemp) {
		setTheTemperatureRangeOnHVAC(controller.getMinTemp(), maxTemp);
	}

	public void setTheLowTemperatureOnHVAC(int minTemp) {
		setTheTemperatureRangeOnHVAC(minTemp, controller.getMaxTemp());
	}

}
