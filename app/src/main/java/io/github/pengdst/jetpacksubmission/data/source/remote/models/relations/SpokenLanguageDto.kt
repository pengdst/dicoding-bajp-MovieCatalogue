package io.github.pengdst.jetpacksubmission.data.source.remote.models.relations

import com.google.gson.annotations.SerializedName

data class SpokenLanguageDto(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("iso_639_1")
	val iso6391: String? = null,

	@field:SerializedName("english_name")
	val englishName: String? = null
)