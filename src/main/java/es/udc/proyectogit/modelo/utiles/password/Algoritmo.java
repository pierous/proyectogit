/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.modelo.utiles.password;


/*----------------------------------Imports-----------------------------------*/

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;

/*----------------------------------------------------------------------------*/


public class Algoritmo {


    /*------------------------------Atributos---------------------------------*/
    
    private static byte[] linebreak = {};
    private static String s= "SunJCAES/ECB3";
    private static SecretKey key;
    private static Cipher cipher;
    private static Base64 coder;
    
    /*------------------------------------------------------------------------*/


    /*-----------------------------Auxiliares---------------------------------*/
    
    static {
        try {
            s+="CS5";
            key = new SecretKeySpec(s.getBytes(), "AES" );
            cipher = Cipher.getInstance("AES/ECB/PKCS5Padding", "SunJCE" );
            coder = new Base64(32,linebreak,true);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    /*------------------------------------------------------------------------*/


    /*-------------------------------Metodos----------------------------------*/
    
    public static synchronized String encriptar(String plainText) throws Exception {
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] cipherText = cipher.doFinal(plainText.getBytes());
        return new String(coder.encode(cipherText));
    }//fin encriptar(String plainText)
    
    
    public static synchronized String desencriptar(String codedText) throws Exception {
        byte[] encypted = coder.decode(codedText.getBytes());
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decrypted = cipher.doFinal(encypted);
        return new String(decrypted);
    }//fin desencriptar(String codedText)

    /*------------------------------------------------------------------------*/


}//fin Clase Algoritmo