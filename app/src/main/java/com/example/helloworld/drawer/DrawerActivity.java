package com.example.helloworld.drawer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.helloworld.R;
import com.example.helloworld.whatsappclone.CallFragment;
import com.example.helloworld.whatsappclone.ChatFragment;
import com.example.helloworld.whatsappclone.StatusFragment;
import com.google.android.material.navigation.NavigationView;

public class DrawerActivity extends AppCompatActivity {
    private TextView title;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ImageView btnMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        findViews();
        setListeners();
        initInterface();

    }

    private void findViews(){
        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.navigation_view);
        btnMenu=findViewById(R.id.btn_menu);
        title=findViewById(R.id.title);
    }

    private void setListeners(){
        btnMenu.setOnClickListener(view->{
            drawerLayout.openDrawer(GravityCompat.START);
        });

        navigationView.setNavigationItemSelectedListener(item->{
            Fragment fragment;
            switch (item.getItemId()){
                case R.id.nav_chat:
                    fragment=new ChatFragment();
                    break;
                case R.id.nav_calls:
                    fragment=new CallFragment();
                    break;
                case R.id.nav_status:
                    fragment=new StatusFragment();
                    break;
                default:
                    fragment=new ChatFragment();
                    break;
            }
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();

            item.setChecked(true);
            title.setText(item.getTitle());
            drawerLayout.closeDrawers();
            return true;
        });
    }

    private void initInterface(){
        navigationView.setCheckedItem(R.id.nav_chat);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new ChatFragment())
                .commit();
    }
}