package sagar.cybernetics.com.technicalguide.Communication;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sagar on 7/9/17.
 */

public class UnitContact {

    @SerializedName("subject")
    private String subject;

    @SerializedName("unit")
    private String unit;

    @SerializedName("id")
    private String id;



    public String getSubject() {
        return subject;
    }
    public String getUnit() {
        return unit;
    }

    public String getId() {
        return id;
    }


}
