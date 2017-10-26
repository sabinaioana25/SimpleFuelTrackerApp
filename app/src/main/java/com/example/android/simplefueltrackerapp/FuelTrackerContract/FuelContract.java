package com.example.android.simplefueltrackerapp.FuelTrackerContract;

import android.provider.BaseColumns;

/**
 * Created by Sabina on 10/26/2017.
 */

public class FuelContract {

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private FuelContract() {
    }

    public static abstract class FuelEntry implements BaseColumns {

        //Name of the table
        public static final String TABLE_NAME = "fuel";

        //ID of the habit
        public static final String _ID = BaseColumns._ID;

        // quantity of fuel
        public static final String COLUMN_FUEL_LITRES = "litres";

        // price of fuel (per litre)
        public static final String COLUMN_FUEL_PRICE = "price";

        // current mileage
        public static final String COLUMN_FUEL_MILEAGE = "mileage";

        // current date
        public static final String COLUMN_FUEL_DATE = "date";
    }
}
