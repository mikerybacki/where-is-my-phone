package phone.my.whereismyphone.network;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import phone.my.whereismyphone.BuildConfig;
import phone.my.whereismyphone.models.Position;
import phone.my.whereismyphone.network.interfaces.SpringInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {

    private SpringInterface mApi;

    private static Api sSharedInstance;

    public static Api sharedInstance() {
        if (sSharedInstance == null) {
            synchronized (Api.class) {
                if (sSharedInstance == null) {
                    sSharedInstance = new Api();
                }
            }
        }
        return sSharedInstance;
    }

    private Api() {
        init();
    }

    private void init() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
            builder.addInterceptor(loggingInterceptor);
        }

        OkHttpClient client = builder.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.1.10.122:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        mApi = retrofit.create(SpringInterface.class);
    }

    public void getPositions() {
        Call<List<Position>> call = mApi.getPositions();
        call.enqueue(new Callback<List<Position>>() {
            @Override
            public void onResponse(Call<List<Position>> call, Response<List<Position>> response) {

            }

            @Override
            public void onFailure(Call<List<Position>> call, Throwable t) {

            }
        });
    }

}
