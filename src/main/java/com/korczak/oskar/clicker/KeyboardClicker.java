package com.korczak.oskar.clicker;

import org.joda.time.DateTime;

import java.awt.*;
import java.util.concurrent.Callable;

import static java.awt.event.KeyEvent.VK_SHIFT;
import static java.lang.Boolean.FALSE;

public class KeyboardClicker {

	private static final DateTime START_WORKING_DAY = new DateTime().withHourOfDay(7)
																	.withMinuteOfHour(59)
																	.withSecondOfMinute(0)
																	.withMillisOfSecond(0);

	private static final DateTime FINISH_WORKING_DAY = new DateTime().withHourOfDay(18)
																	 .withMinuteOfHour(1)
																	 .withSecondOfMinute(0)
																	 .withMillisOfSecond(0);

	private Robot robot;
	private TimeProvider timeProvider;

	public KeyboardClicker(Robot aRobot, TimeProvider aTimeProvider) {
		this.robot = aRobot;
		this.timeProvider = aTimeProvider;
	}

	private void triggerKeyboardKeyClickDuringWorkingHours() {
		DateTime currentTime = timeProvider.getTime();
		if (START_WORKING_DAY.isBefore(currentTime) && FINISH_WORKING_DAY.isAfter(currentTime)) {
			robot.keyPress(VK_SHIFT);
			robot.keyRelease(VK_SHIFT);
		}
	}

	public Callable<Boolean> isTriggerKeyboardKey() {
		return new Callable<Boolean>() {
			public Boolean call() throws Exception {
				triggerKeyboardKeyClickDuringWorkingHours();
				return FALSE;
			}
		};
	}
}