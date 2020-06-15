package models ;

/* ajout des imports */
import io.ebean.*;
import javax.persistence.*;

import play.data.validation.Constraints.*;

// ajout du tag et héritage (extend Model)
@Entity
public class Person extends Model{

// clé pour la sérialisation    
 private static final long serialVersionUID = 1L; 
    
public String firstname ;
public int age ; 

    @Id
    public Long id ;

    
    public Person(String firstname, int age){
        this.firstname=firstname;
        this.age=age;
    }
    
    public Person(){   
    }
    
    
    public void setId(Long id){
        this.id = id;
    }
    
    public Long getId(){
        return this.id;
    }
    public String getFirstname(){
        return this.firstname;
    }
    
    public int getAge(){
        return this.age;
    }
    
    public void setAge(int a){
        this.age = a;
    }
    
    public void setFirstname(String f){
        this.firstname = f;
    }
    
     
     public static Finder<Long,Person> find = new Finder<Long,Person>(Person.class);
    
} //FIN DU CODE DE LA CLASSE Person