package com.example.clinic_project.fragment;

/**
 * @author msahakyan
 */

public interface NavigationManager {

    void showFragmentFavourite(String title);

    void showFragmentHome(String title);

    void showFragmentLogout(String title);

    void showFragmentMedicine(String title);

    void showFragmentProfile(String title);

    void showFragmentSetting(String title);
}
