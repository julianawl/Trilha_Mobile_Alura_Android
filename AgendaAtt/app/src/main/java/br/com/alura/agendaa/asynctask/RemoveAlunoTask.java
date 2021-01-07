package br.com.alura.agendaa.asynctask;

import android.os.AsyncTask;

import br.com.alura.agendaa.database.dao.AlunoDAO;
import br.com.alura.agendaa.model.Aluno;
import br.com.alura.agendaa.ui.activity.ListaAlunosAdapter;

public class RemoveAlunoTask extends AsyncTask<Void, Void, Void>{
    private final AlunoDAO dao;
    private final ListaAlunosAdapter adapter;
    private final Aluno aluno;

    public RemoveAlunoTask(AlunoDAO dao, ListaAlunosAdapter adapter, Aluno aluno) {
        this.dao = dao;
        this.adapter = adapter;
        this.aluno = aluno;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        dao.remove(aluno);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        adapter.remove(aluno);
    }
}
