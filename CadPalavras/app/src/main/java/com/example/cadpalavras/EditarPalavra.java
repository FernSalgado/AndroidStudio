package com.example.cadpalavras;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditarPalavra extends AppCompatActivity {
    EditText edtTitulo;
    TextView edtId;
    Intent it;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_palavra);
        edtTitulo = (EditText) findViewById(R.id.txtTitulo);
        edtId = (TextView) findViewById(R.id.txtId);
        it = getIntent();
        edtId.setText(it.getStringExtra("id").toString());
        edtTitulo.setText(it.getStringExtra("titulo").toString());
    }

    public void alterarClick(View v) {
        ManipulaBD alteraLivro = new ManipulaBD(this);
        Livro lv = new Livro();
        lv.setId(Integer.parseInt(edtId.getText().toString()));
        lv.setTitulo(edtTitulo.getText().toString());
        alteraLivro.atualizar(lv);
        Toast.makeText(this, " o livro " + edtTitulo.getText() +
                "\n foi alterado com sucesso....", Toast.LENGTH_LONG).show();
        finish();
    }

    public void excluirClick(View v) {
        ManipulaBD excluirLivro = new ManipulaBD(this);
        Livro lv = new Livro();
        lv.setId(Integer.parseInt(edtId.getText().toString()));
        lv.setTitulo(edtTitulo.getText().toString());
        excluirLivro.deletar(lv);
        Toast.makeText(this, " o livro " + edtTitulo.getText() +
                "\n foi excluido com sucesso....", Toast.LENGTH_LONG).show();
        finish();
    }
}
