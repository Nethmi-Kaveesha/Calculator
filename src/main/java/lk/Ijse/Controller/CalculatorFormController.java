package lk.Ijse.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.input.MouseEvent;

public class CalculatorFormController{
    @FXML public Label lblResult;
    public TextField txtDisplay;
    @FXML private Pane titlePane;
    @FXML private ImageView btnMinimize,btnClose;

    private String generalOperationalObject;
     private double firstDouble;
    private int decimalClick = 0;
    private double x,y;

   // private double num1 = 0;
   // private String operator = "+";

    private boolean start = true;

    public void init(Stage stage) {
        titlePane.setOnMousePressed(mouseEvent -> {
            x = mouseEvent.getSceneX();
            y = mouseEvent.getSceneY();

        });
        titlePane.setOnMouseDragged(mouseEvent -> {
            stage.setX(mouseEvent.getSceneX()-x);
            stage.setY(mouseEvent.getSceneY()-y);

        });

        btnClose.setOnMouseClicked(mouseEvent -> stage.close());
        btnMinimize.setOnMouseClicked(mouseEvent -> stage.setIconified(true));

    }

    public void handleGenerateAction(ActionEvent event) {
        generalOperationalObject = ((Button)event.getSource()).getText();
        switch (generalOperationalObject){
            case "AC":
                txtDisplay.setText("");
                decimalClick=0;
                break;
            case "+/-":
                double plusMinus = Double.parseDouble(String.valueOf(txtDisplay.getText()));
                plusMinus = plusMinus * (-1);
                txtDisplay.setText(String.valueOf(plusMinus));
                break;
            case "+":
            case "-":
            case "*":
            case "/":
            case "%":
                String currentText = txtDisplay.getText();
                firstDouble = Double.parseDouble(currentText);
                txtDisplay.setText("");
                decimalClick=0;
                break;
        }
    }

    public void handleDigitAction(ActionEvent event) {
        String digitObject = ((Button)event.getSource()).getText();
        String oldText = txtDisplay.getText();
        String newText = oldText+digitObject;
        txtDisplay.setText(newText);
    }

    public void handleEqualAction(ActionEvent event) {
        double secondDouble;
        double result=0;

        String secondText = txtDisplay.getText();
        secondDouble=Double.parseDouble(secondText);

        switch (generalOperationalObject){
            case "+":
                result=firstDouble + secondDouble;
                break;
            case "-":
                result= firstDouble-secondDouble;
                break;
            case "*":
                result=firstDouble*secondDouble;
                break;
            case "/":
                result=firstDouble/secondDouble;
                break;
            default:

        }
        String format = String.format("%,1f",result);
        txtDisplay.setText(format);
    }

    public void handleDecimalAction(ActionEvent event) {
        if (decimalClick==0){
            String decimalObject = ((Button)event.getSource()).getText();
            String oldText = txtDisplay.getText();
            String newText = oldText+decimalObject;
            txtDisplay.setText(newText);
            decimalClick=1;
        }

    }
}
