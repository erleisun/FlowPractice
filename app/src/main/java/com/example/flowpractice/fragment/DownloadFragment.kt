package com.example.flowpractice.fragment

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.flowpractice.R
import com.example.flowpractice.databinding.FragmentDownloadBinding
import com.example.flowpractice.download.DownLoadManager
import com.example.flowpractice.download.DownloadStatus
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.collect
import java.io.File

/**
 * A simple [Fragment] subclass.
 */
class DownloadFragment : Fragment() {

    private val mBinding :FragmentDownloadBinding by lazy {
        FragmentDownloadBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return mBinding.root;
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val URL = "https://tse1-mm.cn.bing.net/th/id/R-C.8adb5361a5be9608307725939c365b9f?rik=BSbwLtF73fVzjQ&riu=http%3a%2f%2fwww.dnzhuti.com%2fuploads%2fallimg%2f170323%2f95-1F323144448.jpg&ehk=tBNKAIxpdhwEe%2bM54QdzfbpHFo65HWl9Z8HKmCnSW60%3d&risl=&pid=ImgRaw&r=0"
        lifecycleScope.launchWhenCreated {
            context?.apply {
                val file = File(externalCacheDir,"love.jpg")

                val mFlow = DownLoadManager.download(URL,file)
//                mFlow.cancellable()
                mFlow.collect { status ->
                    when(status){
                      is DownloadStatus.Progress ->{
                          mBinding.progressBar.progress = status.value
                          mBinding.text.text="${status.value}%"
                      }
                      is DownloadStatus.Error->{
                          Toast.makeText(activity,"下载错误",Toast.LENGTH_LONG).show()
                      }
                      is DownloadStatus.Done->{
                          mBinding.progressBar.progress = 100
                          mBinding.text.text = "100%"
                          Toast.makeText(activity,"下载完成",Toast.LENGTH_LONG).show()

                          val bitmap = BitmapFactory.decodeFile(file.absolutePath)
                          bitmap?.let {
                              mBinding.image.setImageBitmap(it)
                          }
                      }
                        else -> "error"
                    }
                }
            }

        }
    }

}