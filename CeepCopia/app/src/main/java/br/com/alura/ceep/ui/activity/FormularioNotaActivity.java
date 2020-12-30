package br.com.alura.ceep.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import br.com.alura.ceep.R;
import br.com.alura.ceep.model.Nota;

import static br.com.alura.ceep.ui.activity.NotaActivityConstantes.CHAVE_NOTA;
import static br.com.alura.ceep.ui.activity.NotaActivityConstantes.POSICAO_INVALIDA;
import static br.com.alura.ceep.ui.activity.NotaActivityConstantes.POSITION;

public class FormularioNotaActivity extends AppCompatActivity {

    public static final String INSERE_NOTA_APPBAR = "Insere nota";
    public static final String ALTERA_NOTA_APPBAR = "Altera nota";
    private int posicaoRecebida = POSICAO_INVALIDA;

    private TextView titulo;
    private TextView descricao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_nota);
        setTitle(INSERE_NOTA_APPBAR);

        inicializaCampos();

        Intent dadosRecebidos = getIntent();
        if (dadosRecebidos.hasExtra(CHAVE_NOTA)) {
            setTitle(ALTERA_NOTA_APPBAR);
            Nota notaRecebida = (Nota) dadosRecebidos
                    .getSerializableExtra(CHAVE_NOTA);
            posicaoRecebida = dadosRecebidos.getIntExtra(POSITION,
                    POSICAO_INVALIDA);
            preencheCampos(notaRecebida);
        }
    }

    private void inicializaCampos() {
        titulo = findViewById(R.id.formulario_nota_titulo);
        descricao = findViewById(R.id.formulario_nota_descricao);
    }

    private void preencheCampos(Nota nota) {
        titulo.setText(nota.getTitulo());
        descricao.setText(nota.getDescricao());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_formulario_nota_salva, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (ehMenuSalvarNota(item)) {
            Nota notaCriada = criaNota();
            retornaNota(notaCriada);
        }
        return super.onOptionsItemSelected(item);
    }

    private void retornaNota(Nota nota) {
        Intent resultadoInsercao = new Intent();
        resultadoInsercao.putExtra(CHAVE_NOTA, nota);
        resultadoInsercao.putExtra(POSITION, posicaoRecebida);
        setResult(Activity.RESULT_OK, resultadoInsercao);
        finish();
    }

    private Nota criaNota() {
        return new Nota(titulo.getText().toString()
                , descricao.getText().toString());
    }

    private boolean ehMenuSalvarNota(@NonNull MenuItem item) {
        return item.getItemId() == R.id.menu_formulario_nota_salva;
    }
}