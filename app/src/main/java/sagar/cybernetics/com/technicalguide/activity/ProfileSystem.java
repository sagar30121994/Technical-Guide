package sagar.cybernetics.com.technicalguide.activity;

import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import sagar.cybernetics.com.technicalguide.R;
import sagar.cybernetics.com.technicalguide.fragment.EditProfileFragment;
import sagar.cybernetics.com.technicalguide.fragment.SignInFragment;

public class ProfileSystem extends AppCompatActivity  implements SignInFragment.dataListner{

SignInFragment signInFragment;
    EditProfileFragment fr;
 FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        String account, picUrl, DisName, DisMail;
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        //MainActivity.this.getSharedPreferences("Profile",MODE_PRIVATE);
        account = sharedPreferences.getString("profile", "");
        picUrl = sharedPreferences.getString("photoUrl", "");
        DisName = sharedPreferences.getString("displayName", "");
        DisMail = sharedPreferences.getString("displayEmail", "");

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_to_left,R.anim.slidout);

        if (account.equals("true")) {


            Bundle bundle = new Bundle();
            fr = new EditProfileFragment();

            String as = String.valueOf(picUrl);
            bundle.putString("name", DisName);
            bundle.putString("email", DisMail);
            bundle.putString("uri", as);

            fr.setArguments(bundle);
            fragmentTransaction.replace(R.id.container, fr);


            fragmentTransaction.commit();


        } else {


            signInFragment=new SignInFragment();
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_to_left,R.anim.slidout);

            fragmentTransaction.replace(R.id.container, signInFragment)



                    .commit();

        }






    }

    @Override
    public void setAccountData(Uri uri, String name, String Email) {

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_to_left,R.anim.slidout);

        Bundle bundle = new Bundle();
        fr = new EditProfileFragment();

        String as = String.valueOf(uri);
        bundle.putString("name", name);
        bundle.putString("email", Email);
        bundle.putString("uri", as);

        fr.setArguments(bundle);
        fragmentTransaction.replace(R.id.container, fr)


                .commit();

    }
}
