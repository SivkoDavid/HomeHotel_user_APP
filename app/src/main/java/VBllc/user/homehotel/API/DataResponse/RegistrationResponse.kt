package VBllc.user.homehotel.API.DataResponse


data class RegistrationResponse(
    val success: Boolean,
    val token: String?,
    val errors: Map<String, List<String>>? = null
)