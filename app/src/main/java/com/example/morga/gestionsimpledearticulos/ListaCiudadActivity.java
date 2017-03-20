package com.example.morga.gestionsimpledearticulos;

import android.app.ListActivity;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ListaCiudadActivity extends ListActivity {

    private static int ACTIVITY_CIUDAD_ADD = 1;
    private static int ACTIVITY_CIUDAD_UPDATE = 2;

    private ArticuloDataSource bd;
    private SimpleCursorAdapter scCiudad;

    private static String[] from = new String[] { ArticuloDataSource.CIUDAD_NOMBRE };
    //private static int[] to = new int[]

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_ciudad);
    }
}
