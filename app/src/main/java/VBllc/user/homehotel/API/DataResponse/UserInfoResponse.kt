package VBllc.user.homehotel.API.DataResponse

data class UserInfoResponse(
        val result: Boolean,
        val user: UserInfoData? = null,
        val errors: Map<String, List<String> >? = null
){
    data class UserInfoData(
            val id: Int,
            val name: String,
            val email: String,
            val phone: String,
            val created_at: String,
            val updated_at: String
    ) {

        fun isEmpty(): Boolean {
            return (id <= 0 &&
                    name.isEmpty() &&
                    email.isEmpty() &&
                    phone.isEmpty() &&
                    created_at.isEmpty() &&
                    updated_at.isEmpty())
        }
    }
}