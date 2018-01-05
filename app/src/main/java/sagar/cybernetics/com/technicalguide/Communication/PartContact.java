package sagar.cybernetics.com.technicalguide.Communication;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sagar on 8/9/17.
 */

public class PartContact {

    @SerializedName("part")
    private String part;

    @SerializedName("id")
    private String id;


    public String getPart() {
        return part;
    }

    public String getId() {
        return id;
    }


}
