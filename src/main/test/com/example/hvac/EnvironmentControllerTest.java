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
		assertEquals(false, controller.isFanStatus());
		assertEquals(false, controller.isHeatStatus());
		assertEquals(false, controller.isCoolStatus());
	}

	@Test
	public void turnOnHeatWhenTheTemparatureIsLessThan65() {
		HVACDummy dummy = new HVACDummy(60);
		controller = new EnvironmentController(dummy);
		controller.tick();
		assertEquals(true, controller.isHeatStatus());
		assertEquals(true, controller.isFanStatus());
	}

	@Test
	public void turnOnCoolWhenTheTemparatureIsGreaterThan75() {
		HVACDummy dummy = new HVACDummy(80);
		controller = new EnvironmentController(dummy);
		controller.tick();
		assertEquals(true, controller.isCoolStatus());
		assertEquals(true, controller.isFanStatus());
	}

	@Test
	public void turnOnCoolWhenTheTemparatureIsGreaterThan75AndFanTimerIsOn() {
		controller = new EnvironmentController(new HVACDummy(70));
		controller.setHeatStatus(true);
		controller.tick();
		assertEquals(false, controller.isFanStatus());
		controller.setHvac(new HVACDummy(80));
		controller.tick();
		assertEquals(false, controller.isFanStatus());
		assertEquals(true, controller.isCoolStatus());
		controller.tick();
		controller.tick();
		controller.tick();
		controller.tick();
		assertEquals(true, controller.isFanStatus());
		assertEquals(true, controller.isCoolStatus());
	}
	
	public void turnOnHeatWhenTheTemparatureIsLessThan65AndFanTimerIsOn() {
		controller = new EnvironmentController(new HVACDummy(70));
		controller.setCoolStatus(true);
		controller.tick();
		assertEquals(false, controller.isFanStatus());
		controller.setHvac(new HVACDummy(60));
		controller.tick();
		assertEquals(true, controller.isHeatStatus());
		assertEquals(false, controller.isFanStatus());
		controller.tick();
		controller.tick();
		assertEquals(true, controller.isFanStatus());
		assertEquals(true, controller.isHeatStatus());
	}

}
