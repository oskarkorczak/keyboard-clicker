package com.korczak.oskar.clicker;

import com.jayway.awaitility.Awaitility;
import com.jayway.awaitility.Duration;

import java.awt.*;
import java.io.IOException;

import static com.jayway.awaitility.Awaitility.await;
import static com.jayway.awaitility.Awaitility.with;
import static com.jayway.awaitility.Duration.FOREVER;
import static com.jayway.awaitility.Duration.ONE_SECOND;
import static java.lang.Boolean.TRUE;
import static java.util.concurrent.TimeUnit.MINUTES;
import static org.hamcrest.Matchers.equalTo;

public class KeyboardClickerLauncher {

	private static final Duration THIRTY_MINUTES = new Duration(30, MINUTES);

	public static void main(String[] args) throws AWTException, IOException, InterruptedException {
		KeyboardClicker keyboardClicker = new KeyboardClickerFactory().createKeyboardClicker();

		Awaitility.setDefaultTimeout(FOREVER);
		with().pollDelay(ONE_SECOND)
			.pollInterval(THIRTY_MINUTES)
			.timeout(FOREVER)
			.await("Trigger keyboard key click.")
			.atMost(FOREVER)
			.until(keyboardClicker.isTriggerKeyboardKey(), equalTo(TRUE));
	}
}
