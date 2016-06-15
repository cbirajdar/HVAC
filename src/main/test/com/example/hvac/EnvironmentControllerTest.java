package com.example.hvac;

import static org.junit.Assert.*;

import org.junit.Test;

import com.example.hvac.EnvironmentController;
import com.example.hvac.HVACDummy;

public class EnvironmentControllerTest {

	private EnvironmentController controller;
	
	@Test
	public void doesNothingWhenTheTemperatureIsBetween65And75() {
		HVACDummy dummy = new HVACDummy(70);
		controller = new EnvironmentController(dummy);
		controller.tick();
		assertEquals(70, dummy.temp());
		assertEquals(false, controller.isFanOn());
		assertEquals(false, controller.isHeatOn());
		assertEquals(false, controller.isCoolOn());
	}

	@Test
	public void turnOnHeatWhenTheTemparatureIsLessThan65() {
		HVACDummy dummy = new HVACDummy(60);
		controller = new EnvironmentController(dummy);
		controller.tick();
		assertEquals(true, controller.isHeatOn());
		assertEquals(true, controller.isFanOn());
	}

	@Test
	public void turnOnCoolWhenTheTemparatureIsGreaterThan75() {
		HVACDummy dummy = new HVACDummy(80);
		controller = new EnvironmentController(dummy);
		controller.tick();
		assertEquals(true, controller.isCoolOn());
		assertEquals(true, controller.isFanOn());
	}

	@Test
	public void turnOnCoolWhenTheTemparatureIsGreaterThan75AndFanTimerIsOn() {
		controller = new EnvironmentController(new HVACDummy(70));
		controller.setHeatOn(true);
		controller.tick();
		assertEquals(false, controller.isFanOn());
		controller.setHvac(new HVACDummy(80));
		// 1st tick when fan counter is set
		controller.tick();
		assertEquals(false, controller.isFanOn());
		assertEquals(true, controller.isCoolOn());
		controller.tick();
		controller.tick();
		controller.tick();
		// 5th tick when fan counter is reset
		controller.tick();
		assertEquals(true, controller.isFanOn());
		assertEquals(true, controller.isCoolOn());
	}
	
	public void turnOnHeatWhenTheTemparatureIsLessThan65AndFanTimerIsOn() {
		controller = new EnvironmentController(new HVACDummy(70));
		controller.setCoolOn(true);
		controller.tick();
		assertEquals(false, controller.isFanOn());
		controller.setHvac(new HVACDummy(60));
		// 1st tick when fan counter is set
		controller.tick();
		assertEquals(true, controller.isHeatOn());
		assertEquals(false, controller.isFanOn());
		controller.tick();
		// 3rd tick when fan counter is reset
		controller.tick();
		assertEquals(true, controller.isFanOn());
		assertEquals(true, controller.isHeatOn());
	}

}
