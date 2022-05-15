package com.lilianbittar.readmeagain.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.lilianbittar.readmeagain.R;
import com.lilianbittar.readmeagain.adapters.ProfileAdapter;

public class ProfileFragment extends Fragment {

    private View root;

    private ViewPager2 profileViewPager;
    private ProfileAdapter profileAdapter;
    private TabLayout profileTabLayout;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_profile, container, false);
        initViews();

        profileAdapter = new ProfileAdapter(getActivity().getSupportFragmentManager(), getLifecycle());

        profileAdapter.addFragment(new ToReadFragment());
        profileAdapter.addFragment(new ReadFragment());

        profileViewPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        profileViewPager.setAdapter(profileAdapter);

        new TabLayoutMediator(profileTabLayout, profileViewPager,
                (tab, position) -> {
                    if (position == 0) tab.setText("Books To Read");
                    else if (position == 1) tab.setText("Read Books");
                    else tab.setText("");
                }
        ).attach();

        return root;
    }

    private void initViews() {
        profileViewPager = root.findViewById(R.id.profile_viewpager);
        profileTabLayout= root.findViewById(R.id.profile_tabLayout);
    }

}
