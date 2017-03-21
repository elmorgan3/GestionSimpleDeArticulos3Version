package com.example.morga.gestionsimpledearticulos;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

import org.w3c.dom.Text;

public class AddCityActivity extends Activity {

    private long idCity;
    private ArticuloDataSource bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_city);

        bd = new ArticuloDataSource(this);

        // Botones de aceptar y cancelar
        // Boton ok
        Button btnAddCity = (Button) findViewById(R.id.buttonAddCity);
        btnAddCity.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AddCity();
            }
        });
    }

    private void AddCity() {
        //Validamos los datos
        TextView tv;

        tv = (TextView) findViewById(R.id.editTextNameCity);
        String nameCity = tv.getText().toString();

        if (nameCity.isEmpty()) {
            MyDialog.showToast(this,"Hay que poner el nombre de la ciudad");
            return;
        }

        idCity = bd.InsertCiudad(nameCity);

        Intent mIntent = new Intent();
        mIntent.putExtra("id", idCity);
        setResult(RESULT_OK, mIntent);

        finish();

    }
}
