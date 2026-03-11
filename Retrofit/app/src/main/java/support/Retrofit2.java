package support;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.krishna.retrofit.eversaleskotlinnewversion.Keys;

import java.security.cert.CertificateException;
import java.util.Arrays;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class Retrofit2 {
    private final RetrofitResponse result;
    public final Context mContext;
    private final int requestCode;
    private final String url;

    public Retrofit2(Context context, RetrofitResponse result, int requestCode, String url) {
        mContext = context;
        this.result = result;
        this.requestCode = requestCode;
        this.url = url;
    }

    public void callService(boolean dialog) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Keys.testURL)
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);

        Call<ResponseBody> call = retrofitService.callGetService(url);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> callback, @NonNull Response<ResponseBody> response) {

                try {
                    if (response.isSuccessful()) {
                        result.onServiceResponse(requestCode, response);
                    } else {
                        Log.e("KEY_TAG", "message- " + response.message());
                        Log.e("KEY_TAG", "errorBody-  " + response.errorBody());
                    }
                } catch (Exception ignored) {
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                try {
                    Log.e(Keys.KEY_TAG, call.toString());
                    call.cancel();
                    Log.e(Keys.KEY_TAG, "t.getMessage()--" + t.getMessage());
                    Log.e(Keys.KEY_TAG, "t.getLocalizedMessage()--" + t.getLocalizedMessage());
                    Log.e(Keys.KEY_TAG, "t.getCause()--" + t.getCause());
                    Log.e(Keys.KEY_TAG, "getStackTrace()--" + Arrays.toString(t.getStackTrace()));
                } catch (Exception ignored) {
                }
            }
        });
    }
    @SuppressLint("TrustAllX509TrustManager")
    public static OkHttpClient.Builder getUnsafeOkHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }
                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }
                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
            builder.hostnameVerifier((hostname, session) -> true);
            return builder;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
