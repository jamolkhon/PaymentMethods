package com.example.paymentmethod.helper;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;

import java.net.MalformedURLException;
import java.net.URL;

public class URLAdapter {

  @FromJson
  URL fromJson(String url) throws MalformedURLException {
    return new URL(url);
  }

  @ToJson
  String toJson(URL url) {
    return url.toString();
  }
}
