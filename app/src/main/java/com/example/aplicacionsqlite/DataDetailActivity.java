package com.example.aplicacionsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DataDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_detail);
        TextView textViewDetailData = findViewById(R.id.textViewDetailData);

        // Obtener el dato seleccionado del intent
        String selectedData = getIntent().getStringExtra("selectedData");

        // Mostrar el dato en el TextView
        textViewDetailData.setText(selectedData);
    }
}