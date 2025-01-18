package com.loyalflower.myrecipe.model.di

import com.loyalflower.myrecipe.model.apiService.YouTubeApiService
import com.loyalflower.myrecipe.model.data.searchRecipe.SearchRecipeDao
import com.loyalflower.myrecipe.model.data.searchRecipe.SearchRecipeRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

//DaggerHilt 모듈, Singleton 객체(인스턴스가 하나만)
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    //YouTube API와의 네트워크 통신을 수행하는 Retrofit 객체
    @Provides
    @Singleton
    fun provideYouTubeRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://www.googleapis.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    //Retrofit을 사용해 YouTubeApiService 인터페이스의 구현체 생성
    @Provides
    @Singleton
    fun provideYouTubeApiService(retrofit: Retrofit): YouTubeApiService {
        return retrofit.create(YouTubeApiService::class.java)
    }

    // apiKey를 네트워크 모듈 내에서 제공
    @Provides
    @Singleton
    fun provideApiKey(): String {
        return "Your YouTube API Key" // 실제 API 키를 입력하세요
    }

    //SearchRecipeRepositoryImpl 객체를 생성해서 필요 값 제공
    @Provides
    @Singleton
    fun provideRecipeRepository(
        youTubeApiService: YouTubeApiService,
        recipeDao: SearchRecipeDao,
        apiKey:String
    ): SearchRecipeRepositoryImpl {
        return SearchRecipeRepositoryImpl(
            youTubeApiService,
            recipeDao,
            apiKey
        )
    }
}