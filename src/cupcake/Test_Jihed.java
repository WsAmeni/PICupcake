/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cupcake;

import Entities.Client;
import Entities.Patisserie;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author jihed
 */
public class Test_Jihed extends Application {

    public static Client client;
    public static Patisserie patisserie;

    public void start(Stage primaryStage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("/GUI/FXMLtest.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("/GUI/suggestionliste.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("/GUI/feedback.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("/GUI/Listefeedback.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("/GUI/feedbackliste.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("/GUI/Reclamation.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/Reclamationliste.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //        Patisserie p=new Patisserie("Masmoudi");
//        Suggestion s = new Suggestion(1,2,1);
//        FeedBack f = new FeedBack(1,1,"test ajout");
//        Promotion prom = new Promotion(1, 1, 25);
//        Promotion updateprom = new Promotion(2, 30);
//        CRUD_Patisserie crud = new CRUD_Patisserie();
//        CRUD_Suggestion cruds = new CRUD_Suggestion();
//        CRUD_FeedBack crudf = new CRUD_FeedBack();
//        CRUD_Promotion crudprom = new CRUD_Promotion();
//        String nom ="Masmoudi" ;
//        try {
//            cruds.rechercheSuggestionP(nom);
//            //crud.insertPatisserie(p);
//            //crud.delete(1);
//            //crud.update(1);
//            //cruds.insertSuggestionP(s);
//            //crudf.insertFeedBackP(f);
//            //crudf.displayAllFeedBackP().forEach(System.out::println);
//            //crudprom.insertPromotion(prom);
//            //crudprom.deletePromotion(1);
//            //crudprom.updatePromotion(updateprom);
//            cruds.displaySuggestionP().forEach(System.out::println);
//        } catch (SQLException ex) {
//            Logger.getLogger(Cupcake.class.getName()).log(Level.SEVERE, null, ex);
//        }
        launch(args);
    }

}
