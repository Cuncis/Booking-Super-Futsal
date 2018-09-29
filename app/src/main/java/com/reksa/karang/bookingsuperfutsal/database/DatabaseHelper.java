package com.reksa.karang.bookingsuperfutsal.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import com.reksa.karang.bookingsuperfutsal.model.Akun;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "akun.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "akun";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE akun (id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT NOT NULL, email TEXT NOT NULL, alamat TEXT NOT NULL, " +
                "jenis_kelamin TEXT NOT NULL, password TEXT NOT NULL)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS akun");
        onCreate(db);
    }

    public void insertAkun(Akun a) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", a.getUsername());
        contentValues.put("email", a.getEmail());
        contentValues.put("alamat", a.getAlamat());
        contentValues.put("jenis_kelamin", a.getJenisKelamin());
        contentValues.put("password", a.getPassword());

        db.insert(TABLE_NAME, null, contentValues);
    }

    public String searchPass(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT username, password FROM " + TABLE_NAME, null);

        String a, b;
        b = "not found";

        if (c.moveToFirst()) {
            do {
                a = c.getString(0);
                if (a.equals(username)) {
                    b = c.getString(1);
                    break;
                }
            } while (c.moveToNext());
        }
        c.close();

        return b;
    }

    public String getEmail(String user) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE username='" + user + "'", null);

        String a = null;

        if (c.moveToFirst()) {
            do {
                a = c.getString(2);
            } while (c.moveToNext());
        }

        c.close();

        return a;
    }
}























