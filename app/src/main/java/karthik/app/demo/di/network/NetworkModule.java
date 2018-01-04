package karthik.app.demo.di.network;

import com.github.slashrootv200.retrofithtmlconverter.HtmlConverterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import karthik.app.demo.constants.ApiConstants;
import karthik.app.demo.data.api.ApiService;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by NiCK on 1/2/2018.
 */

@Module
public class NetworkModule {

    @Provides
    @Singleton
    HttpLoggingInterceptor provideLoggingInterceptor() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return logging;
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(HttpLoggingInterceptor loggingInterceptor) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .readTimeout(ApiConstants.TIME_OUT, TimeUnit.SECONDS)
                .connectTimeout(ApiConstants.TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(ApiConstants.TIME_OUT, TimeUnit.SECONDS);
        httpClient.addInterceptor(loggingInterceptor);  // <-- this is the important line!
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request request = original.newBuilder()
                        .method(original.method(), original.body())
                        .build();

                return chain.proceed(request);
            }
        });

        OkHttpClient okHttpClient = httpClient.build();

        return okHttpClient;
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(ApiConstants.BASE_URL)
                .addConverterFactory(HtmlConverterFactory.create(ApiConstants.BASE_URL))
                .client(okHttpClient)
                .build();

    }

    @Provides
    @Singleton
    ApiService provideApiService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }
}
