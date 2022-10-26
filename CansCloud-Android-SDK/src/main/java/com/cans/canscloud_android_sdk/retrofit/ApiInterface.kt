package com.cans.canscloud_android_sdk.retrofit

import com.cans.canscloud_android_sdk.model.PhonebookContacts
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

    @FormUrlEncoded
    @POST("https://{domain}/Cpanel/contacts/")
    fun getPhonebook(@Path("domain") domain: String,
                     @Field("extension") extension: String?
    ): Call<PhonebookContacts>

}