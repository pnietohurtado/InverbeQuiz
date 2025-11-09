package com.example.inverbequiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class MiDBHelper extends SQLiteOpenHelper {

    public MiDBHelper(Context context){
        super(context, "MiBaseDatos", null ,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE ranking (id INTEGER PRIMARY KEY, nombre TEXT, score INT)");

        db.execSQL("INSERT INTO ranking (id, nombre, score) VALUES (1,'Bot', 4)");
        db.execSQL("INSERT INTO ranking (id, nombre, score) VALUES (2,'Bot2', 3)");
        db.execSQL("INSERT INTO ranking (id, nombre, score) VALUES (3, 'Bot3', 2)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS ranking");
        onCreate(db);

    }

    public void insertarNombre(String nombre, int score){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nombre", nombre);
        values.put("score", score);
        db.insert("ranking", null, values);
        db.close();
    }

    public ArrayList<String> obtenerTop3() {
        ArrayList<String> lista = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT nombre FROM ranking ORDER BY score DESC LIMIT 3", null);

        if (cursor.moveToFirst()) {
            do {
                lista.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return lista;
    }


}
