/** @author Ulloa Sobrino, David */


package es.udc.proyectogit.web.mixins;


/*----------------------------------Imports-----------------------------------*/

import es.udc.proyectogit.modelo.utiles.entidad.Entidad;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.Link;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.InjectContainer;
import org.apache.tapestry5.annotations.MixinAfter;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.corelib.components.Grid;
import org.apache.tapestry5.dom.Element;
import org.apache.tapestry5.dom.Node;
import org.apache.tapestry5.grid.GridDataSource;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;

/*----------------------------------------------------------------------------*/


@MixinAfter
public class RowEvent {


    /*------------------------------Atributos---------------------------------*/
    
    @InjectContainer
    private Grid grid;
    @Inject
    private JavaScriptSupport jss;
    @Inject
    private ComponentResources componentResrouces;
    @Parameter(required=true, defaultPrefix=BindingConstants.LITERAL)
    private String event;
    
    /*------------------------------------------------------------------------*/


    /*-------------------------------Metodos----------------------------------*/
    
    public void afterRender(MarkupWriter writer) {
        GridDataSource dataSource = grid.getDataSource();
        List<Node> topChildren =  writer.getElement().getChildren();
        if (topChildren.get(topChildren.size() - 1).getClass() == Element.class) {
            Element div = (Element) topChildren.get(topChildren.size() - 1);
            int pagina = 1;
            if (div.getElementByAttributeValue("class", "current") != null)
                pagina = Integer.parseInt(div.getElementByAttributeValue("class", "current").getChildMarkup());
            Element tbody = div.find("table/tbody");
            List<Node> children = tbody.getChildren();
            for (int i = 0; i < children.size(); ++i) {
                Element tr = (Element) children.get(i);
                String rowId = jss.allocateClientId("row");
                
                // give each row an id
                tr.attribute("id", rowId);
            
                // this will be passed as a parameter to the serverside event
                int j = i + (20*(pagina-1));
                Object rowContext = dataSource.getRowValue(j);
                Entidad entidad = (Entidad) rowContext;
                Long clave = entidad.getClave();
            
                // this event will bubble up to the containing page / component
                Link eventLink = componentResrouces.createEventLink(event, clave);
            
                // observe the 'click' event and fire the eventlink
                jss.addScript("Event.observe('%s', 'click', function() { window.location.href = '%s' })", rowId, eventLink);
            }//fin for (int i = 0; i < children.size(); ++i)
        }//fin if (topChildren.size() == 6)
    }//fin afterRender(MarkupWriter writer)

    /*------------------------------------------------------------------------*/


}//fin Clase RowEvent