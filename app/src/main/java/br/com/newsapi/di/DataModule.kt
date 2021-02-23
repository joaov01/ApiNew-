package br.com.newsapi.di

import br.com.newsapi.data.repository.IRepository
import br.com.newsapi.data.repository.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
abstract class DataModule {

    @Singleton
    @Binds
    abstract fun bindNewsRepository(
        repository: Repository
    ):IRepository

}