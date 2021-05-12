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

    @POST("reviews/filial/store")
    @FormUrlEncoded
    suspend fun sendHotelReview(
        @Field("token") token: String,
        @Field("filial_id") filial_id: Int,
        @Field("text") text: String,
        @Field("rating") rating: Int
    ): Response<SendReviewResponse>

    @GET("services/get_service_for_room")
    suspend fun getHotelServices(
        @Query("room_id") room_id: Int
    ): Response<HotelServicesResponse>

    @GET("services/get_partner_service")
    suspend fun getPrtnerServices(
            @Query("filial_id") filial_id: Int
    ): Response<PartnersServicesResponse>

    @GET("services/get_products")
    suspend fun getProducts(
            @Query("settlement_uid") settlement_uid: String
    ): Response<FoodMenuResponse>

    @POST("services/send_order")
    @FormUrlEncoded
    suspend fun sendOrder(
            @Field("settlement_uid") settlement_uid: String,
            @Field("preferred_time") preferred_time: String?,
            @Field("service_id") service_id: Int?,
            @Field("token") token: String,
            @Field("products[]") products: List<Int>?
    ): Response<SendOrderResponse>

    @POST("services/send_cleaning_order")
    @FormUrlEncoded
    suspend fun sendCleaningOrder(
            @Field("settlement_uid") settlement_uid: String,
            @Field("preferred_time") preferred_time: String?,
            @Field("token") token: String,
            @Field("products") products: List<Int>?
    ): Response<SendOrderResponse>

    @POST("services/send_hygiene_order")
    @FormUrlEncoded
    suspend fun sendHugieneOrder(
            @Field("settlement_uid") settlement_uid: String,
            @Field("preferred_time") preferred_time: String?,
            @Field("token") token: String,
            @Field("products") products: List<Int>?
    ): Response<SendOrderResponse>

}