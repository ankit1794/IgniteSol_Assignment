package com.ankit.ignitesolassignment.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ankit.ignitesolassignment.model.Books
import com.ankit.ignitesolassignment.retrofit.ApiInterface
import com.ankit.ignitesolassignment.retrofit.RetrofitClientInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BooksViewModel : ViewModel() {

    var books_data: MutableLiveData<Books> = MutableLiveData()
    private val service =
        RetrofitClientInstance.getRetrofitInstance().create(ApiInterface::class.java)


    fun init(topic: String, page: Int) {
//        if (books_data != null) {
//            return
//        }
        getBooksData(topic, page)
    }

    fun loadMoreData(topic: String, page: Int) {

    }

    fun getBooks(): MutableLiveData<Books>? {
        return this.books_data
    }

    init {
    }

    fun getBooksData(topic: String, page: Int): MutableLiveData<Books>? {
        val booksData: MutableLiveData<Books> = MutableLiveData<Books>()

        val call: Call<Books> = service.getBooksData(topic, page)
        call.enqueue(object : Callback<Books?> {
            override fun onResponse(call: Call<Books?>, response: Response<Books?>) {
                books_data.setValue(response.body())
            }

            override fun onFailure(call: Call<Books?>, t: Throwable) {
                t.printStackTrace()
            }
        })

        return booksData;
    }
}