package telran.time;

import java.time.DayOfWeek;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.util.Collections;
import java.util.EnumSet;


public class WorkingDays implements TemporalAdjuster {
	int nDays;
	EnumSet<DayOfWeek> dayOff = EnumSet.noneOf(DayOfWeek.class);

	@Override
	public Temporal adjustInto(Temporal temporal) {
		int count = 0;		
		while (count != nDays) {
			if (!dayOff.contains(DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK)))) {
				count++;
			}
			temporal = temporal.plus(1, ChronoUnit.DAYS);
		}
		return temporal;
	}

	public WorkingDays(int nDays, DayOfWeek[] dayOffs) {
		Collections.addAll(dayOff, dayOffs);		
		this.nDays = nDays;
	}

}