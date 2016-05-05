package gr11.compumovil.udea.edu.co.weather_lab4_gr11;

import gr11.compumovil.udea.edu.co.weather_lab4_gr11.POJO.Model;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by kundan on 8/8/2015.
 */
public interface RestInterface {

    /**
     * @param place
     * @param appId
     * @param cb
     */
    //@GET("/weather?q=London,UK")
    //void getWheatherReport(Callback<Model>cb);
    @GET(value = "/weather")
    void getWheatherReport(@Query(value = "q") String place, @Query(value = "appid") String appId, Callback<Model> cb);
}
