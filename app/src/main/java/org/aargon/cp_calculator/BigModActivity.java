package org.aargon.cp_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BigModActivity extends AppCompatActivity {
    EditText editTextn1, editTextn2, editTextn3;
    TextView textViewResult;
    Button calculateButton, resetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_mod);

        editTextn1 = findViewById(R.id.editTextNumber1);
        editTextn2 = findViewById(R.id.editTextNumber2);
        editTextn3 = findViewById(R.id.editTextNumber3);

        textViewResult = findViewById(R.id.result);
        calculateButton = findViewById(R.id.buttonCalculate);
        resetButton = findViewById(R.id.buttonReset);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nu1 = "0", nu2 = "0";
                Long nu3 = 1l;
                if (editTextn1.getText().toString().isEmpty()) {
                    editTextn1.setText("0");
                }
                else {
                    nu1 = editTextn1.getText().toString();
                }
                if (editTextn2.getText().toString().isEmpty()) {
                    editTextn2.setText("0");
                }
                else {
                    nu2 = editTextn2.getText().toString();
                }
                if (editTextn3.getText().toString().isEmpty()) {
                    editTextn3.setText("1");
                }
                else {
                    nu3 = Long.parseLong(editTextn3.getText().toString());
                    if (nu3 == 0l) {
                        Toast.makeText(getApplicationContext(), "Modulo must be nonzero!", Toast.LENGTH_SHORT).show();
                        reset();
                        return;
                    }
                }

                Long res = ApowBmodM(nu1, nu2, nu3);

                String str1 = "big_mod(a, b, m) = ";
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
        editTextn3.getText().clear();
        textViewResult.setText("");
    }

    Long aModM(String s, Long mod)
    {
        Long number = 0l;
        for (Integer i = 0; i < s.length(); i++) {
            number = (number*10l + (Character.getNumericValue(s.charAt(i))));
            number %= mod;
        }
        return number;
    }

    Long ApowBmodM(String a, String b,Long m)
    {
        Long res=1l;
        Long x = aModM(a,m);
        Long y = aModM(b,m);
        while(y>0) {
            if(y%2 == 1)
                res=(res*x)%m;
            y=y>>1;
            x=((x%m)*(x%m))%m;
        }
        return (res%m+m)%m;
    }
}