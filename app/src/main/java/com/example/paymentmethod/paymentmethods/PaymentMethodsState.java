package com.example.paymentmethod.paymentmethods;

import com.example.paymentmethod.model.PaymentMethodItem;

import java.util.Collections;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@RequiredArgsConstructor
@ToString
public class PaymentMethodsState {

  private final List<PaymentMethodItem> paymentMethods;
  private final boolean loading;
  private final Throwable error;

  static PaymentMethodsState defaultState() {
    return new PaymentMethodsState(Collections.emptyList(), false, null);
  }

  PaymentMethodsState loading() {
    return new PaymentMethodsState(paymentMethods, true, null);
  }

  PaymentMethodsState loaded(List<PaymentMethodItem> paymentMethods) {
    return new PaymentMethodsState(paymentMethods, false, null);
  }

  PaymentMethodsState failed(Throwable error) {
    return new PaymentMethodsState(paymentMethods, false, error);
  }
}
