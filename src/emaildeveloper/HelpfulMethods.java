/* HelpfulMethods
 * Brandon Reid, Nathan Ingraham, Donovan Clofer, Austin Holford
 * 1/25/17
 * Info: This class consists of helpful methods that'll make logging and other tasks easier.
 * 
 */
package emaildeveloper;

import java.io.FileWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class HelpfulMethods {
    
        public static void writeToLogger(String messageToBelogged){
        //This method allows the program to write to a log file, which is responsible for gathering user events for debugging..
        
        try{
            FileWriter UILogger = new FileWriter("emailLog.txt", true);
            UILogger.write("\n" + messageToBelogged + "\n");
            UILogger.close();
              
        } catch (Exception e){
            System.out.println(e);
        }
     }
    
    public static void writeToSystemErrorLogger(String messageToBelogged){
        //This method allows the program to write the system file, which is responsible for gathering system errors for debugging.
        
        try{
            FileWriter UILogger = new FileWriter("systemLogs.txt", true);
            UILogger.write("\n" + messageToBelogged + "\n");
            UILogger.close();
              
        } catch (Exception e){
            System.out.println(e);
        }
     }
    
    public static void createLabel(Label labelName, String labelContents, double setTransX, double setTransY){
        //This method makes creating labels easy.
        Label actualLabel = labelName;
        actualLabel.setText(labelContents);
        actualLabel.setTranslateX(setTransX);
        actualLabel.setTranslateY(setTransY);
     
    }
    
    public static void presentGameCrashNotifier(Stage primaryStage){
        
        primaryStage.hide();
        Stage notifierStage = new Stage();
                              
        StackPane notifierPane = new StackPane();
                    
       
        // Create confirm button to send email.
        
        
                    
        Button SureButton = new Button("Sure");
                    
                   
        SureButton.setTranslateY(50);
                    
        SureButton.setTranslateX(-30);
                    
          // Create cancel button to cancel email.
        
        SureButton.setOnAction(sureEvent ->{
        
        
        primaryStage.show();
        notifierStage.hide();
        
            
            
        });
        
        
        Button NoButton = new Button("No Thanks");
                    
        NoButton.setTranslateX(40);
        NoButton.setTranslateY(50);
        
        NoButton.setOnAction(event -> { //Button Click Event
            
        
            System.exit(0);
        
        });
        ImageView questionMarkImageConfirm = new ImageView("resources/questionmark.png");
                    
        questionMarkImageConfirm.setLayoutX(0);
        questionMarkImageConfirm.setLayoutY(0);
        questionMarkImageConfirm.setTranslateY(-30);  
                
                    
        Label NotifierLabel = new Label("It looks like the game has crashed.\nWould you like to send a crash report?");
                    
        NotifierLabel.setTranslateX(0);
        NotifierLabel.setTranslateY(5);
        notifierPane.getChildren().addAll(SureButton, NotifierLabel, NoButton, questionMarkImageConfirm);
        Scene notifierScene = new Scene(notifierPane, 400,150);
        notifierStage.initStyle(StageStyle.UNDECORATED); //Prevents user from closing out of the confirmation box
        notifierStage.setResizable(false);
        notifierStage.setTitle("Game Crash");
        notifierStage.setScene(notifierScene);
        notifierStage.show();
        
        
        
        
    }
}
    

