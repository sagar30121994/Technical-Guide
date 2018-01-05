package sagar.cybernetics.com.technicalguide.adaptor;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import sagar.cybernetics.com.technicalguide.Communication.UnitContact;
import sagar.cybernetics.com.technicalguide.R;
import sagar.cybernetics.com.technicalguide.Utils.Animations;
import sagar.cybernetics.com.technicalguide.fragment.MCQTestFragment;
import sagar.cybernetics.com.technicalguide.fragment.PartFragment;

/**
 * Created by sagar on 7/9/17.
 */

public class UnitTestAdaptor extends RecyclerView.Adapter<UnitTestAdaptor.MyviewHolder> {

    private List<UnitContact> contacts;
    int previousPosition=0;

    Context ctx;

    public UnitTestAdaptor(List<UnitContact> contacts, Context activity) {
    this.contacts=contacts;
        this.ctx=activity;



    }

    @Override
    public MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent, false);
        return new MyviewHolder(view);


    }

    @Override
    public void onBindViewHolder(MyviewHolder holder,final  int position) {

        holder.name.setText(contacts.get(position).getSubject());
        holder.desc.setText(contacts.get(position).getUnit());

        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Bundle bundle=new Bundle();
                bundle.putString("id",contacts.get(position).getId());

                bundle.putString("subjectName",contacts.get(position).getSubject());



                try {
                    PartFragment partFragment=new PartFragment();

                    partFragment.setArguments(bundle);



                    ((AppCompatActivity) ctx).getSupportFragmentManager()
                            .beginTransaction().setCustomAnimations(R.anim.slide_to_left,R.anim.slidout)
                            .replace(R.id.container, partFragment)
                            .addToBackStack(null)
                            .commit();

                } catch (Exception e) {
                    //     Toast.makeText(ctx,""+e,Toast.LENGTH_LONG).show();
                }




            }
        });


        if (previousPosition>position){
            Animations.animatorTwo(holder,true);

        }else{
            Animations.animatorTwo(holder,false);

        }
        previousPosition=position;




    }


    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public class MyviewHolder extends RecyclerView.ViewHolder {


        TextView name, desc;

        LinearLayout ll;

        public MyviewHolder(View itemView) {
            super(itemView);

            desc = (TextView) itemView.findViewById(R.id.test_Description);

            ll = (LinearLayout) itemView.findViewById(R.id.linearlayout);

            name = (TextView) itemView.findViewById(R.id.test_code);

        }
    }



}
