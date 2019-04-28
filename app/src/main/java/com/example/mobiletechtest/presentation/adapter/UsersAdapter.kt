package com.example.mobiletechtest.presentation.adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.mobiletechtest.R
import com.example.mobiletechtest.domain.model.User
import com.squareup.picasso.Picasso

class UsersAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var data = mutableListOf<User>()

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.d("onBindViewHolder", data[position].login)
        (holder as UserViewHolder).bind(data[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_user_item, parent, false)
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    fun setUsers(userData: List<User>){
        data.clear()
        for(user in userData){
            data.add(user)
        }
        notifyDataSetChanged()
    }

    fun clear(){
        data.clear()
        notifyDataSetChanged()
    }

    private class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var login: TextView = view.findViewById(R.id.login)
        var image: ImageView = view.findViewById(R.id.image)
        var type: TextView = view.findViewById(R.id.type)
        var score: TextView = view.findViewById(R.id.score)

        fun bind(user: User){
            login.text = "Name: ${user.login}"
            Picasso.get().load(user.avatarUrl).into(image)
            type.text = "Type: ${user.type}"
            score.text = String.format("Score: %.2f", user.score)
        }

    }
}