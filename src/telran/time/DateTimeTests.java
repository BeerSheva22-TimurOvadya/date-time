package telran.time;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DateTimeTests {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void localDateTest() {
		LocalDate birthDateAS = LocalDate.parse("1799-06-06");
		LocalDate barMizvaAS = birthDateAS.plusYears(13);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMM,YYYY,d", Locale.CANADA);
		System.out.println(barMizvaAS.format(dtf));
		ChronoUnit unit = ChronoUnit.MONTHS;
		System.out.printf("Number of %s between %s and %s is %d", unit,
				 birthDateAS, barMizvaAS, unit.between(birthDateAS, barMizvaAS));
	}
	

}
