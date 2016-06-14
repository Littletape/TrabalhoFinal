package com.example.aluno.trabalhofinal.Model;

import java.io.Serializable;

/**
 * Created by Anderson on 04/06/2016.
 */
public class Produto implements Serializable {
    private long idProduto;
    private String marca;
    private String modelo;
    private String preco;

    public long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(long idProduto) {
        this.idProduto = idProduto;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    @Override
    public String toString() { return getIdProduto() + " - " + getMarca(); }
}
