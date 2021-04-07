package VBllc.user.homehotel.API.DataResponse

data class UserInfoResponse(
        val success: Boolean,
        val user: UserInfoData? = null,
        val errors: Map<String, List<String> >? = null
){
    data class UserInfoData(
            val id: Int,
            val name: String?,
            val second_name: String?,
            val third_name: String?,
            val email: String?,
            val avatar: String?,
            val created_at: String?,
            val updated_at: String?

    ) {

        fun isEmpty(): Boolean {
            return (id <= 0 &&
                    name?.isEmpty()?:true &&
                    email?.isEmpty()?:true &&
                    created_at?.isEmpty()?:true &&
                    updated_at?.isEmpty()?:true)
        }
    }
}