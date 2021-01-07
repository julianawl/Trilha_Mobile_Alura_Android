package br.com.alura.agendaa.asynctask;

import android.os.AsyncTask;

import java.util.List;

import br.com.alura.agendaa.database.dao.AlunoDAO;
import br.com.alura.agendaa.model.Aluno;
import br.com.alura.agendaa.ui.activity.ListaAlunosAdapter;

public class BuscaAlunosTask extends AsyncTask<Void, Void, List<Aluno>> {
    private final AlunoDAO dao;
    private final ListaAlunosAdapter adapter;

    public BuscaAlunosTask (AlunoDAO dao, ListaAlunosAdapter adapter){

        this.dao = dao;
        this.adapter = adapter;
    }
    @Override
    protected List<Aluno> doInBackground(Void[] objects) {
        return dao.todos();
    }

    @Override
    protected void onPostExecute(List<Aluno> todosAlunos) {
        super.onPostExecute(todosAlunos);
        adapter.atualiza(todosAlunos);
    }
}
