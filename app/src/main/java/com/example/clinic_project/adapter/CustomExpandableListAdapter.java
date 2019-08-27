package com.example.clinic_project.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.clinic_project.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomExpandableListAdapter extends BaseExpandableListAdapter {

    private Context mContext;
    private List<String> mExpandableListTitle;
    private Map<String, List<String>> mExpandableListDetail;
    private LayoutInflater mLayoutInflater;

    public List<Integer> groupImages;
    public HashMap<Integer, List<Integer>> childImages;

    public CustomExpandableListAdapter(Context context, List<String> expandableListTitle,
                                       Map<String, List<String>> expandableListDetail) {
        mContext = context;
        mExpandableListTitle = expandableListTitle;
        mExpandableListDetail = expandableListDetail;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }


    @Override
    public Object getChild(int listPosition, int expandedListPosition) {
        return mExpandableListDetail.get(mExpandableListTitle.get(listPosition)).get(expandedListPosition);
    }

    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }

    @Override
    public View getChildView(int listPosition, final int expandedListPosition, boolean isLastChild, View convertView, ViewGroup parent) {


        final String expandedListText = (String) getChild(listPosition, expandedListPosition);

        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.list_item, null);
        }

        TextView expandedListTextView = (TextView) convertView.findViewById(R.id.expandedListItem);

        ImageView expandableImgaeView= convertView.findViewById(R.id.expandable_image);
//        expandableImgaeView.setImageResource(R.drawable.home);
//        int imageId = this.childImages.get(this.groupImages.get(listPosition)).get(expandedListPosition);
//        expandableImgaeView.setImageResource(imageId);
        expandedListTextView.setText(expandedListText);

        expandableImgaeView.setImageResource(R.drawable.ic_clinic);

        String name = getGroup(listPosition).toString();

//        if (name == "Medicine")
//        {
//            if (expandedListText == "Doctor")
//            {
//                expandableImgaeView.setImageResource(R.drawable.ic_doctor);
//            }
//            else if (expandedListText == "Hospital")
//            {
//                expandableImgaeView.setImageResource(R.drawable.ic_hospital3);
//            }
//            else if (expandedListText == "Lab")
//            {
//                expandableImgaeView.setImageResource(R.drawable.ic_lab);
//            }
//            else if (expandedListText == "Clinic")
//            {
//                expandableImgaeView.setImageResource(R.drawable.ic_clinic);
//            }
//
//        }else if (name == "Favourite")
//        {
//            if (expandedListText == "My Booking")
//            {
//                expandableImgaeView.setImageResource(R.drawable.book);
//            }
//            else if (expandedListText == "My Favourite Doctor"){
//                expandableImgaeView.setImageResource(R.drawable.ic_doctor);
//            }
//            else if (expandedListText  == "My Favourite Hospital"){
//                expandableImgaeView.setImageResource(R.drawable.ic_hospital3);
//            }
//        }


        return convertView;

    }

    @Override
    public int getChildrenCount(int listPosition) {
        return mExpandableListDetail.get(mExpandableListTitle.get(listPosition)).size();

    }

    @Override
    public Object getGroup(int listPosition) {
        return mExpandableListTitle.get(listPosition);
    }

    @Override
    public int getGroupCount() {
        return mExpandableListTitle.size();
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

    @SuppressLint("ResourceType")
    @Override
    public View getGroupView(int listPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        String listTitle = (String) getGroup(listPosition);

        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.list_group, null);
        }

        TextView listTitleTextView = (TextView) convertView.findViewById(R.id.listTitle);
        listTitleTextView.setTypeface(null, Typeface.BOLD);
        listTitleTextView.setText(listTitle);

        ImageView expandableImageView1 = convertView.findViewById(R.id.expandable_image1);
        expandableImageView1.setImageResource(R.drawable.ic_dr_home);

//        if (listTitle == "Favourite")
//        {
//            expandableImageView1.setImageResource(R.drawable.favourite_red);
//        }
//        else if (listTitle == "Home")
//        {
//            expandableImageView1.setImageResource(R.drawable.ic_dr_home);
//        }
//        else if (listTitle == "Medicine")
//        {
//            expandableImageView1.setImageResource(R.drawable.medical_service);
//        }
//        else if (listTitle == "Profile")
//        {
//            expandableImageView1.setImageResource(R.drawable.people);
//        }
//        else if (listTitle == "Setting")
//        {
//            expandableImageView1.setImageResource(R.drawable.setting);
//        }
//        else if (listTitle == "Log Out")
//        {
//            expandableImageView1.setImageResource(R.drawable.ic_logout);
//        }

//        ImageView expandableImageView1 = convertView.findViewById(R.id.expandable_image1);
//        int imageId = this.groupImages.get(listPosition);
//        expandableImageView1.setImageResource(imageId);
//        listTitleTextView.setText(listTitle);

        return convertView;

    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }
}
