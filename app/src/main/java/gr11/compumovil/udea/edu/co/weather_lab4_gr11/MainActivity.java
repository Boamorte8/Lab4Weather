package gr11.compumovil.udea.edu.co.weather_lab4_gr11;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import gr11.compumovil.udea.edu.co.weather_lab4_gr11.POJO.Imagen;
import gr11.compumovil.udea.edu.co.weather_lab4_gr11.POJO.Model;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static android.widget.Toast.*;

public class MainActivity extends AppCompatActivity {

    TextView city, status, humidity, temperature;
    AutoCompleteTextView ciudad;
    ImageView icono;
    String url = "http://api.openweathermap.org/data/2.5";
    Imagen imagen = new Imagen();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ciudad = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        city = (TextView) findViewById(R.id.txt_city);
        status = (TextView) findViewById(R.id.txt_status);
        humidity = (TextView) findViewById(R.id.txt_humidity);
        temperature = (TextView) findViewById(R.id.txt_temperature);
        icono = (ImageView) findViewById(R.id.imageView);
    }



    public void onClick(View v) {

        //making object of RestAdapter
        RestAdapter adapter = new RestAdapter.Builder().setEndpoint(url).build();

        //Creating Rest Services
        RestInterface restInterface = adapter.create(RestInterface.class);

        System.out.println("Apretando el boton");
        //Toast.makeText(this, "Esta haciendo algo el boton", Toast.LENGTH_LONG).show();
        //Calling method to get whether report
        restInterface.getWheatherReport(ciudad.getText().toString(), "4b0c8611aa62583a27da23cb3acd704f", new Callback<Model>() {
            @Override
            public void success(Model model, Response response) {
                String nombreIcono = model.getWeather().get(0).getIcon();
                String urlbase = "http://openweathermap.org/img/w/";
                city.setText("Ciudad :" + model.getName());
                temperature.setText("Temperatura :" + model.getMain().getTemp().toString());
                humidity.setText("Humedad :" + model.getMain().getHumidity().toString());
                status.setText("Descripción :" + model.getWeather().get(0).getDescription());
                icono.setImageBitmap(imagen.descargarImagen(urlbase + nombreIcono + ".png"));
                System.out.println("Nombre" + model.getName());
            }

            @Override
            public void failure(RetrofitError error) {

                String merror = error.getMessage();
                System.out.println("El error es" +merror);
            }
        });
    }
}
