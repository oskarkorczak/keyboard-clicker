package com.korczak.oskar.clicker;

import org.joda.time.DateTime;

public class JodaTimeProvider implements TimeProvider{

	@Override
	public DateTime getTime() {
		return new DateTime();
	}
}
