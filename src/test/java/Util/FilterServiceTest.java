package Util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

class FilterServiceTest {

	private final FilterService filterService = new FilterService();
	private final Set<String> noStrike = Collections.emptySet();
	private final Set<String> noBlanket = Collections.emptySet();
	private final Set<String> noTest = Collections.emptySet();

	private Developments wideOpenFilter() {
		Developments filterdev = new Developments();
		filterdev.setPriceMin(0);
		filterdev.setPriceMax(999999);
		return filterdev;
	}

	@Test
	void noCriteriaMatchesAnyActiveDevelopment() {
		Developments dev = new Developments();
		dev.setInactive(false);

		assertTrue(filterService.filter(dev, wideOpenFilter(), false, noStrike, noBlanket, noTest));
	}

	@Test
	void titleCodeMatchesCaseInsensitiveSubstring() {
		Developments filterdev = wideOpenFilter();
		filterdev.setCode("abc");

		Developments matching = new Developments();
		matching.setCode("XYZABC123");
		assertTrue(filterService.filter(matching, filterdev, false, noStrike, noBlanket, noTest));

		Developments nonMatching = new Developments();
		nonMatching.setCode("nomatch");
		assertFalse(filterService.filter(nonMatching, filterdev, false, noStrike, noBlanket, noTest));
	}

	@Test
	void nullTitleCodeOnFilterdevDoesNotThrowAndMatchesEverything() {
		// filterdev.getCode() is null unless setCode() was called - filter() must not NPE here
		Developments filterdev = wideOpenFilter();
		Developments dev = new Developments();
		dev.setCode("anything");

		assertTrue(filterService.filter(dev, filterdev, false, noStrike, noBlanket, noTest));
	}

	@Test
	void priceRangeExcludesOutOfRangeCost() {
		Developments filterdev = new Developments();
		filterdev.setPriceMin(10);
		filterdev.setPriceMax(20);

		Developments inRange = new Developments();
		inRange.setCost(15);
		assertTrue(filterService.filter(inRange, filterdev, false, noStrike, noBlanket, noTest));

		Developments tooLow = new Developments();
		tooLow.setCost(5);
		assertFalse(filterService.filter(tooLow, filterdev, false, noStrike, noBlanket, noTest));

		Developments tooHigh = new Developments();
		tooHigh.setCost(25);
		assertFalse(filterService.filter(tooHigh, filterdev, false, noStrike, noBlanket, noTest));
	}

	@Test
	void booleanCriterionOnlyExcludesWhenRequiredAndMissing() {
		Developments filterdev = wideOpenFilter();
		filterdev.setParagonClean(true);

		Developments matches = new Developments();
		matches.setParagonClean(true);
		assertTrue(filterService.filter(matches, filterdev, false, noStrike, noBlanket, noTest));

		Developments doesNotMatch = new Developments();
		doesNotMatch.setParagonClean(false);
		assertFalse(filterService.filter(doesNotMatch, filterdev, false, noStrike, noBlanket, noTest));
	}

	@Test
	void showAllBypassesInactiveFilter() {
		Developments filterdev = wideOpenFilter();
		filterdev.setInactive(false);

		Developments inactiveDev = new Developments();
		inactiveDev.setInactive(true);

		assertFalse(filterService.filter(inactiveDev, filterdev, false, noStrike, noBlanket, noTest),
				"showAll=false should exclude a dev whose inactive flag doesn't match filterdev's");
		assertTrue(filterService.filter(inactiveDev, filterdev, true, noStrike, noBlanket, noTest),
				"showAll=true should include developments regardless of inactive flag");
	}

	@Test
	void blankSeasonOnFilterdevMeansNoFilterApplied() {
		Developments filterdev = wideOpenFilter(); // season left unset (null)
		Developments dev = new Developments();
		dev.setSeason("Spring");

		assertTrue(filterService.filter(dev, filterdev, false, noStrike, noBlanket, noTest));
	}

	@Test
	void setSeasonRequiresExactMatch() {
		Developments filterdev = wideOpenFilter();
		filterdev.setSeason("Spring");

		Developments matching = new Developments();
		matching.setSeason("Spring");
		assertTrue(filterService.filter(matching, filterdev, false, noStrike, noBlanket, noTest));

		Developments nonMatching = new Developments();
		nonMatching.setSeason("Fall");
		assertFalse(filterService.filter(nonMatching, filterdev, false, noStrike, noBlanket, noTest));
	}

	@Test
	void selectedStrikeProgressSetRestrictsMatches() {
		Developments filterdev = wideOpenFilter();
		Set<String> selected = new HashSet<>();
		selected.add("Pending");

		Developments matching = new Developments();
		matching.setStrike_off_status("Pending");
		assertTrue(filterService.filter(matching, filterdev, false, selected, noBlanket, noTest));

		Developments nonMatching = new Developments();
		nonMatching.setStrike_off_status("Done");
		assertFalse(filterService.filter(nonMatching, filterdev, false, selected, noBlanket, noTest));
	}

	@Test
	void colorlineDneSentinelMeansNoFilterApplied() {
		Developments filterdev = wideOpenFilter();
		filterdev.setColorline_status("DNE");

		Developments dev = new Developments();
		dev.setColorline_status("anything");

		assertTrue(filterService.filter(dev, filterdev, false, noStrike, noBlanket, noTest));
	}
}
