package edu.student.midlandstech.anthonydmolnar.currency;

import android.app.Activity;
import android.app.AppComponentFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class InsertActivity extends AppCompatActivity {
    private CurrencyDB db;
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
    }
    public void insert(View v) {
        EditText currencyET = findViewById(R.id.etCurrency);
        EditText rateET = findViewById(R.id.etRate);
        String currencyStr =  currencyET.getText().toString();
        double rate = Double.parseDouble(rateET.getText().toString());
        try {
            db.insertCurrency(currencyStr, rate);
            Toast.makeText(this, "Currency added", Toast.LENGTH_SHORT).show();
        } catch (NumberFormatException nfe) {
            Toast.makeText(this,"Error adding currency", Toast.LENGTH_LONG).show();
        }
        currencyET.setText("");
        rateET.setText("");
    }
}
