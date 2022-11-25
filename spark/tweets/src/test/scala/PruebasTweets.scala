
class PruebasTweets extends munit.FunSuite {
  
  test("Probar menciones") {
    val mensaje = "@LuisEnrique se parte el culo de los que le estaba criticando #España70#MundialRuinoso#PutosAmos"
    val nuevoTweet = new Tweet(mensaje)
    
    assert( nuevoTweet.menciones.length == 1, "Debe encontrarse una única mención!" ) // ASERCIÓN:   REGLA QUE ASEGURE ALGO
    assert( nuevoTweet.menciones(0) == "LuisEnrique", "Debe ser LuisEnrique!" ) // ASERCIÓN:   REGLA QUE ASEGURE ALGO
  }
  
  test("Probar hashtags") {
    val mensaje = "@LuisEnrique se parte el culo de los que le estaba criticando #España70#MundialRuinoso#PutosAmos"
    val nuevoTweet = new Tweet(mensaje)
    
    assert( nuevoTweet.hashtags.length == 3, "Debe encontrarse una única mención!" ) // ASERCIÓN:   REGLA QUE ASEGURE ALGO
    assert( nuevoTweet.hashtags(0) == "España70", "Debe ser España70!" ) // ASERCIÓN:   REGLA QUE ASEGURE ALGO
    assert( nuevoTweet.hashtags(1) == "MundialRuinoso", "Debe ser MundialRuinoso!" ) // ASERCIÓN:   REGLA QUE ASEGURE ALGO
    assert( nuevoTweet.hashtags(2) == "PutosAmos", "Debe ser PutosAmos!" ) // ASERCIÓN:   REGLA QUE ASEGURE ALGO
  }
  
}
