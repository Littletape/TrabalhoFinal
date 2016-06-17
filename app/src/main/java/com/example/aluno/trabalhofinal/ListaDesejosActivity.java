package com.example.aluno.trabalhofinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;

import com.example.aluno.trabalhofinal.dao.DAO;

import java.util.ArrayList;

public class ListaDesejosActivity extends AppCompatActivity {
    NumberPicker np1, np2;
    Spinner spCliente, spProduto;
    DAO dao = new DAO(this);
    EditText display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_desejos);
        np1 = (NumberPicker) findViewById(R.id.numberPicker);
        np2 = (NumberPicker) findViewById(R.id.numberPicker2);
        spCliente = (Spinner) findViewById(R.id.spCliente);
        spProduto = (Spinner) findViewById(R.id.spProduto);

        np1.setMinValue(0);
        np1.setMaxValue(10000);
        np2.setMinValue(0);
        np2.setMaxValue(10000);

        ArrayList<String> listaCliente = dao.spClientes();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_layout, R.id.txt, listaCliente);
        spCliente.setAdapter(adapter);

        ArrayList<String> listaProduto = dao.spProdutos();
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, R.layout.spinner_layout, R.id.txt, listaProduto);
        spProduto.setAdapter(adapter2);




    }


}
