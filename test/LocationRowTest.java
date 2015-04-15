import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LocationRowTest {

    private LocationRow lr;

    @Before
    public void setUp() throws Exception {
        lr = new LocationRow(1, "testLocation", "testType", 1.0, 2.0);
    }

    @Test
    public void testEquals_EqualObjects() throws Exception {
        LocationRow other = new LocationRow(1, "testLocation", "testType", 1.0, 2.0);
        assertTrue(lr.equals(other));
    }

    @Test
    public void testEquals_EqualNullObject() throws Exception {
        LocationRow other = null;
        assertFalse(lr.equals(other));
    }

    @Test
    public void testEquals_EqualDifferentObjectTypes() throws Exception {
        String other = "a string";
        assertFalse(lr.equals(other));
    }

    @Test
    public void testEquals_EqualUnequalObject() throws Exception {
        LocationRow other = new LocationRow(2, "testLocation2", "testType", 3.0, 2.0);
        assertFalse(lr.equals(other));
    }

    @Test
    public void testGetCSVRow() throws Exception {
        String rowOutput = "1,testLocation,testType,1.0,2.0";
        assertEquals(lr.getCSVRow(), rowOutput);
    }

    @Test
    public void testGetCSVRowWithNulls() throws Exception {
        lr.setName(null);
        lr.setType(null);
        String rowOutput = "1,null,null,1.0,2.0";
        assertEquals(lr.getCSVRow(), rowOutput);
    }
}