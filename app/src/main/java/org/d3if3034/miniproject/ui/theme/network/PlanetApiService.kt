package org.d3if3034.miniproject.ui.theme.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.d3if3034.miniproject.ui.theme.model.OpStatus
import org.d3if3034.miniproject.ui.theme.model.Planet
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

private const val BASE_URL = "https://ghania.my.id/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface PlanetApiService {
    @GET("json.php")
    suspend fun getPlanet(
        @Query("auth") userId: String
    ): List<Planet>

    @Multipart
    @POST("json.php")
    suspend fun postPlanet(
        @Part("auth") userId: String,
        @Part("nama") nama: RequestBody,
        @Part("luas") luas: RequestBody,
        @Part image: MultipartBody.Part
    ): OpStatus

    @DELETE("json.php")
    suspend fun deletePlanet(
        @Query("auth") userId: String,
        @Query("id") id: String
    ): OpStatus
}

object PlanetApi {
    val service: PlanetApiService by lazy {
        retrofit.create(PlanetApiService::class.java)
    }
    fun getPlanetUrl(image: String): String {
        return "$BASE_URL$image"
    }
}

enum class ApiStatus { LOADING, SUCCESS, FAILED }