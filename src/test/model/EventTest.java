/* CITATION: The code was modelled after AlarmSystem 
 * initialled commited by Ali Madooei of UBC CS on Oct 14, 2018.
 * https://github.students.cs.ubc.ca/CPSC210/AlarmSystem.git
 */

package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the Event class
 */
public class EventTest {
    private Event event;
    private Date date;

    // NOTE: these tests might fail if time at which line (2) below is executed
    // is different from time that line (1) is executed. Lines (1) and (2) must
    // run in same millisecond for this test to make sense and pass.

    @BeforeEach
    public void runBefore() {
        event = new Event("Stocks sold"); // (1)
        date = Calendar.getInstance().getTime(); // (2)
    }

    @Test
    public void testEvent() {
        assertEquals("Stocks sold", event.getDescription());
        assertEquals(date, event.getDate());
    }

    @Test
    public void testToString() {
        assertEquals(date.toString() + "\n" + "Stocks sold", event.toString());
    }
}
