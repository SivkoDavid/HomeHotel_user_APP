package VBllc.user.homehotel.API

import VBllc.user.homehotel.API.DataResponse.LoginResponse
import VBllc.user.homehotel.API.DataResponse.RegistrationResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

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
}