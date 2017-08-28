package org.code5.code5push.api.rest;

import org.code5.code5push.api.model.Area;
import org.code5.code5push.api.model.City;
import org.code5.code5push.api.model.Division;
import org.code5.code5push.api.model.LoginResponse;
import org.code5.code5push.api.model.Result;
import org.code5.code5push.api.model.State;
import org.code5.code5push.api.model.Ticket;
import org.code5.code5push.api.model.TicketResponse;
import org.code5.code5push.api.model.User;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * Created by sony on 8/26/2017.
 */
public interface RestInterface
{

    @FormUrlEncoded
    @POST("register")
    Call<Result<User>> registerUser(@Field("connection_number")String connectionNo,@Field("registration_id")String regId,@Field("name")String name, @Field("email")String email,
                                    @Field("password")String password, @Field("password_confirmation")String passwordConfirmation
            , @Field("mobile")String mobileNumber, @Field("state")int stateId, @Field("city")int cityId, @Field("division")int divisionId, @Field("area")int areaCode);


    @GET("area/{division_id}")
    Call<Result<List<Area>>> getAreas(@Path("division_id") int divisionId);

    @GET("division/{city_id}")
    Call<Result<List<Division>>> getDivisions(@Path("city_id") int cityId);

    @GET("city/{state_id}")
    Call<Result<List<City>>> getCities(@Path("state_id") int stateId);

    @GET("state/list")
    Call<Result<List<State>>> getStates();

    @FormUrlEncoded
    @POST("login")
    Call<LoginResponse> login(@Field("email")String email, @Field("password")String password);

    @GET("logout/{token}")
    // Call<Logout> logout(@Path("token") String token);

    @FormUrlEncoded
    @POST("ticket/create")
    Call<TicketResponse> createTicket(@Field("subject") String subject, @Field("description") String description);

    @POST("ticket/{id}")
    Call<Ticket> getTicketDetail(@Path("id") String tokenId);

    @Multipart
    @POST("ticket/create")
    Call<TicketResponse> createTicket(@Part MultipartBody.Part image,
                                      @Part("subject") RequestBody subject,
                                      @Part("description") RequestBody description);

}