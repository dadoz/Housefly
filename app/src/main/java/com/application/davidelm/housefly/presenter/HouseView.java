package com.application.davidelm.housefly.presenter;

import java.util.List;

public interface HouseView {
    void bindData(List<?> items, int i);
    void onRetrieveDataError(String error);
}
