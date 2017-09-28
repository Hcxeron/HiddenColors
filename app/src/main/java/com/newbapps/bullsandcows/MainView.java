package com.newbapps.bullsandcows;

/**
 * Created by hcxer on 9/27/2017.
 */

import java.util.List;

public interface MainView {

    void showProgress();

    void hideProgress();

    void setItems(List<String> items);

    void showMessage(String message);
}
