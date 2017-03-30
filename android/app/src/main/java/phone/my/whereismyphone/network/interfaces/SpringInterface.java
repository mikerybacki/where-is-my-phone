package phone.my.whereismyphone.network.interfaces;

import java.util.List;

import phone.my.whereismyphone.models.Position;
import retrofit2.Call;
import retrofit2.http.GET;

public interface SpringInterface {

    @GET("/getPositions")
    Call<List<Position>> getPositions();

}
