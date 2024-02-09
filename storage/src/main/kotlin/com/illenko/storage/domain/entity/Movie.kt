package com.illenko.storage.domain.entity

import com.illenko.storage.domain.SourcedBy
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.util.*

@Table("movie")
data class Movie(
    @Id var id: UUID? = null,
    var title: String = "",
    var year: Int = 0,
    var genre: String = "",
    var description: String = "",
    var country: String = "",
    @Column("sourced_by") var sourcedBy: SourcedBy = SourcedBy.USER
)