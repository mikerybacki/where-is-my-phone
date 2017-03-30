package phone.my.whereismyphone.network.interfaces;

import java.util.List;

import phone.my.whereismyphone.models.Position;
import phone.my.whereismyphone.models.ResponseInfo;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface SpringInterface {

    @GET("/getPositions")
    Call<List<Position>> getPositions();

    @POST("/position")
    Call<ResponseInfo> sendPosition(@Body Position body);
}
