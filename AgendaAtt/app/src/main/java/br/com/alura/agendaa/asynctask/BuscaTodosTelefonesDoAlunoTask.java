package br.com.alura.agendaa.asynctask;

import android.os.AsyncTask;

import java.util.List;

import br.com.alura.agendaa.database.TelefoneDAO;
import br.com.alura.agendaa.model.Aluno;
import br.com.alura.agendaa.model.Telefone;

public class BuscaTodosTelefonesDoAlunoTask extends AsyncTask<Void, Void, List<Telefone>> {
    private final TelefoneDAO telefoneDAO;
    private final Aluno aluno;
    private final TelefoneDoAlunoEncontradoListener listener;

    public BuscaTodosTelefonesDoAlunoTask(TelefoneDAO telefoneDAO, Aluno aluno, TelefoneDoAlunoEncontradoListener listener) {
        this.telefoneDAO = telefoneDAO;
        this.aluno = aluno;
        this.listener = listener;
    }

    @Override
    protected List<Telefone> doInBackground(Void... voids) {
        return telefoneDAO.buscaTodosTelefonesDoAluno(aluno.getId());
    }

    @Override
    protected void onPostExecute(List<Telefone> telefones) {
        super.onPostExecute(telefones);
        listener.quandoEncontrados(telefones);
    }

    public interface TelefoneDoAlunoEncontradoListener{
        void quandoEncontrados(List<Telefone> telefones);
    }
}
