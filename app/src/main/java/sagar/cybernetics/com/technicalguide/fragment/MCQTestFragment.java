package sagar.cybernetics.com.technicalguide.fragment;


import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sagar.cybernetics.com.technicalguide.Communication.QuestionContact;
import sagar.cybernetics.com.technicalguide.R;
import sagar.cybernetics.com.technicalguide.Utils.Animations;
import sagar.cybernetics.com.technicalguide.Utils.Dialogs;
import sagar.cybernetics.com.technicalguide.api_Client.api_Client;
import sagar.cybernetics.com.technicalguide.api_interface.Api_Interface;

/**
 * A simple {@link Fragment} subclass.
 */
public class MCQTestFragment extends Fragment {


    int progress=0;

    private RadioGroup radioGroup;
    RadioButton optionA, optionB, optionC, optionD;
    private List<QuestionContact> contacts;
    private Api_Interface api_interface;
    TestFragmentListner testFragmentListner;

    public TextView ques,Timer;
    Button next, submit, discuss,exit;

    String answers[],id;
    String test;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_mcqtest, container, false);

        radioGroup = (RadioGroup) view.findViewById(R.id.readioGroup);
        optionA = (RadioButton) view.findViewById(R.id.option1);
        optionB = (RadioButton) view.findViewById(R.id.option2);
        optionC = (RadioButton) view.findViewById(R.id.option3);
        optionD = (RadioButton) view.findViewById(R.id.option4);

        Timer=(TextView)view.findViewById(R.id.timer);
        next = (Button) view.findViewById(R.id.next);
        ques = (TextView) view.findViewById(R.id.question);

        discuss = (Button) view.findViewById(R.id.discussion);
        submit = (Button) view.findViewById(R.id.submit);

        exit= (Button) view.findViewById(R.id.exietest);
        final ProgressDialog progressDoalog;






        api_interface = api_Client.Api_Client.getApi_Client().create(Api_Interface.class);


        try {
            Bundle bundle=getArguments();
            test=bundle.getString("test");
            id=bundle.getString("id");

        } catch (Exception e) {
            e.printStackTrace();
        }

        progressDoalog = new ProgressDialog(getActivity());

        if (test.equalsIgnoreCase("true")){

            progressDoalog.setMessage("Initializing Test...");




        }else
        {
            progressDoalog.setMessage("Initializing Module...");

        }
        progressDoalog.setProgressStyle(R.style.Widget_AppCompat_ProgressBar);
        progressDoalog.setTitle("Plese Wait.");

        progressDoalog.setIndeterminate(true);
        progressDoalog.setCancelable(false);
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        // show it


        progressDoalog.show();



        Map<String, String> map=new HashMap<>();
        map.put("id",id);
        map.put("test",test);




        Call<List<QuestionContact>> call = api_interface.getQuestionList(map);
        call.enqueue(new Callback<List<QuestionContact>>() {
            @Override
            public void onResponse(Call<List<QuestionContact>> call, Response<List<QuestionContact>> response) {
                contacts = response.body();

                answers=new String[contacts.size()];


                if (test.equalsIgnoreCase("true")){
                    activateTimer(contacts.size());
                }

                traverseQues(contacts, 0);
                progressDoalog.dismiss();


            }


            @Override
            public void onFailure(Call<List<QuestionContact>> call, Throwable t) {

                Dialogs.networkDialog(getActivity());
                progressDoalog.dismiss();
            }
        });




        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emergencyExit();
            }
        });



        return  view;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            testFragmentListner = (TestFragmentListner) activity;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    public void checkedAns(final int i , final String test) {


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int id = radioGroup.getCheckedRadioButtonId();


                switch (id) {

                    case R.id.option1:
                        if ((optionA.getText()).equals(contacts.get(i).getAns())) {
                            progress=progress+1;
                            answers[i]="Correct";
                            showCorrectToast();

                        } else {
                            answers[i]="Incorrect";
                            showIncorrectToast();

                        }
                        break;
                    case R.id.option2:
                        if (optionB.getText().equals(contacts.get(i).getAns())) {
                            progress=progress+1;
                            answers[i]="Correct";
                            showCorrectToast();

                        } else {
                            answers[i]="Incorrect";

                            showIncorrectToast();

                        }
                        break;
                    case R.id.option3:

                        if (optionC.getText().equals(contacts.get(i).getAns())) {
                            progress=progress+1;
                            answers[i]="Correct";
                            showCorrectToast();

                        } else {
                            answers[i]="Incorrect";
                            showIncorrectToast();

                        }
                        break;
                    case R.id.option4:

                        if (optionD.getText().equals(contacts.get(i).getAns())) {
                            progress=progress+1;
                            answers[i]="Correct";
                            showCorrectToast();

                        } else {
                            answers[i]="incorrect";

                            showIncorrectToast();
                        }
                        break;


                    default:


                        answers[i]="Not Selected";

                        break;

                }

                if ("true".equals(test)){

                    discuss.setVisibility(View.GONE);

                    submit.setVisibility(View.GONE);


                }else {
                    discuss.setVisibility(View.VISIBLE);


                    submit.setVisibility(View.GONE);

                }



                discuss.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        final Dialog dialog = new Dialog(getActivity());
                        dialog.setContentView(R.layout.discussion_dialog);
                        dialog.setTitle("Answer");
                        TextView tv = (TextView) dialog.findViewById(R.id.textExplanation);
                        Button button = (Button) dialog.findViewById(R.id.declineButton);
                        tv.setText("Answer : "+contacts.get(i).getAns());
                        dialog.show();

                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });


                    }
                });


                nextQues(i);


            }
        });

    }


    public void nextQues(final int i) {
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deactivateDiscuss();

                submit.setVisibility(View.VISIBLE);
                int b = incresei(i);

                optionA.setBackgroundColor(getResources().getColor(R.color.question));
                optionB.setBackgroundColor(getResources().getColor(R.color.question));
                optionC.setBackgroundColor(getResources().getColor(R.color.question));
                optionD.setBackgroundColor(getResources().getColor(R.color.question));

                traverseQues(contacts, b);

            }
        });

    }


    public void traverseQues(List<QuestionContact> contacts, int size) {

        if (contacts != null) {

            try {
                if (size == contacts.size()) {
                    //    Toast.makeText(getActivity(),""+answers[1],Toast.LENGTH_LONG).show();

                    if(size==0)
                    {
                        exitTest();


                    }else {
                        testFragmentListner.getProgress( (progress*100)/size,answers,test,id);

                    }

                } else {

                    radioGroup.clearCheck();


                    ques.setText("Q" + (size + 1) + ". " + contacts.get(size).getQuestion());

                    Animations.animatorTextProfile(ques);
                    optionA.setText(contacts.get(size).getOptionA());
                    optionB.setText(contacts.get(size).getOptionB());
                    optionC.setText(contacts.get(size).getOptionC());
                    optionD.setText(contacts.get(size).getOptionD());

                    Animations.animatorTextMCQ(optionA);
                    Animations.animatorTextMCQ(optionB);

                    Animations.animatorTextMCQ(optionC);
                    Animations.animatorTextMCQ(optionD);

                    checkedAns(size,test);


                }
            } catch (Exception e) {
            }


        } else {
        }


    }

    public int incresei(int j) {

        j++;
        return j;
    }

    public int decresei(int j) {

        j--;
        return j;
    }

    public interface TestFragmentListner {
        public void getProgress(int i,String[] answers,String test,String id);

    }

    public void activateTimer(final int i){

        Timer.setVisibility(View.VISIBLE);
        new CountDownTimer(i*60000, 1000) {



            public void onTick(long millisUntilFinished) {

                    Timer.setText(""+(millisUntilFinished / 1000)/60+" Mins" );






            }



            public void onFinish() {
                Timer.setText("Times UP..");

                    Toast.makeText(getActivity(),"Times Up Exiting Test",Toast.LENGTH_LONG).show();

                testFragmentListner.getProgress( (progress*100)/i,answers,test, id);

            }
        }.start();


    }

    public void activateDiscuss(){
        discuss.setVisibility(View.VISIBLE);


    }

    public void deactivateDiscuss(){
        discuss.setVisibility(View.GONE);



    }


    public void showIncorrectToast(){
        //Creating the LayoutInflater instance
        LayoutInflater li = getActivity().getLayoutInflater();
        //Getting the View object as defined in the customtoast.xml file
        final View layout = li.inflate(R.layout.custom_toast,
                (ViewGroup)getActivity(). findViewById(R.id.custom_toast_layout));


        Toast toast = new Toast(getActivity());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM,0, 0);
        toast.setMargin(0.0f,.1f);

        toast.setView(layout);//setting the view of custom toast layout
        toast.show();




    }



    public void showCorrectToast(){
        //Creating the LayoutInflater instance
        LayoutInflater lia = getActivity().getLayoutInflater();
        //Getting the View object as defined in the customtoast.xml file
        View layouta = lia.inflate(R.layout.custom_toast_correct,
                (ViewGroup) getActivity().findViewById(R.id.custom_toast_layout_correct));


        Toast toast = new Toast(getActivity());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setMargin(0.0f,0.1f);
        toast.setGravity(Gravity.BOTTOM,0, 0);
        toast.setView(layouta);//setting the view of custom toast layout
        toast.show();


    }



    public void exitTest(){

        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.error_dialog);
        dialog.setTitle("");
        Button button = (Button) dialog.findViewById(R.id.declineButton);

        dialog.show();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testFragmentListner.getProgress( (progress*100)/1,answers,test, id);

                dialog.dismiss();
            }
        });


    }




    @Override
    public void onResume() {
        super.onResume();

        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();

        DrawerLayout drawerLayout= (DrawerLayout) ((AppCompatActivity)getActivity()).findViewById(R.id.drawer_layout);
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        ((AppCompatActivity)getActivity()).findViewById(R.id.fab).setVisibility(View.GONE);
    }



    @Override
    public void onStop() {
        super.onStop();
        ((AppCompatActivity)getActivity()).findViewById(R.id.fab).setVisibility(View.VISIBLE);

        DrawerLayout drawerLayout= (DrawerLayout) ((AppCompatActivity)getActivity()).findViewById(R.id.drawer_layout);
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();

    }


    public void emergencyExit(){

        testFragmentListner.getProgress( 0,answers,test,id);



    }



    }





