package sagar.cybernetics.com.technicalguide.fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.games.Games;

import sagar.cybernetics.com.technicalguide.R;
import sagar.cybernetics.com.technicalguide.Utils.Dialogs;

import static com.google.android.gms.internal.zzs.TAG;


public class SignInFragment extends Fragment implements
        GoogleApiClient.OnConnectionFailedListener,
        View.OnClickListener{

    private static final int RC_SIGN_IN = 9001;
    private GoogleApiClient mGoogleApiClient;
    private ProgressDialog mProgressDialog;
    private SignInButton signInButton;

    dataListner dtlist;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_sign_in, container, false);

        signInButton=(SignInButton)view.findViewById(R.id.sign_in_button);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();





        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .enableAutoManage(getActivity(), null)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)

                .addApi(Games.API).addScope(Games.SCOPE_GAMES)
                .build();




        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signin();
            }
        });


         return  view;
    }




    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();

            /*fragmentManager = getFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragment_SIgn_In = new Fragment_SIgn_In();
*/
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplication());

            //   SharedPreferences sharedPreferences=getActivity().getSharedPreferences("Profile",MODE_PRIVATE);
            //  SharedPreferences.Editor editor=sharedPreferences.edit();

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("profile", "true");
            editor.putString("photoUrl", String.valueOf(acct.getPhotoUrl()));
            editor.putString("displayName", acct.getDisplayName());
            editor.putString("displayEmail", acct.getEmail());



            editor.commit();

            dtlist.setAccountData(acct.getPhotoUrl(), acct.getDisplayName(), acct.getEmail());




        } else {
            // Signed out, show unauthenticated UI.

        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            dtlist = (dataListner) activity;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG, "onConnectionFailed:" + connectionResult);

        Dialogs.networkDialog(getActivity());


    }

    @Override
    public void onStop() {
        super.onStop();
        ((AppCompatActivity)getActivity()).findViewById(R.id.fab).setVisibility(View.VISIBLE);

        DrawerLayout drawerLayout= (DrawerLayout) ((AppCompatActivity)getActivity()).findViewById(R.id.drawer_layout);
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();

    }




    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == RC_SIGN_IN) {

            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);

            handleSignInResult(result);

        }

    }

    public interface dataListner {


        public void setAccountData(Uri uri, String name, String Email);


    }

    @Override
    public void onStart() {
        super.onStart();

        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
        if (opr.isDone()) {
            // If the user's cached credentials are valid, the OptionalPendingResult will be "done"
            // and the GoogleSignInResult will be available instantly.
            showProgressDialog();

            Log.d(TAG, "Got cached sign-in");
            GoogleSignInResult result = opr.get();
            handleSignInResult(result);
          //  Toast.makeText(getActivity(),"sign in",Toast.LENGTH_LONG).show();

            hideProgressDialog();

        } else {
            // If the user has not previously signed in on this device or the sign-in has expired,
            // this asynchronous branch will attempt to sign in the user silently.  Cross-device
            // single sign-on will occur in this branch.
            showProgressDialog();



            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(GoogleSignInResult googleSignInResult) {
                    hideProgressDialog();
                    handleSignInResult(googleSignInResult);
                }
            });
        }
    }


    public void showProgressDialog() {

            mProgressDialog = new ProgressDialog(getActivity());
            mProgressDialog.setMessage("Loading...");
          mProgressDialog.setTitle("Signing In...");
            mProgressDialog.setCancelable(false);
            mProgressDialog.setIndeterminate(true);
            mProgressDialog.show();
        }



    private void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        hideProgressDialog();

        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();

       DrawerLayout drawerLayout= (DrawerLayout) ((AppCompatActivity)getActivity()).findViewById(R.id.drawer_layout);
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        ((AppCompatActivity)getActivity()).findViewById(R.id.fab).setVisibility(View.GONE);
    }



public void  signin(){

    Intent intent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
    startActivityForResult(intent, RC_SIGN_IN);



}

    }





