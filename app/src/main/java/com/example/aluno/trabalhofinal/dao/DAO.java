package com.example.aluno.trabalhofinal.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aluno on 30/05/2016.
 */
public class DAO {
    private final String bdName = "Agenda";

    public DAO(Context context) {
        super(context, "Agenda", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Alunos " +
                "(id INTEGER PRIMARY KEY, " +
                "nome TEXT NOT NULL, " +
                "endereco TEXT, " +
                "telefone TEXT, " +
                "email TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXIST Alunos";
        db.execSQL(sql);
        onCreate(db);
    }

    public void insere(Aluno aluno){

        ContentValues dados = new ContentValues();
        dados.put("nome", aluno.getNome());
        dados.put("endereco", aluno.getEndereco());
        dados.put("telefone", aluno.getTelefone());
        dados.put("email", aluno.getEmail());

        SQLiteDatabase db = getWritableDatabase();
        db.insert("Alunos", null, dados);
    }

    public List<Aluno> getAlunos(){
        String sql = "SELECT * FROM Alunos";

        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        List<Aluno> alunos = new ArrayList<Aluno>();

        while(cursor.moveToNext()){
            Aluno aluno = new Aluno();
            aluno.setId(cursor.getLong(
                    cursor.getColumnIndex("id")));
            aluno.setNome(cursor.getString(
                    cursor.getColumnIndex("nome")));
            aluno.setEndereco(cursor.getString(
                    cursor.getColumnIndex("endereco")));
            aluno.setTelefone(cursor.getString(
                    cursor.getColumnIndex("telefone")));
            aluno.setEmail(cursor.getString(
                    cursor.getColumnIndex("email")));

            alunos.add(aluno);
        }

        cursor.close();
        return alunos;
    }

    public void altera(Aluno aluno) {
        ContentValues dados = new ContentValues();
        dados.put("nome", aluno.getNome());
        dados.put("endereco", aluno.getEndereco());
        dados.put("telefone", aluno.getTelefone());
        dados.put("email", aluno.getEmail());

        SQLiteDatabase db = getWritableDatabase();
        String[] params = {String.valueOf(aluno.getId())};
        db.update("Alunos", dados, "id = ?", params);
    }

    public void deleta(Aluno aluno) {
        SQLiteDatabase db = getWritableDatabase();
        String[] params = {String.valueOf(aluno.getId())};
        db.delete("Alunos", "id = ?", params);
    }
}
