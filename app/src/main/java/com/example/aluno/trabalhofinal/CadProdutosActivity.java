package com.example.aluno.trabalhofinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.aluno.trabalhofinal.Helper.FormHelperProduto;
import com.example.aluno.trabalhofinal.Model.Produto;
import com.example.aluno.trabalhofinal.dao.DAO;

public class CadProdutosActivity extends AppCompatActivity {
    private FormHelperProduto helper;
    private boolean alterar;
    private long idProduto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_produtos);

        Intent intent = getIntent();
        helper = new FormHelperProduto(this);
        Produto produto = (Produto)intent.getSerializableExtra("produto");
        if(produto != null){
            alterar = true;
            idProduto = produto.getIdProduto();
            helper.setProduto(produto);
        }
        else {
            alterar = false;
        }
    }

    public void onClickSalvar(View view){
        Produto produto = helper.getProduto();

        DAO dao = new DAO(this);

        if(alterar){
            produto.setIdProduto(idProduto);
            dao.alteraProduto(produto);
        }
        else{
            try {
                dao.insereProduto(produto);
            }catch(Exception e){
                Toast.makeText(this, "Erro ao inserir", Toast.LENGTH_SHORT).show();
            }
        }

        finish();
    }
}

