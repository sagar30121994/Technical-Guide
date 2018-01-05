package sagar.cybernetics.com.technicalguide.api_Client;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sagar on 3/9/17.
 */

public class api_Client {

    public static class Api_Client {
        public static final String BASE_URL = "http://technicalguide.000webhostapp.com/Android/";

        public static Retrofit retrofit = null;


        public static Retrofit getApi_Client() {

            if (retrofit == null) {

                retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create()).build();
            }

            return retrofit;
        }

    }


}
