package com.example.hvac;

public class EnvironmentController {

	private HVAC hvac;

	private boolean heatStatus;

	private boolean coolStatus;

	private boolean fanStatus;

	public boolean isHeatStatus() {
		return heatStatus;
	}

	public void setHeatStatus(boolean heatStatus) {
		this.heatStatus = heatStatus;
	}

	public void setCoolStatus(boolean coolStatus) {
		this.coolStatus = coolStatus;
	}

	public boolean isCoolStatus() {
		return coolStatus;
	}

	public boolean isFanStatus() {
		return fanStatus;
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
		if (temp >= 65 && temp <= 75) {
			if (heatStatus) {
				heatStatus = false;
				fanTurnOffTimer = 5;
			} else if (coolStatus) {
				coolStatus = false;
				fanTurnOffTimer = 3;
			}
			fanStatus = false;
			hvac.heat(false);
			hvac.cool(false);
			hvac.fan(false);
		} else if (temp < 65) {
			hvac.heat(true);
			heatStatus = true;
			turnOnFanIfCounterIsOff();
		} else if (temp > 75) {
			hvac.cool(true);
			coolStatus = true;
			turnOnFanIfCounterIsOff();
		}
	}

	private void turnOnFanIfCounterIsOff() {
		if (fanTurnOffTimer == 0) {
			hvac.fan(true);
			fanStatus = true;
		}
	}
}
