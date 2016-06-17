package com.example.aluno.trabalhofinal;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.aluno.trabalhofinal.Helper.FormHelperCliente;
import com.example.aluno.trabalhofinal.Model.Cliente;
import com.example.aluno.trabalhofinal.dao.DAO;

import java.util.Calendar;

public class InfPessoaisActivity extends AppCompatActivity {
    private FormHelperCliente helper;
    private boolean alterar;
    private long idCliente;
    private RadioButton rbMasculino, rbFeminino;
    private EditText display;
    private Calendar calendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inf_pessoais);

        rbMasculino = (RadioButton)findViewById(R.id.rbMasculino);
        rbFeminino = (RadioButton)findViewById(R.id.rbFeminino);

        Intent intent = getIntent();
        helper = new FormHelperCliente(this);
        Cliente cliente = (Cliente)intent.getSerializableExtra("cliente");
        if(cliente != null){
            alterar = true;
            idCliente = cliente.getIdCliente();
            helper.setCliente(cliente);
            switch (cliente.getSexo()){
                case "Masculino": rbMasculino.setChecked(true); break;
                case "Feminino": rbFeminino.setChecked(true); break;
            }
            Toast.makeText(this, "sexo " + cliente.getSexo(), Toast.LENGTH_LONG).show();
        }
        else {
            alterar = false;
        }

        display = (EditText) findViewById(R.id.etData);
        ImageButton btcalendar = (ImageButton) findViewById(R.id.btcalendar);
        btcalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(InfPessoaisActivity.this, listener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

     DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            display.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
        }
    };

    public void onClickSalvar(View view){
        Cliente cliente = helper.getCliente();

        DAO dao = new DAO(this);

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

