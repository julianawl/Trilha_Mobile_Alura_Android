package br.com.alura.leilao.ui.recyclerview.adapter;


import android.content.Context;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;

import br.com.alura.leilao.model.Leilao;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ListaLeilaoAdapterTest{

    @Mock
    private Context context;
    @Spy
    private ListaLeilaoAdapter adapter = new ListaLeilaoAdapter(context);

    @Test
    public void deve_AtualizarListaDeLeiloes_QuandoReceberListaDeLeiloes(){
        doNothing().when(adapter).atualizaLista();

        adapter.atualiza(new ArrayList<>(Arrays.asList(
                new Leilao ("Barco"),
                new Leilao("Piano")
        )));
        int qtdLeilosDevolvida = adapter.getItemCount();

        verify(adapter).atualizaLista();
        assertThat(qtdLeilosDevolvida, is(2));
    }

}