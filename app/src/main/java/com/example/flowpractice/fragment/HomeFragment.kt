package com.example.flowpractice.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.flowpractice.R
import com.example.flowpractice.databinding.FragmentHomeBinding

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {
    companion object{
        val TAG = HomeFragment::class.java.simpleName
    }

    private val mBinding : FragmentHomeBinding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG,"homeFragment onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG,"homeFragment onCreateView")
        return mBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d(TAG,"homeFragment onActivityCreated")
        mBinding.btnFlow.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_downloadFragment)
        }

        mBinding.btn2.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_userFragment)
        }

        mBinding.btn3.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_articleFragment)
        }

        mBinding.btn3.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_pagingFragment)
        }
    }

}