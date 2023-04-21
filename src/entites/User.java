/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

/**
 *
 * @author Souid
 */
public class User {
    int id ;
    String email;
    String roles ;
    String Password ;
    String username ; 
    Boolean blocked ;

    public Boolean getBlocked() {
        return blocked;
    }

    public void setBlocked(Boolean blocked) {
        this.blocked = blocked;
    }

  
    

    public User(int id ,String email ,String roles,String Password,String username)
        {
            this.id=id ;
            this.email=email ;
            this.roles=roles ;
            this.Password =Password ;
            
        }

    public User(String email, String Password, String username) {
        this.email = email;
        this.Password = Password;
        this.username = username;
    }
    
 
    public User(int id,String email, String roles, String username) {
        this.id=id;
        this.email = email;
        this.roles = roles;
        this.username = username;
    }


    public User() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
}
