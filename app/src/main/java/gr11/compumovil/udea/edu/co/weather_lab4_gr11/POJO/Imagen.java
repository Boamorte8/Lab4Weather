package gr11.compumovil.udea.edu.co.weather_lab4_gr11.POJO;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by sala__000 on 4/05/2016.
 */
public class Imagen extends AsyncTask<URL, Integer, Long> {

    public Bitmap descargarImagen(String imageHttpAddress) {
        URL imageUrl = null;
        Bitmap imagen = null;
        try {
            imageUrl = new URL(imageHttpAddress);
            HttpURLConnection conn = (HttpURLConnection) imageUrl.openConnection();
            conn.connect();

            imagen = BitmapFactory.decodeStream(conn.getInputStream());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return imagen;
    }

    @Override
    protected Long doInBackground(URL... params) {
        return null;
    }
}
