package com.korczak.oskar.clicker;

import com.korczak.oskar.clicker.KeyboardClicker;
import com.korczak.oskar.clicker.TimeProvider;
import org.joda.time.DateTime;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.awt.*;

import static java.awt.event.KeyEvent.VK_SHIFT;
import static java.lang.Boolean.FALSE;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class)
public class KeyboardClickerTest {

	@Mock private Robot robotKeyboardClicker;
	@Mock private TimeProvider timeProvider;
	@InjectMocks private KeyboardClicker keyboardClicker;

	@BeforeMethod
	public void initializeMocks() {
		initMocks(this);
	}

	@DataProvider private static final Object[][] workingHours(){
		return new Object[][] { {8}, {9}, {10}, {11}, {12}, {13}, {14}, {15}, {16}, {17}, {18}};
	}

	@Test(dataProvider = "workingHours")
	public void shouldClickKeyboardKeyDuringWorkingHours(int hour) throws Exception {
		given(timeProvider.getTime()).willReturn(getJodaTime(hour));

		boolean result = keyboardClicker.isTriggerKeyboardKey().call();

		verify(robotKeyboardClicker).keyPress(VK_SHIFT);
		verify(robotKeyboardClicker).keyRelease(VK_SHIFT);
		assertThat(result, equalTo(FALSE));
	}

	@DataProvider private static final Object[][] nonWorkingHours(){
		return new Object[][] { {19}, {20}, {21}, {22}, {23}, {0}, {1}, {2}, {3}, {4}, {5}, {6}, {7}};
	}

	@Test(dataProvider = "nonWorkingHours")
	public void shouldNotClickKeyboardKeyDuringNonWorkingHours(int hour) throws Exception {
		given(timeProvider.getTime()).willReturn(getJodaTime(hour));

		boolean result = keyboardClicker.isTriggerKeyboardKey().call();

		verify(robotKeyboardClicker, never()).keyPress(VK_SHIFT);
		verify(robotKeyboardClicker, never()).keyRelease(VK_SHIFT);
		assertThat(result, equalTo(FALSE));
	}

	private DateTime getJodaTime(int hour) {
		return new DateTime()
				.withHourOfDay(hour)
				.withMinuteOfHour(0)
				.withSecondOfMinute(0)
				.withMillisOfSecond(0);
	}

}
