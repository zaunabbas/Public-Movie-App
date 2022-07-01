package com.zenjob.android.browsr.di

import com.zenjob.android.browsr.network.TMDBApi
import com.zenjob.android.browsr.ui.list.ListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

  @Provides
  @ViewModelScoped
  fun provideMainRepository(
    tmdbApi: TMDBApi
  ): ListRepository {
    return ListRepository(tmdbApi)
  }
}
