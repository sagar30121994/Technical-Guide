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

import sagar.cybernetics.com.technicalguide.Communication.TestContact;
import sagar.cybernetics.com.technicalguide.R;
import sagar.cybernetics.com.technicalguide.Utils.Animations;
import sagar.cybernetics.com.technicalguide.fragment.MCQTestFragment;

/**
 * Created by sagar on 7/9/17.
 */

public class TestAdaptor extends RecyclerView.Adapter <TestAdaptor.MySubViewHolder> {


    private List<TestContact> contacts;
    int previousPosition=0;

    Context ctx;


    public TestAdaptor(List<TestContact> contacts,Context context){
        this.ctx=context;
        this.contacts=contacts;

    }

    @Override
    public MySubViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent, false);
        return new TestAdaptor.MySubViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MySubViewHolder holder, final int position) {
        holder.name.setText(contacts.get(position).getSubject1());
        holder.desc.setText(contacts.get(position).getTest());

        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {





                Bundle bundle=new Bundle();
                bundle.putString("id",contacts.get(position).getId());
                bundle.putString("test","true");
                bundle.putString("Subject",contacts.get(position).getSubject1()+" "+contacts.get(position).getTest());
               Toast.makeText (ctx,""+contacts.get(position).getSubject1()+" "+contacts.get(position).getTest(), Toast.LENGTH_SHORT).show();



                try {
                    MCQTestFragment mcqTestFragment=new MCQTestFragment();

                    mcqTestFragment.setArguments(bundle);

                    ((AppCompatActivity) ctx).getSupportFragmentManager()
                            .beginTransaction().setCustomAnimations(R.anim.slide_to_left,R.anim.slidout)
                            .replace(R.id.container, mcqTestFragment)
                            .addToBackStack(null)
                            .commit();

                } catch (Exception e) {
                    //     Toast.makeText(ctx,""+e,Toast.LENGTH_LONG).show();
                }


            }
        });


        if (previousPosition>position){
            Animations.animatorTest(holder,true);

        }else{
            Animations.animatorTest(holder,false);

        }
        previousPosition=position;


    }





    @Override
    public int getItemCount() {
        return contacts.size();
    }


    public class MySubViewHolder extends RecyclerView.ViewHolder {

        TextView name, desc;

        LinearLayout ll;

        public MySubViewHolder(View itemView) {
            super(itemView);



            desc = (TextView) itemView.findViewById(R.id.test_Description);

            ll = (LinearLayout) itemView.findViewById(R.id.linearlayout);

            name = (TextView) itemView.findViewById(R.id.test_code);

        }
    }
}
