package telran.time;
import static org.junit.jupiter.api.Assertions.*;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.*;
import java.util.Locale;

import org.junit.jupiter.api.*;


class DateTimeTests {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	@Disabled
	void localDateTest() {
		LocalDate birthDateAS = LocalDate.parse("1799-06-06");
		LocalDate barMizvaAS = birthDateAS.plusYears(13);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMMM,YYYY,d", Locale.CANADA);
		System.out.println(barMizvaAS.format(dtf));
		ChronoUnit unit = ChronoUnit.MONTHS;
		System.out.printf("Number of %s between %s and %s is %d", unit,
				 birthDateAS, barMizvaAS, unit.between(birthDateAS, barMizvaAS));
	}
	@Test
	@Disabled
	void barMizvaTest() {
		LocalDate current = LocalDate.now();
		assertEquals(current.plusYears(13), current.with(new BarMizvaAjuster()));
	}

	@Test
	void displayCurrentDateTimeCanadaTimeZones() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm VV");
		
		ZoneId.getAvailableZoneIds().stream()
			.filter(zoneId -> zoneId.contains("Canada"))
			.map(ZoneId::of)
			.map(Instant.now()::atZone)
			.map(dateTime -> dateTime.format(dtf))
			.sorted().forEach(System.out::println); 
		
		ZoneId.getAvailableZoneIds().stream()
			.filter(zoneId -> zoneId.contains("Israel"))
			.map(ZoneId::of)
			.map(Instant.now()::atZone)
			.map(dateTime -> dateTime.format(dtf))
			.sorted().forEach(System.out::println);
		
		DateTimeFormatter RealTimeNow = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
		System.out.println(LocalDateTime.now().format(RealTimeNow) + " Israel Real Time");
	}	
		
	@Test
	void nextFriday13test() {
		TemporalAdjuster nextFriday13 = new NextFriday13();
		assertEquals(LocalDate.of(2023, 10, 13), LocalDate.of(2023, 2, 14).with(nextFriday13));				
		assertEquals(LocalDate.of(2024, 9, 13), LocalDate.of(2024, 2, 29).with(nextFriday13));
		assertEquals(LocalDate.of(2024, 12, 13), LocalDate.of(2024, 9, 13).with(nextFriday13));
	}
	
	@Test
	void workingDayTest() {
		TemporalAdjuster add4WorkingDaysSatSun = new WorkingDays(7, new DayOfWeek[] { DayOfWeek.FRIDAY, DayOfWeek.SUNDAY });				
		assertEquals(LocalDate.of(2023, 02, 24), LocalDate.of(2023, 02, 15).with(add4WorkingDaysSatSun));
		assertEquals(LocalDate.of(2023, 02, 28), LocalDate.of(2023, 02, 18).with(add4WorkingDaysSatSun));
		
		TemporalAdjuster add2WorkingDaysFriSun = new WorkingDays(10, new DayOfWeek[] {
				DayOfWeek.SUNDAY, 
				DayOfWeek.MONDAY,
				DayOfWeek.TUESDAY,
				DayOfWeek.WEDNESDAY,
				DayOfWeek.THURSDAY});			
		assertEquals(LocalDate.parse("2023-03-25"), LocalDate.parse("2023-02-18").with(add2WorkingDaysFriSun));		
	}
}
