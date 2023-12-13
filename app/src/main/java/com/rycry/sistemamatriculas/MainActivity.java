package com.rycry.sistemamatriculas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spnMarcaModelo = findViewById(R.id.spnMarcaModelo);
        EditText edNombre = findViewById(R.id.edNombre);
        EditText edCedula = findViewById(R.id.edCedula);
        EditText edAnioFabricacion = findViewById(R.id.edAnioFabricacion);
        EditText edValor = findViewById(R.id.edValor);
        EditText edPlaca = findViewById(R.id.edPlaca);
        CheckBox cbxMultas = findViewById(R.id.cbxMultas);

        String[] opciones = {"Toyota-Jeep", "Toyota-Camioneta", "Suzuki-Vitara", "Suzuki-Automóvil"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opciones);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spnMarcaModelo.setAdapter(adapter);

        spnMarcaModelo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                Toast.makeText(MainActivity.this, "Opción seleccionada: " + opciones[position], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });

        Button btnIngresarDatos = findViewById(R.id.btnIngresarDatos);
        btnIngresarDatos.setOnClickListener(view -> {
            String nombre = edNombre.getText().toString();
            String cedula = edCedula.getText().toString();
            int anioFabricacion = Integer.parseInt(edAnioFabricacion.getText().toString());
            double valor = Double.parseDouble(edValor.getText().toString());
            String placa = edPlaca.getText().toString();
            boolean tieneMultas = cbxMultas.isChecked();

            double importeRenovacion = calcularImporteRenovacion(cedula, placa);
            double multaContaminacion = calcularMultaContaminacion(anioFabricacion);
            double valorMarcaTipo = calcularValorMarcaTipo(spnMarcaModelo.getSelectedItem().toString(), valor);
            double valorTotal = calcularValorTotal(valorMarcaTipo, tieneMultas);

            launchResultadoActivity(nombre, cedula, importeRenovacion, tieneMultas, valorTotal, valorMarcaTipo, multaContaminacion);
        });
    }

    private double calcularImporteRenovacion(String cedula, String placa) {
        if (cedula.startsWith("1") && placa.toLowerCase().contains("i")) {
            return 0.05 * 435;
        } else {
            return 0.0;
        }
    }

    private double calcularMultaContaminacion(int anioFabricacion) {
        if (anioFabricacion < 2010) {
            return 0.02 * (2023 - anioFabricacion) * 435;
        } else {
            return 0.0;
        }
    }

    private double calcularValorMarcaTipo(String marcaTipo, double valor) {
        switch (marcaTipo) {
            case "Toyota-Jeep":
                return 0.08 * valor;
            case "Toyota-Camioneta":
                return 0.12 * valor;
            case "Suzuki-Vitara":
                return 0.10 * valor;
            case "Suzuki-Automóvil":
                return 0.09 * valor;
            default:
                return 0.0;
        }
    }

    private double calcularValorTotal(double valorMarcaTipo, boolean tieneMultas) {
        double valorTotal = valorMarcaTipo;
        if (tieneMultas) {
            valorTotal += 0.25 * 435;
        }
        return valorTotal;
    }

    private void launchResultadoActivity(String nombre, String cedula, double importeRenovacion,
                                         boolean tieneMultas, double valorTotal, double valorMarcaTipo,
                                         double multaContaminacion) {
        Intent intent = new Intent(MainActivity.this, Resultado.class);
        intent.putExtra("NOMBRE", nombre);
        intent.putExtra("CEDULA", cedula);
        intent.putExtra("IMPORTE_RENOVACION", importeRenovacion);
        intent.putExtra("TIENE_MULTAS", tieneMultas);
        intent.putExtra("VALOR_TOTAL", valorTotal);
        intent.putExtra("VALOR_MARCA_TIPO", valorMarcaTipo);
        intent.putExtra("MULTA_CONTAMINACION", multaContaminacion);
        startActivity(intent);
    }
}