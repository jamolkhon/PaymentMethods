package com.example.paymentmethod.helper;

import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

public interface AssistedSavedStateViewModelFactory<T extends ViewModel> {

  T create(SavedStateHandle savedStateHandle);
}
