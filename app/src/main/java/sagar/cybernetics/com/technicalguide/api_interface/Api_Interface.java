package sagar.cybernetics.com.technicalguide.api_interface;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import sagar.cybernetics.com.technicalguide.Communication.PartContact;
import sagar.cybernetics.com.technicalguide.Communication.QuestionContact;
import sagar.cybernetics.com.technicalguide.Communication.Subject_Contact;
import sagar.cybernetics.com.technicalguide.Communication.TestContact;
import sagar.cybernetics.com.technicalguide.Communication.UnitContact;

/**
 * Created by sagar on 3/9/17.
 */

public interface Api_Interface {

    @GET("GetUnit.php")
    Call<List<UnitContact>> getSubject(@QueryMap Map<String ,String> map);


    @GET("GetTest.php")
    Call<List<TestContact>> getTest(@QueryMap Map<String,String> map);



    @GET("SubjectFetch.php")
    Call<List<Subject_Contact>>getSubjectName(@QueryMap Map<String, String> map);




    @GET("NewQuestions.php")
    Call<List<QuestionContact>>getQuestionList(@QueryMap Map<String, String> map);





    @GET("FetchParts.php")
    Call<List<PartContact>>getPartList(@QueryMap Map<String, String> map);







}
