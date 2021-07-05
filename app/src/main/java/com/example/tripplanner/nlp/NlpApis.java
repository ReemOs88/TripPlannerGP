package com.example.tripplanner.nlp;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface NlpApis {

    @POST("v0.9/functions/siu1tevk44p7wm0l/invoke")
    Call<NlpResponse> analysisReview(@Body NlpRequest nlpRequest);

}
