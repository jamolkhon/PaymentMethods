package com.example.paymentmethod.paymentmethods;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.paymentmethod.App;
import com.example.paymentmethod.AppComponent;
import com.example.paymentmethod.R;
import com.example.paymentmethod.databinding.FragmentPaymentMethodsBinding;
import com.example.paymentmethod.helper.SpacesItemDecoration;
import com.zhuinden.eventemitter.EventSource;

import org.jetbrains.annotations.NotNull;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.AbstractSavedStateViewModelFactory;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

public class PaymentMethodsFragment extends Fragment {

  private AppComponent appComponent;
  private PaymentMethodsViewModel vm;
  private final PaymentMethodsAdapter adapter = new PaymentMethodsAdapter(new PaymentMethodItemDiffer());
  private FragmentPaymentMethodsBinding binding;
  private EventSource.NotificationToken eventSubscription;

  public PaymentMethodsFragment() {
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    appComponent = ((App) getActivity().getApplication()).appComponent;

    vm = new ViewModelProvider(this, new AbstractSavedStateViewModelFactory(this, null) {
      @NonNull
      @NotNull
      @Override
      protected <T extends ViewModel> T create(@NonNull @NotNull String key,
                                               @NonNull @NotNull Class<T> modelClass,
                                               @NonNull @NotNull SavedStateHandle handle) {
        return (T) appComponent.paymentMethodsViewModelFactory().create(handle);
      }
    }).get(PaymentMethodsViewModel.class);

    vm.fetch();
  }

  @Nullable
  @org.jetbrains.annotations.Nullable
  @Override
  public View onCreateView(@NonNull @NotNull LayoutInflater inflater,
                           @Nullable @org.jetbrains.annotations.Nullable ViewGroup container,
                           @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
    binding = FragmentPaymentMethodsBinding.inflate(inflater, container, false);
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull @NotNull View view,
                            @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    binding.toolbar.setTitle(R.string.payment_methods);
    binding.toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
    binding.toolbar.setNavigationOnClickListener(v -> {
      NavController navController = NavHostFragment.findNavController(this);
      navController.navigateUp();
    });

    binding.swipeRefreshLayout.setOnRefreshListener(() -> {
      binding.swipeRefreshLayout.setRefreshing(false);
      vm.fetch();
    });

    int spacing = (int) getResources().getDimension(R.dimen.payment_method_spacing);
    binding.paymentMethodsView.addItemDecoration(new SpacesItemDecoration(spacing));
    binding.paymentMethodsView.setAdapter(adapter);

    vm.state.observe(getViewLifecycleOwner(), this::render);

    eventSubscription = vm.events.startListening(event -> {
      Toast.makeText(requireContext(), event, Toast.LENGTH_SHORT).show();
    });
  }

  private void render(PaymentMethodsState state) {
    if (state.isLoading()) {
      binding.loader.show();
    } else {
      binding.loader.hide();
    }
    adapter.submitList(state.getPaymentMethods());
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    binding = null;
    if (eventSubscription != null) {
      eventSubscription.stopListening();
      eventSubscription = null;
    }
  }
}
