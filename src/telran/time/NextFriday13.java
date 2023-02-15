package telran.time;

import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;

public class NextFriday13 implements TemporalAdjuster {
	ChronoField DAY_OF_WEEK = ChronoField.DAY_OF_WEEK;
	ChronoField DAY_OF_MONTH = ChronoField.DAY_OF_MONTH;
	ChronoUnit MONTHS = ChronoUnit.MONTHS;
	ChronoUnit DAYS = ChronoUnit.DAYS;

	@Override
	public Temporal adjustInto(Temporal temporal) {
		Temporal next13 = temporal;
		int currentDay = 13 - next13.get(DAY_OF_MONTH);
		if (currentDay < 0) {	
			next13 = next13.plus(currentDay, DAYS);			
		} else {
			next13 = next13.minus(currentDay, DAYS);
			next13 = next13.plus(1, MONTHS);
		}
		while (next13.get(DAY_OF_WEEK) != 5) {
			next13 = next13.plus(1, MONTHS);
		}
		return next13;
	}

}
