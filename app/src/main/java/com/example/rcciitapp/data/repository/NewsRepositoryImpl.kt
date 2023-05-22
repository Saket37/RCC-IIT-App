package com.example.rcciitapp.data.repository

import com.example.rcciitapp.data.remote.entity.NewsItem
import com.example.rcciitapp.domain.repository.NewsRepository
import com.example.rcciitapp.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor() : NewsRepository {
    override suspend fun getNews(): Flow<Resource<List<List<String>>>> = flow {
            emit(Resource.loading(null))

            try {
                val url = "https://www.rcciit.org/updates/news.aspx"
                val document = fetchDocument(url)

                val newsItems = parseNewsItems(document)

                emit(Resource.success(newsItems))
            } catch (e: Exception) {
                emit(Resource.error(null, e.message ?: "Error occurred"))
            }
        }.flowOn(Dispatchers.IO)


    private fun fetchDocument(url: String): Document {
        return Jsoup.connect(url).get()
    }

    private fun parseNewsItems(document: Document): List<List<String>> {
        val newsItems = mutableListOf<List<String>>()

        val container = document.selectFirst(".ipcontainer3")
        val rows = container?.select("tr")

        if (rows != null) {
            for (row in rows) {
                val dateElement = row.selectFirst(".newsDate")
                val contentElement = row.selectFirst("td:nth-child(2)")

                val date = dateElement?.text()?.trim() ?: ""
                val content = contentElement?.text()?.trim() ?: ""

                if (date.isNotEmpty() && content.isNotEmpty()) {
                    val newsItem = listOf(date, content)
                    newsItems.add(newsItem)
                }
            }
        }

        return newsItems
    }
}
