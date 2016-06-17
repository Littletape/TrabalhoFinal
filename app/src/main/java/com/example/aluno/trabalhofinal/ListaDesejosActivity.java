package com.example.aluno.trabalhofinal;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
    String strCliente, strProduto, strNp1, strNp2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_desejos);
        np1 = (NumberPicker) findViewById(R.id.numberPicker);
        np2 = (NumberPicker) findViewById(R.id.numberPicker2);
        spCliente = (Spinner) findViewById(R.id.spCliente);
        spProduto = (Spinner) findViewById(R.id.spProduto);

        final ArrayList<String> listaCliente = dao.spClientes();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_layout, R.id.txt, listaCliente);
        spCliente.setAdapter(adapter);
        spCliente.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strCliente = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        ArrayList<String> listaProduto = dao.spProdutos();
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, R.layout.spinner_layout, R.id.txt, listaProduto);
        spProduto.setAdapter(adapter2);
        spProduto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strProduto = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        np1.setMinValue(0);
        np1.setMaxValue(10000);
        np1.setValue(50);
        strNp1 = String.valueOf(np1.getValue());
        np1.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                strNp1 = String.valueOf(newVal);
            }
        });

        np2.setMinValue(0);
        np2.setMaxValue(10000);
        np2.setValue(50);
        strNp2 = String.valueOf(np2.getValue());
        np2.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                strNp2 = String.valueOf(newVal);
            }
        });

    }

    public void onClickBtEmail(View view){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("plain/text");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"contato@google.com.br"});
        intent.putExtra(Intent.EXTRA_SUBJECT, "Lista de desejos");
        intent.putExtra(Intent.EXTRA_TEXT,
                        "O cliente " + strCliente +
                        ", deseja comprar o produto " + strProduto+
                        " no valor minimo de R$ " + strNp1 +
                        " e maximo de R$ " + strNp2
        );
        //Cria tela para escolha do software de envio de email
        startActivity(Intent.createChooser(intent, "Enviar email"));
    }

    public void onClickBtTelefonar(View view){
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:12345678"));
        startActivity(intent);
    }

}
