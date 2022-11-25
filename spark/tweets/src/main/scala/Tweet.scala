import scala.collection.mutable.ListBuffer

class Tweet (mensaje:String){
    
    private val lista_menciones: ListBuffer[String] = new ListBuffer()
    private val lista_hashtags: ListBuffer[String] = new ListBuffer()
    
    val patron_hashtags_menciones ="[#@][a-zA-Z0-9áéíóúÁÉÍÓÚüñç]+".r

    patron_hashtags_menciones.findAllIn(mensaje)
            .foreach(ente => {
                                if(ente.charAt(0)=='#'){
                                    lista_hashtags += ente.substring(1)
                                }else {
                                    lista_menciones += ente.substring(1)
                                }
                             })    
                             
    def menciones:List[String] = {
        return lista_menciones.toList
    }
    def hashtags:List[String] = {
        return lista_hashtags.toList
    }
    
}
