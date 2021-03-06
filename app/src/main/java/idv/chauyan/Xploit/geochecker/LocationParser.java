package idv.chauyan.Xploit.geochecker;

import android.os.AsyncTask;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by ChauyanWang on 7/10/14.
 */
public class LocationParser {

    private double lat;
    private double lng;

    public class address_components {
        public String long_name;
        public String short_name;
        public ArrayList<String> types;
    }

    public class location {
        public String lat;
        public String lng;
    }

    public class northeast {
        public String lat;
        public String lng;
    }

    public class southwest {
        public String lat;
        public String lng;
    }

    public class viewport {
        public northeast NEposition;
        public southwest SWposition;
    }

    public class bounds {
        public northeast NEposition;
        public southwest SWposition;

    }

    public class geometry {
        public location locData;
        public String location_type;
        public bounds boundsData;
        public viewport viewportData;
    }

    public class addressGoup {
        public ArrayList<address_components> addresList;
        public String formatted_address;
        public geometry geoData;
        public ArrayList<String> typesList;
    }

    public class resultGroup {
        public ArrayList<addressGoup> addressGroupList;
        public String status;
    }

    private resultGroup results;

    public LocationParser(LatLng latlng) {
        lat = latlng.latitude;
        lng = latlng.longitude;
    }

    public void doParse() {
        new parseTask().execute();
    }

    class parseTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {

            try {
                URL url = new URL(
                        "http://maps.googleapis.com/maps/api/geocode/json?latlng=" + lat + ","
                                + lng + "&sensor=true");
                final Gson gson = new GsonBuilder().create();
                results = gson.fromJson(
                        new InputStreamReader(url.openStream()), resultGroup.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            /**
             * here you could use results to do anything.
             */
        }
    }
}
