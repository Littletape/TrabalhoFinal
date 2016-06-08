package com.example.aluno.trabalhofinal;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.aluno.trabalhofinal.Helper.FormHelperCliente;
import com.example.aluno.trabalhofinal.Model.Cliente;
import com.example.aluno.trabalhofinal.dao.ClientesDAO;

public class InfPessoaisActivity extends AppCompatActivity {
    private FormHelperCliente helper;
    private boolean alterar;
    private long idCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inf_pessoais);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Intent intent = getIntent();
        helper = new FormHelperCliente(this);
        Cliente cliente = (Cliente)intent.getSerializableExtra("cliente");
        if(cliente != null){
            alterar = true;
            idCliente = cliente.getIdCliente();
            helper.setCliente(cliente);
        }
        else {
            alterar = false;
        }
    }

    public void onClickSalvar(View view){
        Cliente cliente = helper.getCliente();

        ClientesDAO dao = new ClientesDAO(this);

        if(alterar){
            cliente.setIdCliente(idCliente);
            dao.alteraCliente(cliente);
        }
        else{
            dao.insereCliente(cliente);
        }

        finish();
    }
}

