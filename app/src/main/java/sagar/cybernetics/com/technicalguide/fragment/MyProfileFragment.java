package sagar.cybernetics.com.technicalguide.fragment;

import android.app.Activity;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;
import sagar.cybernetics.com.technicalguide.R;
import sagar.cybernetics.com.technicalguide.Utils.Animations;

public class MyProfileFragment extends Fragment {


    MyProfileFragmentListner myProfileFragmentListner;
    FloatingActionButton btn;
    ImageView background;
    CircleImageView profile;
    TextView uni,branch,name,mail,rank,year;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_my_profile, container, false);


        background=(ImageView)view.findViewById(R.id.iv_detail);

        btn=(FloatingActionButton)view.findViewById(R.id.fab);
        profile=(CircleImageView) view.findViewById(R.id.profileView);
        uni=(TextView)view.findViewById(R.id.nameUniversity);
        branch=(TextView)view.findViewById(R.id.nameBranch);
        name=(TextView)view.findViewById(R.id.displayName);
        mail=(TextView)view.findViewById(R.id.displayEmail);
        rank=(TextView)view.findViewById(R.id.rank);

        year=(TextView)view.findViewById(R.id.nameYear);


        String account,accountCopmlete, picUrl, DisName, DisMail,University,Branch,Year;
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());

        //SharedPreferences sharedPreferences=ListHome.this.getSharedPreferences("Profile",MODE_PRIVATE);
        account = sharedPreferences.getString("profile", "");
        accountCopmlete = sharedPreferences.getString("profileComplete", "");
        picUrl = sharedPreferences.getString("photoUrl", "");

        DisName = sharedPreferences.getString("displayName", "");
        University=sharedPreferences.getString("University","");
        Branch=sharedPreferences.getString("Branch","");
        Year=sharedPreferences.getString("Year","");


        name.setText(DisName);
        DisMail = sharedPreferences.getString("displayEmail", "");




        try {
            //If account Exist then set photo and other values
            if(accountCopmlete.equals("true")){


                name.setText(DisName);
                Animations.animatorTextProfile(name);

                mail.setText(DisMail);
                Animations.animatorTextProfile(mail);

                uni.setText(University);
                Animations.animatorTextProfile(uni);

                branch.setText(Branch);
                Animations.animatorTextProfile(branch);

                Uri uri = Uri.parse(picUrl);
                Glide.with(getActivity())
                        .load(uri)
                        .into(profile);

                Animations.animatorImageProfile(profile);

                Glide.with(getActivity())
                        .load(uri)
                        .into(background);



                year.setText(Year);
                Animations.animatorTextProfile(year);



            }
            else
            {
                profile.setImageResource(R.drawable.account_pic);
                background.setImageResource(R.drawable.account_pic);

            }
        } catch (Exception e) {

            Log.d("name",""+e);


        }




        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

 myProfileFragmentListner.actvateEditProfileFragment();



            }
        });



        return view;

    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            myProfileFragmentListner = (MyProfileFragmentListner) activity;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    public interface MyProfileFragmentListner {
        public void actvateEditProfileFragment();

    }


}
