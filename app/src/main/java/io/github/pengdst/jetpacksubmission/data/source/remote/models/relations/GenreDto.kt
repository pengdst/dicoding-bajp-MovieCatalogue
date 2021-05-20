package io.github.pengdst.jetpacksubmission.data.source.remote.models.relations

import com.google.gson.annotations.SerializedName

data class GenreDto(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)