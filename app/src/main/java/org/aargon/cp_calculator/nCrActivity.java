package org.aargon.cp_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class nCrActivity extends AppCompatActivity {
    EditText editTextn1, editTextn2;
    TextView textViewResult;
    Button calculateButton, resetButton;

    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapterItems;

    SelectorClass selector = new SelectorClass();

    public void switchToActivity(String item) {
        Intent intent = new Intent(this, selector.activityHashMap.get(item));
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ncr);

        autoCompleteTextView = findViewById(R.id.auto_complete_text);
        adapterItems = new ArrayAdapter<String>(this, R.layout.list_item, selector.calculatorItems);

        autoCompleteTextView.setAdapter(adapterItems);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                switchToActivity(item);
            }
        });

        editTextn1 = findViewById(R.id.editTextNumber1);
        editTextn2 = findViewById(R.id.editTextNumber2);
        textViewResult = findViewById(R.id.result);
        calculateButton = findViewById(R.id.buttonCalculate);
        resetButton = findViewById(R.id.buttonReset);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Long nu1, nu2;
                if (editTextn1.getText().toString().isEmpty()) {
                    nu1 = 1l;
                    editTextn1.setText("1");
                }
                else {
                    nu1 = Long.parseLong(editTextn1.getText().toString());
                    if (nu1 < 0l) {
                        Toast.makeText(getApplicationContext(), "n can't be negative!", Toast.LENGTH_SHORT).show();
                        reset();
                        return;
                    }
                }
                if (editTextn2.getText().toString().isEmpty()) {
                    nu2 = 1l;
                    editTextn2.setText("1");
                }
                else {
                    nu2 = Long.parseLong(editTextn2.getText().toString());
                    if (nu2 < 0l) {
                        Toast.makeText(getApplicationContext(), "r can't be negative!", Toast.LENGTH_SHORT).show();
                        reset();
                        return;
                    }
                }

                Long res = nCr(nu1, nu2);

                String str1 = "nCr(n, r) = ";
                String str2 = String.format("%d", res);
                String str3 = str1 + str2;

                textViewResult.setText(str3);
            }
        });
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });
    }

    void reset() {
        editTextn1.getText().clear();
        editTextn2.getText().clear();
        textViewResult.setText("");
    }

    Long nCr(Long n, Long r) {
        Long c = 1l;
        for(Long i = 1l; i <= r; i++) c = ( c * (n - i +1l) / i);
        return c;
    }
}