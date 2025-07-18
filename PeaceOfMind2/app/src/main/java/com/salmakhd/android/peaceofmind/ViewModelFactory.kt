package com.salmakhd.android.peaceofmind

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.salmakhd.android.peaceofmind.db.SessionDao
import com.salmakhd.android.peaceofmind.db.UserDao
import com.salmakhd.android.peaceofmind.viewmodels.SessionViewModel
import com.salmakhd.android.peaceofmind.viewmodels.UserViewModel
import java.lang.IllegalArgumentException
import kotlin.reflect.KClass

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(
    private val sessionsDao: SessionDao,
    private val userDao: UserDao
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: KClass<T>, extras: CreationExtras): T {
        if(modelClass.isAssignableFrom(SessionViewModel::class.java)) {
            return SessionViewModel(sessionsDao) as T
        } else if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(userDao) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel Class")
        }
    }
}