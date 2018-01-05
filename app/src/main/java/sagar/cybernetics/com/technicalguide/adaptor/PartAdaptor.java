package sagar.cybernetics.com.technicalguide.adaptor;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import sagar.cybernetics.com.technicalguide.Communication.PartContact;
import sagar.cybernetics.com.technicalguide.R;
import sagar.cybernetics.com.technicalguide.Utils.Animations;
import sagar.cybernetics.com.technicalguide.fragment.MCQTestFragment;


/**
 * Created by sagar on 8/9/17.
 */

public class PartAdaptor extends RecyclerView.Adapter<PartAdaptor.MyviewHolder>  {

    private List<PartContact> parts;
    Context context;
    String subjectName;
    int previousPosition=0;


    public PartAdaptor(List<PartContact> contacts, Context ct, String Subject) {

        this.parts=contacts;
        this.context=ct;

        this.subjectName=Subject;


    }


    @Override
    public MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.raw_part_item, parent, false);

        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyviewHolder holder, final int position) {
        holder.subjectname.setText(subjectName);
        holder.part.setText(parts.get(position).getPart());

        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                Bundle bundle=new Bundle();
              //  bundle.putString("id",);
                bundle.putString("id",parts.get(position).getId());
                bundle.putString("test","false");




                try {
                    MCQTestFragment test_fragment=new MCQTestFragment();

                    test_fragment.setArguments(bundle);

                    ((AppCompatActivity) context).getSupportFragmentManager()
                            .beginTransaction().setCustomAnimations(R.anim.slide_to_left,R.anim.slidout)
                            .replace(R.id.container, test_fragment)
                            .addToBackStack(null)
                            .commit();
                } catch (Exception e) {
                    Toast.makeText(context,""+e,Toast.LENGTH_SHORT).show();
                }



            }
        });


        if (previousPosition>position){
            Animations.animator(holder,true);

        }else{
            Animations.animator(holder,false);

        }
        previousPosition=position;




    }


    @Override
    public int getItemCount() {
        return parts.size();
    }

    public class MyviewHolder extends RecyclerView.ViewHolder {


        TextView subjectname, part;

        LinearLayout ll;

        public MyviewHolder(View itemView) {
            super(itemView);

            part = (TextView) itemView.findViewById(R.id.part);

            ll = (LinearLayout) itemView.findViewById(R.id.linearlayout);

            subjectname = (TextView) itemView.findViewById(R.id.subjectname);

        }
    }



}
