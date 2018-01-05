package sagar.cybernetics.com.technicalguide.Communication;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sagar on 7/9/17.
 */

public class TestContact {


    @SerializedName("Subject")
    private String Subject;

    @SerializedName("Test")
    private String Test;

    @SerializedName("id")
    private String id;



    public String getSubject1() {
        return Subject;
    }
    public String getTest() {
        return Test;
    }

    public String getId() {
        return id;
    }


}
