package org.aargon.cp_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MobiusActivity extends AppCompatActivity {
    EditText editTextn1;
    TextView textViewResult;
    Button calculateButton, resetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobius);
        editTextn1 = findViewById(R.id.editTextNumber1);
        textViewResult = findViewById(R.id.result);
        calculateButton = findViewById(R.id.buttonCalculate);
        resetButton = findViewById(R.id.buttonReset);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Long nu1;
                if (editTextn1.getText().toString().isEmpty()) {
                    nu1 = 1l;
                    editTextn1.setText("0.00");
                }
                else {
                    nu1 = Long.parseLong(editTextn1.getText().toString());
                }

                Integer res = mobius(nu1);

                String str1 = "µ(n) = ";
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

    Integer mobius(Long n) {
        int p = 0;
        if (n % 2 == 0) {
            n = n / 2;
            p++;
            if (n % 2 == 0)
                return 0;
        }

        for (int i = 3; i <= Math.sqrt(n); i = i + 2) {
            if (n % i == 0) {
                n = n / i;
                p++;
                if (n % i == 0)
                    return 0;
            }
        }

        return (p % 2 == 0)? -1 : 1;
    }
}