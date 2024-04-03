package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity {

    private Spinner sourceUnitSpinner, destinationUnitSpinner;
    private EditText valueInput;
    private Button convertButton;
    private TextView convertedValueTextView;

    private static final double INCH_TO_CM = 2.54;
    private static final double FOOT_TO_CM = 30.48;
    private static final double YARD_TO_CM = 91.44;
    private static final double MILE_TO_KM = 1.60934;
    private static final double POUND_TO_KG = 0.453592;
    private static final double OUNCE_TO_G = 28.3495;
    private static final double TON_TO_KG = 907.185;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sourceUnitSpinner = findViewById(R.id.source_unit_spinner);
        destinationUnitSpinner = findViewById(R.id.destination_unit_spinner);
        valueInput = findViewById(R.id.value_input);
        convertButton = findViewById(R.id.convert_button);
        convertedValueTextView = findViewById(R.id.converted_value_textview);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.unit_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sourceUnitSpinner.setAdapter(adapter);
        destinationUnitSpinner.setAdapter(adapter);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertValue();
            }
        });
    }

    private void convertValue() {
        String sourceUnit = sourceUnitSpinner.getSelectedItem().toString();
        String destinationUnit = destinationUnitSpinner.getSelectedItem().toString();
        double inputValue = Double.parseDouble(valueInput.getText().toString());

        double convertedValue;

        // Temperature conversions
        if (sourceUnit.equals("Celsius") && destinationUnit.equals("Fahrenheit")) {
            convertedValue = (inputValue * 1.8) + 32;
        } else if (sourceUnit.equals("Fahrenheit") && destinationUnit.equals("Celsius")) {
            convertedValue = (inputValue - 32) / 1.8;
        } else if (sourceUnit.equals("Celsius") && destinationUnit.equals("Kelvin")) {
            convertedValue = inputValue + 273.15;
        } else if (sourceUnit.equals("Kelvin") && destinationUnit.equals("Celsius")) {
            convertedValue = inputValue - 273.15;
        } else if (sourceUnit.equals("Fahrenheit") && destinationUnit.equals("Kelvin")) {
            convertedValue = (inputValue + 459.67) * 5 / 9;
        } else if (sourceUnit.equals("Kelvin") && destinationUnit.equals("Fahrenheit")) {
            convertedValue = (inputValue * 9 / 5) - 459.67;
        }
        // Weight conversions
        else if (sourceUnit.equals("Pound") && destinationUnit.equals("Kilogram")) {
            convertedValue = inputValue * POUND_TO_KG;
        } else if (sourceUnit.equals("Ounce") && destinationUnit.equals("Gram")) {
            convertedValue = inputValue * OUNCE_TO_G;
        } else if (sourceUnit.equals("Ton") && destinationUnit.equals("Kilogram")) {
            convertedValue = inputValue * TON_TO_KG;
        }
        // Length conversions
        else if (sourceUnit.equals("Inch") && destinationUnit.equals("Centimeter")) {
            convertedValue = inputValue * INCH_TO_CM;
        } else if (sourceUnit.equals("Foot") && destinationUnit.equals("Centimeter")) {
            convertedValue = inputValue * FOOT_TO_CM;
        } else if (sourceUnit.equals("Yard") && destinationUnit.equals("Centimeter")) {
            convertedValue = inputValue * YARD_TO_CM;
        } else if (sourceUnit.equals("Mile") && destinationUnit.equals("Kilometer")) {
            convertedValue = inputValue * MILE_TO_KM;
        } else {
            // Default case, units are the same, return the same value
            convertedValue = inputValue;
        }

        convertedValueTextView.setText(String.valueOf(convertedValue));
    }
}

