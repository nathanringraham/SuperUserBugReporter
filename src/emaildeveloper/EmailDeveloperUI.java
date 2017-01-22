/* EmailDeveloperUI
 * Brandon Reid, Nathan Ingraham, Donovan Clofer, Austin Holford
 * 1/11/17
 * Info: The objective for this class is to present a user-friendly UI that allows a user
 * to enter their email address and their recent actions that caused the game to crash.
 */
package emaildeveloper;


import java.io.File;
import java.io.FileWriter;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;



public class EmailDeveloperUI extends Application {
    
    
    
    
    @Override
    public void start(Stage primaryStage) {
    
        /****************************
         *   CREATES STAGE ELEMENTS *
         *    FOR EMAILDEVELOPERUI  * 
         *                          *
         *                          *
         *                          *
         *                          *
         *                          *
         ****************************/
        File existingEmailFile = new File("emailLog.txt");
        
        if (!existingEmailFile.exists()){
            System.out.println("File doesn't exist");
        } else {
            System.out.println("File exists.. Deleting.");
            existingEmailFile.delete();
            
        }
        
        try{
            FileWriter UILogWriter = new FileWriter(existingEmailFile);
            
            //Create label for email...
            Label RecipientEmail = new Label("Enter your email address: ");
            RecipientEmail.setTranslateX(-250);
            RecipientEmail.setTranslateY(-200);
            UILogWriter.write("\nReceipient Email Created\n ");
        //Create text field so people can enter their email..
             TextField ToEmailTextField = new TextField();
        
             ToEmailTextField.setTranslateX(-65);
             ToEmailTextField.setTranslateY(-200);
             ToEmailTextField.setMaxWidth(200);
             UILogWriter.write("\nToEmailTextField Created\n ");
       
        //Create content email label...
        
             Label ContentsLabel = new Label("What were you doing at the time of the crash?");
             ContentsLabel.setTranslateX(-188);
             ContentsLabel.setTranslateY(-150);
             UILogWriter.write("\nContents label created properly\n");
             
        //Create text area for people to enter their report.
        
             TextArea EmailContentsField = new TextArea();
        
             EmailContentsField.setTranslateX(0);
             EmailContentsField.setTranslateY(0);
             EmailContentsField.setMaxWidth(450);
             EmailContentsField.setMaxHeight(280);
             EmailContentsField.setWrapText(true);
        
             UILogWriter.write("\nTextArea for EmailContents created.\n ");
        
             
      
        
              // Creates Error Label email errors..
        
              Label emailErrorLabel = new Label("");
              emailErrorLabel.setVisible(false);
              emailErrorLabel.setTranslateX(ToEmailTextField.getTranslateX() + 230);
              emailErrorLabel.setTranslateY(ToEmailTextField.getTranslateY());
              emailErrorLabel.setTextFill(Color.RED);
        
              //Creates send email button
        
        
        
              Button CreateEmailButton = new Button();
              CreateEmailButton.setTranslateY(200);
              CreateEmailButton.setText("Send Email");
              CreateEmailButton.setOnAction(event -> { //Button Click Event
            
                {
                
                
                    String toEmailName = ToEmailTextField.getText();
                    String emailContentsItself = EmailContentsField.getText();
                
                
               
                    if (!toEmailName.isEmpty()){
                        emailErrorLabel.setVisible(false);
                        
                        if (emailIsValid(toEmailName)){
                            try{
                
                                SendEmail emailOptionPane = new SendEmail(toEmailName, emailContentsItself);
                    
                                emailOptionPane.presentConfirmationBox(toEmailName, emailContentsItself);
                                emailErrorLabel.setVisible(false);
                                
                                } catch (Exception e){
                                    System.out.println(e);
                                    emailErrorLabel.setVisible(false);
                            
                                    }
                            
                        } else{
                            emailErrorLabel.setText("ERROR: Email is not valid.");
                            emailErrorLabel.setVisible(true);
                            try{
                                UILogWriter.write("\nUser didn't a valid email into the email box..\n");
                                
                                } catch (Exception e){
                                    System.out.println(e);
                            }
                            
                        }
                        
                        
                        
                    } else{
                        emailErrorLabel.setText("ERROR: No email entered.");
                        emailErrorLabel.setVisible(true);
                        try{
                            UILogWriter.write("\nUser didn't enter anything in the email box...\n");
                        } catch (Exception e){
                            System.out.println(e);
                        }
                        
                    }                    
            }
        });
        UILogWriter.close();
        
        
        StackPane root = new StackPane();
        root.getChildren().addAll(CreateEmailButton, RecipientEmail, ToEmailTextField, EmailContentsField, ContentsLabel, emailErrorLabel);
        
        Scene scene = new Scene(root, 700, 500);
        
        primaryStage.setResizable(false);
        primaryStage.setTitle("Email Developers");
        primaryStage.setScene(scene);
        primaryStage.show();
            
        } catch (Exception e){
            System.out.println(e);
        }
        
        
    }

    public static void main(String[] args) {
        
   
        launch(args);
    }
    
    

    public static boolean emailIsValid(String emailAddress){
        
        return emailAddress.contains("@") && ((emailAddress.contains(".com") || (emailAddress.contains(".org")) || (emailAddress.contains(".net") || (emailAddress.contains(".edu")) || (emailAddress.contains(".co")))));
        
        
        
    }
    
    
    
    
}
