package support;

import okhttp3.ResponseBody;
import retrofit2.Response;

public interface RetrofitResponse
{
    void onServiceResponse(int requestCode, Response<ResponseBody> response);
}