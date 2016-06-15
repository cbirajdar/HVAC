package com.example.hvac.router;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.example.hvac.EnvironmentController;
import com.example.hvac.HVACDummy;

public class HvacRouterTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	private HvacRouter hvacRouter;

	private EnvironmentController controller;

	@Before
	public void setUp() {
		controller = new EnvironmentController(new HVACDummy());
	}

	@Test
	public void setTheValidTemperatureRanges() {
		hvacRouter = new HvacRouter(controller);
		hvacRouter.setTheTemperatureRangeOnHVAC(70, 80);
		assertEquals(70, controller.getMinTemp());
		assertEquals(80, controller.getMaxTemp());
	}

	@Test(expected = IllegalArgumentException.class)
	public void setMinTempHigherThanMaxTemp() {
		hvacRouter = new HvacRouter(controller);
		hvacRouter.setTheTemperatureRangeOnHVAC(70, 65);
	}

	@Test(expected = IllegalArgumentException.class)
	public void setMaxTempMuchHigherThanMinTemp() {
		hvacRouter = new HvacRouter(controller);
		hvacRouter.setTheTemperatureRangeOnHVAC(65, 66);
	}

	@Test
	public void setValidMaxTempGivenTheDefaultTempRange() {
		hvacRouter = new HvacRouter(controller);
		hvacRouter.setTheHighTemperatureOnHVAC(80);
		assertEquals(65, controller.getMinTemp());
		assertEquals(80, controller.getMaxTemp());
	}

	@Test
	public void setInValidMaxTempGivenTheDefaultTempRange() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("Max temperature should be at least five degrees warmer than min temperature.");
		hvacRouter = new HvacRouter(controller);
		hvacRouter.setTheHighTemperatureOnHVAC(69);
	}

	@Test
	public void setValidMinTempGivenTheDefaultTempRange() {
		hvacRouter = new HvacRouter(controller);
		hvacRouter.setTheLowTemperatureOnHVAC(60);
		assertEquals(60, controller.getMinTemp());
		assertEquals(75, controller.getMaxTemp());
	}

	@Test
	public void setInValidMinTempGivenTheDefaultTempRange() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("Max temperature should be at least five degrees warmer than min temperature.");
		hvacRouter = new HvacRouter(controller);
		hvacRouter.setTheLowTemperatureOnHVAC(72);
	}

}
