package com.example.aluno.trabalhofinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.NumberPicker;

public class ListaDesejosActivity extends AppCompatActivity {
    NumberPicker np1, np2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_desejos);
        np1 = (NumberPicker) findViewById(R.id.numberPicker);
        np2 = (NumberPicker) findViewById(R.id.numberPicker2);

        np1.setMinValue(0);
        np1.setMaxValue(10000);

        np2.setMinValue(0);
        np2.setMaxValue(10000);
    }

}
