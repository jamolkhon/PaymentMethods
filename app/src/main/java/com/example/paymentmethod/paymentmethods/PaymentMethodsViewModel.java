package com.example.paymentmethod.paymentmethods;

import com.example.paymentmethod.Api;
import com.example.paymentmethod.helper.AssistedSavedStateViewModelFactory;
import com.example.paymentmethod.model.ApplicableNetwork;
import com.example.paymentmethod.model.ListResult;
import com.example.paymentmethod.model.PaymentMethodItem;
import com.zhuinden.eventemitter.EventEmitter;
import com.zhuinden.eventemitter.EventSource;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import dagger.assisted.Assisted;
import dagger.assisted.AssistedFactory;
import dagger.assisted.AssistedInject;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Response;
import timber.log.Timber;

public class PaymentMethodsViewModel extends ViewModel {

  private final Api api;
  private final SavedStateHandle savedStateHandle;

  @AssistedInject
  public PaymentMethodsViewModel(Api api, @Assisted SavedStateHandle savedStateHandle) {
    this.api = api;
    this.savedStateHandle = savedStateHandle;
  }

  private final MutableLiveData<PaymentMethodsState> _state =
      new MutableLiveData<>(PaymentMethodsState.defaultState());

  public final LiveData<PaymentMethodsState> state = _state;

  private final EventEmitter<String> _events = new EventEmitter<>();
  public final EventSource<String> events = _events;

  private final CompositeDisposable disposables = new CompositeDisposable();

  @AssistedFactory
  public interface Factory extends AssistedSavedStateViewModelFactory<PaymentMethodsViewModel> {
  }

  void fetch() {
    Disposable d = api.fetch()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .map(this::toState)
        .onErrorReturn(error -> {
          _events.emit("Connection error. Try again please. (pull-down)");
          return state().failed(error);
        })
        .startWith(Single.just(state().loading()))
        .subscribe(_state::setValue);
    disposables.add(d);
  }

  private PaymentMethodsState toState(Response<ListResult> response) {
    if (response.code() != 200 || response.body() == null) {
      Timber.e("Unexpected response (code: %d)", response.code());
      _events.emit("Unexpected error. Try again please.");
      return state();
    }
    List<ApplicableNetwork> networks = response.body().getNetworks().getApplicable();
    ArrayList<PaymentMethodItem> items = new ArrayList<>();
    for (int i = 0; i < networks.size(); i++) {
      items.add(toItem(networks.get(i)));
    }
    return state().loaded(items);
  }

  private PaymentMethodItem toItem(ApplicableNetwork network) {
    return new PaymentMethodItem(network.getCode(), network.getLabel(),
        network.getLinks().get("logo").toString());
  }

  private PaymentMethodsState state() {
    return _state.getValue();
  }

  @Override
  protected void onCleared() {
    super.onCleared();
    disposables.clear();
  }
}
