package com.example.aluno.trabalhofinal.Helper;

import android.widget.EditText;
import com.example.aluno.trabalhofinal.ProdutosActivity;
import com.example.aluno.trabalhofinal.R;

import com.example.aluno.trabalhofinal.Model.Produto;

/**
 * Created by Anderson on 04/06/2016.
 */
public class FormHelperProduto {
    ProdutosActivity activity;
    private EditText etMarca, etModelo, etPreco;


    public FormHelperProduto(ProdutosActivity formActivity){
        activity = formActivity;

        etMarca = (EditText)activity.findViewById(R.id.etMarca);
        etModelo = (EditText)activity.findViewById(R.id.etModelo);
        etPreco = (EditText)activity.findViewById(R.id.etPreco);
    }

    public Produto getProduto(){
        Produto produto = new Produto();
        produto.setMarca(String.valueOf(etMarca.getText()));
        produto.setModelo(String.valueOf(etModelo.getText()));
        produto.setPreco(String.valueOf(etPreco.getText()));
        return produto;
    }

    public void setProduto(Produto produto) {
        etMarca.setText(produto.getMarca());
        etModelo.setText(produto.getModelo());
        etPreco.setText(produto.getPreco());
    }
}

