package com.example.clinic_project.datasource;

import android.content.Context;

import com.example.clinic_project.R;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by msahakyan on 22/10/15.
 */
public class ExpandableListDataSource {

    /**
     * Returns fake data of films
     *
     * @param context
     * @return
     */
    public static Map<String, List<String>> getData(Context context) {
        Map<String, List<String>> expandableListData = new TreeMap<>();

        List<String> HomeNavigation = Arrays.asList(context.getResources().getStringArray(R.array.HomeNavigation));

        List<String> homes = Arrays.asList(context.getResources().getStringArray(R.array.homes));
        List<String> medicines = Arrays.asList(context.getResources().getStringArray(R.array.medicines));
        List<String> favourites = Arrays.asList(context.getResources().getStringArray(R.array.favourites));
        List<String> profiles = Arrays.asList(context.getResources().getStringArray(R.array.profiles));
        List<String> settings = Arrays.asList(context.getResources().getStringArray(R.array.settings));
        List<String> logout = Arrays.asList(context.getResources().getStringArray(R.array.logouts));

        expandableListData.put(HomeNavigation.get(0), homes);
        expandableListData.put(HomeNavigation.get(1), medicines);
        expandableListData.put(HomeNavigation.get(2), favourites);
        expandableListData.put(HomeNavigation.get(3), profiles);
        expandableListData.put(HomeNavigation.get(4), settings);
        expandableListData.put(HomeNavigation.get(5), logout);

        return expandableListData;
    }
}
