/**
 * Created by jeff on 13/04/15.
 *
 * Represents a LocationRow for CSV output.
 */
public class LocationRow {

    public static final String LOCATION_CSV_HEADER = "_id,name,type,latitude,longitude";

    private int _id;
    private String name;
    private String type;
    private double latitude;
    private double longitude;

    public LocationRow(int _id, String name, String type, double latitude, double longitude) {
        this._id = _id;
        this.name = name;
        this.type = type;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;

        LocationRow other = (LocationRow) obj;

        if (this.get_id() != other.get_id() ||
                !this.getName().equals(other.getName()) ||
                !this.getType().equals(other.getType()) ||
                this.getLatitude() != other.getLatitude() ||
                this.getLongitude() != other.getLongitude()) {

            return  false;
        }


        return true;

    }

    /**
     * Generate a csv entry for the LocationRow.
     * null strings replaced with "null".
     * @return
     */
    public String getCSVRow() {

        StringBuilder sb = new StringBuilder();
        sb.append(_id).append(",")
                .append((name != null) ? name : "null").append(",")
                .append((type != null) ? type : "null").append(",")
                .append(latitude).append(",")
                .append(longitude);

        return sb.toString();
    }
}
