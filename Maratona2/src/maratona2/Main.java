/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maratona2;

import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author josenaldo
 */
public class Main extends Application
{
    private static Stage mainStage;
    
    @Override
    public void start(Stage stage) throws Exception
    {
        Main.mainStage = stage;
        
        URL urlLoad = getClass().getResource("view/Main.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(urlLoad);
        
        Parent root = fxmlLoader.load();
        
//        ((MainController) fxmlLoader.getController()).setStage(stage);
        
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();      
    }
    
    public static Stage getMainStage()
    {
        return mainStage;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }
}
