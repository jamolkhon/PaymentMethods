package com.example.paymentmethod.paymentmethods;

import com.example.paymentmethod.model.PaymentMethodItem;

import org.jetbrains.annotations.NotNull;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

public class PaymentMethodItemDiffer extends DiffUtil.ItemCallback<PaymentMethodItem> {

  @Override
  public boolean areItemsTheSame(@NonNull @NotNull PaymentMethodItem oldItem,
                                 @NonNull @NotNull PaymentMethodItem newItem) {
    return oldItem.getId().equals(newItem.getId());
  }

  @Override
  public boolean areContentsTheSame(@NonNull @NotNull PaymentMethodItem oldItem,
                                    @NonNull @NotNull PaymentMethodItem newItem) {
    return oldItem.equals(newItem);
  }
}
