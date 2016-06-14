package com.example.aluno.trabalhofinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.aluno.trabalhofinal.Helper.FormHelperCliente;
import com.example.aluno.trabalhofinal.Model.Cliente;
import com.example.aluno.trabalhofinal.dao.ClientesDAO;

import java.io.IOException;

public class InfPessoaisActivity extends AppCompatActivity {
    private FormHelperCliente helper;
    private boolean alterar;
    private long idCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inf_pessoais);

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
           try {
               dao.insereCliente(cliente);
           }catch(Exception e){
              Toast.makeText(this, "Erro ao inserir",Toast.LENGTH_SHORT).show();
           }
        }

        finish();
    }
}

