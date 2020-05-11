package controllers;

import play.mvc.*;

import play.data.* ;
import javax.inject.Inject ;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */

public class HomeController extends Controller {

      
    public Result index() {
        return ok(views.html.index.render(" et moi le controleur."));
    }


     public Result helloworld(String fname) {
        return ok(views.html.helloworld.render(fname));
    }
    
    
 /** Dit "Hello"+ prénom **/
    public Result formulaire() {
        return ok(views.html.formulaire.render()) ;
    }
   

    
    public Result inscription(){
        return ok(views.html.inscription.render());
    }
    
    
     public Result resume( String nom , String age , String adresse , String codePostal , String ville){
        return ok(views.html.resume.render(nom,age,adresse, codePostal,ville));
    }
    

}
