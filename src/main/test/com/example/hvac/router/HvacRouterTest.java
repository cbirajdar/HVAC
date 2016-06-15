package com.example.hvac.router;

import org.junit.Test;

public class HvacRouterTest {

	private HvacRouter hvacRouter;

	@Test
	public void setTheValidTemperatureRanges() {
		hvacRouter = new HvacRouter();
		hvacRouter.setTheTemperatureRangeOnHVAC(65, 75);
	}

	@Test(expected = IllegalArgumentException.class)
	public void setMinTempHigherThanMaxTemp() {
		hvacRouter = new HvacRouter();
		hvacRouter.setTheTemperatureRangeOnHVAC(70, 65);
	}

	@Test(expected = IllegalArgumentException.class)
	public void setMaxTempMuchHigherThanMinTemp() {
		hvacRouter = new HvacRouter();
		hvacRouter.setTheTemperatureRangeOnHVAC(65, 66);
	}

}
