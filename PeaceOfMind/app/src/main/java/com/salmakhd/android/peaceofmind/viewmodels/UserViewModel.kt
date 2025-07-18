package com.salmakhd.android.peaceofmind.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.salmakhd.android.peaceofmind.db.UserDao
import com.salmakhd.android.peaceofmind.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

sealed interface UserEvent {
    data class NameFieldChanged(val newValue: String): UserEvent
    data class UsernameFieldValueChanged(val newValue: String): UserEvent
    data class PhoneFieldValueChanged(val newValue: String): UserEvent
    data class PasswordFieldValueChanged(val newValue: String): UserEvent
}

class UserViewModel(
    private val userDao: UserDao
): ViewModel() {
    // for viewing
    val user = userDao.getUser()
        .stateIn(
            scope = viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            User()
        )

    // for creating
    val _userState = MutableStateFlow(User())
    val userState = _userState.asStateFlow()

    fun onEvent(event: UserEvent) {
        when(event) {
            is UserEvent.NameFieldChanged -> {
                _userState.update {
                    it.copy(
                        name = event.newValue
                    )
                }
            }
            is UserEvent.PasswordFieldValueChanged -> {
                _userState.update {
                    it.copy(
                        password = event.newValue
                    )
                }
            }
            is UserEvent.PhoneFieldValueChanged -> {
                _userState.update {
                    it.copy(
                        phoneNumber = event.newValue
                    )
                }
            }
            is UserEvent.UsernameFieldValueChanged -> {
                _userState.update {
                    it.copy(
                        username = event.newValue
                    )
                }
            }
        }
    }
}