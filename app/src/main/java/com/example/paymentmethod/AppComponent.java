package com.example.paymentmethod;

import android.content.Context;

import com.example.paymentmethod.paymentmethods.PaymentMethodsViewModel;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import okhttp3.HttpUrl;

@Component(modules = AppModule.class)
@Singleton
public interface AppComponent {

  @Component.Factory
  interface Factory {
    AppComponent create(@BindsInstance Context context,
                        @BindsInstance @Named("base_url") HttpUrl httpUrl);
  }

  //Api api();

  PaymentMethodsViewModel.Factory paymentMethodsViewModelFactory();
}
