package io.github.adefulki.spaceflightnews.data.local.entity

import io.github.adefulki.spaceflightnews.domain.model.Author
import io.github.adefulki.spaceflightnews.domain.model.Event
import io.github.adefulki.spaceflightnews.domain.model.Launch
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class RecentSearchEntity() : RealmObject {

    @PrimaryKey
    var id: Int? = null
    var keyword: String? = null

    constructor(
        keyword: String? = null,
    ) : this() {
        this.keyword = keyword
    }
}