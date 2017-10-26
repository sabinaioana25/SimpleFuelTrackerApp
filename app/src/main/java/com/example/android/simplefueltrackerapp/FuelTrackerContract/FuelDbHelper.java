package com.example.android.simplefueltrackerapp.FuelTrackerContract;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.android.simplefueltrackerapp.FuelTrackerContract.FuelContract.FuelEntry;

/**
 * Created by Sabina on 10/26/2017.
 */

public class FuelDbHelper extends SQLiteOpenHelper {

    private static final String LOG_TAG = FuelDbHelper.class.getSimpleName();
    private static final String DATABASE_NAME = "fuel.db";
    private static final int DATABASE_VERSION = 1;

    public FuelDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db ) {
        String SQL_CREATE_FUEL_TABLE = "CREATE TABLE " + FuelEntry.TABLE_NAME +
                " ( " + FuelEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                FuelEntry.COLUMN_FUEL_LITRES + " INTEGER NOT NULL, " +
                FuelEntry.COLUMN_FUEL_PRICE + " INTEGER NOT NULL DEFAULT 0, " +
                FuelEntry.COLUMN_FUEL_MILEAGE + " INTEGER NOT NULL DEFAULT 0, " +
                FuelEntry.COLUMN_FUEL_DATE + " TEXT );";

        db.execSQL(SQL_CREATE_FUEL_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    /**
     * Method to insert a record of fuel in tracker database
     *
     * @param litres how much fuel was added
     * @param price  how much it cost per one litre
     * @param mileage at what mileage was the fuel added
     * @param date  when did this take place
     **/
    public void insertFuel (int litres, int price, int mileage, String date) {

        ContentValues values = new ContentValues();
        values.put(FuelEntry.COLUMN_FUEL_LITRES, litres);
        values.put(FuelEntry.COLUMN_FUEL_PRICE, price);
        values.put(FuelEntry.COLUMN_FUEL_MILEAGE, mileage);
        values.put(FuelEntry.COLUMN_FUEL_DATE, date);

        SQLiteDatabase writeableDatabase = getWritableDatabase();

        long summary = writeableDatabase.insert(FuelEntry.TABLE_NAME, null,  values);
        if (summary != -1) {
            Log.d(LOG_TAG,  "Insert row successful ID = " + summary);
        } else {
            Log.d(LOG_TAG, "Insert row unsuccessful");
        }
    }

    /**
     * Method to read and print(in Logs) records present in database.
     *
     * @return Cursor object
     */

    public Cursor readFuelHabit () {
        SQLiteDatabase readableDataBase = getReadableDatabase();

        String [] projection = {
                FuelEntry._ID,
                FuelEntry.COLUMN_FUEL_LITRES,
                FuelEntry.COLUMN_FUEL_PRICE,
                FuelEntry.COLUMN_FUEL_MILEAGE,
                FuelEntry.COLUMN_FUEL_DATE };

        Cursor cursor = readableDataBase.query(FuelEntry.TABLE_NAME, projection,  null, null, null, null, null);
        return cursor;
    }
}
