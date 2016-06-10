package com.example.aluno.trabalhofinal;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.aluno.trabalhofinal.Model.Cliente;
import com.example.aluno.trabalhofinal.dao.ClientesDAO;

import java.util.List;

public class ClientesActivity extends AppCompatActivity {
    private String[] clientes;
    private ListView lvClientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes);

        lvClientes = (ListView)findViewById(R.id.listView);
        lvClientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long idCliente) {
                Cliente cliente = (Cliente) lvClientes.getItemAtPosition(position);

                Intent intent = new Intent(ClientesActivity.this, InfPessoaisActivity.class);
                intent.putExtra("cliente", cliente);
                startActivity(intent);
            }
        });

        registerForContextMenu(lvClientes);

    }


    @Override
    protected void onResume() {
        super.onResume();
        carregaLista();
    }

    private void carregaLista() {
        ClientesDAO dao = new ClientesDAO(this);
        List<Cliente> cliente = dao.getCliente();
        ArrayAdapter<Cliente> adapter = new ArrayAdapter<Cliente>
                (this, android.R.layout.simple_list_item_1, cliente);
        lvClientes.setAdapter(adapter);
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
        Intent intentForm = new Intent(this, InfPessoaisActivity.class);
        startActivity(intentForm);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        final MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Cliente cliente = (Cliente) lvClientes.getItemAtPosition(info.position);
                deletarCliente(cliente);
                return false;
            }
        });


    }

    private void deletarCliente(Cliente cliente) {
        ClientesDAO dao = new ClientesDAO(this);
        dao.deletaCliente(cliente);
        carregaLista();
    }


}