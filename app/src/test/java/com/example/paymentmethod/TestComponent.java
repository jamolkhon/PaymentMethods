package com.example.paymentmethod;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import okhttp3.HttpUrl;

@Component(modules = AppModule.class)
@Singleton
public interface TestComponent {

  @Component.Factory
  interface Factory {
    TestComponent create(@BindsInstance @Named("base_url") HttpUrl httpUrl);
  }

  Api api();
}
