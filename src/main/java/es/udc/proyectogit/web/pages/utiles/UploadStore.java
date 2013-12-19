/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.web.pages.utiles;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.web.pages.prueba.PerfilPrueba;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.tapestry5.Link;
import org.apache.tapestry5.StreamResponse;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.PageRenderLinkSource;
import org.apache.tapestry5.services.Response;

/*----------------------------------------------------------------------------*/


public class UploadStore {


    /*------------------------------Atributos---------------------------------*/
    
    @Inject 
    private PageRenderLinkSource pageRenderLinkSource; 
    
    /*------------------------------------------------------------------------*/


    /*----------------------------Constructores-------------------------------*/

    /*------------------------------------------------------------------------*/


    /*-----------------------------Gets y Sets--------------------------------*/
    
    public Link getUploadedFile(String uuid) {
        return pageRenderLinkSource.createPageRenderLinkWithContext(UploadStore.class, uuid); 
    }//fin getUploadedFile(String uuid)

    /*------------------------------------------------------------------------*/


    /*-----------------------------Auxiliares---------------------------------*/

    /*------------------------------------------------------------------------*/


    /*-------------------------------Metodos----------------------------------*/
    
    public StreamResponse onActivate(final String uuid) {
        return new StreamResponse() {
            InputStream inputStream;

            @Override
            public void prepareResponse(Response response) {
                File file = new File(uuid);
                try {
                    inputStream = new FileInputStream(file);  
                    // http://tapestry-users.832.n2.nabble.com/Disable-Transfer-Encoding-chunked-from-StreamResponse-td5269662.html#a5269662
                    // http://tapestry-users.832.n2.nabble.com/Disable-Transfer-Encoding-chunked-from-StreamResponse-td5269662.html#a5269662
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(PerfilPrueba.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    response.setHeader("Content-Length", "" + inputStream.available());
                }
                catch (IOException e) {
                    // Ignore the exception in this simple example.
                }
            }//fin prepareResponse(Response response)
            
            
            @Override
            public String getContentType() {
                return "image/png";
            }//fin getContentType()
            
            
            @Override
            public InputStream getStream() throws IOException {
                return inputStream;
            }//fin getStream()
        };
    }//fin onActivate(final String uuid)

    /*------------------------------------------------------------------------*/


}//fin Clase UploadStore