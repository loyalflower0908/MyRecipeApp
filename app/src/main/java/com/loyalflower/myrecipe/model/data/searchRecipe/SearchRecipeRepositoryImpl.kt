package com.loyalflower.myrecipe.model.data.searchRecipe

import com.loyalflower.myrecipe.model.utils.HtmlUtils
import com.loyalflower.myrecipe.model.apiService.YouTubeApiService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

//repository의 기능들 구현
//APIService도 주입받아서 같이 처리
class SearchRecipeRepositoryImpl @Inject constructor(
    private val youTubeApiService: YouTubeApiService,
    private val searchRecipeDao: SearchRecipeDao,
    private val apiKey: String
) : SearchRecipeRepository {

    //API 서비스를 통해 검색 결과를 가져옴, Entity 형식에 맞게 변환해서 반환
    override suspend fun searchRecipes(query: String): List<SearchRecipeEntity> {
        val response = youTubeApiService.searchYoutube(query = query, apiKey = apiKey)
        return if (response.isSuccessful) {
            response.body()?.items?.map { youtubeItem ->
                SearchRecipeEntity(
                    id = youtubeItem.id.videoId,  // 유튜브 비디오 ID를 ID로 사용
                    title = HtmlUtils.decodeHtmlEntities(youtubeItem.snippet.title),
                    description = youtubeItem.snippet.description,
                    thumbnailUrl = youtubeItem.snippet.thumbnails.high.url,
                    publishDate = youtubeItem.snippet.publishedAt,
                    channelName = youtubeItem.snippet.channelTitle
                )
            } ?: emptyList()
        } else {
            emptyList()
        }
    }


    override suspend fun insertRecipe(recipe: SearchRecipeEntity) {
        searchRecipeDao.insertRecipe(recipe)
    }

    override suspend fun deleteRecipe(recipe: SearchRecipeEntity){
        searchRecipeDao.deleteRecipe(recipe)
    }

    override suspend fun deleteRecipes(recipes: List<SearchRecipeEntity>){
        searchRecipeDao.deleteRecipes(recipes)
    }

    override suspend fun isRecipeExists(recipeId: String): Boolean{
        return searchRecipeDao.isRecipeExists(recipeId)
    }

    override fun getAllRecipes(): Flow<List<SearchRecipeEntity>>{
        return searchRecipeDao.getAllRecipes()
    }
}