package com.example.googleauthenticator.views.sign_in

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.googleauthenticator.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val repository: UserRepository
): ViewModel() {

    private val _state = MutableStateFlow(SignInState())
    val state = _state.asStateFlow()

    fun onSignInResult(result: SignInResult) {
        _state.update { it.copy(
            isSignInSuccessful = result.data != null,
            signInError = result.errorMessage
        ) }
        updateUserInfo(result)

    }


    private fun updateUserInfo(result: SignInResult?){
        val params:MutableMap<String,String> = mutableMapOf()
        val currentTimeMillis = System.currentTimeMillis()
        val sdf = SimpleDateFormat("yyyy-MM-dd - HH:mm:ss") //Date and time can be formatted to any format using getDateInstance or getTimeInstance, keeping it default for now
         val time =  sdf.format(Date(currentTimeMillis))
        result?.data?.run {
            params["action"] = "create"
            params["email"] = email ?: ""
            params["time"] =  time
        }
        viewModelScope.launch {
            repository.getLocation().let {
                params["location"] = "${it.first} - ${it.second}"
            }
            repository.updateUser(params)
        }
    }

    fun resetState() {
        _state.update { SignInState() }
    }
}