package br.com.alura.financask.extensions

import java.text.SimpleDateFormat
import java.util.*

fun Calendar.formataParaBrasileiro(): String{
    val formatoBR = "dd/MM/yyyy"
    val format = SimpleDateFormat(formatoBR)
    return format.format(this.time)
}
