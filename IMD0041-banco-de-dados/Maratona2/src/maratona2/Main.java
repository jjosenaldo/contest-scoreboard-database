/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maratona2;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import maratona2.utils.DBUtils;
import maratona2.controller.MainController;

/**
 *
 * @author josenaldo
 */
public class Main extends Application
{
    @Override
    public void start(Stage stage) throws Exception
    {
        URL urlLoad = getClass().getResource("view/Main.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(urlLoad);
        
        Parent root = fxmlLoader.load();
        
        ((MainController) fxmlLoader.getController()).setStage(stage);
        
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();      
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }
}
