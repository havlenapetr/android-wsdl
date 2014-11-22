package cz.havlena.wsdl;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import com.cdyne.ws.weatherws.*;

public class AndroidWSTestAppActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        new TestTask().execute(new Weather().getWeatherSoap());
    }

    private static class TestTask extends AsyncTask<WeatherSoap, Void, Void> {

        @Override
        protected Void doInBackground(WeatherSoap... weatherSoap) {
            ArrayOfWeatherDescription result = weatherSoap[0].getWeatherInformation();
            for (WeatherDescription weatherDescription : result.getWeatherDescription()) {
                System.out.println(weatherDescription.toString());
            }
            return null;
        }
    }
}
