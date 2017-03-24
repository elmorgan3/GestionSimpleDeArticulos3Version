package com.example.morga.gestionsimpledearticulos;

import android.app.Dialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.preference.PreferenceActivity;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;

import cz.msebera.android.httpclient.Header;

public class ListaCiudadActivity extends ListActivity {

    private static int ACTIVITY_CIUDAD_ADD = 1;
    private static int ACTIVITY_CIUDAD_SHOW = 2;

    private ArticuloDataSource bd;
    private long idActual;
    private SimpleCursorAdapter scCiudad;

    private static String[] from = new String[] { ArticuloDataSource.CIUDAD_NOMBRE };
    private static int[] to = new int[] { R.id.textViewNombre };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_ciudad);
        setTitle("Activity list of city");

        //Boton de añaadir
        Button btnAddCity = (Button)findViewById(R.id.buttonAddCity);
        btnAddCity.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AddCity();
            }
        });

        bd = new ArticuloDataSource(this);
        LoadCity();
    }

    private void LoadCity() {

        // Demanem totes les tasques
        Cursor cursorTasks = bd.Ciudades();

        // Now create a simple cursor adapter and set it to display
        scCiudad = new SimpleCursorAdapter(this, R.layout.row_lista_ciudad, cursorTasks, from, to, 1);
        setListAdapter(scCiudad);
    }

    private void RefreshCity() {

        // Demanem totes les tasques
        Cursor cursorTasks = bd.Ciudades();

        // Now create a simple cursor adapter and set it to display
        scCiudad.changeCursor(cursorTasks);
        scCiudad.notifyDataSetChanged();
    }

    private void AddCity() {
        // Cridem a l'activity del detall de la tasca enviant com a id -1
        Bundle bundle = new Bundle();
        bundle.putLong("id",-1);

        idActual = -1;

        Intent i = new Intent(this, AddCityActivity.class );
        i.putExtras(bundle);
        startActivityForResult(i, ACTIVITY_CIUDAD_ADD);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ACTIVITY_CIUDAD_ADD) {
            if (resultCode == RESULT_OK) {
                // Carreguem totes les tasques a lo bestia
                RefreshCity();
            }
        }



    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        //Miro la posicion en el cursor del elemento que me han clicado y le
        // digo que me guarde atributo que se llame "nombre"
        Cursor linia = (Cursor) scCiudad.getItem(position);
        String nombreCiudad = linia.getString(linia.getColumnIndexOrThrow("nombre"));

        //String nombreCiudad = ArticuloDataSource.CIUDAD_NOMBRE;
        //EnviarPeticion(nombreCiudad);

        Bundle bundle = new Bundle();
        bundle.putLong("id",id);
        bundle.putString("nombreCiudad", nombreCiudad);

        idActual = id;

        Intent i = new Intent(this, MostrarClimaActivity.class );
        i.putExtras(bundle);
        startActivityForResult(i,ACTIVITY_CIUDAD_SHOW);

    }



}
