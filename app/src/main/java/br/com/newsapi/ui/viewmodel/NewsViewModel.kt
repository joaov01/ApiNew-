package br.com.newsapi.ui.viewmodel
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.newsapi.data.model.News
import br.com.newsapi.data.repository.IRepository
import br.com.newsapi.data.repository.Repository
import br.com.newsapi.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception


class NewsViewModel @ViewModelInject constructor(private val repository: IRepository): ViewModel(){

    val newsLiveData by lazy {
        MutableLiveData<Resource<News>>()
    }

//    Essa forma de chamar a Api usando a funcão emit também é interessante,
//    porém, dessa forma o serviço sempre é chamado mesmo quando só há uma mudança de configuração
//
//    fun getNews() = liveData(Dispatchers.IO) {
//        emit(Resource.loading(null))
//        try {
//            emit(Resource.success(data = repository.getNews()))
//        }catch (exception: Exception){
//            emit(Resource.error(data = null, message = exception.message ?: "Erro ao buscar notícia"))
//        }
//    }

    fun getNews() {
        CoroutineScope(Dispatchers.Main).launch {
            newsLiveData.value = Resource.loading(null)
            try {
                val news = withContext(Dispatchers.Default){
                    repository.getNews()
                }
                newsLiveData.value = Resource.success(news)
            }catch (exception: Exception){
                newsLiveData.value = Resource.error(data = null,
                        message = exception.message ?: "Erro ao buscar notícia")
            }
        }
    }

    class NewsViewModelFactory(private val repository: Repository): ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return NewsViewModel(repository) as T
        }
    }
}