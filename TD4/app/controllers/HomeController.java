package controllers;

import play.mvc.*;

import models.*;

import javax.inject.Inject;
import play.i18n.MessagesApi;
import play.data.*;

import views.html.*;
import play.data.validation.Constraints.*;

import java.util.List;


public class HomeController extends Controller {

    @Inject 
    FormFactory formFactory; 
    Form <Person > personForm ;
    MessagesApi messagesApi;
    
    @Inject
    public HomeController(FormFactory formFactory, MessagesApi messagesApi) {
     this.personForm = formFactory.form(Person.class);
     this.messagesApi = messagesApi;
    }
    
  
    
   /* plus nécessaire, nous allons rediriger vers all l'acceuil du site 
   public Result index() {
        return ok(index.render());
    }
    */
    
     public Result helloworld(String fname) {
        return ok(helloworld.render(fname));
    }
    
    
      
    
    public Result sayhelloform (Http.Request request) {
        personForm = formFactory.form(Person.class) ;
        return ok(sayhelloform.render(personForm, request, messagesApi.preferred(request))) ; 
    }
    
    
        public Result sayhello() {
        return ok(views.html.sayhello.render());
    }
         
    
    
    // Redirect
    public Result helloworldform(Http.Request request) {
        
       final Form<Person> pForm = personForm.bindFromRequest(request) ;
        
               
        if (pForm.hasErrors()){
            return badRequest(sayhelloform.render(pForm,request, messagesApi.preferred(request)));
    }
        else{ //codeCreate : création d'une personned
            Person a = pForm.get();
            
            //enregistre la personne dans la database
            a.save();
        return redirect(routes.HomeController.all()) ;
        }
    }

    
    
     public Result all(){
         
         List<Person> liste = Person.find.all();
         return ok(all.render(liste));
     }     
    
    
    
    /**    
    * Montre une personne specifique dans la database 
     */
    
   public Result show(Long id) {
        
        Person p = Person.find.byId(id) ;
        return ok(show.render(p)) ;
    }
   
    
    
    
    public Result update(Long id,  Http.Request request) {    /** codeUpdateAction **/
        
        // trouver une personne
        
        Person p = Person.find.byId(id) ;
        // remplir un formulaire avec la personne p
        Form<Person> pForm = formFactory.form(Person.class) ;
        pForm = pForm.fill(p);
        
        // Afficher CETTE personne p
        return ok(update.render(pForm, id, request, messagesApi.preferred(request)));
    }
   
        
     /**
     * Supprimer une personne spécifique basée sur l'ID
     */
    public Result delete(Long id) {
        Person p = Person.find.byId(id) ;
        p.delete();
        return redirect(routes.HomeController.delete(id)) ;
    }
    
     public Result updateform(Long id, Http.Request request){
         Form<Person> pform = personForm.bindFromRequest(request);
        if (pform.hasErrors()){
            return badRequest(update.render(pform, id, request, messagesApi.preferred(request)));
        } else {
        Person p = pform.get();
            p.setId(id);
            p.update();
        // return redirect(routes.HomeController.delete());
           return redirect(routes.HomeController.all()) ; 
        }
    }
    
} // FIN DU CODE