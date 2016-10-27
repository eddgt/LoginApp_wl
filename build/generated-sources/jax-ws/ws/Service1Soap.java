
package ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6-1b01 
 * Generated source version: 2.2
 * 
 */
@WebService(name = "Service1Soap", targetNamespace = "http://tigobusiness.tigo.com.gt/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface Service1Soap {


    /**
     * 
     * @return
     *     returns java.lang.String
     */
    @WebMethod(operationName = "HelloWorld", action = "http://tigobusiness.tigo.com.gt/HelloWorld")
    @WebResult(name = "HelloWorldResult", targetNamespace = "http://tigobusiness.tigo.com.gt/")
    @RequestWrapper(localName = "HelloWorld", targetNamespace = "http://tigobusiness.tigo.com.gt/", className = "ws.HelloWorld")
    @ResponseWrapper(localName = "HelloWorldResponse", targetNamespace = "http://tigobusiness.tigo.com.gt/", className = "ws.HelloWorldResponse")
    public String helloWorld();

    /**
     * 
     * @param appclave
     * @param wsclave
     * @param wsuser
     * @param appuser
     * @return
     *     returns ws.Respuesta
     */
    @WebMethod(operationName = "CambioClave", action = "http://tigobusiness.tigo.com.gt/CambioClave")
    @WebResult(name = "CambioClaveResult", targetNamespace = "http://tigobusiness.tigo.com.gt/")
    @RequestWrapper(localName = "CambioClave", targetNamespace = "http://tigobusiness.tigo.com.gt/", className = "ws.CambioClave")
    @ResponseWrapper(localName = "CambioClaveResponse", targetNamespace = "http://tigobusiness.tigo.com.gt/", className = "ws.CambioClaveResponse")
    public Respuesta cambioClave(
        @WebParam(name = "wsuser", targetNamespace = "http://tigobusiness.tigo.com.gt/")
        String wsuser,
        @WebParam(name = "wsclave", targetNamespace = "http://tigobusiness.tigo.com.gt/")
        String wsclave,
        @WebParam(name = "appuser", targetNamespace = "http://tigobusiness.tigo.com.gt/")
        String appuser,
        @WebParam(name = "appclave", targetNamespace = "http://tigobusiness.tigo.com.gt/")
        String appclave);

}