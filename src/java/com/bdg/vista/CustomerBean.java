package com.bdg.vista;

/**
 *
 * @author oulloa
 */
import com.bdg.dao.UserDAO;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
 
@ManagedBean(name = "customer")
@SessionScoped
public class CustomerBean {
    private String lastname, firstname, email, address;

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
// insert getter setter here
public ArrayList<CustomerBean> getMessages() {
        return UserDAO.getCustomer();
    }
}