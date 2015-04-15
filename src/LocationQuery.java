import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jeff on 13/04/15.
 *
 * Contains the parsed query information for a location.
 *
 * Note: We can generalize things some more if we are going to use other APIs by making an interface or an abstract
 * superclass (e.g. APIQuery).
 */
public class LocationQuery {

    private static final String BASE_URL = "http://api.goeuro.com/api/v2/position/suggest/en/";

    private List<LocationRow> locations;        // multiple locations are possible in one query.

    /**
     * Constructor method populates the locations ArrayList with all returned locations.
     * @param location
     */
    public LocationQuery(String location) {
        locations = new ArrayList<LocationRow>();

        if (location != null && location.length() > 0) {
            queryLocation(location);
        }

    }

    public List<LocationRow> getLocationResults() {
        return locations;
    }

    private void queryLocation(String location) {

        String jsonResult = null;

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        // connect to the API URL and read the stream into a variable.
        try {
            URL url = new URL(BASE_URL + location);

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // no data
                return;
            }

            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                // empty stream
                return;
            }

            jsonResult = buffer.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        parseJson(jsonResult);

    }

    /**
     * parse the JSON data and add LocationRows to the locations arrayList.
     * If the passed in JSON is empty or null, the locations ArrayList will be null.
     * @param jsonResult
     */
    private void parseJson(String jsonResult) {
        if (jsonResult == null || jsonResult == "") {
            return;
        }

        final String _ID = "_id";
        final String NAME = "name";
        final String TYPE = "type";
        final String GEO_POSITION = "geo_position";
        final String LONGITUDE = "longitude";
        final String LATITUDE = "latitude";

        try {
            JSONArray locationsArray = new JSONArray(jsonResult);

            for (int i = 0; i < locationsArray.length(); ++i) {
                JSONObject locationJson = locationsArray.getJSONObject(i);

                int _id = locationJson.getInt(_ID);
                String name = locationJson.getString(NAME);
                String type = locationJson.getString(TYPE);
                double longitude = locationJson.getJSONObject(GEO_POSITION).getDouble(LONGITUDE);
                double latitude = locationJson.getJSONObject(GEO_POSITION).getDouble(LATITUDE);

                locations.add(new LocationRow(_id, name, type, longitude, latitude));
            }

        }
        catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
