package com.example.rcciitapp.data.repository

import com.example.rcciitapp.data.remote.entity.Event
import com.example.rcciitapp.domain.repository.EventRepository
import com.example.rcciitapp.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import javax.inject.Inject

class EventRepositoryImpl @Inject constructor() : EventRepository {
    override suspend fun getEvents(): Flow<Resource<List<Event>>> = flow {
        emit(Resource.loading(null))

        val url = "https://www.rcciit.org/updates/events.aspx"
        val events = mutableListOf<Event>()

        try {
            val doc: Document = Jsoup.connect(url).get()
            val eventElements: List<Element> =
                doc.select("div.ipcontainer3 table#ctl00_ctl00_ContentPlaceHolder_RightContent_GridView1 tbody tr")

            for (element in eventElements) {
                val title = element.select("span.gridtag").text()
                val date = element.select("span.gridtagdate").text().replace("Dated: ", "")
                val venue = element.select("td").text()
                val type = element.select("span.gridtagdate").text().substringAfter("Type: ")

                val event = Event(title, date, venue, type)
                events.add(event)
            }

            emit(Resource.success(events))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Resource.error(null, e.message ?: "An error occurred"))
        }
    }.flowOn(Dispatchers.IO)

}