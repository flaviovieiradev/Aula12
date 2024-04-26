package com.example.aula12;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    private Spinner spnNomes, spnCidades;
    private Button btnCadastrar;
    private List<String> listaCidadades;

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

        spnNomes = (Spinner) findViewById(R.id.spinner1);
        spnCidades = (Spinner) findViewById(R.id.spinner2);
        btnCadastrar = (Button) findViewById(R.id.button1);
        spnNomes.setOnItemSelectedListener(this);
        spnCidades.setOnItemSelectedListener(this);
        btnCadastrar.setOnClickListener(this);

        //Métodos que vão ser criados

        carregarListaNomes();
        carregarListaCidades();
    }

    // CARREGAR lista de nomes do RESOURCE
    private void carregarListaNomes() {
        ArrayAdapter<CharSequence> adp = ArrayAdapter.createFromResource(this, R.array.lista_nomes, android.R.layout.simple_spinner_item);
        adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnNomes.setAdapter(adp);
    } // CARREGAR lista de cidades do ARRAYLIST

    private void carregarListaCidades() {
        listaCidadades = new ArrayList<String>();
        listaCidadades.add("Americana");
        listaCidadades.add("Araraquara");
        listaCidadades.add("Batatais");
        listaCidadades.add("Campinas");
        listaCidadades.add("Limeira");
        listaCidadades.add("Ribeirão Preto");
        listaCidadades.add("São Paulo");
        ArrayAdapter<String> adp = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaCidadades);
        adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnCidades.setAdapter(adp);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button1) {
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle(R.string.app_name);
            dlg.setMessage(spnNomes.getSelectedItem().toString() + "\n" + spnCidades.getSelectedItem().toString());
            dlg.setPositiveButton("OK", null);
            dlg.show();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        // Exibir item selecionado
        String item = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(MainActivity.this, item, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}