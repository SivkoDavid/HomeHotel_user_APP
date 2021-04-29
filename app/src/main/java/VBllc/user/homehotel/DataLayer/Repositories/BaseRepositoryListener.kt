package VBllc.user.homehotel.DataLayer.Repositories

interface BaseRepositoryListener {

    fun startRequest(name: String, code: Int = 0)

    fun noInternet(code: Int? = null)

    fun onErrors(errorMessages: List<String> = listOf(), errorCode: Int, code: Int = 0)
}