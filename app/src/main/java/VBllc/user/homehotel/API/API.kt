package VBllc.user.homehotel.API

import VBllc.user.homehotel.API.DataResponse.LoginResponse
import VBllc.user.homehotel.API.DataResponse.RegistrationResponse
import VBllc.user.homehotel.API.DataResponse.UserInfoResponse
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
}