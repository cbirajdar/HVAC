package com.example.hvac.router;

import com.example.hvac.EnvironmentController;

public class HvacRouter {

	private EnvironmentController controller;

	public HvacRouter(EnvironmentController controller) {
		this.controller = controller;
	}

	public void setTheTemperatureRangeOnHVAC(int minTemp, int maxTemp) {
		if (maxTemp - minTemp < 5) {
			throw new IllegalArgumentException(
					"Max temperature should be at least five degrees warmer than min temperature.");
		}
		this.controller.setTempratureRanges(minTemp, maxTemp);
	}

}
