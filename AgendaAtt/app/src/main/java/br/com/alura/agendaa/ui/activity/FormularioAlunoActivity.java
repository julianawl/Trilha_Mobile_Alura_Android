 package br.com.alura.agendaa.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import br.com.alura.agendaa.R;
import br.com.alura.agendaa.asynctask.BuscaTodosTelefonesDoAlunoTask;
import br.com.alura.agendaa.asynctask.EditaAlunoTask;
import br.com.alura.agendaa.asynctask.SalvaAlunoTask;
import br.com.alura.agendaa.database.AgendaDatabase;
import br.com.alura.agendaa.database.TelefoneDAO;
import br.com.alura.agendaa.database.dao.AlunoDAO;
import br.com.alura.agendaa.model.Aluno;
import br.com.alura.agendaa.model.Telefone;
import br.com.alura.agendaa.model.TipoTelefone;

import static br.com.alura.agendaa.ui.activity.ConstantesActivities.CHAVE_ALUNO;

public class FormularioAlunoActivity extends AppCompatActivity {

    private static final String TITLE_APPBAR_NOVO_ALUNO = "Novo Aluno";
    private static final String TITLE_APPBAR_EDITA_ALUNO = "Edita Aluno";
    private EditText campoNome;
    private EditText campoTelefoneFixo;
    private EditText campoTelefoneCelular;
    private EditText campoEmail;
    private AlunoDAO alunoDAO;
    private Aluno aluno;
    private TelefoneDAO telefoneDAO;
    private List<Telefone> telefonesDoAluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);
        AgendaDatabase database = AgendaDatabase.getInstance(this);
        alunoDAO = database.getAlunoDAO();
        telefoneDAO = database.getTelefoneDAO();
        inicializacaoDosCampos();
        carregaAluno();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater()
                .inflate(R.menu.activity_formulario_aluno_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if(itemId == R.id.activity_formulario_aluno_salvar){
            finalizaFormulario();
        }
        return super.onOptionsItemSelected(item);
    }

    private void carregaAluno() {
        Intent dados = getIntent();
        if (dados.hasExtra(CHAVE_ALUNO)){
            setTitle(TITLE_APPBAR_EDITA_ALUNO);
            aluno = (Aluno) dados.getSerializableExtra(CHAVE_ALUNO);
            preencheCampos();
        } else{
            setTitle(TITLE_APPBAR_NOVO_ALUNO);
            aluno = new Aluno();
        }
    }

    private void preencheCampos() {
        campoNome.setText(aluno.getNome());
        preencheCamposDeTelefone();
        campoEmail.setText(aluno.getEmail());
    }

    private void preencheCamposDeTelefone() {
        new BuscaTodosTelefonesDoAlunoTask(telefoneDAO, aluno, telefones -> {
            this.telefonesDoAluno=telefones;
            for(Telefone telefone:
                    telefonesDoAluno){
                if(telefone.getTipo() == TipoTelefone.FIXO){
                    campoTelefoneFixo.setText(telefone.getNumero());
                }else{
                    campoTelefoneCelular.setText(telefone.getNumero());
                }
            }
        }).execute();

    }

    private void finalizaFormulario() {
        preencheAluno();

        Telefone telefoneFixo = criaTelefone(campoTelefoneFixo, TipoTelefone.FIXO);
        Telefone telefoneCelular = criaTelefone(campoTelefoneCelular, TipoTelefone.CELULAR);

        if(aluno.temIdValido()){
            editaAluno(telefoneFixo, telefoneCelular);
        } else{
            salvaAluno(telefoneFixo, telefoneCelular);
        }
    }

    private Telefone criaTelefone(EditText campoTelefoneFixo, TipoTelefone fixo) {
        String numeroFixo = campoTelefoneFixo.getText().toString();
        return new Telefone(numeroFixo, fixo);
    }

    private void salvaAluno(Telefone telefoneFixo, Telefone telefoneCelular) {
        new SalvaAlunoTask(alunoDAO, aluno,
                telefoneFixo, telefoneCelular, telefoneDAO, this::finish)
                .execute();
    }

    private void editaAluno(Telefone telefoneFixo, Telefone telefoneCelular) {
        new EditaAlunoTask(alunoDAO, aluno,
                telefoneFixo, telefoneCelular,
                telefoneDAO, telefonesDoAluno, this::finish).execute();
    }

    private void inicializacaoDosCampos() {
        campoNome = findViewById(R.id.activity_formulario_aluno_nome);
        campoTelefoneFixo = findViewById(R.id.activity_formulario_aluno_telefone_fixo);
        campoTelefoneCelular = findViewById(R.id.activity_formulario_aluno_telefone_celular);
        campoEmail = findViewById(R.id.activity_formulario_aluno_email);
    }

    private void preencheAluno() {
        String nome = campoNome.getText().toString();
        String email = campoEmail.getText().toString();

        aluno.setNome(nome);
        aluno.setEmail(email);
    }
}