package edu.student.midlandstech.anthonydmolnar.currency;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private CurrencyDB db;
    protected static Currency fromCurrency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        db = new CurrencyDB(this);
        ArrayList<Currency> list = db.selectAll();
        fromCurrency = list.get(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add) {
            // Open the InsertActivity window
            Intent addActivity = new Intent(this, InsertActivity.class);
            this.startActivity(addActivity);
            return true;
        }
        if (id == R.id.action_edit) {
            // Open the UpdateActivity window
            Intent editActivity = new Intent(this, UpdateActivity.class);
            this.startActivity(editActivity);
            return true;
        }
        if (id == R.id.action_from) {
            // Open the FromActivity window
            Intent fromActivity = new Intent(this, InsertActivity.class);
            this.startActivity(fromActivity);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void convert(View v) {
        DecimalFormat df = new DecimalFormat("#,###.0");
        TextView answer = findViewById(R.id.answer);
        EditText amountStr = findViewById(R.id.etAmount);
        double amount = Double.parseDouble(amountStr.getText().toString());
        double result = fromCurrency.toDollar(amount);
        answer.setText(df.format(amount) + fromCurrency.getCurrency() + " is "
                       + df.format(result) + "USD");
    }
}
