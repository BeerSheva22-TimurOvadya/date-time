package telran.time;

import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;


public class BarMizvaAjuster implements TemporalAdjuster {
	

	@Override
	public Temporal adjustInto(Temporal temporal) {
		Temporal res = temporal.plus(13, ChronoUnit.YEARS);
		return res;
	}

}
