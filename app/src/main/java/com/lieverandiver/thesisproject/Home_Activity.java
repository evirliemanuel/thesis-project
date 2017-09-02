package com.lieverandiver.thesisproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.lieverandiver.thesisproject.adapter.ClassAdapter;
import com.lieverandiver.thesisproject.fragment.Home_Logs_Slidebar_Fragment;
import com.lieverandiver.thesisproject.fragment.Home_Student_Slidebar_Fragment;
import com.lieverandiver.thesisproject.fragment.SliderClassFragment;
import com.lieverandiver.thesisproject.fragment.SliderScheduleFragment;
import com.lieverandiver.thesisproject.fragment.SliderSettingFragment;

import java.util.ArrayList;
import java.util.List;


public class Home_Activity extends AppCompatActivity implements ClassAdapter.ClassAdapterListener {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new SliderScheduleFragment(), "Shedule");
        adapter.addFragment(new SliderClassFragment(), "Class");
        adapter.addFragment(new Home_Student_Slidebar_Fragment(), "Student");
        adapter.addFragment(new SliderSettingFragment(), "Setting");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void showClassView(final long classId) {
        Intent intent = new Intent(this, ClassViewActivity.class);
        intent.putExtra("classId", classId);
        startActivity(intent);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
