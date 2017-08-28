package org.code5.code5push;

import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import com.nguyenhoanglam.imagepicker.activity.ImagePicker;
import com.nguyenhoanglam.imagepicker.model.Image;
import com.yarolegovich.lovelydialog.LovelyCustomDialog;

import org.code5.code5push.api.rest.TicketCreateCallbak;
import org.code5.code5push.api.services.UserService;
import org.code5.code5push.fragments.IntimateFragment;
import org.code5.code5push.fragments.LoginFragment;
import org.code5.code5push.fragments.NotificationFragment;
import org.code5.code5push.fragments.SignUpFragment;
import org.code5.code5push.listeners.FragmentListener;
import org.code5.code5push.listeners.OnCompleteListener;

import java.io.File;
import java.util.ArrayList;

import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by sony on 8/26/2017.
 */

public class LaunchActivity extends AppCompatActivity implements OnCompleteListener,FragmentListener
{
    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;
    private ActionBarDrawerToggle drawerToggle;
    private FancyButton registerBtn;
    private FancyButton signInBtn;
    private FancyButton powerBtn;
    private FancyButton intimatePowerBtn;
    private static final int REQUEST_CODE_PICKER = 100;
    private Dialog ticketRaise;
    private ImageView mImageView;
    private EditText description;
    private EditText subject;
    private File imageFile;
    private String desc;
    private String subj;
    private ArrayList<Image> images = new ArrayList<>();



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        Intent intent = getIntent();
        String push = intent.getStringExtra("push");
        if(push!=null)
        {
           if(push.equals("alert")){
            getFragmentManager().beginTransaction().replace(R.id.flContent,NotificationFragment.newInstance(LaunchActivity.this)).setCustomAnimations(R.animator.slide_in_left, R.animator.slide_out_left,
                    R.animator.slide_out_right, R.animator.slide_in_right).addToBackStack(null).commit();}
        }


        registerBtn=(FancyButton)findViewById(R.id.registerBtn);
        registerBtn.setOnClickListener(registerListener);
        powerBtn=(FancyButton)findViewById(R.id.powerBtn);
        powerBtn.setOnClickListener(powerListener);
        signInBtn=(FancyButton)findViewById(R.id.loginBtn);
        signInBtn.setOnClickListener(loginListener);
        intimatePowerBtn=(FancyButton)findViewById(R.id.intimateBtn);
        intimatePowerBtn.setOnClickListener(intimateListener);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        // Find our drawer view
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = setupDrawerToggle();
        mDrawer.addDrawerListener(drawerToggle);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nvView);
        setupDrawerContent(navigationView);
    }
private View.OnClickListener registerListener = new View.OnClickListener() {
    @Override
    public void onClick(View v) {
     getFragmentManager().beginTransaction().add(R.id.flContent,SignUpFragment.newInstance(LaunchActivity.this)).setCustomAnimations(R.animator.slide_in_left, R.animator.slide_out_left,
             R.animator.slide_out_right, R.animator.slide_in_right).addToBackStack(null).commit();


    }
};
    private View.OnClickListener powerListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            getFragmentManager().beginTransaction().replace(R.id.flContent,NotificationFragment.newInstance(LaunchActivity.this)).setCustomAnimations(R.animator.slide_in_left, R.animator.slide_out_left,
                    R.animator.slide_out_right, R.animator.slide_in_right).addToBackStack(null).commit();


        }
    };
    private View.OnClickListener intimateListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            ticketRaise = new LovelyCustomDialog(LaunchActivity.this)
                    .setView(R.layout.dialog_ticket_raise)
                    .setTopColorRes(R.color.black)
                    .setTitle("Create Ticket")
                    .setListener(R.id.imageId, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            ImagePicker.create(LaunchActivity.this)
                                    .folderMode(true) // folder mode (false by default)
                                    .folderTitle("Gallery") // folder selection title
                                    .imageTitle("Tap to select") // image selection title
                                    .single()
                                    .origin(images)
                                    .limit(1) // max images can be selected (999 by default)
                                    .showCamera(true)// show camera or not (true by default)
                                    .imageDirectory("Camera") // directory name for captured image  ("Camera" folder by default)
                                    .start(REQUEST_CODE_PICKER);

                        }
                    })
                    .setListener(R.id.okButtonId, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            if(imageFile.getName()!=null) {

                                UserService.createTicket(subject.getText().toString(), description.getText().toString(), imageFile, new TicketCreateCallbak() {
                                    @Override
                                    public void onSuccess(boolean result) {

                                        Toast.makeText(getApplicationContext(), "Successfully Created Ticket", Toast.LENGTH_LONG).show();
                                        ticketRaise.dismiss();


                                    }




                                });

                            }

                        }
                    })
                    .setListener(R.id.cancelButtonId, new View.OnClickListener() {
                        @Override
                        public void onClick(View v)
                        {
                            ticketRaise.dismiss();
                        }
                    })


                    .show();
            mImageView = (ImageView)ticketRaise.findViewById(R.id.imageId);
            description = (EditText)ticketRaise.findViewById(R.id.descId);
            subject = (EditText)ticketRaise.findViewById(R.id.subjectId);
            getFragmentManager().beginTransaction().replace(R.id.flContent,new IntimateFragment()).setCustomAnimations(R.animator.slide_in_left, R.animator.slide_out_left,
                    R.animator.slide_out_right, R.animator.slide_in_right).addToBackStack(null).commit();


        }
    };
    private View.OnClickListener loginListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            FragmentManager fm = getFragmentManager();
            LoginFragment loginFragment = new LoginFragment();
            loginFragment.show(fm,"fragment_login");

        }
    };

    private ActionBarDrawerToggle setupDrawerToggle() {
        // NOTE: Make sure you pass in a valid toolbar reference.  ActionBarDrawToggle() does not require it
        // and will not render the hamburger icon without it.
        return new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.drawer_open,  R.string.drawer_close);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawer.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }
    public void selectDrawerItem(MenuItem menuItem) {
        // Create a new fragment and specify the fragment to show based on nav item clicked
        Fragment fragment = null;
        Class fragmentClass;
        switch(menuItem.getItemId()) {
            case R.id.home:
               // fragmentClass = FirstFragment.class;
                break;
            case R.id.update:
               // fragmentClass = SecondFragment.class;
                break;
            case R.id.smsStatus:
                //fragmentClass = ThirdFragment.class;
                break;
            case R.id.intimate:
                // fragmentClass = FirstFragment.class;
                break;
            case R.id.feedback:
                //fragmentClass = ThirdFragment.class;
                break;
            case R.id.changeLanguage:
                // fragmentClass = FirstFragment.class;
                break;
            case R.id.changePassword:
                // fragmentClass = SecondFragment.class;
                break;
            case R.id.shareApp:
                //fragmentClass = ThirdFragment.class;
                break;
            case R.id.rateApp:
                // fragmentClass = FirstFragment.class;
                break;
            case R.id.aboutUs:
                // fragmentClass = SecondFragment.class;
                break;
            case R.id.logOut:
                //fragmentClass = ThirdFragment.class;
                break;
            default:
                //fragmentClass = FirstFragment.class;
        }

        try {
           // fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);
        // Set action bar title
        setTitle(menuItem.getTitle());
        // Close the navigation drawer
        mDrawer.closeDrawers();
    }


    public void openForm(View view)
    {
       // getFragmentManager().beginTransaction().replace(R.id.container,SignUpFragment.newInstance(this)).commit();


    }



    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
         drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onComplete(String message)
    {
        Toast.makeText(this,"Successfully signed up",Toast.LENGTH_LONG).show();

    }

    @Override
    public void onTicketCreate()
    {
        getFragmentManager().beginTransaction().replace(R.id.flContent,new IntimateFragment()).setCustomAnimations(R.animator.slide_in_left, R.animator.slide_out_left,
                R.animator.slide_out_right, R.animator.slide_in_right).addToBackStack(null).commit();
    }
}
