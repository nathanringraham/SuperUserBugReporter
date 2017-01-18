/* EmailDeveloperUI
 * 1/11/17
 * Info: The objective for this class is to present a user-friendly UI that allows a user
 * to enter their email address and their recent actions that caused the game to crash.
 */
package emaildeveloper;

import java.io.File;
import java.io.PrintWriter;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.io.*;
import java.util.HashSet;
import java.util.Set;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;


/**
 *
 * @author nathan
 */
public class EmailDeveloperUI extends Application {
    
    @Override
    public void start(Stage primaryStage) {
    
        //Create label for email...
        Label RecipientEmail = new Label("Enter your email address: ");
        RecipientEmail.setTranslateX(-250);
        RecipientEmail.setTranslateY(-200);
        
        //Create text field so people can enter their email..
        TextField ToEmailTextField = new TextField();
        
        ToEmailTextField.setTranslateX(-65);
        ToEmailTextField.setTranslateY(-200);
        ToEmailTextField.setMaxWidth(200);
      
       
        //Create content email label...
        
        Label ContentsLabel = new Label("What were you doing at the time of the crash?");
        ContentsLabel.setTranslateX(-188);
        ContentsLabel.setTranslateY(-150);
        
        //Create text area for people to enter their report.
        
        TextArea EmailContentsField = new TextArea();
        
        EmailContentsField.setTranslateX(0);
        EmailContentsField.setTranslateY(0);
        EmailContentsField.setMaxWidth(450);
        EmailContentsField.setMaxHeight(280);
        
        //Creates "Error" Image view on the pane.
        
        ImageView errorImageView = new ImageView();
        errorImageView.setImage(new Image("resources/RedBlock.gif"));     
        errorImageView.setLayoutX(0);
        errorImageView.setLayoutY(0);
        errorImageView.setTranslateX(310);
        errorImageView.setTranslateY(-210);
        
        
        
        
        
        
        System.out.println(errorImageView.getX());
        
        
        Button CreateEmailButton = new Button();
        CreateEmailButton.setTranslateY(200);
        CreateEmailButton.setText("Send Email");
        CreateEmailButton.setOnAction(event -> { //Button Click Event
            
             {
                
                
                String toEmailName = ToEmailTextField.getText();
                String emailContentsItself = EmailContentsField.getText();
                
                 System.out.println("");
               
                //Check if file exists...
                File emailTextFile;
                if (!new File("emailLog.txt").exists()){
                    emailTextFile = new File("emailLog.txt");
                }
                
                // The try-catch statement is going to create a log file or report an error to debug email feature.
                
                
                SendEmail emailOptionPane = new SendEmail(toEmailName, emailContentsItself);
                    
                emailOptionPane.presentConfirmationBox(toEmailName, emailContentsItself);
                
                  
                    
           
                    
                    
                    
                
                
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().addAll(CreateEmailButton, RecipientEmail, ToEmailTextField, EmailContentsField, ContentsLabel);
        
        Scene scene = new Scene(root, 700, 500);
        
        primaryStage.setResizable(false);
        
        primaryStage.setTitle("Email Developers");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    

    
    
}
