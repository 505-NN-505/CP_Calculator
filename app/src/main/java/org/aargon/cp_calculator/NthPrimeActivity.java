package org.aargon.cp_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class NthPrimeActivity extends AppCompatActivity {
    EditText editTextn1;
    TextView textViewResult;
    Button calculateButton, resetButton;

    ArrayList<Integer> Primes = new ArrayList<Integer>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nth_prime);
        editTextn1 = findViewById(R.id.editTextNumber1);
        textViewResult = findViewById(R.id.result);
        calculateButton = findViewById(R.id.buttonCalculate);
        resetButton = findViewById(R.id.buttonReset);

        SievePrime(100001);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer nu1;
                if (editTextn1.getText().toString().isEmpty()) {
                    nu1 = 1;
                    editTextn1.setText("0.00");
                }
                else {
                    nu1 = Integer.parseInt(editTextn1.getText().toString());
                }

                Integer res = Primes.get(nu1 - 1);

                String str1 = "Nth Prime = ";
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

    int isNonPrime(int prime[], int x) {
        return (prime[x / 64] & (1 << ((x >> 1) & 31)));
    }

    void makeComposite(int prime[], int x) {
        prime[x / 64] |= (1 << ((x >> 1) & 31));
    }

    void SievePrime(int n) {
        int prime[] = new int[n / 64 + 1];
        for (int i = 3; i * i <= n; i += 2) {
            if (isNonPrime(prime, i) == 0)
                for (int j = i * i, k = i << 1; j < n; j += k)
                    makeComposite(prime, j);
        }

        Primes.add(2);
        for (int i = 3; i <= n; i += 2)
            if (isNonPrime(prime, i) == 0)
                Primes.add(i);
    }
}