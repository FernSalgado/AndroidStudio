package com.example.cadpalavras;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListarPalavras extends ListActivity {
    String regLivro;
    List<Livro> listLivros;
    ArrayList<String> arrlLivro;
    ManipulaBD db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_listar_palavras);
        arrlLivro = new ArrayList<String>();
        db = new ManipulaBD(this);
        listLivros = db.buscar();
        for (Livro lv : listLivros) {
            regLivro = lv.getId() + " " + lv.getTitulo();
            arrlLivro.add(regLivro);
            //Toast.makeText(this, lv.getTitulo(), Toast.LENGTH_SHORT).show();
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrlLivro);
        this.setListAdapter(dataAdapter);
    }
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        //Toast.makeText(this, "Registro selecionado: "+(position+1), Toast.LENGTH_SHORT).show();
        Intent it = new Intent(this, EditarPalavra.class);
        String tit = listLivros.get(position).getTitulo();
        String idlivro = "" + listLivros.get(position).getId();
        Toast.makeText(this, "" + idlivro + "-" + tit, Toast.LENGTH_SHORT).show();
        it.putExtra("id", idlivro);
        it.putExtra("titulo", tit);
        startActivity(it);
        finish();
    }

}
