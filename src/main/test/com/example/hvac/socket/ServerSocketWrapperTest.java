package com.example.hvac.socket;

import java.io.IOException;
import java.net.Socket;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.example.hvac.utils.CommonUtils;

import static org.junit.Assert.*;

public class ServerSocketWrapperTest {

	private Socket client;

	private static ServerSocketWrapper wrapper;

	@BeforeClass
	public static void beforeClass() throws IOException {
		wrapper = new ServerSocketWrapper();
		new Thread() {
			public void run() {
				try {
					wrapper.startAndAccept();
				} catch (IOException e) {
					//
				}
			}
		}.start();
	}

	@AfterClass
	public static void afterClass() throws IOException {
		wrapper.stop();
	}

	@Before
	public void setUp() throws Exception {
		client = new Socket("localhost", 9999);
	}

	@After
	public void tearDown() throws Exception {
		client.close();
	}

	@Test
	public void testServerSocketConnection() throws IOException {
		assertTrue(client.isConnected());
	}

	@Test
	public void testWriteToAndReadFromServerSocket() throws IOException, InterruptedException {
		CommonUtils.writeToSocket(client, "testWriteToServerSocket");
		assertEquals("testWriteToServerSocket", CommonUtils.readFromSocket(client));
	}

	@Test
	public void testSetValidHighTemperature() {

	}

	@Test
	public void testSetInvalidHighTemperature() {

	}

	@Test
	public void testSetValidLowTemperature() {

	}

	@Test
	public void testSetInvalidLowTemperature() {

	}

}
