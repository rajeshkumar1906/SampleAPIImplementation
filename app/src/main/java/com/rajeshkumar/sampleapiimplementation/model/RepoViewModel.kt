package com.rajeshkumar.sampleapiimplementation.model

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class RepoViewModel @Inject constructor(private val savedStateHandle: SavedStateHandle):ViewModel() {
}