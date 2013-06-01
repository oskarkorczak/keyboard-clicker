package com.korczak.oskar.clicker;

import java.awt.*;

public class KeyboardClickerFactory {

	public KeyboardClicker createKeyboardClicker() throws AWTException {
		GraphicsEnvironment localGraphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice[] screenDevices = localGraphicsEnvironment.getScreenDevices();
		Robot robot = new Robot(screenDevices[0]);
		return new KeyboardClicker(robot, new JodaTimeProvider());
	}
}