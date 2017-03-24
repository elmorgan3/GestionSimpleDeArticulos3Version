package com.example.morga.gestionsimpledearticulos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;

import cz.msebera.android.httpclient.Header;



public class MostrarClimaActivity extends AppCompatActivity {

    TextView txtNombreCiudad;
    TextView txtDescripcion;
    TextView txtTemperaturaMaxima;
    TextView txtTemperaturaMinima;
    TextView txtHumedad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_clima);

        txtNombreCiudad = (TextView)findViewById(R.id.textViewCiudad);
        txtDescripcion = (TextView)findViewById(R.id.textViewDescripcion);
        txtTemperaturaMaxima = (TextView)findViewById(R.id.textViewTempMax);
        txtTemperaturaMinima  = (TextView)findViewById(R.id.textViewTempMin);
        txtHumedad = (TextView)findViewById(R.id.textViewHumedad);

        Bundle b = getIntent().getExtras();

        txtNombreCiudad.setText(b.getString("nombreCiudad"));

        EnviarPeticion(b.getString("nombreCiudad"));
    }

    //*****
    // Metodo para enviar el JSON con el nombre de la ciudad
    //*****
    private void EnviarPeticion (String nombreCiudad) {
        AsyncHttpClient client = new AsyncHttpClient();
        client.setMaxRetriesAndTimeout(0, 10000);

//
        client.addHeader("Authorization","73179fa82b8f7c26c84c23f64e858ce7");


        // Generamos la URL con el json generado
        String url = "http://api.openweathermap.org/data/2.5/weather?q=" + nombreCiudad + "&units=metric&APPID=73179fa82b8f7c26c84c23f64e858ce7";

        client.get(this,url, new AsyncHttpResponseHandler() {

            @Override
            public void onStart() {
                Toast.makeText(MostrarClimaActivity.this, "Cargando...", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                JSONObject temps = null;
                JSONArray arrayWeathers = new JSONArray();
                JSONObject weather = new JSONObject();
                JSONObject main = null;

                String descripcion = "";
                double temperaturaMax=0, temperaturaMin=0, humedad=0;
                int contador= 0;

                String str = new String(responseBody);

                try {
                    temps = new JSONObject(str);

                }
                catch (JSONException e) {
                    Toast.makeText(MostrarClimaActivity.this, "Error en la conexion comprueba la el acceso a internet", Toast.LENGTH_SHORT).show();
                }

                try {
                    if (temps != null) {
                        arrayWeathers = temps.getJSONArray("weather");
                        weather = arrayWeathers.getJSONObject(contador);
                        main = temps.getJSONObject("main");
                    }
                }
                catch (JSONException e) {
                    Toast.makeText(MostrarClimaActivity.this, "Error en la conexion comprueba la el acceso a internet", Toast.LENGTH_SHORT).show();
                }

                try {
                    descripcion = weather.getString("description");
                    temperaturaMax = main.getDouble("temp_max");
                    temperaturaMin = main.getDouble("temp_min");
                    humedad = main.getDouble("humidity");
                }
                catch(JSONException e){
                    Toast.makeText(MostrarClimaActivity.this, "Error en la conexion comprueba la el acceso a internet", Toast.LENGTH_SHORT).show();

                }

                txtDescripcion.setText(descripcion+"");
                txtHumedad.setText(humedad+"%");
                txtTemperaturaMaxima.setText(temperaturaMax+"ºC");
                txtTemperaturaMinima.setText(temperaturaMin+"ºC");



            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                Toast.makeText(MostrarClimaActivity.this, "Error al consultar el tiempo", Toast.LENGTH_SHORT).show();

                txtDescripcion.setText("Error");
                txtHumedad.setText("Error");
                txtTemperaturaMaxima.setText("Error");
                txtTemperaturaMinima.setText("Error");

            }
        });
    }
}
