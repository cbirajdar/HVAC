package com.example.hvac;

import com.example.hvac.HVAC;

public class HVACDummy implements HVAC {

	private int currentTemp;

	public HVACDummy() {

	}

	public HVACDummy(int temp) {
		this.currentTemp = temp;
	}

	@Override
	public void heat(boolean on) {

	}

	@Override
	public void cool(boolean on) {

	}

	@Override
	public void fan(boolean on) {

	}

	@Override
	public int temp() {
		return currentTemp;
	}
}
