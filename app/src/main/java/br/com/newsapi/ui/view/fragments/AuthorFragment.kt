package br.com.newsapi.ui.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import br.com.newsapi.R
import br.com.newsapi.data.model.Article
import br.com.newsapi.databinding.FragmentAuthorBinding

class AuthorFragment: Fragment() {

    private lateinit var bind: FragmentAuthorBinding
    private lateinit var article: Article
    private val args by navArgs<DetailsNewsFragmentArgs>()
    private val controller by lazy {
        findNavController()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            article = args.article
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = FragmentAuthorBinding.inflate(layoutInflater, container, false)
        return bind.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        article?.let {
            bind.article = it
            bind.btnBack.setOnClickListener {
                val direction = AuthorFragmentDirections.authorId()
                controller.navigate(direction)
                //controller.navigate(R.id.authorId)
            }
        }

    }
}