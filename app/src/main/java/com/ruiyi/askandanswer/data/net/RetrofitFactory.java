package com.ruiyi.askandanswer.data.net;

import android.content.Context;
import android.util.Log;

import com.ruiyi.askandanswer.R;
import com.ruiyi.askandanswer.base.BaseApplication;
import com.ruiyi.askandanswer.base.BaseConstant;
import com.ruiyi.askandanswer.utils.AppPrefsUtils;
import com.ruiyi.askandanswer.utils.SharedPreferencesKey;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateFactory;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/*
    Retrofit工厂，单例
 */
public class RetrofitFactory {

    private Retrofit retrofit = null;

    public static RetrofitFactory retrofitFactory;

    public static RetrofitFactory getIntance() {
        if (retrofitFactory == null)
            synchronized (RetrofitFactory.class) {
                if (retrofitFactory == null)
                    retrofitFactory = new RetrofitFactory();
            }
        return retrofitFactory;
    }

    public Retrofit getRetrofit(){
        //Retrofit实例化
        return retrofit = new Retrofit.Builder()
                .baseUrl(BaseConstant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(initClient())
                .build();
    }

    public Retrofit getByteRetrofit(){
        //Retrofit实例化
        return retrofit = new Retrofit.Builder()
                .baseUrl(BaseConstant.BASE_URL)
                .addConverterFactory(new ToByteConvertFactory())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(initClient())
                .build();
    }

    /*
       OKHttp创建
    */
    private OkHttpClient initClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(initLogInterceptor())
//                 .sslSocketFactory(getSSLSocketFactory(BaseApplication.getInstance().getApplicationContext()))
                .sslSocketFactory(getSocketFactory(BaseApplication.getInstance().getApplicationContext()))
                 .hostnameVerifier(getHostnameVerifier())
                .retryOnConnectionFailure(true)
                .addInterceptor(mTokenInterceptor)
//                .addInterceptor(new LoggerInterceptor())
                .readTimeout(10, TimeUnit.SECONDS)
                .build();
    }

    Interceptor mTokenInterceptor = new Interceptor() {
        @Override public Response intercept(Chain chain) throws IOException {
            Request originalRequest = chain.request();
            if (AppPrefsUtils.getString(SharedPreferencesKey.SHAREDPREFERENCES_TOKEN,"").equals("")) {
                return chain.proceed(originalRequest);
            }
            Request authorised = originalRequest.newBuilder()
                    .addHeader("Content_Type","application/json")
                    .addHeader("charset","UTF-8")
                    .build();
            return chain.proceed(authorised);
        }
    };

    /*
        日志拦截器
     */
    private HttpLoggingInterceptor initLogInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    /**
     * 添加证书
     *
     */
    public static SSLSocketFactory getSocketFactory(Context context) {
        try {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null);
            InputStream is = context.getResources().openRawResource(R.raw.key);
            keyStore.setCertificateEntry("0", certificateFactory.generateCertificate(is));
            if (is!=null){
                is.close();
            }
            SSLContext sslContext = SSLContext.getInstance("TLS");

            TrustManagerFactory trustManagerFactory =
                    TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());

            trustManagerFactory.init(keyStore);
            sslContext.init(null, trustManagerFactory.getTrustManagers(), new SecureRandom());
            return sslContext.getSocketFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取bks文件的sslsocketfactory
     * @param context
     * @return
     */
    public static SSLSocketFactory getSSLSocketFactory(Context context) {
        final String CLIENT_TRUST_PASSWORD = "";//信任证书密码，该证书默认密码是123456
        final String CLIENT_AGREEMENT = "TLS";//使用协议
        final String CLIENT_TRUST_KEYSTORE = "BKS";
        SSLContext sslContext = null;
        try {
            //取得SSL的SSLContext实例
            sslContext = SSLContext.getInstance(CLIENT_AGREEMENT);
            //取得TrustManagerFactory的X509密钥管理器实例
            TrustManagerFactory trustManager = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            //取得BKS密库实例
            KeyStore tks = KeyStore.getInstance(CLIENT_TRUST_KEYSTORE);
            InputStream is = context.getResources().openRawResource(R.raw.key);
            try {
                tks.load(is, CLIENT_TRUST_PASSWORD.toCharArray());
            } finally {
                is.close();
            }
            //初始化密钥管理器
            trustManager.init(tks);
            //初始化SSLContext
            sslContext.init(null, trustManager.getTrustManagers(), null);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("SslContextFactory", e.getMessage());
        }
        return sslContext.getSocketFactory();
    }

    //获取HostnameVerifier
    private HostnameVerifier getHostnameVerifier() {
        return new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };
    }
}
