package com.example.hvac.router;

import com.example.hvac.EnvironmentController;
import com.example.hvac.HVACDummy;

public class HvacRouter {

	private EnvironmentController controller;
	
	public HvacRouter() {
		init();
	}
	
	public void init() {
		this.controller = new EnvironmentController(new HVACDummy());
	}
	
	public void setTheTemperatureRangeOnHVAC(int minTemp, int maxTemp) {
		if (maxTemp - minTemp < 5) {
			throw new IllegalArgumentException("Max temperature should be at least five degrees warmer than min temperature.");
		}
		this.controller.setTempratureRanges(minTemp, maxTemp);
	}

}
