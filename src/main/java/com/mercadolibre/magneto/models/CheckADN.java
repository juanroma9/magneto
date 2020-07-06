package com.mercadolibre.magneto.models;

public class CheckADN {

    static final int    NUMBER_OF_OCCURENCES    = 4;
    static final String STRING_EMPTY            = "";

    private int         count;
    private int         rowSize;
    private int         colSize;
    private boolean     found;
    private char[][]    matrix;
    private String      strAdn;

    public PeopleStatistic peopleStatistic;

    public CheckADN(){
        peopleStatistic = new PeopleStatistic();
    }
    
    public boolean isMutant(String[] dna){
    
        initMatrix(dna);

        if(searchByRows())
            return true;
        else if (searchByCols())
            return true;
        else if(searchByDiagonal())
            return true;
        
        return false;
    }

    /// <summary>
    /// Inicializa la matriz de acuerdo al array que ingresa como parámetro 
    /// </summary>
    /// <param title="dna">Arreglo con las cadenas de ADN</param>
    /// <returns></returns>
    private void initMatrix(String[] dna){

        rowSize = dna.length; 
        colSize = dna[0].toCharArray().length;

        matrix = new char[rowSize][colSize];          

        int row = 0; 
        int col = 0;

        for (String dnaItem : dna) {
            for(char item : dnaItem.toCharArray()){
                matrix[row][col] = item;
                col++;
            }
            row++;
            col = 0;
        }
    }

    /// <summary>
    /// Cuenta las repeticiones secuenciales de un carácter en una cadena de texto
    /// </summary>
    /// <param title="str">Cadena de texto donde se va a buscar el carácter</param>
    /// <param title="charToSearch">Carácter a buscar</param>
    /// <returns></returns>
    private void charCount(String str, char charToSearch){
        
        this.count  = 0;
        int posTemp = 0;
        int pos     = str.indexOf(charToSearch);

        if(pos != -1){
            this.count++;
            posTemp = pos;
        }            

        while(pos != -1){

            pos = str.indexOf(charToSearch,pos +1); 
                
            if(pos != -1 && pos - posTemp == 1){
                this.count++;
                posTemp = pos;
            }
            else
                pos = -1; 
        }  
    }

    /// <summary>
    /// Realiza la búsqueda en la matriz por filas, para esto recorre todas las columnas de la fila para armar la cadena en donde se va a buscar el carácter.
    /// </summary>
    /// <returns></returns>
    private boolean searchByRows(){
        
        outerloop:

        for(int row = 0; row <= this.rowSize -1; row++){

            strAdn = STRING_EMPTY;

            //Armar la cadena de texto en donde se va a buscar
            for(int col = 0; col <= this.colSize - 1; col++)
                strAdn += matrix[row][col];

            //Recorre las columnas para tomar cada caracter que se va a buscar en la cadena de texto
            for(int col = 0; col <= this.colSize - 1; col++){

                charCount(strAdn, matrix[row][col]);
                
                if(this.count >= NUMBER_OF_OCCURENCES ){
                    setMutant(strAdn);
                    break outerloop;
                }
            }
        }

        return this.found;
    }

    /// <summary>
    /// Realiza la búsqueda en la matriz por columnas, para esto recorre todas las filas de la columna para armar la cadena en donde se va a buscar el carácter.
    /// </summary>
    /// <returns></returns>
    private boolean searchByCols(){

        outerloop:

        for(int col = 0; col <= this.colSize -1; col++){
            count = 0;
            strAdn = STRING_EMPTY;

            //Armar la cadena de texto en donde se va a buscar
            for(int row = 0; row <= this.rowSize -1; row++)
                strAdn += matrix[row][col];
            
            //Recorre las filas para tomar cada caracter que se va a buscar en la cadena de texto
            for(int row = 0; row <= this.rowSize -1; row++){
                
                charCount(strAdn, matrix[row][col]);

                if(this.count >= NUMBER_OF_OCCURENCES ){
                    setMutant(strAdn);
                    break outerloop;
                }
            }
        }

        return this.found;
    }

    /// <summary>
    /// Realiza la búsqueda en la matriz por diagonales, para esto recorre todas las columnas descontanto la longitud de cadena del ADN
    /// </summary>
    /// <returns></returns>
    private boolean searchByDiagonal(){

        int row = 0;
        int col = 0;
        outerloop:

        for(int i = 0; i <= this.colSize - NUMBER_OF_OCCURENCES; i ++){
            
            strAdn = STRING_EMPTY;

             //Armar la cadena de texto en donde se va a buscar
            while(col <= this.colSize - 1){
                strAdn += matrix[row][col];
                row++;
                col++;
            }

            //Recorre la diagonal para hacer la busqueda del caracter
            for(char item : strAdn.toCharArray()){
                charCount(strAdn, item);

                if(this.count >= NUMBER_OF_OCCURENCES ){
                    setMutant(strAdn);
                    break outerloop;
                }
            }
            row = 0;
            col = i + 1;
        }

        return this.found;
    }

    private void setMutant(String strAdn){        
        peopleStatistic.setAdnMutant(strAdn);
        peopleStatistic.setMutant(true);
        this.found = true;
    }
}