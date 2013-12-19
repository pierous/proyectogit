/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.modelo.utiles.excepciones;


public class FormatoInvalidoExcepcion extends Exception {
    
    private Class clase;

    
    /*----------------------------Constructores-------------------------------*/
    
    public FormatoInvalidoExcepcion(Class clase) {
        super("(Formato Invalido): El formato introducido no es correcto.");
        System.out.println(this.clase + " == " + clase);
        this.clase = clase;
    }//fin FormatoInvalidoExcepcion()
    
    
    public FormatoInvalidoExcepcion(Class clase, String elemento, String pauta) {
        super("(Formato Invalido): El " + elemento + " debe tener " + pauta + ".");
        this.clase = clase;
    }

    /*------------------------------------------------------------------------*/


    /*-----------------------------Gets y Sets--------------------------------*/
    
    public Class getClase() {
        return clase;
    }//fin getClase()
    
    /*------------------------------------------------------------------------*/


}//fin Clase FormatoInvalidoExcepcion