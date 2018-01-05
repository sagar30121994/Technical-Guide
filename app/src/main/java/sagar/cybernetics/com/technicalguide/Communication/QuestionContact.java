package sagar.cybernetics.com.technicalguide.Communication;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sagar on 7/9/17.
 */

public class QuestionContact {

    @SerializedName("id")
    private String id;



    @SerializedName("Question")
    private String question;


    @SerializedName("option1")
    private String optionA;


    @SerializedName("option2")
    private String optionB;


    @SerializedName("option3")
    private String optionC;


    @SerializedName("option4")
    private String optionD;


    @SerializedName("answer")
    private String ans;

    public String getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public String getOptionA() {
        return optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public String getAns() {
        return ans;
    }


}
