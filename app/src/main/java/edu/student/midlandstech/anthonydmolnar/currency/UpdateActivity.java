package edu.student.midlandstech.anthonydmolnar.currency;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class UpdateActivity extends AppCompatActivity {
    CurrencyDB db;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new CurrencyDB(this);
        updateView();
    }

    public void updateView() {
        ArrayList<Currency> currencies = db.selectAll();
        if (currencies.size() > 0) {
            ScrollView scrollView = new ScrollView(this);
            GridLayout grid = new GridLayout(this);
            grid.setRowCount(currencies.size());
            grid.setColumnCount(4);
            TextView [] ids = new TextView[currencies.size()];
            EditText [][] namesAndRates = new EditText[currencies.size()][2];
            Button [] buttons = new Button[currencies.size()];
            ButtonHandler bh = new ButtonHandler();
            Point size = new Point();
            getWindowManager().getDefaultDisplay().getSize(size);
            int width = size.x;

            int i = 0;
            for (Currency currency : currencies) {
                ids[i] = new TextView(this);
                ids[i].setGravity(Gravity.CENTER);
                ids[i].setText("" + currency.getId());

                namesAndRates[i][0] = new EditText(this);
                namesAndRates[i][1] = new EditText(this);
                namesAndRates[i][0].setText(currency.getCurrency());
                namesAndRates[i][1].setText("" + currency.getRate());
                namesAndRates[i][1]
                  .setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                namesAndRates[i][0].setId(10 * currency.getId());
                namesAndRates[i][0].setId(10 * currency.getId() + 1);

                buttons[i] = new Button(this);
                buttons[i].setText("Update");
                buttons[i].setId(currency.getId());
                buttons[i].setOnClickListener(bh);

                grid.addView(ids[i], width / 10,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                grid.addView(namesAndRates[i][0], (int) (width * .4),
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                grid.addView(namesAndRates[i][1], (int) (width * 20),
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                grid.addView(buttons[i], (int) (width * 30),
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                i++;
            }
            scrollView.addView(grid);
            setContentView(scrollView);
        }
    }

    private class ButtonHandler implements View.OnClickListener {
        public void  onClick(View v) {
            int currencyId = v.getId();
            EditText nameET = (EditText) findViewById(10 * currencyId);
            EditText rateET = (EditText) findViewById(10 * currencyId + 1);
            String name = nameET.getText().toString();
            double rate = Double.parseDouble(rateET.getText().toString());
            try {
                db.updateCurrencyById(currencyId, name, rate);
                Toast.makeText(UpdateActivity.this, "Currency Updated",
                        Toast.LENGTH_SHORT).show();
                updateView();
            } catch (NumberFormatException nfe) {
                Toast.makeText(UpdateActivity.this, "Update Error",
                        Toast.LENGTH_LONG).show();
            }
        }
    }
}
