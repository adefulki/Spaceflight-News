package io.github.adefulki.spaceflightnews.data.local.entity

import io.github.adefulki.spaceflightnews.domain.model.Author
import io.github.adefulki.spaceflightnews.domain.model.Event
import io.github.adefulki.spaceflightnews.domain.model.Launch
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.Ignore
import io.realm.kotlin.types.annotations.PrimaryKey
import java.util.UUID

class ArticleEntity() : RealmObject {

    @PrimaryKey
    var id: String = ""
    var idArticle: Int? = null
    var title: String? = null
    @Ignore
    var authors: ArrayList<Author> = arrayListOf()
    var url: String? = null
    var imageUrl: String? = null
    var newsSite: String? = null
    var summary: String? = null
    var publishedAt: String? = null
    var updatedAt: String? = null
    var featured: Boolean? = null
    @Ignore
    var launches: ArrayList<Launch> = arrayListOf()
    @Ignore
    var events: ArrayList<Event> = arrayListOf()

    constructor(
        id: String = UUID.randomUUID().toString(),
        idArticle: Int? = null,
        title: String? = null,
        authors: ArrayList<Author> = arrayListOf(),
        url: String? = null,
        imageUrl: String? = null,
        newsSite: String? = null,
        summary: String? = null,
        publishedAt: String? = null,
        updatedAt: String? = null,
        featured: Boolean? = null,
        launches: ArrayList<Launch> = arrayListOf(),
        events: ArrayList<Event> = arrayListOf()
    ) : this() {
        this.id = id
        this.idArticle = idArticle
        this.title = title
        this.authors = authors
        this.url = url
        this.imageUrl = imageUrl
        this.newsSite = newsSite
        this.summary = summary
        this.publishedAt = publishedAt
        this.updatedAt = updatedAt
        this.featured = featured
        this.launches = launches
        this.events = events
    }
}