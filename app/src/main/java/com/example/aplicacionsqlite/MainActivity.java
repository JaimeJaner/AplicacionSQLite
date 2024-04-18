package com.example.aplicacionsqlite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText editTextData;
    Button btnSave, btnShowData, btnResetData;
    ListView listViewData;
    DBHelper dbHelper;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextData = findViewById(R.id.editTextData);
        btnSave = findViewById(R.id.btnSave);
        btnShowData = findViewById(R.id.btnShowData);
        listViewData = findViewById(R.id.listViewData);
        btnResetData = findViewById(R.id.btnResetData);

        dbHelper = new DBHelper(this);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = editTextData.getText().toString().trim();
                if (!data.isEmpty()) {
                    dbHelper.addData(data);
                    editTextData.setText("");
                    Toast.makeText(MainActivity.this, "Datos guardados", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Ingrese datos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnShowData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showData();
            }
        });

        btnResetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetData();
            }
        });
    }


    private void showData() {
        List<String> dataList = dbHelper.getAllData();
        DataListAdapter adapter = new DataListAdapter(this, dataList);
        listViewData.setAdapter(adapter);

        int totalCount = dbHelper.getDataCount();
        Toast.makeText(this, "Cantidad total de datos: " + totalCount, Toast.LENGTH_SHORT).show();
    }

    private void resetData() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Resetear Datos");
        builder.setMessage("¿Estás seguro de que quieres resetear todos los datos?");

        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dbHelper.getWritableDatabase().delete(DBHelper.TABLE_NAME1, null, null);
                Toast.makeText(MainActivity.this, "Datos reseteados", Toast.LENGTH_SHORT).show();
                // Actualizar la lista después de resetear los datos
                showData();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // No hacer nada y cerrar el diálogo
            }
        });

        builder.create().show();
    }
}
