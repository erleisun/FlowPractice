package com.example.flowpractice.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.flowpractice.databinding.UserItemBinding
import com.example.flowpractice.db.User
import com.example.flowpractice.viewmode.UserViewMode

class UserAdapter(private var context: Context, var viewMode:UserViewMode)
    : RecyclerView.Adapter<UserBindingViewHolder>() {

    private var mData:ArrayList<User> = ArrayList()

    fun setData(data:List<User>){
        mData.clear()
        mData.addAll(data)
        Log.e("UserAdapter","uid ====== ${mData.toString()}")
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserBindingViewHolder {
        val userItemBinding:UserItemBinding =
            UserItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return UserBindingViewHolder(userItemBinding)
    }

    override fun onBindViewHolder(holder: UserBindingViewHolder, position: Int) {

        val user = mData[position]
        holder.binding.apply {
            Log.e("UserAdapter","uid ====== ${user.uid}")
            itemText.text = "${user.uid} ${user.firstName } ${user.lastName }"
        }

    }

    override fun getItemCount() = mData.size
}

class UserBindingViewHolder(var binding: UserItemBinding)
    :RecyclerView.ViewHolder(binding.root) {

}

