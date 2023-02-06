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

public class ModuloInverseActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_modulo_inverse);

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
                }
                if (editTextn2.getText().toString().isEmpty()) {
                    nu2 = 1l;
                    editTextn2.setText("1");
                }
                else {
                    nu2 = Long.parseLong(editTextn2.getText().toString());
                    if (nu2 == 0l) {
                        Toast.makeText(getApplicationContext(), "Modulo must be nonzero!", Toast.LENGTH_SHORT).show();
                        reset();
                        return;
                    }
                }

                if (gcd(nu1, nu2) != 1) {
                    textViewResult.setText(nu1 + " has no inverse under mod " + nu2);
                    return;
                }

                Long res = modInverse(nu1, nu2);

                String str1 = "modulo_inverse(a, m) = ";
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

    Long modInverse(Long a, Long m) {
        Long m0 = m;
        Long y = 0l, x = 1l;

        if (m == 1)
            return 0l;

        while (a > 1) {
            Long q = a / m;
            Long t = m;

            m = a % m;
            a = t;
            t = y;

            y = x - q * y;
            x = t;
        }

        if (x < 0)
            x += m0;

        return x;
    }

    Long gcd(Long a, Long b)
    {
        if (a == 0l)
            return b;
        return gcd(b % a, a);
    }
}