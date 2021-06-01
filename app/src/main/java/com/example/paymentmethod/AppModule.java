package com.example.paymentmethod;

import com.example.paymentmethod.helper.URLAdapter;
import com.squareup.moshi.Moshi;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

@Module
public abstract class AppModule {

  @Provides
  @Singleton
  static Api api(Retrofit retrofit) {
    return retrofit.create(Api.class);
  }

  @Provides
  @Singleton
  static Retrofit retrofit(@Named("base_url") HttpUrl baseUrl, OkHttpClient client, Moshi moshi) {
    return new Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(client)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build();
  }

  @Provides
  @Singleton
  static OkHttpClient okHttpClient() {
    return new OkHttpClient.Builder().build();
  }

  @Provides
  @Singleton
  static Moshi moshi() {
    return new Moshi.Builder()
        .add(new URLAdapter())
        .build();
  }
}
