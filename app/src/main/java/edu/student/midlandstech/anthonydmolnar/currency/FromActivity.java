package edu.student.midlandstech.anthonydmolnar.currency;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class FromActivity extends AppCompatActivity {
    private CurrencyDB db;
    private ArrayList<Currency> list;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new CurrencyDB(this);
        list = db.selectAll();
        updateView();
    }
    public void updateView() {
        RelativeLayout layout = new RelativeLayout(this);
        ScrollView scrollView = new ScrollView(this);
        RadioGroup group = new RadioGroup(this);
        for (Currency c: list) {
            RadioButton rb = new RadioButton(this);
            rb.setId(c.getId());
            rb.setText(c.getCurrency());
            group.addView(rb);
        }
        RadioButtonHandler rbh = new RadioButtonHandler ();
        group.setOnCheckedChangeListener(rbh);
        Button backButton = new Button(this);
        backButton.setText("Back");
        backButton.setOnClickListener( new View.OnClickListener( ) {
            public void onClick(View v) {
                FromActivity.this.finish();  // this is inside an anonymous class
            }
        });
        scrollView.addView(group);
        layout.addView(scrollView);

        RelativeLayout.LayoutParams params
                = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        params.setMargins(0,0,0,50);
        setContentView(layout);
    }

    private class RadioButtonHandler
            implements  RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            for (Currency f: list) {
                if (f.getId()== checkedId) {
                    MainActivity.fromCurrency = f;
                }
            }
        }
    }
}
