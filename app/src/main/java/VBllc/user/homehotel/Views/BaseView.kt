package VBllc.user.homehotel.Views

interface BaseView {
    fun showError(errorMessage: String, errorCode: Int)

    fun showLoading()

    fun showNoNetwork()
}