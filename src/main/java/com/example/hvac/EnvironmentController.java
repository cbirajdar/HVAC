package com.example.hvac;

public class EnvironmentController {

	private HVAC hvac;

	private boolean heatOn;

	private boolean coolOn;

	private boolean fanOn;

	private int thresholdTemp = 3;

	private boolean enableThreshold;

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

	public void setEnableThreshold(boolean enableThreshold) {
		this.enableThreshold = enableThreshold;
	}

	private int fanTurnOffTimer = 0;

	public void decrementFanTurnOffTimer() {
		if (fanTurnOffTimer > 0)
			fanTurnOffTimer--;
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
			if (enableThreshold && checkForHeatThresholdSettings()) {
				heatOn = false;
				fanTurnOffTimer = 5;
			}
		} else if (coolOn) {
			if (enableThreshold && checkForCoolThresholdSettings()) {
				coolOn = false;
				fanTurnOffTimer = 3;
			}
		}
		if (!enableThreshold) {
			fanOn = false;
			hvac.heat(false);
			hvac.cool(false);
			hvac.fan(false);
		}
	}

	private boolean checkForHeatThresholdSettings() {
		boolean heatThreshold = Math.abs(hvac.temp() - minTemp) >= thresholdTemp;
		return heatThreshold;
	}

	private boolean checkForCoolThresholdSettings() {
		boolean coolThreshold = Math.abs(hvac.temp() - maxTemp) >= thresholdTemp;
		return coolThreshold;
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
