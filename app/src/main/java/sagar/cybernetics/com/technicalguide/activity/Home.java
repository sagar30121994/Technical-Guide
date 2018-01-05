package sagar.cybernetics.com.technicalguide.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;
import sagar.cybernetics.com.technicalguide.R;
import sagar.cybernetics.com.technicalguide.fragment.AboutFragment;
import sagar.cybernetics.com.technicalguide.fragment.EditProfileFragment;
import sagar.cybernetics.com.technicalguide.fragment.MCQTestFragment;
import sagar.cybernetics.com.technicalguide.fragment.MyProfileFragment;
import sagar.cybernetics.com.technicalguide.fragment.SignInFragment;
import sagar.cybernetics.com.technicalguide.fragment.SubjectFragment;
import sagar.cybernetics.com.technicalguide.fragment.SuccessFragment;


public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener ,SignInFragment.dataListner,MyProfileFragment.MyProfileFragmentListner,MCQTestFragment.TestFragmentListner{


    SignInFragment signInFragment;
    MyProfileFragment mv;
    EditProfileFragment fr;
    SubjectFragment subjectFragment;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    String account, picUrl, DisName, DisMail;

    AboutFragment aboutFragment;
    CircleImageView iv;
    SuccessFragment success_fragment;
    TextView name, email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);






        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);


        email = (TextView) findViewById(R.id.textEmail);
        name = (TextView) findViewById(R.id.nameDisplay);
        iv = (CircleImageView)findViewById(R.id.imageView);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        //SharedPreferences sharedPreferences=ListHome.this.getSharedPreferences("Profile",MODE_PRIVATE);
        account = sharedPreferences.getString("profile", "");
        picUrl = sharedPreferences.getString("photoUrl", "");

        DisName = sharedPreferences.getString("displayName", "");

        DisMail = sharedPreferences.getString("displayEmail", "");


        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_to_left,R.anim.slidout);

        signInFragment = new SignInFragment();
        fragmentTransaction.replace(R.id.container, signInFragment);

        fragmentTransaction.commit();




        try {
            if (account.equals("true")) {

                name.setText(DisName);

                email.setText(DisMail);
                Uri uri = Uri.parse(picUrl);
                Glide.with(Home.this)
                        .load(uri)
                        .into(iv);


            }
        } catch (Exception e) {

        }





        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent=new Intent(Home.this,ProfileSystem.class);
            startActivity(intent);


            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent i=new Intent(Home.this,Home.class);
            startActivity(i);
            finish();

        } else if (id == R.id.nav_my_profile) {


            String account;
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Home.this);

//SharedPreferences sharedPreferences=ListHome.this.getSharedPreferences("Profile",MODE_PRIVATE);
            account = sharedPreferences.getString("profile", "");
//if account exist then goto my profile otherwisw goto sign in activity ie main activity

            if (account.equals("true")) {

                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_to_left,R.anim.slidout);

                mv = new MyProfileFragment();
                fragmentTransaction.replace(R.id.container, mv).addToBackStack(null);

                fragmentTransaction.commit();


            } else {

            }

        }else if (id == R.id.nav_share) {

            share();

        }else if (id==R.id.about){


            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_to_left,R.anim.slidout);




            aboutFragment=new AboutFragment();
            fragmentTransaction.add(R.id.container,aboutFragment).addToBackStack(null).commit();



        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void setAccountData(Uri uri, String name, String Email) {

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_to_left,R.anim.slidout);



        fragmentTransaction.remove(signInFragment).commit();

        subjectFragment=new SubjectFragment();
        fragmentTransaction.add(R.id.container,subjectFragment);


        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Home.this);


        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("name", name);
        editor.putString("email", Email);

        String as = String.valueOf(uri);
        editor.putString("uri", as);


        editor.commit();







    }

    @Override
    public void actvateEditProfileFragment() {
        Intent intent=new Intent(Home.this,ProfileSystem.class);

        startActivity(intent);
/*

        EditProfileFragment editProfileFragment=new EditProfileFragment();
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_to_left,R.anim.slidout);
        fragmentTransaction.replace(R.id.container, editProfileFragment);
*/

    }

    @Override
    public void getProgress(int i, String[] answers, String test, String id) {
        success_fragment=new SuccessFragment();
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_to_left,R.anim.slidout);
        fragmentTransaction.replace(R.id.container, success_fragment);
        Bundle bundle=new Bundle();
        bundle.putInt("progress",i);

        bundle.putString("id",id);

        bundle.putString("test",test);
        bundle.putStringArray("answers",answers);

        success_fragment.setArguments(bundle);

        fragmentTransaction.commit();
    }



public void share(){
    Intent shareIntent = new Intent();
    shareIntent.setAction(Intent.ACTION_SEND);
    shareIntent.setType("text/plain");
    shareIntent.putExtra(Intent.EXTRA_TEXT, "Hey I found this killer application for the preparation of online exams. Hurry Try out it.");
    startActivity(Intent.createChooser(shareIntent, "Technical Guide"));

}


}
