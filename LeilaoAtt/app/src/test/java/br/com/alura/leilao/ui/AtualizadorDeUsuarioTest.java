package br.com.alura.leilao.ui;

import android.support.v7.widget.RecyclerView;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.alura.leilao.database.dao.UsuarioDAO;
import br.com.alura.leilao.model.Usuario;
import br.com.alura.leilao.ui.recyclerview.adapter.ListaUsuarioAdapter;

@RunWith(MockitoJUnitRunner.class)
public class AtualizadorDeUsuarioTest {

    @Mock
    private UsuarioDAO dao;
    @Mock
    private ListaUsuarioAdapter adapter;
    @Mock
    private RecyclerView recyclerview;

    @Test
    public void deve_AtualizarListaDeUsuarios_QuandoSalvarUsuario(){
        AtualizadorDeUsuario atualizador = new AtualizadorDeUsuario(dao, adapter, recyclerview);

        Usuario alex = new Usuario("Alex");
        Mockito.when(dao.salva(alex)).thenReturn(new Usuario(1, "Alex"));
        Mockito.when(adapter.getItemCount()).thenReturn(1);
        atualizador.salva(alex);

        Mockito.verify(dao).salva(new Usuario("Alex"));
        Mockito.verify(adapter).adiciona(new Usuario(1, "Alex"));
        Mockito.verify(recyclerview).smoothScrollToPosition(0);

    }
}