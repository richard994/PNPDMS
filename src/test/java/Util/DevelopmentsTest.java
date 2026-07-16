package Util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class DevelopmentsTest {

	@Test
	void compareWithNoChangesProducesNoLogs() {
		Developments oldDev = new Developments();
		Developments newDev = new Developments();
		ArrayList<Log> logs = oldDev.compare(newDev, "tester");
		assertTrue(logs.isEmpty());
	}

	@Test
	void compareLogsExactlyTheChangedField() {
		Developments oldDev = new Developments();
		oldDev.setCost(10.0);
		Developments newDev = new Developments();
		newDev.setCost(20.0);

		ArrayList<Log> logs = oldDev.compare(newDev, "tester");

		assertEquals(1, logs.size());
		assertEquals("tester", logs.get(0).getName());
		assertEquals("Updated Cost from 10.0 to 20.0", logs.get(0).getContent());
	}

	@Test
	void compareIgnoresTimestampAndIdFields() {
		Developments oldDev = new Developments();
		oldDev.setDev_id(1);
		oldDev.setDateTime("2024-01-01T00:00:00");
		oldDev.setLastModified("2024-01-01T00:00:00");
		oldDev.setDateCurrentPhase("2024-01-01T00:00:00");

		Developments newDev = new Developments();
		newDev.setDev_id(2);
		newDev.setDateTime("2025-06-01T00:00:00");
		newDev.setLastModified("2025-06-01T00:00:00");
		newDev.setDateCurrentPhase("2025-06-01T00:00:00");

		ArrayList<Log> logs = oldDev.compare(newDev, "tester");
		assertTrue(logs.isEmpty(), "dev_id/dateTime/LastModified/DateCurrentPhase must not be diffed");
	}

	@Test
	void checkPhaseReturnsTimestampWhenStrikeOffStatusChanges() {
		Developments oldDev = new Developments();
		oldDev.setStrike_off_status("Pending");
		Developments newDev = new Developments();
		newDev.setStrike_off_status("Done");

		assertFalse(oldDev.checkPhase(newDev).isEmpty());
	}

	@Test
	void checkPhaseReturnsEmptyWhenTrackedStatusesUnchanged() {
		Developments oldDev = new Developments();
		oldDev.setStrike_off_status("Pending");
		Developments newDev = new Developments();
		newDev.setStrike_off_status("Pending");

		assertEquals("", oldDev.checkPhase(newDev));
	}

	@Test
	void checkPhaseIgnoresUnrelatedFieldChanges() {
		Developments oldDev = new Developments();
		Developments newDev = new Developments();
		newDev.setCost(500.0);

		assertEquals("", oldDev.checkPhase(newDev));
	}
}
