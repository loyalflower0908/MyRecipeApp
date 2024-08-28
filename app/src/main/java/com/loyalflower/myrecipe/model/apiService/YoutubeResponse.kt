package com.loyalflower.myrecipe.model.apiService

//API 응답을 위한 데이터 나누기
data class YoutubeResponse(
    val kind: String,
    val etag: String,
    val nextPageToken: String?,
    val regionCode: String,
    val pageInfo: PageInfo,
    val items: List<YoutubeItem>
)

data class PageInfo(
    val totalResults: Int,
    val resultsPerPage: Int
)

data class YoutubeItem(
    val kind: String,
    val etag: String,
    val id: YoutubeVideoId,
    val snippet: YoutubeSnippet
)

data class YoutubeVideoId(
    val kind: String,
    val videoId: String
)

data class YoutubeSnippet(
    val publishedAt: String,
    val channelId: String,
    val title: String,
    val description: String,
    val thumbnails: YoutubeThumbnails,
    val channelTitle: String,
    val liveBroadcastContent: String
)

data class YoutubeThumbnails(
    val default: Thumbnail,
    val medium: Thumbnail,
    val high: Thumbnail
)

data class Thumbnail(
    val url: String,
    val width: Int,
    val height: Int
)
