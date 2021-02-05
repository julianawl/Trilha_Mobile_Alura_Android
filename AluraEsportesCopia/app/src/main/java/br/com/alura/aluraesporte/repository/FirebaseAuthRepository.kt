package br.com.alura.aluraesporte.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.alura.aluraesporte.model.Usuario
import com.google.firebase.auth.*
import kotlin.math.log

private const val TAG = "FirebaseAuthRepository"

class FirebaseAuthRepository(private val firebaseAuth: FirebaseAuth) {

    fun desloga() {
        firebaseAuth.signOut()
    }

    fun cadastraUsuario(usuario: Usuario): LiveData<Resource<Boolean>> {
        val liveData = MutableLiveData<Resource<Boolean>>()
        try {
            val tarefa =
                firebaseAuth.createUserWithEmailAndPassword(usuario.email, usuario.senha)
            tarefa.addOnSuccessListener {
                Log.i(TAG, "Cadastro feito")
                liveData.value = Resource(true)
            }
            tarefa.addOnFailureListener { exception ->
                Log.e(TAG, "cadastro falhou", exception)
                val mensagemErro: String = devolveErroCadastro(exception)
                liveData.value = Resource(false, mensagemErro)
            }
        } catch (e: IllegalArgumentException) {
            liveData.value = Resource(false, "Email ou senha não pode ser vazio")
        }
        return liveData
    }

    private fun devolveErroCadastro(exception: Exception): String {
        return when (exception) {
            is FirebaseAuthWeakPasswordException -> "Senha fraca (mínimo 6 dígitos)"
            is FirebaseAuthInvalidCredentialsException -> "Email inválido"
            is FirebaseAuthUserCollisionException -> "Email já cadastrado"
            else -> "Erro desconhecido"
        }
    }

    fun estalogado(): Boolean {
        val usuarioFirebase: FirebaseUser? = firebaseAuth.currentUser
        if (usuarioFirebase != null) {
            return true
        }
        return false
    }

    fun autentica(usuario: Usuario) : LiveData<Resource<Boolean>> {
        val liveData = MutableLiveData<Resource<Boolean>>()
        try {
            firebaseAuth.signInWithEmailAndPassword(usuario.email, usuario.senha)
                .addOnCompleteListener {
                    if(it.isSuccessful){
                        liveData.value = Resource(true)
                    } else {
                        Log.e(TAG, "autentica: ", it.exception)
                        val mensagemErro: String = devolveErroLogin(it.exception)
                        liveData.value = Resource(false, mensagemErro)
                    }
                }
        } catch (e: IllegalArgumentException) {
            liveData.value = Resource(false, "Email ou senha não pode ser vazio")
        }
        return liveData
    }

    private fun devolveErroLogin(exception: Exception?): String {
        return when(exception){
            is FirebaseAuthInvalidUserException -> "E-mail ou senha incorretos"
            is FirebaseAuthInvalidCredentialsException -> "E-mail ou senha incorretos"
            else -> "Erro desconhecido"
        }
    }

    fun usuario(): LiveData<Usuario> {
        val liveData = MutableLiveData<Usuario>()
        firebaseAuth.currentUser?.let {
            it.email?.let { email ->
                liveData.value = Usuario(email)
            }
        }
        return liveData
    }
}