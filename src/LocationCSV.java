import java.io.*;
import java.util.List;

/**
 * Created by jeff on 14/04/15.
 */
public class LocationCSV {

    private String csvFilename;

    public LocationCSV(String csvFilename) {
        this.csvFilename = csvFilename + ".csv";
    }


    /**
     * Generates a CSV based off of the List of LocationRows passed in.
     * File is overwritten if it already exists.
     * Note: if the List is empty, a header will still be generated.
     * @param locations
     */
    public void generateLocationCSV(List<LocationRow> locations) {

        if (locations == null) return;

        File file = new File(csvFilename);
        FileWriter fw = null;
        BufferedWriter bw = null;

        try {
            fw = new FileWriter(file);
            bw = new BufferedWriter(fw);

            bw.write(LocationRow.LOCATION_CSV_HEADER);

            if (locations.size() > 0) {
                bw.write("\n");

                for (LocationRow location : locations) {
                    bw.write(location.getCSVRow());
                    bw.write("\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
