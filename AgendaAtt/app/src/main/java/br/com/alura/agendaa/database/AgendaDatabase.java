package br.com.alura.agendaa.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import br.com.alura.agendaa.database.converter.ConversorCalendar;
import br.com.alura.agendaa.database.converter.ConversorTipoTelefone;
import br.com.alura.agendaa.database.dao.AlunoDAO;
import br.com.alura.agendaa.model.Aluno;
import br.com.alura.agendaa.model.Telefone;

import static br.com.alura.agendaa.database.AgendaMigrations.TODAS_MIGRATIONS;

@Database(entities = {Aluno.class, Telefone.class}, version = 6, exportSchema = false)
@TypeConverters({ConversorCalendar.class, ConversorTipoTelefone.class})
public abstract class AgendaDatabase extends RoomDatabase {

    public static final String NOME_DB = "agenda.db";
    public abstract AlunoDAO getAlunoDAO();
    public abstract TelefoneDAO getTelefoneDAO();

    public static  AgendaDatabase getInstance(Context context){
        return Room
                .databaseBuilder(context, AgendaDatabase.class, NOME_DB)
                .addMigrations(TODAS_MIGRATIONS)
                .build();
    }

}
