package com.application.davidelm.housefly.presenter;

import java.util.List;

public interface BasePresenter {
    void onFinishedRetrieveItems(List<?> items);
    void unsubscribe();
    void onError(String error);
}
