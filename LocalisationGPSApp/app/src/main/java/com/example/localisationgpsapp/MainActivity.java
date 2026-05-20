package com.example.localisationgpsapp;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private double latitude;
    private double longitude;
    private double altitude;
    private float accuracy;

    private TextView tvInfo;

    private RequestQueue requestQueue;

    // URL DU SERVEUR PHP (ÉMULATEUR ANDROID STUDIO)

    private String insertUrl =
            "http://10.0.2.2/localisation/createPosition.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvInfo = findViewById(R.id.tvInfo);

        // INITIALISATION VOLLEY

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        // LOCATION MANAGER

        LocationManager locationManager =
                (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        // VÉRIFICATION DES PERMISSIONS

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED

                &&

                ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(
                    this,
                    new String[]{

                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.READ_PHONE_STATE

                    },
                    1
            );

            return;
        }

        // ACTIVATION DE L'ÉCOUTE GPS

        locationManager.requestLocationUpdates(

                LocationManager.GPS_PROVIDER,

                60000,

                150,

                new LocationListener() {

                    @Override
                    public void onLocationChanged(@NonNull Location location) {

                        latitude = location.getLatitude();
                        longitude = location.getLongitude();
                        altitude = location.getAltitude();
                        accuracy = location.getAccuracy();

                        String message =
                                "Latitude : " + latitude
                                        + "\nLongitude : " + longitude
                                        + "\nAltitude : " + altitude
                                        + "\nPrécision : " + accuracy + " m";

                        // AFFICHAGE DANS LE TEXTVIEW

                        tvInfo.setText(message);

                        // TOAST

                        Toast.makeText(
                                getApplicationContext(),
                                message,
                                Toast.LENGTH_LONG
                        ).show();

                        // ENVOI AU SERVEUR

                        addPosition(latitude, longitude);
                    }

                    @Override
                    public void onStatusChanged(
                            String provider,
                            int status,
                            Bundle extras) {

                        String newStatus = "";

                        switch (status) {

                            case LocationProvider.OUT_OF_SERVICE:
                                newStatus = "OUT_OF_SERVICE";
                                break;

                            case LocationProvider.TEMPORARILY_UNAVAILABLE:
                                newStatus = "TEMPORARILY_UNAVAILABLE";
                                break;

                            case LocationProvider.AVAILABLE:
                                newStatus = "AVAILABLE";
                                break;
                        }

                        Toast.makeText(
                                getApplicationContext(),
                                "Statut du provider "
                                        + provider
                                        + " : "
                                        + newStatus,
                                Toast.LENGTH_SHORT
                        ).show();
                    }

                    @Override
                    public void onProviderEnabled(@NonNull String provider) {

                        Toast.makeText(
                                getApplicationContext(),
                                "Provider activé : " + provider,
                                Toast.LENGTH_SHORT
                        ).show();
                    }

                    @Override
                    public void onProviderDisabled(@NonNull String provider) {

                        Toast.makeText(
                                getApplicationContext(),
                                "Provider désactivé : " + provider,
                                Toast.LENGTH_SHORT
                        ).show();
                    }
                }
        );
    }

    // MÉTHODE D'ENVOI AU SERVEUR

    private void addPosition(final double lat, final double lon) {

        StringRequest request = new StringRequest(

                Request.Method.POST,

                insertUrl,

                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(
                                getApplicationContext(),
                                "SUCCÈS : " + response,
                                Toast.LENGTH_LONG
                        ).show();
                    }
                },

                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(
                                getApplicationContext(),
                                "ERREUR : " + error.toString(),
                                Toast.LENGTH_LONG
                        ).show();

                        error.printStackTrace();
                    }
                }

        ) {

            @Override
            protected Map<String, String> getParams()
                    throws AuthFailureError {

                Map<String, String> params = new HashMap<>();

                params.put("latitude", String.valueOf(lat));

                params.put("longitude", String.valueOf(lon));

                params.put(
                        "date_position",
                        new SimpleDateFormat(
                                "yyyy-MM-dd HH:mm:ss",
                                Locale.getDefault()
                        ).format(new Date())
                );

                params.put("imei", "emulator");

                return params;
            }
        };

        requestQueue.add(request);
    }
}