package br.com.newsapi.ui.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.newsapi.data.model.News
import br.com.newsapi.databinding.FragmentNewsBinding
import br.com.newsapi.ui.adapter.NewsAdapter
import br.com.newsapi.ui.viewmodel.NewsViewModel
import br.com.newsapi.utils.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment : Fragment() {

    private lateinit var bind: FragmentNewsBinding
    private lateinit var adapter: NewsAdapter
    private val viewmodel: NewsViewModel by viewModels()
    private val controller by lazy {
        findNavController()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        bind = FragmentNewsBinding.inflate(layoutInflater, container, false)
        return bind.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViewModel()
        setupAdapter()
        setupObservers()
        if(viewmodel.newsLiveData.value == null){
            bind.viewmodel?.let {
                it.getNews()
            }
        }
    }

    private fun setupViewModel(){
        bind.viewmodel = viewmodel
    }

    private fun setupObservers(){
        bind?.run {
            viewmodel!!.newsLiveData.observe(viewLifecycleOwner, Observer {
                it?.let {resource -> when(resource.status){
                    Status.SUCCESS -> {
                        retrieveList(resource.data!!)
                        bind.apply {
                            rvNewsMain.visibility = View.VISIBLE
                            pbNews.visibility = View.GONE
                        }
                    }
                    Status.LOADING -> {
                        bind.apply {
                            rvNewsMain.visibility = View.GONE
                            pbNews.visibility = View.VISIBLE
                        }
                    }
                    Status.ERROR ->{
                        bind.apply {
                            pbNews.visibility = View.GONE
                            rvNewsMain.visibility = View.VISIBLE
                        }
                        Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                    }
                }
                }
            })
        }
    }

    private fun setupAdapter(){
        adapter = NewsAdapter(News(), requireContext())
        adapter.onItemClickListener = {it  ->
            val direction = NewsFragmentDirections.actionNewsFragmentToDetailsNewsFragment(it)
            controller.navigate(direction)
        }
        bind.apply {
            rvNewsMain.layoutManager = LinearLayoutManager(context)
            rvNewsMain.adapter = adapter
        }
    }

    private fun retrieveList(news: News){
        adapter.apply {
            addNews(news)
            notifyDataSetChanged()
        }
    }
}