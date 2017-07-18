package org.bdg.service;

import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;
import java.net.URISyntaxException;
import javax.ejb.Stateless;
import javax.ws.rs.*; //Importamos la librería para manejar RESTful
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.bdg.cms_vista.LoginBean;

@Stateless
@Path("rest") //Especificamos una ruta que se debe usar para invocar este método y un parámetro (tipo)
public class Rest  implements Serializable{

    private LoginBean loginBean;

    /*@Context     
    private UriInfo context;*/

    /*@GET	
     public Response userVerification(@Context Response response) throws URISyntaxException{
       
     java.net.URI location = new java.net.URI("localhost:8081/CMS_BUC/");
    

     return Response.temporaryRedirect(location).build();

     }*/
    @POST
    @Path("loginapi")
    @Consumes("application/x-www-form-urlencoded")        
    //public Response login(@Context HttpServletRequest request, @Context UriInfo uriInfo) {
    public Response login(@Context HttpServletRequest request, @Context UriInfo uriInfo, @FormParam("user") String user, @FormParam("pass") String pass,
            @FormParam("time") String time, @FormParam("hash") String hash) {
        try {
            java.net.URI location = new java.net.URI(uriInfo.getRequestUri().getScheme() + "://"
                    + uriInfo.getRequestUri().getHost()
                    + ":"
                    + uriInfo.getRequestUri().getPort()
                    + "/CMS_BUC/");
            return Response.temporaryRedirect(location).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(200)
                    .entity("Ocurrio una Exception en Login: " + e.getMessage())
                    .build();
        }
    }

}
