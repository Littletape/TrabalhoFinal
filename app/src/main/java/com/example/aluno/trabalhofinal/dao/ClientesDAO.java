package com.example.aluno.trabalhofinal.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.aluno.trabalhofinal.Model.Cliente;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anderson on 04/06/2016.
 */
public class ClientesDAO extends SQLiteOpenHelper {
    private final String bdName = "TrabalhoFinal";

    public ClientesDAO(Context context) {
        super(context, "TrabalhoFinal", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE tb_clientes " +
                "(idCliente INTEGER PRIMARY KEY, " +
                "nome TEXT NOT NULL, " +
                "email TEXT, " +
                "telefone TEXT, " +
                "data TEXT," +
                "sexo TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXIST tb_clientes";
        db.execSQL(sql);
        onCreate(db);
    }

    public void insereCliente(Cliente cliente){

        ContentValues dados = new ContentValues();
        dados.put("nome", cliente.getNome());
        dados.put("email", cliente.getEmail());
        dados.put("telefone", cliente.getTelefone());
        dados.put("data", cliente.getData());
        dados.put("sexo", cliente.getSexo());

        SQLiteDatabase db = getWritableDatabase();
        db.insert("tb_clientes", null, dados);
    }

    public List<Cliente> getCliente(){
        String sql = "SELECT * FROM tb_clientes";

        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        List<Cliente> clientes = new ArrayList<Cliente>();

        while(cursor.moveToNext()){
            Cliente cliente = new Cliente();
            cliente.setIdCliente(cursor.getLong(
                    cursor.getColumnIndex("idCliente")));
            cliente.setNome(cursor.getString(
                    cursor.getColumnIndex("nome")));
            cliente.setEmail(cursor.getString(
                    cursor.getColumnIndex("email")));
            cliente.setTelefone(cursor.getString(
                    cursor.getColumnIndex("telefone")));
            cliente.setData(cursor.getString(
                    cursor.getColumnIndex("data")));
            cliente.setSexo(cursor.getString(
                    cursor.getColumnIndex("sexo")));

            clientes.add(cliente);
        }

        cursor.close();
        return clientes;
    }

    public void alteraCliente(Cliente cliente) {
        ContentValues dados = new ContentValues();
        dados.put("nome", cliente.getNome());
        dados.put("email", cliente.getEmail());
        dados.put("telefone", cliente.getTelefone());
        dados.put("data", cliente.getData());
        dados.put("sexo", cliente.getSexo());

        SQLiteDatabase db = getWritableDatabase();
        String[] params = {String.valueOf(cliente.getIdCliente())};
        db.update("tb_clientes", dados, "idCliente = ?", params);
    }

    public void deletaCliente(Cliente cliente) {
        SQLiteDatabase db = getWritableDatabase();
        String[] params = {String.valueOf(cliente.getIdCliente())};
        db.delete("tb_clientes", "idCliente = ?", params);
    }
}
