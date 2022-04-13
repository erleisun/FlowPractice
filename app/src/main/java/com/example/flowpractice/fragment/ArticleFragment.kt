package com.example.flowpractice.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.flowpractice.R
import com.example.flowpractice.databinding.FragmentArticleBinding
import com.example.flowpractice.viewmode.ArticleViewMode
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest

/**
 * A simple [Fragment] subclass.
 * Use the [ArticleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@ExperimentalCoroutinesApi
class ArticleFragment : Fragment() {

    private val viewMode by viewModels<ArticleViewMode>()

    private val binding by lazy { FragmentArticleBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    //写一个flow型的扩展函数
    private fun TextView.textViewFlow() = callbackFlow {
        //edit监听 相当于新建一个接口对象
        val textWatch = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                Log.d("ArticleFragment", s.toString())
                offer(s.toString())
            }
        }
        addTextChangedListener(textWatch)
        awaitClose {
            removeTextChangedListener(textWatch)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //监听edit输入 并搜索
        lifecycleScope.launchWhenCreated {
            binding.editQuery.textViewFlow().collectLatest {
                Log.d("ArticleFragment", "collect is $it")

                viewMode.searchArticles(it)
            }
        }

        //监听回调
        viewMode.articles.observe(viewLifecycleOwner, Observer {articles ->
            Log.d("","articles = $articles")

        })

    }

}