package Network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiUtils {

    private ApiUtils() {
    }

    public static final String BASE_URL = "https://richkart.com/api/";
    public static final String HEADER_KEY = "a97d681eac458ea566383e312d6f0fe342b210ae";


    public static APIService getAPIService() {

       return getClient(BASE_URL).create(APIService.class);
    }

    public static Retrofit getClient(String baseUrl) {
        try {
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
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

            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCerts, new SecureRandom());
            SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            httpClient.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
            httpClient.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });

          /*  httpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Interceptor.Chain chain) throws IOException {
                    Request original = chain.request();
                    Request.Builder requestBuilder = original.newBuilder();
                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            });*/



            httpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Interceptor.Chain chain) throws IOException {
                    Request original = chain.request();
                    Request.Builder requestBuilder = original.newBuilder()
                            .addHeader("authorization", HEADER_KEY);
                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            });


//            OkHttpClient client = httpClient.build();


       /*     Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();*/

            OkHttpClient client = httpClient
                    .connectTimeout(100, TimeUnit.SECONDS)
                    .readTimeout(100,TimeUnit.SECONDS).build();

            return new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(client)
//                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
