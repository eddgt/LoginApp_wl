
package ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="wsuser" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="wsclave" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="appuser" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="appclave" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "wsuser",
    "wsclave",
    "appuser",
    "appclave"
})
@XmlRootElement(name = "CambioClave")
public class CambioClave {

    protected String wsuser;
    protected String wsclave;
    protected String appuser;
    protected String appclave;

    /**
     * Gets the value of the wsuser property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWsuser() {
        return wsuser;
    }

    /**
     * Sets the value of the wsuser property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWsuser(String value) {
        this.wsuser = value;
    }

    /**
     * Gets the value of the wsclave property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWsclave() {
        return wsclave;
    }

    /**
     * Sets the value of the wsclave property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWsclave(String value) {
        this.wsclave = value;
    }

    /**
     * Gets the value of the appuser property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAppuser() {
        return appuser;
    }

    /**
     * Sets the value of the appuser property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAppuser(String value) {
        this.appuser = value;
    }

    /**
     * Gets the value of the appclave property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAppclave() {
        return appclave;
    }

    /**
     * Sets the value of the appclave property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAppclave(String value) {
        this.appclave = value;
    }

}
