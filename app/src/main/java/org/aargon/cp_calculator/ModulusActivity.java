package org.aargon.cp_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ModulusActivity extends AppCompatActivity {
    EditText editTextn1, editTextn2;
    TextView textViewResult;
    Button calculateButton, resetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modulus);

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
                    nu1 = 1;
                    editTextn1.setText("0.00");
                }
                else {
                    nu1 = Double.parseDouble(editTextn1.getText().toString());
                }
                if (editTextn2.getText().toString().isEmpty()) {
                    nu2 = 1;
                    editTextn2.setText("0.00");
                }
                else {
                    nu2 = Double.parseDouble(editTextn2.getText().toString());
                    if (nu2 == 0.00000) {
                        Toast.makeText(getApplicationContext(), "Denominator must be nonzero!", Toast.LENGTH_SHORT).show();
                        reset();
                        return;
                    }
                }

                double res = nu1 % nu2;

                String str1 = "n1 % n2 = ";
                String str2 = String.format("%.2f", res);
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
}