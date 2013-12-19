/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.modelo.utiles.meses;


public enum Meses {
    
    
    ENERO(0), FEBRERO(1), MARZO(2), ABRIL(3), MAYO(4), JUNIO(5), JULIO(6), AGOSTO(7), SEPTIEMBRE(8), OCTUBRE(9), NOVIEMBRE(10), DICIEMBRE(11);
    
    
    /*------------------------------Atributos---------------------------------*/
    
    private final int num;  //Numero del mes dentro del anho.
    
    /*------------------------------------------------------------------------*/
    
    
    /*----------------------------Constructores-------------------------------*/
    
    Meses(int i) {
        this.num = i;
    }//fin Meses(int i)
    
    /*------------------------------------------------------------------------*/
    
    
    /*-----------------------------Gets y Sets--------------------------------*/
    
    public int getNum() {
        return num;
    }//fin getNum()
    
    /*------------------------------------------------------------------------*/
    
    
}//fin Clase Meses