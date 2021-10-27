package com.example.javafxcalc;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    // Separate lines because sonarcloud...
    Button b0;
    Button b1;
    Button b2;
    Button b3;
    Button b4;
    Button b5;
    Button b6;
    Button b7;
    Button b8;
    Button b9;
    Button bp;
    Button bs;
    Button bd;
    Button bm;
    Button beq;
    Button bclear;
    Button bdot;
    Label inputField;
    String num1 = "";
    String num2 = "";
    String inputText = "";
    double resultDouble;
    boolean num1IsSet = false;
    boolean resIsSet = false;
    static final int BUTTON_WIDTH = 30;

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Calculator");
        VBox app = new VBox();
        HBox hContainer = new HBox();
        VBox col1 = new VBox();
        VBox col2 = new VBox();
        VBox col3 = new VBox();
        VBox col4 = new VBox();

        inputField = new Label(inputText);

        b0 = new Button("0");
        b0.setMinWidth(BUTTON_WIDTH);
        b0.setOnAction(actionEvent -> enterNumber("0"));

        b1 = new Button("1");
        b1.setMinWidth(BUTTON_WIDTH);
        b1.setOnAction(actionEvent -> enterNumber("1"));

        b2 = new Button("2");
        b2.setMinWidth(BUTTON_WIDTH);
        b2.setOnAction(actionEvent -> enterNumber("2"));

        b3 = new Button("3");
        b3.setMinWidth(BUTTON_WIDTH);
        b3.setOnAction(actionEvent -> enterNumber("3"));

        b4 = new Button("4");
        b4.setMinWidth(BUTTON_WIDTH);
        b4.setOnAction(actionEvent -> enterNumber("4"));

        b5 = new Button("5");
        b5.setMinWidth(BUTTON_WIDTH);
        b5.setOnAction(actionEvent -> enterNumber("5"));

        b6 = new Button("6");
        b6.setMinWidth(BUTTON_WIDTH);
        b6.setOnAction(actionEvent -> enterNumber("6"));

        b7 = new Button("7");
        b7.setMinWidth(BUTTON_WIDTH);
        b7.setOnAction(actionEvent -> enterNumber("7"));

        b8 = new Button("8");
        b8.setMinWidth(BUTTON_WIDTH);
        b8.setOnAction(actionEvent -> enterNumber("8"));

        b9 = new Button("9");
        b9.setMinWidth(BUTTON_WIDTH);
        b9.setOnAction(actionEvent -> enterNumber("9"));

        bp = new Button("+");
        bp.setMinWidth(BUTTON_WIDTH);
        bp.setOnAction(actionEvent -> {
            if (num1IsSet) {
                return;
            }
            num1 += " + ";
            num1IsSet = true;
            inputText += " + ";
            inputField.setText(inputText);
        });

        bs = new Button("-");
        bs.setMinWidth(BUTTON_WIDTH);
        bs.setOnAction(actionEvent -> {
            if (num1IsSet) {
                return;
            }
            num1 += " - ";
            num1IsSet = true;
            inputText += " - ";
            inputField.setText(inputText);
        });

        bd = new Button("/");
        bd.setMinWidth(BUTTON_WIDTH);
        bd.setOnAction(actionEvent -> {
            if (num1IsSet) {
                return;
            }
            num1 += " / ";
            num1IsSet = true;
            inputText += " / ";
            inputField.setText(inputText);
        });

        bm = new Button("*");
        bm.setMinWidth(BUTTON_WIDTH);
        bm.setOnAction(actionEvent -> {
            if (num1IsSet) {
                return;
            }
            num1 += " * ";
            num1IsSet = true;
            inputText += " * ";
            inputField.setText(inputText);
        });

        beq = new Button("=");
        beq.setMinWidth(BUTTON_WIDTH);
        beq.setOnAction(actionEvent -> doCalc());

        bclear = new Button("C");
        bclear.setMinWidth(BUTTON_WIDTH);
        bclear.setOnAction(actionEvent -> {
            num1IsSet = false;
            inputText = "";
            inputField.setText(inputText);
            resIsSet = false;
        });

        bdot = new Button(".");
        bdot.setMinWidth(BUTTON_WIDTH);
        bdot.setOnAction(actionEvent -> enterNumber("."));

        col1.getChildren().addAll(b7, b4, b1, b0);
        col2.getChildren().addAll(b8, b5, b2, bdot);
        col3.getChildren().addAll(b9, b6, b3, beq);
        col4.getChildren().addAll(bclear, bp, bs, bm, bd);

        hContainer.getChildren().addAll(col1, col2, col3, col4);
        app.getChildren().addAll(inputField, hContainer);
        Scene scene = new Scene(app);
        stage.setScene(scene);
        stage.show();
    }

    private void enterNumber(String num) {
        if (num1IsSet || resIsSet) {
            num2 += num;
        }
        else {
            num1 += num;
        }
        inputText += num;
        inputField.setText(inputText);
    }

    private void doCalc() {
        String[] equation = inputText.trim().split("\\s+");
        double operand1 = Double.parseDouble(equation[0]);

        if (equation.length == 3) {
            double operand2 = Double.parseDouble(equation[2]);

            if (equation[1].equals("+")) {
                resultDouble = operand1 + operand2;
            }
            else if (equation[1].equals("-")) {
                resultDouble = operand1 - operand2;
            }
            else if (equation[1].equals("*")) {
                resultDouble = operand1 * operand2;
            }
            else if (equation[1].equals("/")) {
                if (operand2 == 0) {
                    resultDouble = Double.MIN_VALUE;
                }
                else {
                    resultDouble = operand1 / operand2;
                }
            }
        }
        else {
            resultDouble = operand1;
        }

        if (resultDouble == Double.MIN_VALUE) {
            inputText = "Error";
        }
        else {
            inputText = "" + resultDouble;
        }
        inputField.setText(inputText);

        num1IsSet = false;
        resIsSet = true;
    }

    public static void main(String[] args) {
        launch();
    }
}