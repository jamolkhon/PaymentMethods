package com.example.paymentmethod.model;

import lombok.Getter;
import lombok.Setter;

/**
 * This class is designed to hold list of payment networks available for particular transaction based on provided information and result of
 * initialized payment session.
 * <p>
 * An instance of this object is returned as a result of new <code>Transaction</code> initialization, or during list status update via GET
 * method.
 */
@Getter
@Setter
public class ListResult {

  /**
   * Simple API, optional, always present in native LIST
   */
  private Networks networks;
}
