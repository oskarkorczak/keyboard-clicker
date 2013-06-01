package com.korczak.oskar.clicker;

import com.korczak.oskar.clicker.JodaTimeProvider;
import com.korczak.oskar.clicker.TimeProvider;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertThat;

public class JodaTimeProviderTest {

	@Test
	public void shouldImplementTimeProviderInterface() {
		assertThat(new JodaTimeProvider(), instanceOf(TimeProvider.class));
	}
}
