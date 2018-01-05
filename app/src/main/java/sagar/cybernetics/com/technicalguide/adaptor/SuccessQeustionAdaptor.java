package sagar.cybernetics.com.technicalguide.adaptor;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import sagar.cybernetics.com.technicalguide.Communication.QuestionContact;
import sagar.cybernetics.com.technicalguide.R;

/**
 * Created by sagar on 7/9/17.
 */

public class SuccessQeustionAdaptor extends RecyclerView.Adapter<SuccessQeustionAdaptor.MySubViewHolder> {

    private List<QuestionContact> subjects;
    Context ctx;
    String[] answer=new String[100];

    public SuccessQeustionAdaptor(List<QuestionContact> contacts, Context ct, String[] answer) {
        this.subjects = contacts;
        this.ctx = ct;
        this.answer=answer;
    }


    @Override
    public SuccessQeustionAdaptor.MySubViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sucess_object, parent, false);
        return new SuccessQeustionAdaptor.MySubViewHolder(view);
    }


    @Override
    public void onBindViewHolder(SuccessQeustionAdaptor.MySubViewHolder holder, int position) {

        holder.question.setText("Q"+(position+1)+". "+subjects.get(position).getQuestion());
        holder.answer.setText(subjects.get(position).getAns());
        holder.youranswer.setText(answer[position]);


    }

    @Override
    public int getItemCount() {
        return answer.length;
    }


    public class MySubViewHolder extends RecyclerView.ViewHolder {


        TextView question,answer,youranswer;

        LinearLayout ll;

        public MySubViewHolder(View itemView) {
            super(itemView);

            question=(TextView)itemView.findViewById(R.id.question);
            answer=(TextView)itemView.findViewById(R.id.option1);
            youranswer=(TextView)itemView.findViewById(R.id.youranswer);

        }
    }


}

