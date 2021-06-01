package com.example.paymentmethod;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.paymentmethod.databinding.FragmentHomeBinding;
import com.example.paymentmethod.databinding.FragmentPaymentMethodsBinding;

import org.jetbrains.annotations.NotNull;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

public class HomeFragment extends Fragment {

  private FragmentHomeBinding binding;

  public HomeFragment() {
  }

  @Nullable
  @org.jetbrains.annotations.Nullable
  @Override
  public View onCreateView(@NonNull @NotNull LayoutInflater inflater,
                           @Nullable @org.jetbrains.annotations.Nullable ViewGroup container,
                           @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
    binding = FragmentHomeBinding.inflate(inflater, container, false);
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull @NotNull View view,
                            @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
    Glide.with(requireContext()).load("https://picsum.photos/id/0/5616/3744").into(binding.imageView);

    binding.purchaseButton.setOnClickListener(v -> {
      NavController navController = NavHostFragment.findNavController(this);
      navController.navigate(R.id.action_homeFragment_to_paymentMethodsFragment);
    });
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    binding = null;
  }
}
