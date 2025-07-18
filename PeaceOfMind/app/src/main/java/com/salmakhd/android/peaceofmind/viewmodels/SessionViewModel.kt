package com.salmakhd.android.peaceofmind.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.salmakhd.android.peaceofmind.db.SessionDao
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class SessionViewModel(
    private val sessionDao: SessionDao
): ViewModel() {
    val sessions = sessionDao.getSessions()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )
}