package com.example.transportcostcomparator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TransportDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "transport.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "transport_records";
    public static final String COL_ID = "id";
    public static final String COL_MODE = "mode";
    public static final String COL_TRANSPORT_TYPE = "transport_type";
    public static final String COL_DISTANCE_PER_DAY = "distance_per_day";
    public static final String COL_COST_PER_KM = "cost_per_km";
    public static final String COL_TRAVEL_DAYS = "travel_days";
    public static final String COL_DAILY_COST = "daily_cost";
    public static final String COL_MONTHLY_COST = "monthly_cost";
    public static final String COL_MONTHLY_DISTANCE = "monthly_distance";

    public TransportDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_MODE + " TEXT, " +
                COL_TRANSPORT_TYPE + " TEXT, " +
                COL_DISTANCE_PER_DAY + " REAL, " +
                COL_COST_PER_KM + " REAL, " +
                COL_TRAVEL_DAYS + " INTEGER, " +
                COL_DAILY_COST + " REAL, " +
                COL_MONTHLY_COST + " REAL, " +
                COL_MONTHLY_DISTANCE + " REAL)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public long saveRecord(Transport transport, double dailyCost, double monthlyCost, double monthlyDistance) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_MODE, transport.getMode());
        values.put(COL_TRANSPORT_TYPE, transport.getTransportType());
        values.put(COL_DISTANCE_PER_DAY, transport.getDistancePerDay());
        values.put(COL_COST_PER_KM, transport.getCostPerKm());
        values.put(COL_TRAVEL_DAYS, transport.getTravelDaysPerMonth());
        values.put(COL_DAILY_COST, dailyCost);
        values.put(COL_MONTHLY_COST, monthlyCost);
        values.put(COL_MONTHLY_DISTANCE, monthlyDistance);
        long id = db.insert(TABLE_NAME, null, values);
        return id;
    }

    public Cursor getLatestRecord() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery(
                "SELECT * FROM " + TABLE_NAME + " ORDER BY " + COL_ID + " DESC LIMIT 1",
                null
        );
    }
}