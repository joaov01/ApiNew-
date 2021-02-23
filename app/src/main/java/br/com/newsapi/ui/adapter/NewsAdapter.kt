package br.com.newsapi.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import br.com.newsapi.data.model.Article
import br.com.newsapi.data.model.News
import br.com.newsapi.databinding.NewsMainItemLayoutBinding
import com.bumptech.glide.Glide

class NewsAdapter (private var news: News,  private val context: Context):
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>(){

    var onItemClickListener: (article: Article) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
       val layoutInflater = LayoutInflater.from(parent.context)
       val binding = NewsMainItemLayoutBinding.inflate(layoutInflater, parent, false)
        return NewsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return news.articles.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.run{
            tvTitleNewsItem.text = news.articles[position].title
            tvDescriptionNewsItem.text = news.articles[position].description
            clMain.setOnClickListener { onItemClickListener(news.articles[position])}
        Glide.with(context).load(news.articles[position].urlToImage).into(holder.ivNewsItem)
        }
    }

    class NewsViewHolder(binding:NewsMainItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        val ivNewsItem: ImageView = binding.ivNewsItem
        val tvTitleNewsItem: TextView = binding.tvTitleNewsItem
        val tvDescriptionNewsItem: TextView = binding.tvDescriptionNewsItem
        val clMain: ConstraintLayout = binding.clMain
    }

    fun addNews(news: News){
        this.news = news
    }

}