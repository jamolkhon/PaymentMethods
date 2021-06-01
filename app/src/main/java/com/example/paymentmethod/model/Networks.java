package com.example.paymentmethod.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * This class is designed to hold list of applicable and registered payment network descriptions.
 */
@Getter
@Setter
public class Networks {

  /**
   * Simple API, always present
   */
  private List<ApplicableNetwork> applicable;
}
