package com.domain.mariolopez.testlist.api

import com.domain.mariolopez.testlist.api.model.ListingResults
import com.google.gson.*
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type


class RestAdapter {

    private var domainApi: DomainApi = Retrofit.Builder()
            .baseUrl("https://rest.domain.com.au")
            .addConverterFactory(getListingsGsonConverter())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(DomainApi::class.java)


    //we parse just what we need
    inner class ListingTypeAdapter : JsonDeserializer<ListingResults> {
        @Throws(JsonParseException::class)
        override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): ListingResults {

            val listingsResultObj = json.asJsonObject
                    .getAsJsonObject("ListingResults")

            return Gson().fromJson<ListingResults>(listingsResultObj, ListingResults::class.java)
        }

    }

    private fun getListingsGsonConverter(): GsonConverterFactory {
        val gson = GsonBuilder()
                .registerTypeAdapter(ListingResults::class.java, ListingTypeAdapter())
                .create()
        return GsonConverterFactory.create(gson)
    }

    fun getListings(): Observable<ListingResults> {
        //stub query parameters just for the test
        return domainApi.getList(mapOf(
                "mode" to "buy",
                "sub" to "Bondi",
                "s" to "2026 ",
                "state" to "NSW"))
    }

}