package com.ox55.convert;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String[] units = {"Kilograms", "pounds", "Metres", "Centimetres", "Kilometres", "miles"};
    Spinner unit1;
    Spinner unit2;
    EditText value1;
    EditText value2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ImageButton button = findViewById(R.id.imageButton);

        unit1 = findViewById(R.id.spinner);
        unit2 = findViewById(R.id.spinner2);

        value1 = findViewById(R.id.editTextPhone2);
        value1.setText("0");
        value2 = findViewById(R.id.editTextPhone3);
        value2.setEnabled(false);


        unit1.setOnItemSelectedListener(this);
        // Create the instance of ArrayAdapter
        // having the list of courses
        ArrayAdapter adapterUnits = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                units);

        // set simple layout resource file
        // for each item of spinner
        adapterUnits.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Set the ArrayAdapter (ad) data on the
        // Spinner which binds data to spinner
        unit1.setAdapter(adapterUnits);

        unit2.setOnItemSelectedListener(this);
        unit2.setAdapter(adapterUnits);
        unit2.setSelection(1);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int firstValue = Integer.valueOf(value1.getText().toString());
                if (firstValue < 2147483647 && (String.valueOf(firstValue).matches("^\\d+$"))) {
                    double convertedValue = firstValue;  // Use double for decimal conversions
                    String unit1Selected = unit1.getSelectedItem().toString();
                    String unit2Selected = unit2.getSelectedItem().toString();

                    if (unit1Selected.equals("Kilograms") && unit2Selected.equals("pounds")) {
                        convertedValue *= 2.2046;
                    } else if (unit1Selected.equals("pounds") && unit2Selected.equals("Kilograms")) {
                        convertedValue /= 2.2046;
                    } else if (unit1Selected.equals("Metres") && unit2Selected.equals("Centimetres")) {
                        convertedValue *= 100;
                    } else if (unit1Selected.equals("Centimetres") && unit2Selected.equals("Metres")) {
                        convertedValue /= 100;
                    } else if (unit1Selected.equals("Kilometres") && unit2Selected.equals("miles")) {
                        convertedValue /= 1.609;
                    } else if (unit1Selected.equals("miles") && unit2Selected.equals("Kilometres")) {
                        convertedValue *= 1.609;
                    } else {
                        Toast.makeText(MainActivity.this, "Operation not yet supported", Toast.LENGTH_SHORT).show();
                    }
                    value2.setText(String.valueOf(convertedValue));
                } else {
                    Toast.makeText(MainActivity.this, "Number Too Large or invalid", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        // make toast of name of course which is selected in spinner
        //Toast.makeText(getApplicationContext(), units[position], Toast.LENGTH_LONG).show();
//        if(units[position].equals("Kilograms")) {
//            value2.setText(String.valueOf(firstValue*2.2046));
//        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}