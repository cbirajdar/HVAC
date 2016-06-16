package com.example.hvac;

import static org.junit.Assert.*;

import org.junit.Test;

import com.example.hvac.EnvironmentController;
import com.example.hvac.HVACImpl;

public class EnvironmentControllerTest {

	private EnvironmentController controller;

	@Test
	public void doesNothingWhenTheTemperatureIsBetween65And75() {
		HVAC hvac = new HVACImpl(70);
		controller = new EnvironmentController(hvac);
		controller.tick();
		assertEquals(70, hvac.temp());
		assertEquals(false, controller.isFanOn());
		assertEquals(false, controller.isHeatOn());
		assertEquals(false, controller.isCoolOn());
	}

	@Test
	public void turnOnHeatWhenTheTemparatureIsLessThan65() {
		HVAC hvac = new HVACImpl(60);
		controller = new EnvironmentController(hvac);
		controller.tick();
		assertEquals(true, controller.isHeatOn());
		assertEquals(true, controller.isFanOn());
	}

	@Test
	public void turnOnCoolWhenTheTemparatureIsGreaterThan75() {
		HVAC hvac = new HVACImpl(80);
		controller = new EnvironmentController(hvac);
		controller.tick();
		assertEquals(true, controller.isCoolOn());
		assertEquals(true, controller.isFanOn());
	}

	@Test
	public void turnOnCoolWhenTheTemparatureIsGreaterThan75AndFanTimerIsOn() {
		controller = new EnvironmentController(new HVACImpl(70));
		controller.setHeatOn(true);
		controller.tick();
		assertEquals(false, controller.isFanOn());
		controller.setHvac(new HVACImpl(80));
		// 1st tick when fan counter is set
		controller.tick();
		assertEquals(false, controller.isFanOn());
		assertEquals(true, controller.isCoolOn());
		controller.tick();
		assertEquals(false, controller.isFanOn());
		controller.tick();
		assertEquals(false, controller.isFanOn());
		controller.tick();
		assertEquals(false, controller.isFanOn());
		// 5th tick when fan counter is reset
		controller.tick();
		assertEquals(true, controller.isFanOn());
		assertEquals(true, controller.isCoolOn());
	}

	@Test
	public void turnOnHeatWhenTheTemparatureIsLessThan65AndFanTimerIsOn() {
		controller = new EnvironmentController(new HVACImpl(70));
		controller.setCoolOn(true);
		controller.tick();
		assertEquals(false, controller.isFanOn());
		controller.setHvac(new HVACImpl(60));
		// 1st tick when fan counter is set
		controller.tick();
		assertEquals(true, controller.isHeatOn());
		assertEquals(false, controller.isFanOn());
		controller.tick();
		assertEquals(false, controller.isFanOn());
		// 3rd tick when fan counter is reset
		controller.tick();
		assertEquals(true, controller.isFanOn());
		assertEquals(true, controller.isHeatOn());
	}

}
