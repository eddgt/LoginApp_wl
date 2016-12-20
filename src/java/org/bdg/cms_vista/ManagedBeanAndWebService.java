/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bdg.cms_vista;

/**
 *
 * @author oulloa
 */
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.jws.WebMethod;
import javax.jws.WebService;

@ManagedBean
@WebService
@ViewScoped
public class ManagedBeanAndWebService implements Serializable{

    private static final long   serialVersionUID    = 4479173603147480764L;
    private String someAttribute="xyz";

    public String getSomeAttribute() {
        return someAttribute;
    }

    public void setSomeAttribute(String someAttribute) {
        this.someAttribute = someAttribute;
    }

    @WebMethod(operationName="methodName")
    public void someWebMethod(String s){
        System.out.println(s);
    }
}