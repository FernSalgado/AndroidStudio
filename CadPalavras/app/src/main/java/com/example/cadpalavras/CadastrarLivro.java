package com.example.cadpalavras;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class CadastrarLivro extends AppCompatActivity {
    private Livro livro = new Livro();
    private EditText edtTitulo;
    private Button btnGravar;
    private final int REQ_CODE_SPEECH_INPUT = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_livro);
        edtTitulo = (EditText) findViewById(R.id.edtText);
        btnGravar = (Button) findViewById(R.id.button);
        //getActionBar().hide();

        /*btnGravar.setOnClickListener(new View.OnClickListener() {

            @Override

        });*/
        }
        public void onClick(View v) {
            promptSpeechInput();
        }
        private void promptSpeechInput() {
            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                    RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                    getString(R.string.speech_prompt));
            try {
                startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
            } catch (ActivityNotFoundException a) {
                Toast.makeText(getApplicationContext(),
                        getString(R.string.speech_not_supported),
                        Toast.LENGTH_SHORT).show();
            }
        }
        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);

            switch (requestCode) {
                case REQ_CODE_SPEECH_INPUT: {
                    if (resultCode == RESULT_OK && null != data) {

                        ArrayList<String> result = data
                                .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                        edtTitulo.setText(result.get(0));
                    }
                    break;
                }

            }
        }
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.main, menu);
            return true;
        }

        public void gravarClick (View v){
            livro.setTitulo(edtTitulo.getText().toString());
            ManipulaBD bd = new ManipulaBD(this);
            bd.inserir(livro);
            Toast.makeText(this, "Palavra cadastrada com sucesso", Toast.LENGTH_SHORT).show();
            finish();

        }

        public void cancelarClick (View v){
            finish();
        }
}





