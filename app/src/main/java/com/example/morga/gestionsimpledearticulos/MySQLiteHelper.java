package com.example.morga.gestionsimpledearticulos;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by morga on 19/01/2017.
 */

public class MySQLiteHelper extends SQLiteOpenHelper
{
    // Ponemos el nombre de la base de datos
    private static final String DATABASE_NAME = "ArticulosDB";

    // La version de la base de datos
    private static final int DATABASE_VERSION = 3;

    // Campos de la base de datos de ARTICULOS
    String sqlCreateArticulos = "CREATE TABLE articulos ( _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "codigo TEXT , " +
            "descripcion TEXT NOT NULL, " +
            "pvp REAL NOT NULL, " +
            "estoque REAL )";

    // Campos de la base de datos de MOVIMIENTOS
    String sqlCreateMovimientos = "CREATE TABLE movimientos ( _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "codigo TEXT , " +
            "dia TEXT , " +
            "cantidad INTEGER , " +
            "tipo TEXT )";

    // Campos de la base de datos de CIUDADES
    String sqlCreateCiudades = "CREATE TABLE ciudades ( _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "nombre TEXT )";

    // Hago los inserts de las ciudades que piden por defecto
    String sqlInsertBarcelona = "INSERT INTO ciudades (nombre)" +
            "VALUES ('Barcelona')";
    String sqlInsertLleida = "INSERT INTO ciudades (nombre)" +
            "VALUES ('Girona')";;
    String sqlInsertGirona = "INSERT INTO ciudades (nombre)" +
            "VALUES ('Lleida')";
    String sqlInsertTarragona = "INSERT INTO ciudades (nombre)" +
            "VALUES ('Tarragona')";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    //*********
    //Funcion para hacer el CREATE de la base de datos
    //*********
    @Override
    public void onCreate(SQLiteDatabase db) {

        // //query de creacion de la base de datos con las tablas
        db.execSQL(sqlCreateArticulos);
        db.execSQL(sqlCreateMovimientos);
        db.execSQL(sqlCreateCiudades);

        // Hago los insert de los monumentos que habra en la base de datos
        db.execSQL(sqlInsertBarcelona);
        db.execSQL(sqlInsertLleida);
        db.execSQL(sqlInsertGirona);
        db.execSQL(sqlInsertTarragona);


    }

    //*********
    //Funcion para hacer el UPGADE de la base de datos
    //*********
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // La actualizacion de la segunda version
        if ( oldVersion < 2) {
            db.execSQL(sqlCreateMovimientos);
        }

        // La actualizacion de la tercera version
        if (oldVersion < 3) {

            db.execSQL(sqlCreateCiudades);

            // Hago los insert de los monumentos que habra en la base de datos
            db.execSQL(sqlInsertBarcelona);
            db.execSQL(sqlInsertLleida);
            db.execSQL(sqlInsertGirona);
            db.execSQL(sqlInsertTarragona);
        }



    }


}
