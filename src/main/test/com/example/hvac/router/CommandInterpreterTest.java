package com.example.hvac.router;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.example.hvac.EnvironmentController;
import com.example.hvac.HVACImpl;

public class CommandInterpreterTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	private CommandInterpreter router;

	private EnvironmentController controller;

	@Before
	public void setUp() {
		controller = new EnvironmentController(new HVACImpl());
	}

	@Test
	public void setTheValidTemperatureRanges() {
		router = new CommandInterpreter(controller);
		router.setTheTemperatureRangeOnHVAC(70, 80);
		assertEquals(70, controller.getMinTemp());
		assertEquals(80, controller.getMaxTemp());
	}

	@Test(expected = IllegalArgumentException.class)
	public void setMinTempHigherThanMaxTemp() {
		router = new CommandInterpreter(controller);
		router.setTheTemperatureRangeOnHVAC(70, 65);
	}

	@Test(expected = IllegalArgumentException.class)
	public void setMaxTempMuchHigherThanMinTemp() {
		router = new CommandInterpreter(controller);
		router.setTheTemperatureRangeOnHVAC(65, 66);
	}

	@Test
	public void setValidMaxTempGivenTheDefaultTempRange() {
		router = new CommandInterpreter(controller);
		router.setTheHighTemperatureOnHVAC(80);
		assertEquals(65, controller.getMinTemp());
		assertEquals(80, controller.getMaxTemp());
	}

	@Test
	public void setInValidMaxTempGivenTheDefaultTempRange() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("Max temperature should be at least five degrees warmer than min temperature.");
		router = new CommandInterpreter(controller);
		router.setTheHighTemperatureOnHVAC(69);
	}

	@Test
	public void setValidMinTempGivenTheDefaultTempRange() {
		router = new CommandInterpreter(controller);
		router.setTheLowTemperatureOnHVAC(60);
		assertEquals(60, controller.getMinTemp());
		assertEquals(75, controller.getMaxTemp());
	}

	@Test
	public void setInValidMinTempGivenTheDefaultTempRange() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("Max temperature should be at least five degrees warmer than min temperature.");
		router = new CommandInterpreter(controller);
		router.setTheLowTemperatureOnHVAC(72);
	}

}
