package com.example.aluno.trabalhofinal.Model;

import java.io.Serializable;

/**
 * Created by Anderson on 04/06/2016.
 */
public class Cliente implements Serializable {
    private long idCliente;
    private String nome;
    private String email;
    private String telefone;
    private String data;
    private String sexo;



    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(long idCliente) {
        this.idCliente = idCliente;
    }

    @Override
    public String toString() {
        return getIdCliente() + " - " + getNome();
    }
}
