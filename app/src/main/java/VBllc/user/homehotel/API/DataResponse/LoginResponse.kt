package VBllc.user.homehotel.API.DataResponse

data class LoginResponse (
    val success: Boolean,
    val user_id: Int? = null,
    val name: String? = null,
    val token: String? = null,
    val errors: List<String>? = null
)