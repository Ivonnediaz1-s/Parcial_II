package com.example.desparcialii;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    EditText cedula, nombre, salario;
    Button guardar;
    String select_spinner1, select_spinner2;

    Formulario formulario;
    FormularioController foc;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        foc = new FormularioController(getApplicationContext());

        cedula = findViewById(R.id.edt_cedula);
        nombre = findViewById(R.id.edt_nombre);
        salario = findViewById(R.id.edt_salario);

        ArrayList<String> spinner1_list = new ArrayList<String>();
        spinner1_list.add("Estrato 1");
        spinner1_list.add("Estrato 2");
        spinner1_list.add("Estrato 3");
        spinner1_list.add("Estrato 4");
        spinner1_list.add("Estrato 5");
        spinner1_list.add("Estrato 6");
        Spinner spinner_1 = findViewById(R.id.spinner);
        ArrayAdapter<String> adap = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinner1_list);
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_1.setAdapter(adap);
        spinner_1.setOnItemSelectedListener(new SpinnerUnoClass());

        ArrayList<String> spinner2_list = new ArrayList<String>();
        spinner2_list.add("Bachiller Académico");
        spinner2_list.add("Estudios de Pregrado");
        spinner2_list.add("Maestría, Master o Magister");
        spinner2_list.add("Doctorado");
        Spinner spinner_2 = findViewById(R.id.spinner_2);
        ArrayAdapter<String> adap2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinner2_list);
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_2.setAdapter(adap2);
        spinner_2.setOnItemSelectedListener(new SpinnerDosClass());

        guardar = findViewById(R.id.btn_guardar);
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                formulario = new Formulario();

                formulario.setCedula(cedula.getText().toString());
                formulario.setNombre(nombre.getText().toString());
                formulario.setEstrato(select_spinner1);
                formulario.setSalario(salario.getText().toString());
                formulario.setNivel_educativo(select_spinner2);

                long id = foc.agregarFormulario(formulario);
                Toast.makeText(getApplicationContext(), "Formulario registrado con éxito", Toast.LENGTH_SHORT).show();
                limpiarFormulario();
            }
        });
    }

    public void limpiarFormulario(){
        cedula.setText("");
        nombre.setText("");
        salario.setText("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem it) {
        int id = it.getItemId();

        if (id == R.id.action_listar) {
            Intent m = new Intent(getApplicationContext(), ListaActivity.class);
            startActivity(m);
            return true;
        }

        if (id == R.id.action_actualizar) {
            Intent a = new Intent(getApplicationContext(), RefreshActivity.class);
            startActivity(a);
            return true;
        }

        return super.onOptionsItemSelected(it);
    }


    class SpinnerUnoClass implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            select_spinner1 = parent.getItemAtPosition(position).toString();
        }

        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    class SpinnerDosClass implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            select_spinner2 = parent.getItemAtPosition(position).toString();
        }

        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

}

