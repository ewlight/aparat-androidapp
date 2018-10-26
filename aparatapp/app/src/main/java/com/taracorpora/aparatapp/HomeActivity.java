package com.taracorpora.aparatapp;

import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.EditText;
import android.widget.Toast;

import com.pusher.pushnotifications.PushNotifications;
import com.taracorpora.aparatapp.fragment.GroupFragment;
import com.taracorpora.aparatapp.fragment.PengaturanFragment;
import com.taracorpora.aparatapp.model.AparatGroupRequestModel;
import com.taracorpora.aparatapp.presenter.HomePresenter;
import com.taracorpora.aparatapp.view.HomeView;

public class HomeActivity extends AppCompatActivity implements HomeView {


    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private String fbid;
    TabLayout tabLayout;
    private GroupFragment groupFragment;
    public HomePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            fbid = bundle.getString("fbid");
        }
        PushNotifications.start(getApplicationContext(), "455fc469-f92d-448a-92d3-c732a106ba07");
        PushNotifications.subscribe(fbid);
        presenter = new HomePresenter(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        tabLayout = findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSuccessSaveGroup(String groupNAme) {

        Toast.makeText(getApplicationContext(), groupNAme + "berhasil ditambahkan", Toast.LENGTH_LONG).show();
        mSectionsPagerAdapter.notifyDataSetChanged();
        mViewPager.setCurrentItem(0, true);

    }

    @Override
    public void onError() {

    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    groupFragment = GroupFragment.newInstance(fbid);
                    return  groupFragment;
                case 1:
                    Pengumuman pengumuman1 = new Pengumuman();
                    return pengumuman1;
                case 2:
                    PengaturanFragment pengaturanFragment1 = PengaturanFragment.newInstance(fbid);
                    return pengaturanFragment1;

            }
            return null;
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public int getItemPosition(@NonNull Object object) {
            return POSITION_NONE;
        }
    }

    public void openSignoutPage() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void generateNewGroupDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Buat Group Baru");
        builder.setIcon(R.drawable.logo);
        builder.setMessage("Masukkan nama group");
        EditText textGroupName = new EditText(this);
        builder.setView(textGroupName);
        builder.setPositiveButton("Simpan", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String text = textGroupName.getText().toString();
                AparatGroupRequestModel requestGroup = new AparatGroupRequestModel(text, fbid);
                presenter.saveGroupData(requestGroup);
            }
        });

        builder.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.create().show();
    }

    public void openGroupDetailPage(int groupId, String groupName) {
        Intent intent = new Intent(HomeActivity.this, GroupDetailActivity.class);
        intent.putExtra("groupid", groupId);
        intent.putExtra("fbid", fbid);
        intent.putExtra("groupname", groupName);
        startActivity(intent);
    }
}
