/**
 * Created by jeff on 14/04/15.
 */
public class GoEuroTest {
    public static void main(String[] args) {
        if (args.length < 1 || args == null) return;

        for (String arg : args) {
            LocationQuery lq = new LocationQuery(arg);
            LocationCSV lcsv = new LocationCSV(arg);
            lcsv.generateLocationCSV(lq.getLocationResults());
        }
    }
}
