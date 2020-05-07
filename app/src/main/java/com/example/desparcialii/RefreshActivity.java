package com.example.desparcialii;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class RefreshActivity extends AppCompatActivity {//implements AdapterView.OnItemSelectedListener{
    EditText Cedula, Nombre, Salario;
    Button Actualizar;
    Spinner Spin_1, Spin_2, Spin_3;
    String select_spin1, select_spin2, select_spin3;

    Formulario formulario;
    FormularioController foc;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh);

        foc = new FormularioController(getApplicationContext());
        Cursor mCursor = foc.allFormularios();

        Nombre = findViewById(R.id.edt_nombre);
        Salario = findViewById(R.id.edt_salario);

        ArrayList<String> Spin1_lista = new ArrayList<String>();
        Spin1_lista.add("Estrato 1");
        Spin1_lista.add("Estrato 2");
        Spin1_lista.add("Estrato 3");
        Spin1_lista.add("Estrato 4");
        Spin1_lista.add("Estrato 5");
        Spin1_lista.add("Estrato 6");
        Spin_1 = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Spin1_lista);
        Spin_1.setAdapter(adapter);
        Spin_1.setOnItemSelectedListener(new Spinner1Clase());

        ArrayList<String>Spin2_lista = new ArrayList<String>();
        Spin2_lista.add("Bachiller Académico");
        Spin2_lista.add("Estudios de Pregrado");
        Spin2_lista.add("Maestría, Master o Magister");
        Spin2_lista.add("Doctorado");
        Spin_2 = findViewById(R.id.spinner_2);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Spin2_lista);
        Spin_2.setAdapter(adapter2);
        Spin_2.setOnItemSelectedListener(new Spinner2Clase());


        ArrayList<String> Spin3_lista = new ArrayList<>();
        mCursor.moveToFirst();
        while (!mCursor.isAfterLast()) {
            Spin3_lista.add( mCursor.getString(mCursor.getColumnIndexOrThrow("_id"))); //add the item
            mCursor.moveToNext();
        }

        Spin_3 = findViewById(R.id.spinner_3);
        ArrayAdapter<String> adapter_3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Spin3_lista);
        Spin_3.setAdapter(adapter_3);
        Spin_3.setOnItemSelectedListener(new Spinner3Clase());


        Actualizar = findViewById(R.id.btn_actualizar);
        Actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                formulario = new Formulario();

                formulario.setCedula(select_spin3);
                formulario.setNombre(Nombre.getText().toString());
                formulario.setEstrato(select_spin1);
                formulario.setSalario(Salario.getText().toString());
                formulario.setNivel_educativo(select_spin2);

                long id = foc.actualizarFormulario(formulario);
                Toast.makeText(getApplicationContext(), "Formulario actualizado", Toast.LENGTH_SHORT).show();
            }
        });
    }

    class Spinner1Clase implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            select_spin1 = parent.getItemAtPosition(position).toString();
            Toast.makeText(view.getContext(), "Selección :"+select_spin1 ,Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    class Spinner2Clase implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            select_spin2= parent.getItemAtPosition(position).toString();
        }

        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    class Spinner3Clase implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            select_spin3 = parent.getItemAtPosition(position).toString();
            Formulario formulario = foc.buscarFormulario(select_spin3);

            Spin_1.setSelection(Integer.parseInt(formulario.getEstrato())-1);
            if(formulario.getNivel_educativo().equals("Bachiller Académico")){
                Spin_2.setSelection(0);
            }
            if(formulario.getNivel_educativo().equals("Estudios de Pregrado")){
                Spin_2.setSelection(1);
            }
            if(formulario.getNivel_educativo().equals("Maestría, Master o Magister")){
                Spin_2.setSelection(2);
            }
            if(formulario.getNivel_educativo().equals("Doctorado")){
                Spin_2.setSelection(3);
            }
            Nombre.setText(formulario.getNombre());
            Salario.setText(formulario.getSalario());

        }

        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

}
