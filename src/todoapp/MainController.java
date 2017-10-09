/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todoapp;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXListView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

/**
 *
 * @author shubham_wanne
 */
public class MainController implements Initializable {
        
    private int  max_number_of_task=6;
    int get_task_number=1;

    
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

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        header.setText("Hello, "+System.getProperty("user.name"));
        
        task_space.setExpanded(true);
        task_space.depthProperty().set(1);
        
        task_space.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
        add_button.setOnAction(e->{
            if(!event_text.getText().equals("")){
                Label label=new Label(event_text.getText());
                label.setFont(Font.font("roboto", 14));
                label.setTextFill(Paint.valueOf("#009688"));
                task_space.getItems().add(label);
                event_text.requestFocus();
                event_text.setText("");
        }else{
                event_text.requestFocus();
            }
        });
        trash_button.setOnAction(e->{
            task_space.getItems().removeAll(task_space.getSelectionModel().getSelectedItems());
        });
    }    
}
