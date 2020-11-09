package codelabs


class Repository private constructor(){
    private var users : MutableList<User>? = null

    fun getUsers() : List<User>?{
        return users
    }

    val formattedUserNames: List<String?>
        get() {
            val userNames:MutableList<String?> = ArrayList(users!!.size)
            for (user in users!!){
                var name:String

                name = if(user!!.lastname != null){
                    if (user!!.firstname != null){
                        user.firstname + " " + user.lastname
                    }else{
                        user!!.lastname + ""
                    }
                }else if(user!!.firstname != null){
                    user!!.firstname + ""
                }else{
                    "Unknown"
                }
                userNames.add(name)
            }
            return userNames
        }

    companion object{
        private var INSTANCE : Repository? = null
        val instance : Repository?
            get() {
                if (INSTANCE == null){
                    synchronized(Repository::class.java){
                        if(INSTANCE == null){
                            INSTANCE= Repository()
                        }
                    }
                }
                return INSTANCE;
            }
    }

    init {
        val user1 = User("Jane", "")
        val user2 = User("John", null)
        val user3 = User("Anne", "Doe")
        users = ArrayList<User>()
        users?.add(user1)
    }
}