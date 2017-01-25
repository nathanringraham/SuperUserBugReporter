/*
 * SendEmail
 * 1/11/17
 * Brandon Reid, Nathan Ingraham, Donovan Clofer, Austin Holford
 * Info: The objective of this class is to take in information from the UI to compose an 
 * email to send off to the developer team (us).
 */
package emaildeveloper;
import java.io.File;
import java.io.FileWriter;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.mail.*;
import javax.mail.internet.*;  
import java.util.Properties;  
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;




public class SendEmail extends EmailDeveloperUI {
    
    String emailAddress;
    String emailContent;
    
    File emailLogfile = new File("emailLog.txt");
    File systemLogFile = new File("systemLogs.txt");
    
    SendEmail(String messageRecipient, String messageContent) throws MessagingException{
       
       this.emailAddress = messageRecipient;
       this.emailContent = messageContent;
 
       
       }
    
    
    
    
    public void presentConfirmationBox(String emailAddress, String emailContent) throws MessagingException {
        
         
         HelpfulMethods.writeToLogger("\npresentConfirmationBox method has been called." );
              
               
         
         
        /****************************
         *   CREATE STAGE           *
         *    FOR CONFIRMATION BOX  * 
         *                          *
         *                          *
         *                          *
         *                          *
         *                          *
         ****************************/
         System.out.println("presentConfirmationBox method has been called. " + " To: " + emailAddress + " With contents: " + emailContent);
        
       
        
        
        
        
        Stage confirmStage = new Stage();
                    
                    
        StackPane confirmPane = new StackPane();
                    
        
        // Create confirm button to send email.
                    
                    
        Button confirmEmailButton = new Button("Confirm");
                    
                   
        confirmEmailButton.setTranslateY(50);
                    
        confirmEmailButton.setTranslateX(-30);
                    
          // Create cancel button to cancel email.
        
        
        Button cancelButton = new Button("Cancel");
                    
        cancelButton.setTranslateX(40);
        cancelButton.setTranslateY(50);
                    
        Label confirmItLabel = new Label("Would you like to send the email?");
                    
        confirmItLabel.setTranslateX(0);
        confirmItLabel.setTranslateY(5);
                    
        // Creates an image view to mock JOptionPane's question mark.
                    
        ImageView questionMarkImageConfirm = new ImageView("resources/questionmark.png");
                    
        questionMarkImageConfirm.setLayoutX(0);
        questionMarkImageConfirm.setLayoutY(0);
        questionMarkImageConfirm.setTranslateY(-30);  
        confirmPane.getChildren().addAll(confirmEmailButton, confirmItLabel, questionMarkImageConfirm, cancelButton);
        Scene confirmScene = new Scene(confirmPane, 400,150);
        confirmStage.initStyle(StageStyle.UNDECORATED); //Prevents user from closing out of the confirmation box
        confirmStage.setResizable(false);
        confirmStage.setTitle("Confirm Email");
        confirmStage.setScene(confirmScene);
        confirmStage.show();
        
        confirmEmailButton.setOnAction(magicAction ->{
                        
                        
          try{
               
               
               sendEmailMessage(emailContent, emailAddress);
               System.out.println("\n Email with the contents of \"" +  emailContent + "\" was sent to " + emailAddress);
                    } catch(Exception e){
                        System.err.println("The log file for email was not found.");
                        HelpfulMethods.writeToSystemErrorLogger("The log file for email was not found.");
                        HelpfulMethods.writeToSystemErrorLogger(e.toString());
                        System.out.println(e);
                        
                        }
                      
                        
               System.exit(0);
                    
                    });
                    cancelButton.setOnAction(cancelAction ->{
                        
                        System.out.println("Message confirmation dialog cancelled.");
                        HelpfulMethods.writeToLogger("Message confirmation dialog cancelled.");
                        confirmStage.close();
                        
                    });
                    
                 }
       
        
    public void sendEmailMessage(String emailContent, String emailAddress) throws MessagingException, AddressException{
        
        
                final String emailAccount = "[removed]@gmail.com";
                final String password = "[removed]";
                Properties mailServerProperties;
                Session getMailSession;
                MimeMessage emailMessage;
		File emailLogFile = this.emailLogfile;
                File systemLogFile = this.systemLogFile;
                
             
                // Set up mail properties to ensure the email can be sent.
                
                HelpfulMethods.writeToLogger("\nSetting email SMTP and port properties.");
                
                System.out.println("Setting email SMTP and port properties.");
		
		mailServerProperties = System.getProperties();
		mailServerProperties.put("mail.smtp.port", "587");
		mailServerProperties.put("mail.smtp.auth", "true");
		mailServerProperties.put("mail.smtp.starttls.enable", "true");
		
 
		 System.out.println("Success.. \n\n");
                 HelpfulMethods.writeToLogger("Success.. ");
               
                
		/* MAIL SECTION
                * When a message is sent, a copy of it will be sent to the bug submitter
                * The message will be sent to the developer Gmail account &
                * cc'd to all the developers so that we for sure get the bug report.
                */
                 
                 System.out.println("\nCreating mail session...\n");
                 HelpfulMethods.writeToLogger("Creating mail session...");
                    
               
		getMailSession = Session.getDefaultInstance(mailServerProperties, null);
		emailMessage = new MimeMessage(getMailSession);
		emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(emailAccount));
                emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(emailAddress)); // Comment out the next 4 lines of code while testing to avoid spamming emails
//            
                
                 System.out.println("Adding email receipients.. \n\n");
                 HelpfulMethods.writeToLogger("Adding email receipients..");

		
                 BodyPart messageBodyPart1 = new MimeBodyPart();
		 emailMessage.setSubject("New Bug Report COPY For SuperUser From: " + emailAddress);
		 String emailBodyFixed =   "Hello " + emailAddress + "," + "  \n\n Thanks for your report. Your report has been sent to the developers of SuperUser. \n" + "For your records, this is the report you submitted: \n\n" + emailContent +  "\n\n\n If you have anything to add to the bug report, feel free to reply to this email." + "\n\n SENT FROM SUPERUSER BUG REPORTER";     
		 messageBodyPart1.setText(emailBodyFixed);
               
                 MimeBodyPart messageBodyPart2 = new MimeBodyPart();
                
                 HelpfulMethods.writeToLogger("Email sent. " + " To: " + emailAddress + " With contents: " + emailContent);
                
                 HelpfulMethods.writeToLogger("Email log file created; emailed. Log file will be cleared upon next crash.");
                 
                 DataSource source = new FileDataSource(emailLogFile);  //Sets the text content to the file present... 
                 messageBodyPart2.setDataHandler(new DataHandler(source));  
                 messageBodyPart2.setFileName("emailLog.txt");
                 
                 MimeBodyPart messageBodyPart3 = new MimeBodyPart();
                 DataSource source2 = new FileDataSource(systemLogFile);
                 messageBodyPart3.setDataHandler(new DataHandler(source2));
                 messageBodyPart3.setFileName("systemLogs.txt");
//                 
                 Multipart multipart = new MimeMultipart();  
                 multipart.addBodyPart(messageBodyPart1);  
                 multipart.addBodyPart(messageBodyPart2); 
                 multipart.addBodyPart(messageBodyPart3);
                 
                
                 emailMessage.setContent(multipart);
                
		
		 Transport messageTransport = getMailSession.getTransport("smtp");
 
		
		 messageTransport.connect("smtp.gmail.com", emailAccount, password);
		 messageTransport.sendMessage(emailMessage, emailMessage.getAllRecipients());
		 messageTransport.close();
                
                 //emailLogFile.deleteOnExit(); //Deletes the file afterwards.
                
        
                
 }  
    
}  
      