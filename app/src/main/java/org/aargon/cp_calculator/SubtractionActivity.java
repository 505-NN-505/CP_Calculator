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

public class SubtractionActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_subtraction);

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
                double nu1, nu2;
                if (editTextn1.getText().toString().isEmpty()) {
                    nu1 = 0;
                    editTextn1.setText("0.00");
                }
                else {
                    nu1 = Double.parseDouble(editTextn1.getText().toString());
                }
                if (editTextn2.getText().toString().isEmpty()) {
                    nu2 = 0;
                    editTextn2.setText("0.00");
                }
                else {
                    nu2 = Double.parseDouble(editTextn2.getText().toString());
                }

                double res = nu1 - nu2;

                String str1 = "n1 - n2 = ";
                String str2 = String.format("%.2f", res);
                String str3 = str1 + str2;

                textViewResult.setText(str3);
            }
        });
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextn1.getText().clear();
                editTextn2.getText().clear();
                textViewResult.setText("");
            }
        });
    }
}