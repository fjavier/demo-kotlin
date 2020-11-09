package codelabs

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
    private val _users  = mutableListOf<User>(User("Jane", ""),User("John", null),User("Anne", "Doe"))

    //protegemos la lista de usuarios de tal manera que esta no sea afectada desde donde se accede
    //Haciendo que siempre devuelva la lista creada en el objeto
    val users: List<User>
        get() = _users

    val formattedUserNames: List<String>
        get() {
            //Accedemos por medio de la extension de propiedad
            return _users.map { user: User -> user.formattedUserNames }
        }
}