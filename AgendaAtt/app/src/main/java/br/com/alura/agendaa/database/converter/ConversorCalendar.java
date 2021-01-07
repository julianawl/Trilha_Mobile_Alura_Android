package br.com.alura.agendaa.database.converter;

import androidx.room.TypeConverter;

import java.util.Calendar;

import static java.util.Calendar.getInstance;

public class ConversorCalendar {

    @TypeConverter
    public Long paraLong(Calendar valor){
        if(valor != null){
            return valor.getTimeInMillis();
        }
        return null;
    }

    @TypeConverter
    public  Calendar paraCalendar(Long valor){
        Calendar momentoAtual = getInstance();
        if(valor != null){
            momentoAtual.setTimeInMillis(valor);
        }
        return momentoAtual;
    }
}
