package com.lilianbittar.readmeagain.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.lilianbittar.readmeagain.R;
import com.lilianbittar.readmeagain.adapters.ProfileAdapter;

public class ProfileFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private View root;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_profile, container, false);
        tabLayout = root.findViewById(R.id.tabLayout);
        viewPager = root.findViewById(R.id.read_page);

        tabLayout.setupWithViewPager(viewPager);

        ProfileAdapter profileAdapter = new ProfileAdapter(getActivity().getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        profileAdapter.addFragment(new ReadFragment(), "Read Books");
        profileAdapter.addFragment(new ToReadFragment(), "To Read Books");
        viewPager.setAdapter(profileAdapter);
        return root;
    }
}
