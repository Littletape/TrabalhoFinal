package com.example.aluno.trabalhofinal.Helper;

import android.widget.EditText;

import com.example.aluno.trabalhofinal.Model.Cliente;

/**
 * Created by Anderson on 04/06/2016.
 */
public class FormHelperCliente {
    FormActivity activity;
    private EditText etNome,etEmail, etTelefone, etData, etSexo ;


    public FormHelperCliente(FormActivity formActivity){
        activity = formActivity;

        etNome = (EditText)activity.findViewById(R.id.etNome);
        etEmail = (EditText)activity.findViewById(R.id.etEmail);
        etTelefone = (EditText)activity.findViewById(R.id.etTelefone);
        etData = (EditText)activity.findViewById(R.id.etData);
        etSexo = (EditText)activity.findViewById(R.id.etSexo);
    }

    public Cliente getCliente(){
        Cliente cliente = new Cliente();
        cliente.setNome(String.valueOf(etNome.getText()));
        cliente.setEmail(String.valueOf(etEmail.getText()));
        cliente.setTelefone(String.valueOf(etTelefone.getText()));
        cliente.setData(String.valueOf(etData.getText()));
        cliente.setSexo(String.valueOf(etSexo.getText()));
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        etNome.setText(cliente.getNome());
        etEmail.setText(cliente.getEmail());
        etTelefone.setText(cliente.getTelefone());
        etData.setText(cliente.getEmail());
        etSexo.setText(cliente.getEmail());
    }
}
