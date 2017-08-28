package org.code5.code5push.api.services;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.code5.code5push.api.Api;
import org.code5.code5push.api.model.Area;
import org.code5.code5push.api.model.City;
import org.code5.code5push.api.model.Division;
import org.code5.code5push.api.model.LoginResponse;
import org.code5.code5push.api.model.Result;
import org.code5.code5push.api.model.State;
import org.code5.code5push.api.model.TicketResponse;
import org.code5.code5push.api.model.User;
import org.code5.code5push.api.rest.ListCallBack;
import org.code5.code5push.api.rest.RestCallBack;
import org.code5.code5push.api.rest.RestInterface;
import org.code5.code5push.api.rest.TicketCreateCallbak;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sony on 8/26/2017.
 */

public final class UserService
{



    public static Gson gson = new GsonBuilder().create();

    private static final RestInterface api = new Retrofit.Builder()
            .baseUrl(Api.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(RestInterface.class);

    private UserService()
    {

    }




    public static void register(String connectionNo,String regId,String name,String email,String password,String passwordConfirmation,String mobileNo,int state,int city,int division,int area,final RestCallBack callBack) throws IOException {



        Call<Result<User>> call = api.registerUser(connectionNo,regId,name, email, password, passwordConfirmation, mobileNo, state, city, division, area);
        call.enqueue(new Callback<Result<User>>() {
            @Override
            public void onResponse(Call<Result<User>> call, Response<Result<User>> response) {
                boolean result ;
                callBack.getMessage(response.message());
                if(response.body().getStatusCode()==200)
                    result=true;
                else
                    result=false;

                callBack.onSuccess(result);
                callBack.getMessage(response.message());

                if(response.body().getStatusCode()==200) {
                    User user = response.body().getData();
                    Log.d("id", String.valueOf(user.getID()));
                    Log.d("fullName", user.getFullname());
                    Log.d("email", user.getEmailId());
                    Log.d("apiToken", user.getApiToken());


                }

                if(response.body().getStatusCode()==422) {
                    User user = response.body().getData();
                    Log.d("RegistrationId", user.getRegId().toString());
                    Log.d("ConnectionNo", user.getConnectionNo().toString());





                }



            }

            @Override
            public void onFailure(Call<Result<User>> call, Throwable t) {

            }
        });




    }

    public static void getStateList(final ListCallBack callBack)
    {
        final ArrayList<String> list = new ArrayList<String>();
        Call<Result<List<State>>> call = api.getStates();
        call.enqueue(new Callback<Result<List<State>>>() {
            @Override
            public void onResponse(Call<Result<List<State>>> call, Response<Result<List<State>>> response)
            {
                for (State state:response.body().getData())
                {
                    list.add(state.getName());
                    Log.d("State",state.getName());
                }
               callBack.getList(list.toArray(new String[0]));
            }

            @Override
            public void onFailure(Call<Result<List<State>>> call, Throwable t) {

            }
        });


    }
    public static void getCityList(int id, final ListCallBack callBack)
    {
        final ArrayList<String> list = new ArrayList<String>();
        Call<Result<List<City>>> call = api.getCities(id);
        call.enqueue(new Callback<Result<List<City>>>() {
            @Override
            public void onResponse(Call<Result<List<City>>> call, Response<Result<List<City>>> response)
            {
                for (City state:response.body().getData())
                {
                    list.add(state.getName());
                }
                callBack.getList(list.toArray(new String[0]));
            }

            @Override
            public void onFailure(Call<Result<List<City>>> call, Throwable t) {

            }
        });

    }

    public static void getDivisionList(int id, final ListCallBack callBack) {
        final ArrayList<String> list = new ArrayList<String>();
        Call<Result<List<Division>>> call = api.getDivisions(id);
        call.enqueue(new Callback<Result<List<Division>>>() {
            @Override
            public void onResponse(Call<Result<List<Division>>> call, Response<Result<List<Division>>> response) {

                for (Division state : response.body().getData()) {
                    list.add(state.getName());
                }
                callBack.getList(list.toArray(new String[0]));

            }

            @Override
            public void onFailure(Call<Result<List<Division>>> call, Throwable t) {

            }
        });
    }


    public static List<String> getAreaList(int id)
    {
        final ArrayList<String> areaList = new ArrayList<String>();
        Call<Result<List<Area>>> call = api.getAreas(id);
        call.enqueue(new Callback<Result<List<Area>>>() {
            @Override
            public void onResponse(Call<Result<List<Area>>> call, Response<Result<List<Area>>> response) {
                for (Area state:response.body().getData())
                {
                    areaList.add(state.getName());
                }

            }

            @Override
            public void onFailure(Call<Result<List<Area>>> call, Throwable t) {

            }
        });

        return areaList;
    }

    public static boolean createTicket(String subject, String description, File file, final TicketCreateCallbak callback)
    {

        final boolean created = false;

        RequestBody reqFile = RequestBody.create(MediaType.parse("image/jpeg"), file);
        RequestBody subj = RequestBody.create(MediaType.parse("text/plain"),subject);
        RequestBody desc = RequestBody.create(MediaType.parse("text/plain"),description);

        MultipartBody.Part imagenPerfil = null;

        // create RequestBody instance from file
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);
        // MultipartBody.Part is used to send also the actual file name
        imagenPerfil = MultipartBody.Part.createFormData("imagenPerfil", file.getName(), requestFile);
        Call<TicketResponse> call = api.createTicket(imagenPerfil,subj,desc);
        call.enqueue(new Callback<TicketResponse>() {
            @Override
            public void onResponse(Call<TicketResponse> call, Response<TicketResponse> response)
            {
                if(response.body().getStatusCode()==200)
                {
                    callback.onSuccess(response.body().getStatusCode()==200?true:false);

                }
            }

            @Override
            public void onFailure(Call<TicketResponse> call, Throwable t) {

            }
        });


        return created;

    }

    public static void login(String email, String password , final TicketCreateCallbak callback) {


        Call<LoginResponse> call = api.login(email, password);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                callback.onSuccess(response.body().getStatusCode()==200?true:false);

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

            }
        });

    };



}