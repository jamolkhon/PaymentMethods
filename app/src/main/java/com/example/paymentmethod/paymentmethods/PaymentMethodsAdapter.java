package com.example.paymentmethod.paymentmethods;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.paymentmethod.R;
import com.example.paymentmethod.model.PaymentMethodItem;

import org.jetbrains.annotations.NotNull;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

public class PaymentMethodsAdapter extends ListAdapter<PaymentMethodItem, PaymentMethodItemViewHolder> {

  protected PaymentMethodsAdapter(@NonNull @NotNull DiffUtil.ItemCallback<PaymentMethodItem> diffCallback) {
    super(diffCallback);
  }

  @NonNull
  @NotNull
  @Override
  public PaymentMethodItemViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.payment_method_item, parent, false);
    return new PaymentMethodItemViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull @NotNull PaymentMethodItemViewHolder holder, int position) {
    holder.bind(getItem(position));
  }
}
