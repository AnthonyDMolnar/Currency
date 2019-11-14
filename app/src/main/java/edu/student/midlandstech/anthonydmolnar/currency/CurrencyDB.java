package edu.student.midlandstech.anthonydmolnar.currency;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class CurrencyDB extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "currencyDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_CURRENCY = "table_currency";
    private static final String ID = "id";
    private static final String CURRENCY = "currency";
    private static final String RATE = "rate";
    public static final String QUERY1 = "create table table_currency ( id integer primary key autoincrement, currency text, rate real)";
    public static final String QUERY2 = "insert into table_currency (currency, rate) values ('euro', 1.10)";
    public CurrencyDB (Context context) {super(context, DATABASE_NAME, null, DATABASE_VERSION); }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(QUERY1);
        sqLiteDatabase.execSQL(QUERY2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("drop table if exists table_currency;");
        onCreate(sqLiteDatabase);
    }

    public void insertCurrency(String currency, double rate) {
        String query = "insert into table_currency (currency, rate) values ('"
             + currency +  "' ," + rate + ");";
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(query);
        Log.w("MainActivity", query);
    }

    public void updateCurrencyById(int id, String currency, double rate) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "update " + TABLE_CURRENCY;
        query += " set " + CURRENCY + " = '" + currency + "' ";
        query += RATE + " = '" + rate + "', ";
        query += " where " + ID + " = " + id;
        db.execSQL(query);
        db.close();

    }
    public ArrayList<Currency> selectAll() {
        String sqlQuery = "select * from table_currency";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);
        ArrayList<Currency> currencies = new ArrayList<>();
        while (cursor.moveToNext()) {
            Currency currentCurrency
                    = new Currency(Integer.parseInt( cursor.getString(0)),
                    cursor.getString(1), cursor.getDouble(2));
            currencies.add(currentCurrency);
        }
        db.close();
        return currencies;
    }
}
