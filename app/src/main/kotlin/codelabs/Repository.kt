package codelabs
//Extension de funcion de alto nivel
fun User.getFormmattedUserNames():String{
    return if(lastname != null){
        if (firstname != null){
            //Los stringtemplates nos permiten referenciar el valor de las variables dentro de las cadenas
            //esto meditan ${}
            "${firstname}  ${lastname}"
        }else{
            lastname?:"Unknown"
        }
    }else{
        //El operador elvis ? retorna el valor de la expresion a evaluar si es diferente de nulo
        //en caso contrario devuelve el valor del lado derecho
        firstname?:"Unknown"
    }
}

//Extension de propiedad de alto nivel
 val User.formattedUserNames:String get() {
    return if(lastname != null){
        if (firstname != null){
            //Los stringtemplates nos permiten referenciar el valor de las variables dentro de las cadenas
            //esto meditan ${}
            "${firstname}  ${lastname}"
        }else{
            lastname?:"Unknown"
        }
    }else{
        //El operador elvis ? retorna el valor de la expresion a evaluar si es diferente de nulo
        //en caso contrario devuelve el valor del lado derecho
        firstname?:"Unknown"
    }
}

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
    private val _users  = mutableListOf<User>()

    //protegemos la lista de usuarios de tal manera que esta no sea afectada desde donde se accede
    //Haciendo que siempre devuelva la lista creada en el objeto
    val users: List<User>
        get() = _users

    val formattedUserNames: List<String>
        get() {
            //Accedemos por medio de la extension de propiedad
            return _users.map { user: User -> user.formattedUserNames }
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
        _users.add(user1)
        _users.add(user2)
        _users.add(user3)
        //Accediendo por medio de extension de propiedad
        val name = user1.getFormmattedUserNames()
        //Accediendo por medio de extension de propiedad
        val formmattedName = user2.formattedUserNames
    }
}