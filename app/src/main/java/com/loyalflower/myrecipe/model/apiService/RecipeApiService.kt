package com.loyalflower.myrecipe.model.apiService

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

//Retrofit API interface 유튜브 API를 위함. GET안에 요청 보낼 엔드포인트 경로.
//part의 snippet은 기본적으로 비디오 제목, 설명, 채널 정보 등 핵심 메타데이터를 반환. q가 검색어 type - 비디오 검색
//결과는 15개로 제한(10개는 적고 20개는 많은 느낌..?)
interface YouTubeApiService {
    @GET("youtube/v3/search")
    suspend fun searchYoutube(
        @Query("part") part: String = "snippet",
        @Query("q") query: String,
        @Query("type") type: String = "video",
        @Query("key") apiKey: String,
        @Query("maxResults") maxResults: Int = 15
    ): Response<YoutubeResponse>
}
