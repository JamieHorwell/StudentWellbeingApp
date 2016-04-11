package team10.studentwellbeingapp;

import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.CameraUpdateFactory;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.util.Log;


import java.io.IOException;

public class MapsTestingActivity extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private Marker[] markers;
    private final int MaxMarkers = 15;
    String placesSearchStr;
    private MarkerOptions[] places;
    private Double usersLat;
    private Double usersLng;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_testing);
        markers = new Marker[MaxMarkers];


       
        //placesSearchStr = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-33.8670522,151.1957362&radius=500&type=restaurant&name=cruise&key=AIzaSyCUHT_xAneCI6EG7nGqsqQt_AbDPNV7Lzk";

        setUpMapIfNeeded();
        testMarker(testGeocodeMatches());
        placesSearchStr = "https://maps.googleapis.com/maps/api/place/nearbysearch/" +
                "json?key=AIzaSyCUHT_xAneCI6EG7nGqsqQt_AbDPNV7Lzk&location="+usersLat.toString()+","+usersLng.toString()+
                "&radius=3000" +
                "&keyword=counselling";
        if (mMap != null) {
            mMap.setMyLocationEnabled(true);
        }
        new getPlaces().execute(placesSearchStr);

    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }
            private void testMarker(double[] coords) {
                LatLng testMarker = new LatLng(coords[0], coords[1]);
                Marker testmarker = mMap.addMarker(new MarkerOptions()
                                .position(testMarker).title("test Marker").snippet("this is a test")
                );


                CameraUpdate center=
                        CameraUpdateFactory.newLatLng(testMarker);
                CameraUpdate zoom=CameraUpdateFactory.zoomTo(15);

                mMap.moveCamera(center);
                mMap.animateCamera(zoom);

            }





        private double[] testGeocodeMatches() {

            List<Address> geocodeMatches = null;

            try {
                geocodeMatches =
                        new Geocoder(this).getFromLocationName(
                                "Newcastle University", 5);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            if (!geocodeMatches.isEmpty()) {
                usersLat = geocodeMatches.get(0).getLatitude();
                usersLng = geocodeMatches.get(0).getLongitude();
                return new double[] {geocodeMatches.get(0).getLatitude(), geocodeMatches.get(0).getLongitude()};
            }
            else {
                return null;
            }
        }
    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {

    }

    class getPlaces extends AsyncTask<String, Void, String> {
        JSONArray jsonArray = null;

        protected String doInBackground(String... placesURL) {
            StringBuilder placesBuilder = new StringBuilder();


            for(String placeSearhURL: placesURL) {
                try {
                    URL url = new URL(placeSearhURL);


                try {
                    //open connection
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.connect();

                    InputStream is = conn.getInputStream();
                    BufferedReader streamReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                    StringBuilder respondeStrBuilder = new StringBuilder();

                    String inputStr;
                    while ((inputStr = streamReader.readLine()) != null) {
                       Log.w("mapdebugging", inputStr);
                        respondeStrBuilder.append(inputStr);
                    }
                    placesBuilder = respondeStrBuilder;

                }
                catch (java.io.IOException e) {

                }
                }
                catch (MalformedURLException e) {}
            }
            return placesBuilder.toString();

        }

        protected void onPostExecute(String result) {
             if(markers!=null) {
                 for(int pm=0; pm<markers.length; pm++) {
                     if(markers[pm] != null) {
                         markers[pm].remove();
                     }
                 }
             }

            try {
                    JSONObject resultObject = new JSONObject(result);
                    JSONArray placesArray = resultObject.getJSONArray("results");
                    places = new MarkerOptions[placesArray.length()];
                    for(int i = 0; i <placesArray.length(); i++) {
                       //parse each place
                        boolean missingValue = false;
                        LatLng placeLL = null;
                        String placeName="";
                        String vicinity="";
                        try {
                            JSONObject placeObject = placesArray.getJSONObject(i);
                            JSONObject loc = placeObject.getJSONObject("geometry").getJSONObject("location");
                            placeLL = new LatLng(Double.valueOf(loc.getString("lat")),Double.valueOf(loc.getString("lng")));
                            vicinity = placeObject.getString("vicinity");
                            placeName = placeObject.getString("name");
                        } catch(JSONException e) {
                            missingValue = true;
                        }
                        if (missingValue) {
                            places[i] = null;
                        }
                        else {
                            places[i]=new MarkerOptions()
                            .position(placeLL).title(placeName).snippet(vicinity);
                        }
                    }

                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                if(places!=null && markers!=null) {
                    for(int i = 0; i<places.length && i<markers.length; i++ ) {

                        if(places[i]!=null) {
                            markers[i]= mMap.addMarker(places[i]);
                        }
                    }
                }


        }
    }




}
