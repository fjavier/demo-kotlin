package codelabs

/**
 * Ya que solo necesitamos una sola instancia del objeto Repository esto lo hacemos mediante el patron
 * singleton, en Java forzamos obtener la instancia por medio de un metodo estatico.
 * En el caso de kotlin solo necesitamos declarar la clase como object
 * 
 */
object Repository {
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

    /**
     * <code>companion object </object> es el homologo al keyword static en Java
     * De esta manera creamos un metodo estatico, en este caso estamos obteniendo la instancia de
     * la clase Repository

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
     */
    //El init es el inicializador
    init {
        val user1 = User("Jane", "")
        val user2 = User("John", null)
        val user3 = User("Anne", "Doe")
        users = ArrayList()
        users?.add(user1)
    }
}