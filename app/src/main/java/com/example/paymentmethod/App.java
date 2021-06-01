package com.example.paymentmethod;

import android.app.Application;

import okhttp3.HttpUrl;
import timber.log.Timber;

public class App extends Application {

  public AppComponent appComponent;

  @Override
  public void onCreate() {
    super.onCreate();

    if (BuildConfig.DEBUG) {
      Timber.plant(new Timber.DebugTree());
    } else {
      // TODO: Timber.Tree implementation that logs to firebase
    }

    appComponent = DaggerAppComponent.factory().create(this, HttpUrl.get("https://raw.githubusercontent.com/"));
  }
}
