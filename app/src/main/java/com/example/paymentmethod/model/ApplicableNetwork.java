package com.example.paymentmethod.model;

import java.net.URL;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * This class is designed to hold information about applicable payment network.
 */
@Getter
@Setter
public class ApplicableNetwork {

  /**
   * Simple API, always present
   */
  private String code;

  /**
   * Simple API, always present
   */
  private String label;

  /**
   * Simple API, always present
   */
  private Map<String, URL> links;
}
