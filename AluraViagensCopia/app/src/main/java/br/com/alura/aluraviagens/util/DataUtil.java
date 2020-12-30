package br.com.alura.aluraviagens.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import br.com.alura.aluraviagens.model.Pacote;

public class DataUtil {

    public static final String DIAS_E_MES = "dd/MM";

    public static String periodoEmTexto(Pacote pacote) {
        Calendar dataIda = Calendar.getInstance();
        Calendar dataVolta = Calendar.getInstance();
        dataVolta.add(Calendar.DATE, pacote.getDias());
        SimpleDateFormat formatoBrasileiro = new SimpleDateFormat(DIAS_E_MES);
        String dataFormatadaIda = formatoBrasileiro.format(dataIda.getTime());
        String dataFormatadaVolta = formatoBrasileiro.format(dataVolta.getTime());
        return dataFormatadaIda + " - " + dataFormatadaVolta
                + " de " + dataVolta.get(Calendar.YEAR);
    }
}
