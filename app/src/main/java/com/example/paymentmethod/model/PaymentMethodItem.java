package com.example.paymentmethod.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@EqualsAndHashCode
@RequiredArgsConstructor
public class PaymentMethodItem {

  private final String id;
  private final String label;
  private final String logoUrl;
}
