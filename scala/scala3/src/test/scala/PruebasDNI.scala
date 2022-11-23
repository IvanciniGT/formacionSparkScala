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
  
  test("Formatear el DNI 2300000T") {
    val un_dni=new DNI("2300000T")
    val formateado=un_dni.formatear(
      ceros = false, puntos = false, separador= "", letraMayusculas = true)
    assert( formateado == "2300000T", "El dni debe dar error" )
  }
  
  test("Formatear el DNI 2300000T") {
    val un_dni=new DNI("2300000T")
    val formateado=un_dni.formatear(
      ceros = true, puntos = false, separador= "", letraMayusculas = true)
    assert( formateado == "02300000T", "Formato incorrecto" )
  }
  
  test("Formatear el DNI 2300000T") {
    val un_dni=new DNI("2300000T")
    val formateado=un_dni.formatear(
      ceros = false, puntos = true, separador= "", letraMayusculas = true)
    assert( formateado == "2.300.000T", "Formato incorrecto" )
  }
  
  test("Formatear el DNI 2300000T") {
    val un_dni=new DNI("2300000T")
    val formateado=un_dni.formatear(
      ceros = false, puntos = false, separador= "-", letraMayusculas = true)
    assert( formateado == "2300000-T", "Formato incorrecto" )
  }
  test("Formatear el DNI 2300000T") {
    val un_dni=new DNI("2300000T")
    val formateado=un_dni.formatear(
      ceros = false, puntos = false, separador= "-", letraMayusculas = false)
    assert( formateado == "2300000-t", "Formato incorrecto" )
  }
  
  test("Formatear el DNI 23000T") {
    val un_dni=new DNI("23000T")
    val formateado=un_dni.formatear(
      ceros = false, puntos = true, separador= "-", letraMayusculas = false)
    assert( formateado == "23.000-t", "Formato incorrecto" )
  }
  
  test("Formatear el DNI 23000T") {
    val un_dni=new DNI("23000T")
    val formateado=un_dni.formatear(
      ceros = true, puntos = true, separador= "-", letraMayusculas = false)
    assert( formateado == "00.023.000-t", "Formato incorrecto" )
  }
  
}
