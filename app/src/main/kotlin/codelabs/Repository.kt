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

    val formattedUserNames: List<String?>
        get() {
            //El !! convierte cualquier variable a tipo non-null
            val userNames = ArrayList<String>(users.size)
            //Al hacer la lista inmutable y no permitir que esta sea nula, entonces podemos eliminar el !!
            //Destructuracion
            for ((firstname, lastname) in users){
                val name:String

                name = if(lastname != null){
                    if (firstname != null){
                        //Los stringtemplates nos permiten referenciar el valor de las variables dentro de las cadenas
                        //esto meditan ${}
                        "${firstname}  ${lastname}"
                    }else{
                        lastname
                    }
                }else{
                    //El operador elvis ? retorna el valor de la expresion a evaluar si es diferente de nulo
                    //en caso contrario devuelve el valor del lado derecho
                   firstname?:"Unknown"
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
        users.add(user1)
    }
}