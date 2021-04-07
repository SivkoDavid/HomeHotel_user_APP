package VBllc.user.homehotel.DataLayer.Repositories

interface ProfileRepositoryListener:BaseRepositoryListener {
    fun onLogout(status:Boolean)
}