package com.example.hvac;

import com.example.hvac.HVAC;

public class HVACImpl implements HVAC {

	private int currentTemp;

	public HVACImpl() {

	}

	public HVACImpl(int temp) {
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
