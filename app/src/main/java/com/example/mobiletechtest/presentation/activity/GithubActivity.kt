package com.example.mobiletechtest.presentation.activity

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.mobiletechtest.R
import com.example.mobiletechtest.data.manager.GithubManager
import com.example.mobiletechtest.domain.exception.NoConnectivityException
import com.example.mobiletechtest.presentation.adapter.UsersAdapter
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import kotlinx.android.synthetic.main.activity_github.*
import java.util.concurrent.TimeUnit

class GithubActivity : AppCompatActivity(){

    private val TAG = "GITHUB ACTIVITY1"

    private lateinit var adapter: UsersAdapter
    private lateinit var manager: GithubManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_github)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        adapter = UsersAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        manager = GithubManager()
        walkInThePark()
        setupListener()
    }


    @SuppressLint("CheckResult")
    private fun setupListener() {
        Observable.create(ObservableOnSubscribe<String> { subscriber ->
            editText.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    spinner.visibility = View.VISIBLE
                    if(!fieldEmpty(s.toString()) && s.toString().length > 2)
                        subscriber.onNext(s.toString())
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            })
        })
            .map { text -> text.toLowerCase().trim() }
            .debounce(350, TimeUnit.MILLISECONDS)
            .subscribe { text ->
                Log.d(TAG, "subscriber: $text")
                searchUsers(text)
            }

    }

    @SuppressLint("CheckResult")
    private fun searchUsers(query: String) {
        val userList = manager.getUserList(query)
        userList.subscribe({ users ->
            users.forEach { Log.d(TAG, "GithubResponse: ${it.login}") }
            spinner.visibility = View.GONE
            adapter.setUsers(users)
        }, {
            when (it) {
                is NoConnectivityException -> Log.e(TAG, "NETWORK ERROR")
                else -> {
                    Log.e(TAG, "UNKNOWN ERROR")
                    snackbarError("Looks like you are out of API Requests, try again in a minute")
                }
            }
        })
    }
    private fun fieldEmpty(s: String): Boolean {
        if (s.isEmpty()) {
            adapter.clear()
            spinner.visibility = View.GONE
            return true
        }
        return false
    }

    private fun snackbarError(message: String){
        Snackbar.make(main_layout, message, Snackbar.LENGTH_LONG).show()
        spinner.visibility = View.GONE
        main_layout.hideKeyboard()
    }

    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    private fun walkInThePark() {
        (1..100).forEach {
            when {
                it % 20 == 0 -> Log.d("WalkInThePark: ", "$it HelloWorld")
                it % 5 == 0 -> Log.d("WalkInThePark: ", "$it World")
                it % 4 == 0 -> Log.d("WalkInThePark: ", "$it Hello")
            }
        }
    }
}
