package com.newbapps.bullsandcows;

/**
 * Created by hcxer on 9/27/2017.
 */

public interface MainPresenter {

    void onResume();

    void onItemClicked(int position);

    void onDestroy();
}
