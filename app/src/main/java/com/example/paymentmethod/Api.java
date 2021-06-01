package com.example.paymentmethod;

import com.example.paymentmethod.model.ListResult;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Response;
import retrofit2.http.GET;

public interface Api {

  @GET("optile/checkout-android/develop/shared-test/lists/listresult.json")
  Single<Response<ListResult>> fetch();
}
