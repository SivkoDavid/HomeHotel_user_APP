package VBllc.user.homehotel.API

import VBllc.user.homehotel.API.DataResponse.*
import retrofit2.Response
import retrofit2.http.*

interface API {

    @POST("auth/login")
    @FormUrlEncoded
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<LoginResponse>

    @POST("auth/registration")
    @FormUrlEncoded
    suspend fun registration(
        @Field("name") name: String,
        @Field("second_name") second_name: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("password_confirmation") password_confirmation: String = password
    ): Response<RegistrationResponse>

    @GET("user/info")
    suspend fun getUserInfo(
            @Query("token") token: String
    ): Response<UserInfoResponse>

    @GET("hotels")
    suspend fun getHotels(): Response<HotelsResponse>

    @GET("hotel")
    suspend fun getHotel(
            @Query("filial_id") hotel_id: Int
    ): Response<HotelResponse>

    @POST("settlement")
    @FormUrlEncoded
    suspend fun getSettle(
            @Field("uid") code: String
    ): Response<SettleResponse>
}