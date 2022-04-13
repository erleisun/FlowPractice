package com.example.flowpractice.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.liveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flowpractice.adapter.UserAdapter
import com.example.flowpractice.databinding.FragmentUserBinding
import com.example.flowpractice.db.User
import com.example.flowpractice.viewmode.UserViewMode
import kotlinx.coroutines.flow.collect

class UserFragment : Fragment() {

    val viewMode by viewModels<UserViewMode>()

    private val mBinding: FragmentUserBinding by lazy {
        FragmentUserBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return mBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mBinding.apply {
            button.setOnClickListener() {
                editText1.text?.toString()?.toInt()?.let { editText1 ->
                    viewMode.insert(
                        editText1,
                        editTextText2.text.toString(),
                        editTextText3.text.toString()
                    )
                }
            }
        }

        context?.let {
            val adapter = UserAdapter(it, viewMode)
            mBinding.apply {
                recycleView.adapter = adapter
                recycleView.layoutManager =
                    LinearLayoutManager(it,LinearLayoutManager.VERTICAL,false)
            }

            lifecycleScope.launchWhenCreated {
                viewMode.getAll().collect { value->
                    Log.e("UserFragment","collect  value")
                    adapter.setData(value)
                }
            }
        }
    }
}