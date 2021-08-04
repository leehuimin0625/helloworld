package com.example.helloworld.whatsappclone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.helloworld.R;
import com.google.android.material.tabs.TabLayout;

public class WhatsappMainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whatsapp_main);
        findViews();
        setListeners();
    }

    private void findViews(){
        viewPager = findViewById(R.id.viewpager);
        tabLayout = findViewById(R.id.tablayout);
    }

    private void setListeners(){
        WhatsappPagerAdapter adapter = new WhatsappPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    public static class WhatsappPagerAdapter extends FragmentPagerAdapter {

        public WhatsappPagerAdapter(FragmentManager manager){
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return new ChatFragment();
                case 1:
                    return new CallFragment();
                case 2:
                    return new StatusFragment();
                default:
                    return new ChatFragment();
            }
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0:
                    return "Chat";
                case 1:
                    return "Calls";
                case 2:
                    return "Status";
                default:
                    return "";
            }
        }
    }
}