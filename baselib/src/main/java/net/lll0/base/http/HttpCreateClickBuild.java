package net.lll0.base.http;

import net.lll0.base.BaseApplication;

import java.io.File;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Cache;
import okhttp3.OkHttpClient;

/**
 * Created by liangjun on 2018/11/13
 */
public class HttpCreateClickBuild {

    public final static int CONNECT_TIMEOUT = 5;
    public final static int READ_TIMEOUT = 10;
    public final static int WRITE_TIMEOUT = 60;


    private static SSLContext sslContext;

    private static OkHttpClient.Builder builder;


    private HttpCreateClickBuild() {
    }

    public static OkHttpClient.Builder instance() {
        if (builder == null) {
            synchronized (HttpCreateClickBuild.class) {
                if (builder == null) {
                    init();
                }
            }
        }

        return builder;

    }


    private static void init() {
        overLockCard();
        File cacheFile = BaseApplication.application.getExternalCacheDir();
        int cacheSize = 10 * 1024 * 1024;
        builder = new OkHttpClient.Builder();
        if (cacheFile.getAbsoluteFile() != null) {
            builder.connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                    .cache(new Cache(cacheFile.getAbsoluteFile(), cacheSize))
                    .sslSocketFactory(sslContext.getSocketFactory())
                    .hostnameVerifier(new HostnameVerifier() {
                        @Override
                        public boolean verify(String hostname, SSLSession session) {
                            return true;
                        }
                    });
        } else {
            builder.connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                    .sslSocketFactory(sslContext.getSocketFactory())
                    .hostnameVerifier(new HostnameVerifier() {
                        @Override
                        public boolean verify(String hostname, SSLSession session) {
                            return true;
                        }
                    });
        }
    }

    /**
     * 忽略所有https证书
     */
    private static void overLockCard() {
        final TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            @Override
            public void checkClientTrusted(
                    X509Certificate[] chain,
                    String authType) throws CertificateException {
            }

            @Override
            public void checkServerTrusted(
                    X509Certificate[] chain,
                    String authType) throws CertificateException {
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                X509Certificate[] x509Certificates = new X509Certificate[0];
                return x509Certificates;
            }
        }};
        try {
            sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts,
                    new java.security.SecureRandom());
        } catch (Exception e) {
        }

    }


}
