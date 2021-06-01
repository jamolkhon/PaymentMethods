package com.example.paymentmethod;

import com.example.paymentmethod.model.ApplicableNetwork;
import com.example.paymentmethod.model.ListResult;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import io.reactivex.rxjava3.core.Single;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okio.Buffer;

public class ApiTest {

  private MockWebServer server;

  @Before
  public void setup() throws IOException {
    server = new MockWebServer();
    server.start();
  }

  @After
  public void tearDown() throws IOException {
    server.shutdown();
  }

  @Test
  public void fetch() throws IOException {
    server.enqueue(new MockResponse().setBody(
        new Buffer().readFrom(getClass().getResourceAsStream("/networks.json"))
    ));

    Api api = DaggerTestComponent.factory().create(server.url("/")).api();
    ListResult result = api.fetch().blockingGet().body();
    List<ApplicableNetwork> networks = result.getNetworks().getApplicable();
    Assert.assertEquals(17, networks.size());
    Assert.assertEquals("AMEX", networks.get(0).getCode());
    Assert.assertEquals("American Express", networks.get(0).getLabel());
    Assert.assertEquals("DINERS", networks.get(1).getCode());
    Assert.assertEquals("Diners Club", networks.get(1).getLabel());
  }
}
