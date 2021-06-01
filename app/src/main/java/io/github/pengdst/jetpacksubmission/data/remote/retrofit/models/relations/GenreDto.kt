package io.github.pengdst.jetpacksubmission.data.remote.retrofit.models.relations

import com.google.gson.annotations.SerializedName

data class GenreDto(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)