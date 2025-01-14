/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prjminicalculator;

import com.sun.javafx.scene.control.skin.LabeledText;
import java.awt.Color;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author aymane.chabbaki
 */
public class MiniCalculator extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        BorderPane layout = new BorderPane();
        Text title = new Text("MiniCalculator");
        title.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 25));
        title.setStrokeWidth(2);
        
        BorderPane.setAlignment(title, Pos.TOP_CENTER);
        layout.setTop(title);
        
        VBox vbCenter = new VBox();
        TextField txtInN1 = new TextField();
        TextField txtInN2 = new TextField();
        TextField txtInRis = new TextField();
        txtInRis.setEditable(false);
        //txtInRis.setDisable(true);
        
        HBox hbButtons = new HBox();
        Button btnPiu = new Button("PIU");
        Button btnMeno = new Button("MENO");
        Button btnPer = new Button("PER");
        Button btnDiviso = new Button("DIVISO");
        
        HBox.setHgrow(btnPiu, Priority.ALWAYS);
        HBox.setHgrow(btnMeno, Priority.ALWAYS);
        HBox.setHgrow(btnPer, Priority.ALWAYS);
        HBox.setHgrow(btnDiviso, Priority.ALWAYS);
        btnPiu.setMaxWidth(Double.MAX_VALUE);
        btnMeno.setMaxWidth(Double.MAX_VALUE);
        btnPer.setMaxWidth(Double.MAX_VALUE);;
        btnDiviso.setMaxWidth(Double.MAX_VALUE);
        
        btnPiu.setPrefWidth(100);
        btnMeno.setPrefWidth(100);
        btnPer.setPrefWidth(100);
        btnDiviso.setPrefWidth(100);
        
        hbButtons.getChildren().addAll(btnPiu,btnMeno,btnPer,btnDiviso);
        
        vbCenter.getChildren().addAll(txtInN1,txtInN2,hbButtons,txtInRis);
        
        BorderPane.setAlignment(vbCenter, Pos.CENTER);
        layout.setCenter(vbCenter);
        
        HBox hbRight = new HBox();
        Button btnClear = new Button("clear");
        hbRight.getChildren().addAll(btnClear);
        hbRight.setPadding(new Insets(0, 40, 0, 40));
        
        hbRight.setAlignment(Pos.CENTER_RIGHT);
        layout.setRight(hbRight);
        
        HBox hbleft = new HBox();
        Text txtLeft = new Text("left");
        hbleft.getChildren().addAll(txtLeft);
        hbleft.setPadding(new Insets(0, 60, 0, 0));
        
        hbleft.setAlignment(Pos.CENTER_LEFT);
        layout.setLeft(hbleft);
        
        Text txtCopyright = new Text("©magicSoftware");
        BorderPane.setAlignment(txtCopyright, Pos.BOTTOM_RIGHT);
        layout.setBottom(txtCopyright);
        
        Scene scene = new Scene(layout, 500, 200);
        
        
        primaryStage.setTitle("MiniCalculator");
        primaryStage.setScene(scene);
        primaryStage.show();
       
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
