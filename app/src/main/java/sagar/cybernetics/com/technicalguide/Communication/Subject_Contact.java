package sagar.cybernetics.com.technicalguide.Communication;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sagar on 3/9/17.
 */

public class Subject_Contact {

    @SerializedName("id")
    private String id;

    @SerializedName("subject")
    private String subject;

    @SerializedName("university")
    private String university;




    public String getSubId() {
        return id;
    }

    public String getSubName() {
        return subject;
    }

    public String getUniversity() {
        return university;
    }
}
