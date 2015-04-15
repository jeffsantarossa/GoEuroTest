import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class LocationQueryTest {

    private LocationQuery lq;

    @Test
    public void testGetLocationResultsEmptyLocation() throws Exception {
        lq = new LocationQuery("");
        assertEquals(lq.getLocationResults().size(), 0);
    }

    @Test
    public void testGetLocationResultsNullLocation() throws Exception {
        lq = new LocationQuery(null);
        assertEquals(lq.getLocationResults().size(), 0);
    }

    @Test
    public void testGetLocationResultsNoResultsLocation() throws Exception {
        lq = new LocationQuery("SimCity");
        assertEquals(lq.getLocationResults().size(), 0);
    }

    @Test
    public void testGetLocationResultsPotsdamLocation() throws Exception {
        lq = new LocationQuery("Potsdam");

        LocationRow potsdamGermany = new LocationRow(377078, "Potsdam", "location", 13.06566, 52.39886);
        LocationRow potsdamUS = new LocationRow(410978, "Potsdam", "location", -74.98131, 44.66978);

        boolean equalLocations = false;

        List<LocationRow> locations = lq.getLocationResults();

        if (locations.size() == 2 &&
                locations.get(0).equals(potsdamGermany) &&
                locations.get(1).equals(potsdamUS))
            equalLocations = true;

        assertTrue(equalLocations);
    }

}