package com.ankit.ignitesolassignment

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import android.widget.SearchView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ankit.ignitesolassignment.`interface`.OnBookItemClick
import com.ankit.ignitesolassignment.adapter.BooksAdapter
import com.ankit.ignitesolassignment.model.Books
import com.ankit.ignitesolassignment.model.Results
import com.ankit.ignitesolassignment.view_model.BooksViewModel
import com.google.android.material.appbar.MaterialToolbar
import kotlinx.android.synthetic.main.activity_books_list.*

class BooksListActivity : AppCompatActivity(), OnBookItemClick {


    lateinit var mViewModel: BooksViewModel

    var mBooksData = Books()
    var page = 1
    var topic: String = ""


    //Variable for checking progressbar loading or not
    private var isLoading: Boolean = false
    lateinit var layoutManager: GridLayoutManager

    //list for holding data
    lateinit var list: ArrayList<Results>
    lateinit var adapter: BooksAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books_list)


        topic = intent.getStringExtra("topic")!!

        val toolbar: Toolbar = findViewById(R.id.toolbar) as MaterialToolbar
        // Toolbar :: Transparent
        toolbar.setBackgroundColor(Color.TRANSPARENT)
        setSupportActionBar(toolbar)
        supportActionBar!!.setTitle(topic.capitalize())
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)


        list = ArrayList()
        layoutManager = GridLayoutManager(this, 3)


        // Status bar :: Transparent
        val window: Window = this.window

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.setStatusBarColor(Color.TRANSPARENT)
        }




        mViewModel = ViewModelProviders.of(this).get(BooksViewModel::class.java)
        progress_circular.visibility = View.VISIBLE
        isLoading = true
        mViewModel.init("fiction", page)

        //mViewModel.books_data.observe()

        mViewModel.books_data.observe(this,
            Observer<Books> { booksData ->


                isLoading = false
                progress_circular.visibility = View.GONE
                mBooksData = booksData
                if (page == 1) {
                    setAdapter()
                } else {
                    loadMore()
                }
            })

    }

    fun setAdapter() {
        val recyclerViewBooksList = findViewById<RecyclerView>(R.id.cv_books_list)
        val cancelIcon = booksSearchView.findViewById<ImageView>(R.id.search_close_btn)
        cancelIcon.setColorFilter(Color.BLACK)

        val textView = booksSearchView.findViewById<TextView>(R.id.search_src_text)
        textView.setTextColor(Color.BLACK)


        list = mBooksData.results
        recyclerViewBooksList.layoutManager = layoutManager
        layoutManager.spanCount = 3
        adapter = BooksAdapter(list, applicationContext, this)
        recyclerViewBooksList.setHasFixedSize(true)

        recyclerViewBooksList.adapter = adapter

        booksSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return false
            }

        })


        recyclerViewBooksList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)


                if (isLoading == false) {
                    if (layoutManager.findLastCompletelyVisibleItemPosition() == list.size - 1) {
                        isLoading = true
                        progress_circular.visibility = View.VISIBLE
                        mViewModel.init(topic, page++)

                    }
                }

            }
        })
    }

    fun loadMore() {
        list.addAll(mBooksData.results)
        adapter.notifyItemInserted(list.size - 1)
    }

    fun checkValidURL(url: String): Boolean {
        return url.contains(".zip", ignoreCase = true)
    }

    override fun onItemClick(id: Int) {


        val urlFormats = mBooksData.results.get(id).formats


        if (urlFormats?.application_pdf != null && !checkValidURL(urlFormats?.application_pdf)) {
            openBookInBrowser(urlFormats?.application_pdf)

        } else if (urlFormats?.text_plain_charset_utf_8 != null && !checkValidURL(urlFormats?.text_plain_charset_utf_8)) {
            openBookInBrowser(urlFormats?.text_plain_charset_utf_8)

        } else if (urlFormats?.text_html_charset_utf_8 != null && !checkValidURL(urlFormats?.text_html_charset_utf_8)) {
            openBookInBrowser(urlFormats?.text_html_charset_utf_8)
        } else {
            val alertDialogBuilder: AlertDialog.Builder = AlertDialog.Builder(this)
            alertDialogBuilder.setMessage("No viewable version available")
            alertDialogBuilder.setCancelable(true)

            alertDialogBuilder.setPositiveButton(
                getString(android.R.string.ok)
            ) { dialog, _ ->
                dialog.cancel()
            }

            val alertDialog: AlertDialog = alertDialogBuilder.create()
            alertDialog.show()
        }


    }

    fun openBookInBrowser(url: String) {
        val uri: Uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW)
        intent.setDataAndType(uri, "text/html")
        startActivity(intent)
    }
}