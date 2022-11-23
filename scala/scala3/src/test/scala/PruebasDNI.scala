import dni.DNI

class PruebasDNI extends munit.FunSuite {
  
  test("Validar el DNI 23000000T") {
    val( numero, letra, error):(Int,Char, String) = DNI.validarDNI("23000000T")
    assert( numero == 23000000, "En número devuelto debe ser 23000000" ) 
    assert( letra == 'T', "La letra devuelta debe ser la T" )
    assert( error == null, "No debe dar errores" )
  }
    
  test("Validar el DNI 23.000.000T") {
    val( numero, letra, error):(Int,Char, String) = DNI.validarDNI("23.000.000T")
    assert( numero == 23000000, "En número devuelto debe ser 23000000" ) 
    assert( letra == 'T', "La letra devuelta debe ser la T" )
    assert( error == null, "No debe dar errores" )
  }
  
  test("Validar el DNI 23.000.000-t") {
    val( numero, letra, error):(Int,Char, String) = DNI.validarDNI("23.000.000-t")
    assert( numero == 23000000, "En número devuelto debe ser 23000000" ) 
    assert( letra == 'T', "La letra devuelta debe ser la T" )
    assert( error == null, "No debe dar errores" )
  }
  
  // DNIS invalidos
  test("Validar el DNI 23.000.000KT") {
    val( numero, letra, error):(Int,Char, String) = DNI.validarDNI("23.000.000KT")
    assert( error != null, "El dni debe dar error" )
  }
  test("Validar el DNI 23A00000T") {
    val( numero, letra, error):(Int,Char, String) = DNI.validarDNI("23A00000T")
    assert( error != null, "El dni debe dar error" )
  }
  test("Validar el DNI 23.000..000-t") {
    val( numero, letra, error):(Int,Char, String) = DNI.validarDNI("23.000..000-t")
    assert( error != null, "El dni debe dar error" )
  }
  test("Validar el DNI 23..000.000-t") {
    val( numero, letra, error):(Int,Char, String) = DNI.validarDNI("23..000.000-t")
    assert( error != null, "El dni debe dar error" )
  }
  test("Validar el DNI 2300.0.000-t") {
    val( numero, letra, error):(Int,Char, String) = DNI.validarDNI("2300.0.000-t")
    assert( error != null, "El dni debe dar error" )
  }
  
}
