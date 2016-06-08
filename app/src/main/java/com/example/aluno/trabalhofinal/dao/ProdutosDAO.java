package com.example.aluno.trabalhofinal.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.aluno.trabalhofinal.Model.Produto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anderson Cardoso on 04/06/2016.
 */
public class ProdutosDAO extends SQLiteOpenHelper {
    private final String bdName = "TrabalhoFinal";

    public ProdutosDAO(Context context) {
        super(context, "TrabalhoFinal", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE tb_produtos " +
                "(idProduto INTEGER PRIMARY KEY, " +
                "marca TEXT NOT NULL, " +
                "modelo TEXT, " +
                "preco TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXIST tb_produtos";
        db.execSQL(sql);
        onCreate(db);
    }

    public void insereProduto(Produto produto){

        ContentValues dados = new ContentValues();
        dados.put("idProduto", produto.getIdProduto());
        dados.put("marca", produto.getMarca());
        dados.put("modelo", produto.getModelo());
        dados.put("preco", produto.getPreco());

        SQLiteDatabase db = getWritableDatabase();
        db.insert("tb_produtos", null, dados);
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

    public void deletaProduto(Produto produto) {
        SQLiteDatabase db = getWritableDatabase();
        String[] params = {String.valueOf(produto.getIdProduto())};
        db.delete("tb_produtos", "idProduto = ?", params);
    }
}
