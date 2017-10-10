/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todoapp;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXListView;
import com.sun.javafx.css.CalculatedValue;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author shubham_wanne
 */
public class MainController implements Initializable {
        
    private int  max_number_of_task=6;
    int get_task_number=1;
    DataManager dataManager= new DataManager();
    
    @FXML
    private StackPane stack_root;
    
    @FXML
    private ImageView add_image;

    @FXML
    private JFXButton add_button;

    @FXML
    private JFXButton trash_button;

    @FXML
    private Label header;
    
    @FXML
    private ImageView trash_image;

    @FXML
    private JFXTextField event_text;
    
    @FXML
    private JFXListView<Node> task_space;
    
    @FXML
    private AnchorPane root;
    
    @FXML
    private ImageView info_img;
    
    @FXML
    void loadDialogueBox(ActionEvent event) {
        
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        event_text.requestFocus();
        event_text.setTooltip(new Tooltip("Press 'Enter' to add event."));
        List<ToDo> list = dataManager.getData();
        
        if(list.size()!=0){
            for(ToDo td : list){
                Label label = new Label(td.getTodo());
                label.setFont(Font.font("roboto", 14));
                label.setTextFill(Paint.valueOf("#009688"));
                
                task_space.getItems().addAll(label);
                event_text.requestFocus();
            }
        }
        
        root.addEventHandler(KeyEvent.KEY_PRESSED, e->{
        if(e.getCode() == KeyCode.ENTER){
            add_button.fire();
                e.consume();
            }
        });
        
        header.setText("Hello, "+System.getProperty("user.name"));
       
        info_img.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
            try {
                AnchorPane pane = new AnchorPane();
                pane.setPrefSize(400, 400);
                
                Pane inner_pane = new Pane();
                inner_pane.setLayoutX(0);
                inner_pane.setLayoutY(0);
                inner_pane.setPrefSize(400, 60);
                inner_pane.setStyle("-fx-background-color : #009688");
                
                ImageView close= new ImageView(new Image(getClass().getResource("/Delete.png").toURI().toString(), 20, 20, true, true));
                close.prefHeight(40);
                close.prefWidth(40);
                close.setLayoutX(360);
                close.setLayoutY(20);
                
                ImageView info= new ImageView(new Image(getClass().getResource("/Information.png").toURI().toString(), 25, 25, true, true));
                info.setLayoutX(15);
                info.setLayoutY(20);
                
                Label about = new Label("About");
                about.setLayoutX(45);
                about.setLayoutY(25);
                about.setFont(Font.font("roboto", 14));
                about.setTextFill(Paint.valueOf("#ffffff"));
                
                ImageView icon= new ImageView(new Image(getClass().getResource("/Todo.png").toURI().toString(), 65 , 65, true, true));
                icon.setLayoutX(160);
                icon.setLayoutY(160);
                
               
                Label heading = new Label("To Do Application");
                heading.setLayoutX(97);
                heading.setLayoutY(96);
                heading.setFont(Font.font("roboto", 26));
                heading.setTextFill(Paint.valueOf("#004D40"));
                
                Label subheading = new Label("You Note.. We Notify..");
                subheading.setLayoutX(100);
                subheading.setLayoutY(260);
                subheading.setFont(Font.font("roboto", 22));
                subheading.setTextFill(Paint.valueOf("#004D40"));
                
                
                inner_pane.getChildren().addAll(about,info,close);
                pane.getChildren().addAll(inner_pane,heading,icon,subheading);
                
                JFXDialog dialogue = new JFXDialog(stack_root,pane , JFXDialog.DialogTransition.CENTER);
                dialogue.show();
                 close.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        dialogue.close();
                    }
                });
                
                
            }catch (URISyntaxException ex) {
                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        task_space.setExpanded(true);
        task_space.depthProperty().set(1);
        
        task_space.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
        add_button.setOnAction(e->{
            if(!event_text.getText().equals("")){
                Calendar cal = Calendar.getInstance();
                
                Label label1=new Label(event_text.getText());
                label1.setFont(Font.font("roboto", 14));
                label1.setTextFill(Paint.valueOf("#009688"));
                
                
                task_space.getItems().addAll(label1);                                
                dataManager.AddData(event_text.getText(), cal.getTime());
                event_text.requestFocus();
                event_text.setText("");
        }else{
                event_text.requestFocus();
            }
        });
        trash_button.setOnAction(e->{
            for(Node i : task_space.getSelectionModel().getSelectedItems()){
                String temp[]= i.toString().split("\\'");
                System.out.println(temp[1]);
                dataManager.removedata(temp[1]);
                dataManager.updateData();
            }
            task_space.getItems().removeAll(task_space.getSelectionModel().getSelectedItems());
        });
    }    
}
