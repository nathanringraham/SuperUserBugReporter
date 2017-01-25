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
    
        HelpfulMethods.presentGameCrashNotifier(primaryStage);
   
        /****************************
         *   CREATES STAGE ELEMENTS *
         *    FOR EMAILDEVELOPERUI  * 
         *                          *
         *                          *
         *                          *
         *                          *
         *                          *
         ****************************/
        
        
        
        File systemLogFile = new File("emailLog.txt");
        File errorLogFile = new File("errorLogFile.txt");
        
        
        //To make sure our log file doesn't go on forever, the crash reporter will delete the log file at the beginning
        // when this program is ran.
        // The requirements for this game need two 
        
        if (!new File("systemLogs.txt").exists()){
            System.out.println("systemLogFile File doesn't exist; creating file.");
            HelpfulMethods.writeToLogger(" systemLogFile File doesn't exist; creating file");
            
            
        } else {
            System.out.println("systemLogFile File exists.. Deleting.");
            systemLogFile.delete();
            
        }
        
         if (!new File("errorLogFile").exists()){
            System.out.println("errorLogFile doesn't exist; creating file.");
            HelpfulMethods.writeToSystemErrorLogger(" errorLogFile File doesn't exist; creating file");
            
            
        } else {
            System.out.println("errorLogFile exists Deleting.");
            errorLogFile.delete();
            
        }
        
        
            
            //Create label for email...
            Label RecipientEmail = new Label();
            HelpfulMethods.createLabel(RecipientEmail, "Enter your email address:", -250, -200);
           
            HelpfulMethods.writeToLogger("Recipient Email Created");
        //Create text field so people can enter their email..
             TextField ToEmailTextField = new TextField();
             ToEmailTextField.setTranslateX(-65);
             ToEmailTextField.setTranslateY(-200);
             ToEmailTextField.setMaxWidth(200);
             HelpfulMethods.writeToLogger("ToEmailTextField Created");
       
        //Create content email label...
        
             Label ContentsLabel = new Label();
             
             HelpfulMethods.createLabel(ContentsLabel, "What were you doing at the time of the crash?", -188, -150);
             
             HelpfulMethods.writeToLogger("Contents label created properly");
             
        //Create text area for people to enter their report.
        
             TextArea EmailContentsField = new TextArea();
        
             EmailContentsField.setTranslateX(0);
             EmailContentsField.setTranslateY(0);
             EmailContentsField.setMaxWidth(450);
             EmailContentsField.setMaxHeight(280);
             EmailContentsField.setWrapText(true);
        
             HelpfulMethods.writeToLogger("\nTextArea for EmailContents created.\n ");
        
             
      
        
              // Creates Error Label email errors..
        
              Label emailErrorLabel = new Label();
              
              HelpfulMethods.createLabel(emailErrorLabel, "",ToEmailTextField.getTranslateX() + 230, ToEmailTextField.getTranslateY());
              emailErrorLabel.setVisible(false);
              emailErrorLabel.setTextFill(Color.RED);
        
              //Creates send email button
        
        
        
              Button CreateEmailButton = new Button();
              CreateEmailButton.setTranslateY(200);
              CreateEmailButton.setText("Send Email");
              CreateEmailButton.setOnAction(event -> { //Button Click Event
            
                {
                
                
                    //Sets the variables to read in whatever text is entered into the two fields.
                    String toEmailName = ToEmailTextField.getText();
                    String emailContentsItself = EmailContentsField.getText();
                
                    if (!toEmailName.isEmpty()){ // If the email isn't empty, make sure the error label is not displaying
                        emailErrorLabel.setVisible(false);
                        
                        if (emailIsValid(toEmailName.trim())){
                            try{
                                // If the email is valid and not empty, the program will go ahead and prepare the email.
                                SendEmail emailOptionPane = new SendEmail(toEmailName, emailContentsItself);
                    
                                emailOptionPane.presentConfirmationBox(toEmailName, emailContentsItself);
                                emailErrorLabel.setVisible(false);
                                
                                } catch (Exception e){
                                    System.out.println(e);
                                    emailErrorLabel.setVisible(false);
                            
                                    }
                            
                        } else{
                            //If an email has been entered, but isn't valid, display that it isn't valid.
                            emailErrorLabel.setText("ERROR: Email is not valid.");
                            emailErrorLabel.setVisible(true);
                            
                                HelpfulMethods.writeToSystemErrorLogger("User didn't a valid email into the email box..");
                                
                                HelpfulMethods.writeToLogger("User tried entering an invalid email..");
                                
                            
                        }
                        
                        
                        
                    } else{
                        // Nothing in the email box? Display the error for it.
                        emailErrorLabel.setText("ERROR: No email entered.");
                        emailErrorLabel.setVisible(true);
                        HelpfulMethods.writeToSystemErrorLogger("User didn't enter anything into the email field");
                        HelpfulMethods.writeToLogger("User tried entering a blank email..");
                        
                    }                    
            }
        });
        
        
        
        StackPane root = new StackPane();
        root.getChildren().addAll(CreateEmailButton, RecipientEmail, ToEmailTextField, EmailContentsField, ContentsLabel, emailErrorLabel);
        
        Scene scene = new Scene(root, 700, 500);
        
        primaryStage.setResizable(false);
        primaryStage.setTitle("Email Developers");
        primaryStage.setScene(scene);
        //primaryStage.show(); //Commented out since we are trying to hide the pane until the user confirms that they want to send a report. :) 
            
       
        
        
    }

    public static void main(String[] args) {
        
   
        launch(args);
    }
    
    

    public static boolean emailIsValid(String emailAddress){
        //This method checks the validity of an email by looking for "@" followed by a domain suffix.
        
        return emailAddress.contains("@") && ((emailAddress.contains(".com") || (emailAddress.contains(".org")) || (emailAddress.contains(".net") || (emailAddress.contains(".edu")) || (emailAddress.contains(".co")) || (emailAddress.contains(".gov")))));
        
        
        
    }
}

    

