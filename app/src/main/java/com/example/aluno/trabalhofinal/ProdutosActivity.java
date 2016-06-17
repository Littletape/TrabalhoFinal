package com.example.aluno.trabalhofinal;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.aluno.trabalhofinal.Model.Produto;
import com.example.aluno.trabalhofinal.dao.DAO;

import java.util.List;

public class ProdutosActivity extends AppCompatActivity {
    private String[] produtos;
    private ListView lvProdutos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produtos);
        lvProdutos = (ListView)findViewById(R.id.lvProdutos);
        lvProdutos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long idProduto) {
                Produto produto = (Produto) lvProdutos.getItemAtPosition(position);

                Intent intent = new Intent(ProdutosActivity.this, CadProdutosActivity.class);
                intent.putExtra("produto", produto);
                startActivity(intent);
            }
        });

        registerForContextMenu(lvProdutos);

    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaLista();
    }

    private void carregaLista() {
        DAO dao = new DAO(this);
        List<Produto> produtos = dao.getProduto();
        ArrayAdapter<Produto> adapter = new ArrayAdapter<Produto>
                (this, android.R.layout.simple_list_item_1, produtos);
        lvProdutos.setAdapter(adapter);
        dao.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClickNovo(View view) {
        Intent intentForm = new Intent(this, CadProdutosActivity.class);
        startActivity(intentForm);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        final MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Produto produto = (Produto) lvProdutos.getItemAtPosition(info.position);
                deletarProduto(produto);
                return false;
            }
        });


    }

    private void deletarProduto(Produto produto) {
        DAO dao = new DAO(this);
        dao.deletaProduto(produto);
        carregaLista();
    }




}
