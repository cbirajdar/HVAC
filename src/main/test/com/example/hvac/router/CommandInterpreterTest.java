package com.example.hvac.router;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.example.hvac.EnvironmentController;

public class CommandInterpreterTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	private CommandInterpreter interpreter;

	private EnvironmentController controller;

	@Before
	public void setUp() {
		controller = EnvironmentController.getInstance();
		controller.resetToDefaultTemperatureRange();
	}

	@Test
	public void setTheValidTemperatureRanges() {
		interpreter = new CommandInterpreter();
		interpreter.setTheTemperatureRangeOnHVAC(70, 80);
		assertEquals(70, controller.getMinTemp());
		assertEquals(80, controller.getMaxTemp());
	}

	@Test(expected = IllegalArgumentException.class)
	public void setMinTempHigherThanMaxTemp() {
		interpreter = new CommandInterpreter();
		interpreter.setTheTemperatureRangeOnHVAC(70, 65);
	}

	@Test(expected = IllegalArgumentException.class)
	public void setMaxTempMuchHigherThanMinTemp() {
		interpreter = new CommandInterpreter();
		interpreter.setTheTemperatureRangeOnHVAC(65, 66);
	}

	@Test
	public void setValidMaxTempGivenTheDefaultTempRange() {
		interpreter = new CommandInterpreter();
		interpreter.setTheHighTemperatureOnHVAC(80);
		assertEquals(65, controller.getMinTemp());
		assertEquals(80, controller.getMaxTemp());
	}

	@Test
	public void setInValidMaxTempGivenTheDefaultTempRange() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("Max temperature should be at least five degrees warmer than min temperature.");
		interpreter = new CommandInterpreter();
		interpreter.setTheHighTemperatureOnHVAC(69);
	}

	@Test
	public void setValidMinTempGivenTheDefaultTempRange() {
		interpreter = new CommandInterpreter();
		interpreter.setTheLowTemperatureOnHVAC(60);
		assertEquals(60, controller.getMinTemp());
		assertEquals(75, controller.getMaxTemp());
	}

	@Test
	public void setInValidMinTempGivenTheDefaultTempRange() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("Max temperature should be at least five degrees warmer than min temperature.");
		interpreter = new CommandInterpreter();
		interpreter.setTheLowTemperatureOnHVAC(72);
	}

}
