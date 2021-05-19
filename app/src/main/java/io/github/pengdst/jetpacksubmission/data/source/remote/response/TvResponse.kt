package io.github.pengdst.jetpacksubmission.data.source.remote.response

import com.google.gson.annotations.SerializedName
import io.github.pengdst.jetpacksubmission.data.source.remote.models.TvDto

data class TvResponse(

    @field:SerializedName("page")
	val page: Int? = null,

    @field:SerializedName("total_pages")
	val totalPages: Int? = null,

    @field:SerializedName("results")
	val results: List<TvDto>? = null,

    @field:SerializedName("total_results")
	val totalResults: Int? = null
)