package sagar.cybernetics.com.technicalguide.adaptor;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import sagar.cybernetics.com.technicalguide.Communication.Subject_Contact;
import sagar.cybernetics.com.technicalguide.Communication.UnitContact;
import sagar.cybernetics.com.technicalguide.R;
import sagar.cybernetics.com.technicalguide.Utils.Animations;
import sagar.cybernetics.com.technicalguide.fragment.UnitTestFragment;

/**
 * Created by sagar on 3/9/17.
 */

public class Subject_Adaptor extends RecyclerView.Adapter<Subject_Adaptor.MySubViewHolder> {


    private List<Subject_Contact> subjects;
    int previousPosition=0;


    Context ctx;

    public Subject_Adaptor(List<Subject_Contact> contacts, Context ct) {
        this.subjects = contacts;
        this.ctx = ct;
    }

    @Override
    public MySubViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_subject, parent, false);
        return new MySubViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Subject_Adaptor.MySubViewHolder holder,final int position) {





        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ctx.getApplicationContext());

String University="";
        //SharedPreferences sharedPreferences=ListHome.this.getSharedPreferences("Profile",MODE_PRIVATE);
        University=sharedPreferences.getString("University","");


        if (University.contains(subjects.get(position).getUniversity()))
        {

            holder.name.setText(subjects.get(position).getSubName());

            holder.ll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Toast.makeText(ctx, "" + subjects.get(position).getSubId(), Toast.LENGTH_LONG).show();

                    Bundle bundle=new Bundle();
                    bundle.putString("id",subjects.get(position).getSubId());


                    try {
                        UnitTestFragment unitTestFragment=new UnitTestFragment();

                        unitTestFragment.setArguments(bundle);


                        ((AppCompatActivity) ctx).getSupportFragmentManager()
                                .beginTransaction().setCustomAnimations(R.anim.slide_to_left,R.anim.slidout)
                                .replace(R.id.container, unitTestFragment)
                                .addToBackStack(null)
                                .commit();
                    } catch (Exception e) {
                        //     Toast.makeText(ctx,""+e,Toast.LENGTH_LONG).show();
                    }

                }

            });


            if (previousPosition>position){
                Animations.animator(holder,true);

            }else{
                Animations.animator(holder,false);

            }
            previousPosition=position;



        }else {


        }








    }



    @Override
    public int getItemCount() {

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ctx.getApplicationContext());

        int i=0;
        String University="";
        //SharedPreferences sharedPreferences=ListHome.this.getSharedPreferences("Profile",MODE_PRIVATE);
        University=sharedPreferences.getString("University","");

        for (int position=0; position<subjects.size();position++){


            if (University.contains(subjects.get(position).getUniversity())){
                i=i+1;
            }



        }

            return i;
    }

    public class MySubViewHolder extends RecyclerView.ViewHolder {


        TextView name;

        LinearLayout ll;

        public MySubViewHolder(View itemView) {
            super(itemView);


            ll = (LinearLayout) itemView.findViewById(R.id.linearlayout);

            name = (TextView) itemView.findViewById(R.id.subjectname);

        }
    }




}

