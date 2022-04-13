package com.example.flowpractice.viewmode

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.flowpractice.db.AppDataBase
import com.example.flowpractice.db.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class UserViewMode(app: Application) : AndroidViewModel(app) {

    /**
     * insert需要使用协程
     */
    fun insert(uid:Int,firstName:String,lastName:String){
        viewModelScope.launch {
            AppDataBase.getInstance(getApplication())
                .userDao()
                .insert(User(uid,firstName,lastName))
        }
    }

    /**
     * 访问数据库 在IO线程，不需要使用协程 内部又
     */
    fun getAll():Flow<List<User>>{
        return AppDataBase.getInstance(getApplication())
            .userDao()
            .getAll()
            .catch { e->e.printStackTrace()}
            .flowOn(Dispatchers.IO)
    }
}