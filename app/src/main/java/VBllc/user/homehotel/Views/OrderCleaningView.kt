package VBllc.user.homehotel.Views

interface OrderCleaningView:BaseView {
    fun backToMenu()
    fun showLoadingDialog(message: String)
    fun showOkDialog(message: String)
    fun showErrorDialog(message: String)
}