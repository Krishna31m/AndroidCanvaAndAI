/*
 * *
 *  * Created by Paramjit.Rana on 03/09/21, 2:54 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *  * Last modified 09/08/21, 10:52 AM
 *
 */

package support;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;


public interface RetrofitService
{
    @GET
    Call<ResponseBody> callGetService(@Url String url);

}



