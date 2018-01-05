package sagar.cybernetics.com.technicalguide.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;


import com.bumptech.glide.Glide;

import sagar.cybernetics.com.technicalguide.R;
import sagar.cybernetics.com.technicalguide.activity.Home;

public class EditProfileFragment extends Fragment {

    Spinner university, branch, year;
    TextView name, email;
    ImageView displayImage;

    RadioGroup rg;
    RadioButton male,female;

    Button saveButton;

    ArrayAdapter<String> uni, bra, nm;
    String[] universities, branches, years;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


         View view=inflater.inflate(R.layout.fragment_edit_profile, container, false);




        showDialog();

        university = (Spinner) view.findViewById(R.id.university);
        branch = (Spinner) view.findViewById(R.id.branch);
        year = (Spinner) view.findViewById(R.id.year);

        name = (TextView) view.findViewById(R.id.displayName);
        email = (TextView) view.findViewById(R.id.email);

        displayImage = (ImageView) view.findViewById(R.id.displayPicture);

        male=(RadioButton)view.findViewById(R.id.male);
        female=(RadioButton)view.findViewById(R.id.femaile);
        universities = getResources().getStringArray(R.array.university);
        branches = getResources().getStringArray(R.array.branch);
        years = getResources().getStringArray(R.array.year);


        rg=(RadioGroup)view.findViewById(R.id.radioGroup);

        uni = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, universities);
        bra = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, branches);
        nm = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, years);

        nm.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        uni.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bra.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        university.setAdapter(uni);
        branch.setAdapter(bra);
        year.setAdapter(nm);

        displayImage.setImageResource(R.drawable.account_pic);


        saveButton = (Button) view.findViewById(R.id.savebutton);


        Bundle bundle = getArguments();
        if (bundle != null) {
            try {
                email.setText(bundle.getString("email"));
                name.setText(bundle.getString("name"));
                Uri uri = Uri.parse(bundle.getString("uri"));
                email.setText(bundle.getString("email"));

                Glide.with(getActivity())
                        .load(uri)
                        .into(displayImage);
            } catch (Exception e) {
                e.printStackTrace();
            }

            String account,accountCopmlete,picUrl,DisName,University,Branch,gender,Year;
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());


            //SharedPreferences sharedPreferences=ListHome.this.getSharedPreferences("Profile",MODE_PRIVATE);
            account = sharedPreferences.getString("profile", "");
            accountCopmlete = sharedPreferences.getString("profileComplete", "");
            picUrl = sharedPreferences.getString("photoUrl", "");

            DisName = sharedPreferences.getString("displayName", "");
            University=sharedPreferences.getString("University","");
            Branch=sharedPreferences.getString("Branch","");
            gender=sharedPreferences.getString("Gender","");
            Year=sharedPreferences.getString("Year","");


            try {
                if (accountCopmlete.equals("true")){


                    if (University.contains("Pune")){

                        university.setSelection(1,true);

                    }else if (University.contains("Shivaji")){
                        university.setSelection(2,true);

                    }else{
                        university.setSelection(3,true);

                    }

                    if(gender.equals("female")){

                        rg.check(R.id.femaile);
                    }else
                    if (gender.equals("male")){
                        rg.check(R.id.male);

                    }

                    if (Branch.equals("CO")){
                        branch.setSelection(2,true);

                    }else if (Branch.equals("E&TC")){
                        branch.setSelection(3,true);

                    }

                    else if (Branch.equals("CE")){
                        branch.setSelection(4,true);

                    }
                    else if (Branch.equals("ME")){
                        branch.setSelection(5,true);

                    }
                    else if (Branch.equals("IT")){
                        branch.setSelection(6,true);

                    }else if(Branch.equals("common")){
                        branch.setSelection(1,true);
                    }

                    if (Year.equals("FE")){
                        year.setSelection(1,true);
                    }else if (Year.equals("SE")){
                        year.setSelection(2,true);

                    }
                    else if (Year.equals("TE")){
                        year.setSelection(3,true);
                    }
                    else if (Year.equals("BE")){
                        year.setSelection(4,true);

                    }



                }
            } catch (Exception e) {
                e.printStackTrace();
            }


        }


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplication());

                //   SharedPreferences sharedPreferences=getActivity().getSharedPreferences("Profile",MODE_PRIVATE);
                //  SharedPreferences.Editor editor=sharedPreferences.edit();

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("profileComplete", "true");
                editor.putString("University", university.getSelectedItem().toString());
                editor.putString("Branch", branch.getSelectedItem().toString());
                editor.putString("Year", year.getSelectedItem().toString());
                // editor.putString("Gender",account.getEmail());

                int CheckedID= rg.getCheckedRadioButtonId();
                if (CheckedID==R.id.male){
                    editor.putString("Gender","male");

                }else {
                    editor.putString("Gender","female");

                }


                editor.commit();


                Intent i = new Intent(getActivity(), Home.class);
                startActivity(i);
                getActivity().finish();



            }
        });



        return view;




    }


    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(),R.style.MyDialogTheme);
        builder.setTitle("Note");
        builder.setMessage("If you are in First Year at any branch," +
                " Kindly select Branch as common and year as a FE");

        String positiveText = getString(android.R.string.ok);
        builder.setPositiveButton(positiveText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       dialog.dismiss();
                    }
                });


        AlertDialog dialog = builder.create();
        // display dialog
        dialog.show();
    }



}
