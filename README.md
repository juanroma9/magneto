# magneto
 Proyecto en spring boot y mysql con el fin de detectar si una secuencia de ADN es de humano o es mutante
 
 ## Instrucciones de uso
 1. Descargar una herramienta de cliente para ejecutar Apis, como postman.
    https://www.postman.com/downloads/
 2. Para validar una cadena de ADN se debe realizar un llamado POST a este endpoint
    http://mutantapp-env.eba-gdqbpqve.us-west-2.elasticbeanstalk.com/mutant
    pasando el body del json trama como esta:
    {
     "dna":["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]
    }
 3. Para conocer las estadisticas entre mutantes y humanos se debe realizar un llamado GET a este endpoint
    http://mutantapp-env.eba-gdqbpqve.us-west-2.elasticbeanstalk.com/stats
 4. Se adiciona un método más con el fin de conocer los registros almacenados para las peticiones sobre la validación de cadenas de ADN 
    http://mutantapp-env.eba-gdqbpqve.us-west-2.elasticbeanstalk.com/all
