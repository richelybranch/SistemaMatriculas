package com.rycry.sistemamatriculas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Resultado extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        String nombre = getIntent().getStringExtra("NOMBRE");
        String cedula = getIntent().getStringExtra("CEDULA");
        double importeRenovacion = getIntent().getDoubleExtra("IMPORTE_RENOVACION", 0.0);
        boolean tieneMultas = getIntent().getBooleanExtra("TIENE_MULTAS", false);
        double valorTotal = getIntent().getDoubleExtra("VALOR_TOTAL", 0.0);
        double valorMarcaTipo = getIntent().getDoubleExtra("VALOR_MARCA_TIPO", 0.0);
        double multaContaminacion = getIntent().getDoubleExtra("MULTA_CONTAMINACION", 0.0);

        TextView tvResultadoNombre = findViewById(R.id.tvResultadoNombre);
        TextView tvResultadoCedula = findViewById(R.id.tvResultadoCedula);
        EditText edResultadolmporteRenovacion = findViewById(R.id.edResultadoImporteRenovacion);
        EditText edResultadoMultasSiNo = findViewById(R.id.edResultadoMultasSiNo);
        EditText edResultadoValorTotal = findViewById(R.id.edResultadoValorTotal);
        EditText edResultadoValorMarcaTipo = findViewById(R.id.edResultadoValorMarcaTipo);
        EditText edResultadoMultaContaminacion = findViewById(R.id.edResultadoMultaContaminacion);

        tvResultadoNombre.setText("Nombre: " + nombre);
        tvResultadoCedula.setText("Cédula: " + cedula);
        edResultadolmporteRenovacion.setText(String.valueOf(importeRenovacion));
        edResultadoMultasSiNo.setText(tieneMultas ? "108.75" : "No");
        edResultadoValorTotal.setText(String.valueOf(valorTotal));
        edResultadoValorMarcaTipo.setText(String.valueOf(valorMarcaTipo));
        edResultadoMultaContaminacion.setText(String.valueOf(multaContaminacion));

        Button btnVolver = findViewById(R.id.btnVolver);
        btnVolver.setOnClickListener(view -> finish());

        Button btnSalir = findViewById(R.id.btnSalir);
        btnSalir.setOnClickListener(view -> finishAffinity());
    }
}