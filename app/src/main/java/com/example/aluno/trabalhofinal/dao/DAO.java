package com.example.aluno.trabalhofinal.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.aluno.trabalhofinal.Model.Cliente;
import com.example.aluno.trabalhofinal.Model.Produto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anderson Cardoso on 14/06/2016.
 */
public class DAO extends SQLiteOpenHelper {
    private final String bdName = "TrabalhoFinal";

    public DAO(Context context) {
        super(context, "TrabalhoFinal", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlClientes = "CREATE TABLE tb_clientes " +
                "(idCliente INTEGER PRIMARY KEY, " +
                "nome TEXT NOT NULL, " +
                "email TEXT, " +
                "telefone TEXT, " +
                "data TEXT," +
                "sexo TEXT)";
        String sqlProdutos = "CREATE TABLE tb_produtos " +
                "(idProduto INTEGER PRIMARY KEY, " +
                "marca TEXT NOT NULL, " +
                "modelo TEXT, " +
                "preco TEXT)";
        db.execSQL(sqlClientes);
        db.execSQL(sqlProdutos);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sqlClientes = "DROP TABLE IF EXIST tb_clientes";
        String sqlProdutos = "DROP TABLE IF EXIST tb_produtos";
        db.execSQL(sqlClientes);
        db.execSQL(sqlProdutos);
        onCreate(db);
    }

    public void insereProduto(Produto produto){

        ContentValues dados = new ContentValues();
        dados.put("marca", produto.getMarca());
        dados.put("modelo", produto.getModelo());
        dados.put("preco", produto.getPreco());

        SQLiteDatabase db = getWritableDatabase();
        db.insert("tb_produtos", null, dados);
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

    public List<Produto> getProduto(){
        String sql = "SELECT * FROM tb_produtos";

        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        List<Produto> arrayprodutos = new ArrayList<Produto>();

        while(cursor.moveToNext()){
            Produto produto = new Produto();
            produto.setIdProduto(cursor.getLong(
                    cursor.getColumnIndex("idProduto")));
            produto.setMarca(cursor.getString(
                    cursor.getColumnIndex("marca")));
            produto.setModelo(cursor.getString(
                    cursor.getColumnIndex("modelo")));
            produto.setPreco(cursor.getString(
                    cursor.getColumnIndex("preco")));


            arrayprodutos.add(produto);
        }

        cursor.close();
        return arrayprodutos;
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

    public void alteraProduto(Produto produto) {
        ContentValues dados = new ContentValues();
        dados.put("idProduto", produto.getIdProduto());
        dados.put("marca", produto.getMarca());
        dados.put("modelo", produto.getModelo());
        dados.put("preco", produto.getPreco());

        SQLiteDatabase db = getWritableDatabase();
        String[] params = {String.valueOf(produto.getIdProduto())};
        db.update("tb_produtos", dados, "idProduto = ?", params);
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

    public void deletaProduto(Produto produto) {
        SQLiteDatabase db = getWritableDatabase();
        String[] params = {String.valueOf(produto.getIdProduto())};
        db.delete("tb_produtos", "idProduto = ?", params);
    }

    public void deletaCliente(Cliente cliente) {
        SQLiteDatabase db = getWritableDatabase();
        String[] params = {String.valueOf(cliente.getIdCliente())};
        db.delete("tb_clientes", "idCliente = ?", params);
    }
}
