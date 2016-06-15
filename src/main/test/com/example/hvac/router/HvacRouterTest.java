package com.example.hvac.router;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.example.hvac.EnvironmentController;
import com.example.hvac.HVACDummy;

public class HvacRouterTest {

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
		Assert.assertEquals(70, controller.getMinTemp());
		Assert.assertEquals(80, controller.getMaxTemp());
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

}
