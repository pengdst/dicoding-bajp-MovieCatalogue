package io.github.pengdst.jetpacksubmission.data.remote.retrofit.models.relations

import com.google.gson.annotations.SerializedName

data class DateDto(

	@field:SerializedName("maximum")
	val maximum: String? = null,

	@field:SerializedName("minimum")
	val minimum: String? = null
)