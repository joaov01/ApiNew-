package br.com.newsapi.ui.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import br.com.newsapi.data.model.Article
import br.com.newsapi.databinding.FragmentDetailsNewsBinding
import com.bumptech.glide.Glide

class DetailsNewsFragment : Fragment() {
    private lateinit var bind: FragmentDetailsNewsBinding
    private lateinit var article: Article
    private val args by navArgs<DetailsNewsFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            article = args.article
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = FragmentDetailsNewsBinding.inflate(layoutInflater, container, false)
        return bind.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bind.article = article
        Glide.with(this).load(article.urlToImage).into(bind.ivDetails)
    }
}