package br.com.alura.financask.ui.dialog

import android.content.Context
import android.view.ViewGroup
import br.com.alura.financask.R
import br.com.alura.financask.extensions.formataParaBrasileiro
import br.com.alura.financask.model.Tipo
import br.com.alura.financask.model.Transacao

class AlteraTransacaoDialog(
        viewGroup: ViewGroup,
        private val context: Context) : FormularioTransacaoDialog(context, viewGroup) {

    fun chama(transacao: Transacao,
              delegate: (transacao: Transacao) -> Unit) {
        val tipo = transacao.tipo

        super.chama(tipo, delegate)


        inicializaCampos(transacao)
    }

    private fun inicializaCampos(transacao: Transacao){
        val tipo = transacao.tipo

        inicializaValor(transacao)
        inicializaData(transacao)
        inicializaCategoria(tipo, transacao)
    }

    private fun inicializaValor(transacao: Transacao) = campoValor.setText(transacao.valor.toString())
    private fun inicializaData(transacao: Transacao) = campoData.setText(transacao.data.formataParaBrasileiro())
    private fun inicializaCategoria(tipo: Tipo, transacao: Transacao){
        val categoriasRetornadas = context.resources
                .getStringArray(categoriasPor(tipo))
        val posicaoCategoria = categoriasRetornadas.indexOf(transacao.categoria)
        campoCategoria.setSelection(posicaoCategoria, true)
    }

    override val tituloBotaoPositivo: String
        get() = "Alterar"

    override fun tituloPor(tipo: Tipo) = if (tipo == Tipo.RECEITA)
        R.string.altera_receita else R.string.altera_despesa

}