package com.example.hvac;

public class EnvironmentController {

	private HVAC hvac;

	private boolean heatOn;

	private boolean coolOn;

	private boolean fanOn;

	private int minTemp = 65;

	private int maxTemp = 75;

	public boolean isHeatOn() {
		return heatOn;
	}

	public void setHeatOn(boolean heatOn) {
		this.heatOn = heatOn;
	}

	public void setCoolOn(boolean coolOn) {
		this.coolOn = coolOn;
	}

	public boolean isCoolOn() {
		return coolOn;
	}

	public boolean isFanOn() {
		return fanOn;
	}

	private int fanTurnOffTimer = 0;

	public void decrementFanTurnOffTimer() {
		if (fanTurnOffTimer > 0) fanTurnOffTimer--;
	}

	public void setHvac(HVAC hvac) {
		this.hvac = hvac;
	}

	public EnvironmentController(HVAC hvac) {
		this.hvac = hvac;
	}

	public void tick() {
		decrementFanTurnOffTimer();
		int temp = hvac.temp();
		if (temp >= minTemp && temp <= maxTemp) {
			handleRoomTempSettings();
		} else if (temp < minTemp) {
			turnOnHeatAndFanWhenTempIsLow();
		} else {
			turnOnCoolAndFanWhenTheTempIsHigh();
		}
	}

	private void handleRoomTempSettings() {
		if (heatOn) {
			heatOn = false;
			fanTurnOffTimer = 5;
		} else if (coolOn) {
			coolOn = false;
			fanTurnOffTimer = 3;
		}
		fanOn = false;
		hvac.heat(false);
		hvac.cool(false);
		hvac.fan(false);
	}

	private void turnOnCoolAndFanWhenTheTempIsHigh() {
		hvac.cool(true);
		coolOn = true;
		turnOnFanIfCounterIsOff();
	}

	private void turnOnHeatAndFanWhenTempIsLow() {
		hvac.heat(true);
		heatOn = true;
		turnOnFanIfCounterIsOff();
	}

	private void turnOnFanIfCounterIsOff() {
		if (fanTurnOffTimer == 0) {
			hvac.fan(true);
			fanOn = true;
		}
	}
}
