package com.example.androidretrofitwithcoroutinesapp

// https://www.youtube.com/watch?v=135FpDGJeEQ&list=PLRKyZvuMYSIO0jLgj8g6sADnD0IBaWaw2&index=17

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // We didn't write the body for grtQuotes() in the QuotesAPI interface.
        // The body of the function is handled by retrofit instance.
        // So, we are linking RetrofitHelper class with QuotesAPI Interface
        val quotesAPI = RetrofitHelper.getInstance().create(QuotesAPI::class.java)

        GlobalScope.launch {
            val result = quotesAPI.getQuotes(1)
            if(result!=null){
                val quoteList = result.body()
                quoteList?.results?.forEach {
                    Log.d("Test",it.content)
                }
            }
        }
    }
}