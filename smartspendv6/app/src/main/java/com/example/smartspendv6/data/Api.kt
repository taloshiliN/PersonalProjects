package com.example.smartspendv6.data

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface Api {
    //for the sign up screen
    @POST("/signup")
    fun postUsers(@Body signupUsers: SignupUsers): Call<SignupUsers>

    //for the login screen
    @POST("/login")
    fun postLogin(@Body loginUsers: LoginUsers): Call<LoginUsers>

    //for the add card screen
    @POST("/addcard")
    fun postCard(@Body card: Card): Call<Card>

    @POST("/addgoal")
    fun postGoal(@Body goals: Goals): Call<Goals>

    @POST("/transferdetails")
    fun postTransfer(@Body transfer: Transfer): Call<Transfer>

    @GET("/addgoal")
    suspend fun getGoal(): List<Goals>
    @POST("/addcategory")
    fun postCategory(@Body category: Categories): Call<Categories>

    @POST("/addexpenses")
    fun postExpenses(@Body expenses: Expenses): Call<Expenses>

    @GET("/getcategories")
    suspend fun getCategories(): List<Categories>
    @GET("/getexpense")
    suspend fun getExpenses(): List<Expenses>
}