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

public class SODActivity extends AppCompatActivity {
    EditText editTextn1;
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
        setContentView(R.layout.activity_sodactivity);

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
        textViewResult = findViewById(R.id.result);
        calculateButton = findViewById(R.id.buttonCalculate);
        resetButton = findViewById(R.id.buttonReset);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Long nu1;
                if (editTextn1.getText().toString().isEmpty()) {
                    nu1 = 0l;
                    editTextn1.setText("0.00");
                }
                else {
                    nu1 = Long.parseLong(editTextn1.getText().toString());
                }

                Long res = sumDivisors(nu1);

                String str1 = "Sum of Divisiors(n) = ";
                String str2 = String.format("%d", res);
                String str3 = str1 + str2;

                textViewResult.setText(str3);
            }
        });
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextn1.getText().clear();
                textViewResult.setText("");
            }
        });
    }

    Long sumDivisors(Long num)  {
        Long result = 0L;
        for (Long i = 1L; i <= Math.sqrt(num); i++)  {
            if (num % i == 0) {
                if (i == (num / i))
                    result += i;
                else
                    result += (i + num / i);
                System.out.println(i + " " + num / i);
            }
        }
        return result;
    }
}