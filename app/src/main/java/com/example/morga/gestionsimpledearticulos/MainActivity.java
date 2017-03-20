package com.example.morga.gestionsimpledearticulos;

import android.app.Activity;
import android.os.Bundle;

import android.content.Intent;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity {

    private static final int ACTIVITY_SIMPLE = 0;
    private static final int ACTIVITY_FILTER = 1;
    private static final int ACTIVITY_TASK_ADD = 1;
    private static final int ACTIVITY_ICON = 2;
    private static final int ACTIVITY_MOVIMIENTOS= 3;
    private static final int ACTIVITY_CLIMA= 4;

    private long idActual;

    Button btnListaCompleta;
    Button btnListadoFiltro;
    Button btnCrear;
    Button btnListadoConBotones;
    Button btnMovimientos;
    Button btnClima;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnListaCompleta= (Button)findViewById(R.id.buttonListadoCompleto);

        btnCrear = (Button)findViewById(R.id.buttonCrearArticulo);
        btnListadoConBotones = (Button)findViewById(R.id.buttonListadoConBotones);
        btnMovimientos = (Button)findViewById(R.id.buttonMovimientos);
        btnClima = (Button)findViewById(R.id.buttonClima);
    }

    //******
    //Metodo para abrir la activity con el listado completo
    //******
    public void mostrarListaCompleta(View v)
    {
        Intent i = new Intent(this, ListaCompletaActivity.class);

        //startActivity(i);
        startActivityForResult(i, ACTIVITY_SIMPLE);
    }



    //******
    //Metodo para abrir la activity para crear articulo
    //******
    public void crearArticulo(View v)
    {

        // Cridem a l'activity del detall de la tasca enviant com a id -1
        Bundle bundle = new Bundle();
        bundle.putLong("id",-1);

        idActual = -1;

        Intent i = new Intent(this, CrearActivity.class );
        i.putExtras(bundle);
        startActivityForResult(i,ACTIVITY_TASK_ADD);
    }

    //******
    //Metodo para abrir la activity con el listado completo con iconos
    //******
    public void mostrarListadoConBotones (View v)
    {
        Intent i = new Intent(this, ListaConIconActivity.class);

        startActivityForResult(i, ACTIVITY_ICON);

    }

    //******
    //Metodo para abrir la activity con el listado completo con iconos
    //******
    public void mostrarListadoMovimientos (View v)
    {
        Intent i = new Intent(this, ListaMovimientosActivity.class);

        startActivityForResult(i, ACTIVITY_MOVIMIENTOS);

    }

    //******
    //Metodo para abrir la activity con el listado completo con iconos
    //******
    public void MostrarClima (View v)
    {
        Intent i = new Intent(this, ListaCiudadActivity.class);

        startActivityForResult(i, ACTIVITY_CLIMA);

    }

}



