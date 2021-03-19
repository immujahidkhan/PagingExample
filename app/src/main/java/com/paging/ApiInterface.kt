package com.paging
import com.paging.models.Posts
import io.reactivex.Single
import retrofit2.http.*

interface ApiInterface {

    @GET
    fun getPaginatedProfile(
        @Url endPoint: String,
        @QueryMap map: HashMap<String, String>
    ): Single<Posts>
}