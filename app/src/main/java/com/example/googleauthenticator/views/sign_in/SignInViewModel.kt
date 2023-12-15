package com.example.googleauthenticator.views.sign_in

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.googleauthenticator.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date

class SignInViewModel(private val repository: UserRepository = UserRepository.getInstance()): ViewModel() {

    private val _state = MutableStateFlow(SignInState())
    val state = _state.asStateFlow()

    fun onSignInResult(result: SignInResult) {
        _state.update { it.copy(
            isSignInSuccessful = result.data != null,
            signInError = result.errorMessage
        ) }
        updateUserInfo(result)

    }


    private fun updateUserInfo(result: SignInResult){
        val params:MutableMap<String,String> = mutableMapOf()
        val currentTimeMillis = System.currentTimeMillis()
        val sdf = SimpleDateFormat("yyyy-MM-dd - HH:mm:ss")
         val time =  sdf.format(Date(currentTimeMillis))
        result?.data?.run {
            params["action"] = "create"
            params.put("email", email ?: "")
            params["time"] =  time
            params["location"] = ""
        }
        viewModelScope.launch {
            repository.updateUser(params)
        }
    }

    fun resetState() {
        _state.update { SignInState() }
    }
}