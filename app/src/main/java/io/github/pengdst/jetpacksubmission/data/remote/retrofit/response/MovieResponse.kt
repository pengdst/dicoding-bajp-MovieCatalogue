package io.github.pengdst.jetpacksubmission.data.remote.retrofit.response

import com.google.gson.annotations.SerializedName
import io.github.pengdst.jetpacksubmission.data.remote.retrofit.models.relations.DateDto
import io.github.pengdst.jetpacksubmission.data.remote.retrofit.models.MovieDto

data class MovieResponse(

	@field:SerializedName("dates")
	val date: DateDto? = null,

	@field:SerializedName("page")
	val page: Int? = null,

	@field:SerializedName("total_pages")
	val totalPages: Int? = null,

	@field:SerializedName("results")
	val results: List<MovieDto>? = null,

	@field:SerializedName("total_results")
	val totalResults: Int? = null
)