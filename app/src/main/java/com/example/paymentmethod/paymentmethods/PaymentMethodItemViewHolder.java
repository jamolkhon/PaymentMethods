package com.example.paymentmethod.paymentmethods;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.paymentmethod.R;
import com.example.paymentmethod.model.PaymentMethodItem;

import org.jetbrains.annotations.NotNull;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PaymentMethodItemViewHolder extends RecyclerView.ViewHolder {

  public PaymentMethodItemViewHolder(@NonNull @NotNull View itemView) {
    super(itemView);
  }

  public void bind(PaymentMethodItem item) {
    TextView labelView = itemView.findViewById(R.id.labelView);
    labelView.setText(item.getLabel());
    ImageView logoView = itemView.findViewById(R.id.logoView);
    Glide.with(itemView.getContext()).load(item.getLogoUrl()).into(logoView);
  }
}
