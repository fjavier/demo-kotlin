package codelabs

/**
 * Ya que solo necesitamos una sola instancia del objeto Repository esto lo hacemos mediante el patron
 * singleton, en Java forzamos obtener la instancia por medio de un metodo estatico.
 * En el caso de kotlin solo necesitamos declarar la clase como object
 *
 */
object Repository {
    //Inicializamos la lista de usuarios por medio de la funcion mutableListOf
    //y dejamos que el tipo sea inferido
    //Hacemos la lista inmutable
    private val users  = mutableListOf<User>()

    fun getUsers() : List<User>?{
        return users
    }

    val formattedUserNames: List<String>
        get() {
            return users.map { user ->
                if(user.lastname != null){
                    if (user.firstname != null){
                        //Los stringtemplates nos permiten referenciar el valor de las variables dentro de las cadenas
                        //esto meditan ${}
                        "${user.firstname}  ${user.lastname}"
                    }else{
                        user.lastname?:"Unknown"
                    }
                }else{
                    //El operador elvis ? retorna el valor de la expresion a evaluar si es diferente de nulo
                    //en caso contrario devuelve el valor del lado derecho
                    user.firstname?:"Unknown"
                }

            }
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
        users.add(user1)
    }
}