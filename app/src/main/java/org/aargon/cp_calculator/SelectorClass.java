package org.aargon.cp_calculator;

import static androidx.core.content.ContextCompat.startActivity;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

public class SelectorClass extends AppCompatActivity {
    String[] calculatorItems = {"Addition", "Subtraction", "Multiplication", "Division", "Big Mod", "Euler Totient", "Mobius",
            "Modulo Inverse", "Modulus", "nCr", "Number of Divisors", "Nth Prime", "Sum of Divisors"};

    Map<String, Class> activityHashMap = new HashMap<>();

    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapterItems;

    SelectorClass() {
        activityHashMap.put("Addition", AdditionActivity.class);
        activityHashMap.put("Subtraction", SubtractionActivity.class);
        activityHashMap.put("Multiplication", MultiplicationActivity.class);
        activityHashMap.put("Division", DivisionActivity.class);
        activityHashMap.put("Big Mod", BigModActivity.class);
        activityHashMap.put("Euler Totient", ETFActivity.class);
        activityHashMap.put("Mobius", MobiusActivity.class);
        activityHashMap.put("Modulo Inverse", ModuloInverseActivity.class);
        activityHashMap.put("Modulus", ModulusActivity.class);
        activityHashMap.put("nCr", nCrActivity.class);
        activityHashMap.put("Number of Divisors", NODActivity.class);
        activityHashMap.put("Nth Prime", NthPrimeActivity.class);
        activityHashMap.put("Sum of Divisors", SODActivity.class);
    }
}
