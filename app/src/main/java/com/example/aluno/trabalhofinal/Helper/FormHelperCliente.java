package com.example.aluno.trabalhofinal.Helper;

import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.example.aluno.trabalhofinal.ClientesActivity;
import com.example.aluno.trabalhofinal.InfPessoaisActivity;
import com.example.aluno.trabalhofinal.R;

import com.example.aluno.trabalhofinal.Model.Cliente;

/**
 * Created by Anderson on 04/06/2016.
 */
public class FormHelperCliente {
    InfPessoaisActivity activity;
    private EditText etNome,etEmail, etTelefone, etData;
    private RadioGroup rgSexo;
    String sexo;


    public FormHelperCliente(InfPessoaisActivity formActivity){
        activity = formActivity;

        etNome = (EditText)activity.findViewById(R.id.etNome);
        etEmail = (EditText)activity.findViewById(R.id.etEMail);
        etTelefone = (EditText)activity.findViewById(R.id.etTelefone);
        etData = (EditText)activity.findViewById(R.id.etData);
        rgSexo = (RadioGroup)activity.findViewById(R.id.rgSexo);
    }

    public Cliente getCliente(){
        Cliente cliente = new Cliente();
        cliente.setNome(String.valueOf(etNome.getText()));
        cliente.setEmail(String.valueOf(etEmail.getText()));
        cliente.setTelefone(String.valueOf(etTelefone.getText()));
        cliente.setData(String.valueOf(etData.getText()));
        cliente.setSexo(String.valueOf(rgSexo.getCheckedRadioButtonId()));
    return cliente;
    }

    public void setCliente(Cliente cliente) {
        etNome.setText(cliente.getNome());
        etEmail.setText(cliente.getEmail());
        etTelefone.setText(cliente.getTelefone());
        etData.setText(cliente.getEmail());
        //rgSexo.setText(cliente.getSexo());
        switch (rgSexo.getCheckedRadioButtonId()){
            case R.id.rbFeminino:
                sexo = "Feminino";
            case R.id.rbMasculino:
                sexo = "Masculino";
        }
    }
}
