package com.domain.mariolopez.testlist.api

import com.domain.mariolopez.testlist.api.model.ListingResults
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * Created by mariolopez on 25/8/17.
 */
interface DomainApi {
    @GET("searchservice.svc/mapsearch")
    fun getList(@QueryMap options: Map<String, String>): Observable<ListingResults>
}