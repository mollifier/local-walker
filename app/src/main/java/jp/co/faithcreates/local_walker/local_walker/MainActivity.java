package jp.co.faithcreates.local_walker.local_walker;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

    private LocationManager locationManager;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setContentView(R.layout.activity_main);

        this.textView = new TextView(this);
        this.textView.setText("Test TextView");
        setContentView(this.textView, new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT));


        // GPS
        this.locationManager = (LocationManager)getSystemService(LOCATION_SERVICE);

        Criteria criteria = new Criteria();
        
        final String provider = locationManager.getBestProvider(criteria, true);

        final boolean gpsEnabled = this.locationManager.isProviderEnabled(provider);
        if (gpsEnabled) {
            this.textView.setText("GPS Enabled");

            this.locationManager.requestLocationUpdates(
                    provider,
                    10,
                    10,
                    new LocationListener() {
                        @Override
                        public void onLocationChanged(Location location) {
                            String text = "Lat=" + location.getLatitude()
                                    + "\nLng=" + location.getLongitude();
                            textView.setText(text);
                        }

                        @Override
                        public void onProviderDisabled(String provider) {
                            textView.setText("onProviderDisabled");
                        }

                        @Override
                        public void onProviderEnabled(String provider) {
                            textView.setText("onProviderEnabled");
                        }

                        @Override
                        public void onStatusChanged(String provider, int status, Bundle extras) {
                            textView.setText("onStatusChanged");
                        }
                    });


        } else {
            this.textView.setText("GPS Disabled");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
