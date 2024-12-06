package com.example.todosapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todosapp.model.UserProfile

class ProfileViewModel : ViewModel() {

    private val _userProfile = MutableLiveData<UserProfile>()
    val userProfile: LiveData<UserProfile> = _userProfile

    fun setUserProfile(name: String, email: String) {
        _userProfile.value = UserProfile(name, email)
    }
}