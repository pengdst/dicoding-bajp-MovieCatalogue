package io.github.pengdst.jetpacksubmission.data.source.remote.models.relations

import com.google.gson.annotations.SerializedName

data class NetworkDto(

	@field:SerializedName("logo_path")
	val logoPath: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("origin_country")
	val originCountry: String? = null
)