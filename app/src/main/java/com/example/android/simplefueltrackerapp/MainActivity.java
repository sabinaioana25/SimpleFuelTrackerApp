package com.example.android.simplefueltrackerapp;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.android.simplefueltrackerapp.FuelTrackerContract.FuelDbHelper;

import static com.example.android.simplefueltrackerapp.FuelTrackerContract.FuelContract.FuelEntry;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FuelDbHelper dbHelper = new FuelDbHelper(this);

        // Insert fuel1
        dbHelper.insertFuel(36, 112, 236, "20-06-2017");

        // Insert fuel2
        dbHelper.insertFuel(32, 115, 695, "31-06-2017");

        // Reading from database and printing the info in logs
        Cursor cursor = dbHelper.readFuelHabit();
        try {
            int idColumnIndex = cursor.getColumnIndex(FuelEntry._ID);
            int litresColumnIndex = cursor.getColumnIndex(FuelEntry.COLUMN_FUEL_LITRES);
            int priceColumnIndex = cursor.getColumnIndex(FuelEntry.COLUMN_FUEL_PRICE);
            int mileageColumnIndex = cursor.getColumnIndex(FuelEntry.COLUMN_FUEL_MILEAGE);
            int dateColumnIndex = cursor.getColumnIndex(FuelEntry.COLUMN_FUEL_DATE);

            Log.d(LOG_TAG, " ID | FUEL | PRICE | MILEAGE | DATE ");
            while (cursor.moveToNext()) {
                int id = cursor.getInt(idColumnIndex);
                int litres = cursor.getInt(litresColumnIndex);
                int price = cursor.getInt(priceColumnIndex);
                int mileage = cursor.getInt(mileageColumnIndex);
                String date = cursor.getString(dateColumnIndex);

                Log.d(LOG_TAG, id + " " + litres + " " + price + " " + mileage + " " + date);
            }
        } finally {
            cursor.close();
        }
    }
}
