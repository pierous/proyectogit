/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.web.services;


/*----------------------------------Imports-----------------------------------*/

import java.util.HashMap;
import java.util.Map;

/*----------------------------------------------------------------------------*/


public class SupportedLanguages {


    /*------------------------------Atributos---------------------------------*/
    
    private final Map<String, String> options;
    private String codes = "";
    
    /*------------------------------------------------------------------------*/


    /*----------------------------Constructores-------------------------------*/
    
    public SupportedLanguages() {
        String options_en = "en=English, es=Spanish, fr=French, gl=Galician";
        String options_es = "es=Español, gl=Gallego, en=Inglés, fr=Francés";
        String options_fr = "fr=Français, en=Anglais, es=Espagnol, gl=Galicien";
        String options_gl = "gl=Galego, es=Español, en=Inglés, fr=Francés";
        
        options = new HashMap<String, String>();
        options.put("en", options_en);
        options.put("es", options_es);
        options.put("fr", options_fr);
        options.put("gl", options_gl);
        
        codes = "en,es,fr,gl";
    }//fin SupportedLanguages()

    /*------------------------------------------------------------------------*/


    /*-----------------------------Gets y Sets--------------------------------*/
    
    public String getCodes() {
        return codes;
    }//fin getCodes()
    
    
    public String getOptions(String languageCode) {
        String languages = options.get(languageCode);
        
        if (languages != null) {
            return languages;
        } else {
            return options.get("en");
        }//fin if (languages != null)
    }//fin getOptions(String languageCode)

    /*------------------------------------------------------------------------*/


}//fin Clase SupportedLanguages