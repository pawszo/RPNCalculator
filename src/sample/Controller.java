package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import sample.utils.RPNutils;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;


public class Controller {
    @FXML
    private Button one, two, three, four, five, six, seven, eight, nine, zero;
    @FXML
    private Button point, leftBracket, rightBracket, cancel, backspace, equals;
    @FXML
    private Button percent;
    @FXML
    private Button add;
    @FXML
    private Button subtract;
    @FXML
    private Button multiply;
    @FXML
    private Button divide;
    @FXML
    private Button power;
    @FXML
    private Button radical;
    @FXML
    private Button minus;
    @FXML
    private TextArea screen;
    @FXML
    private TextField NthRoot;


    private boolean NRootFocused;

    public Controller() {

    }

    public void initialize() {
        setTips();
        NthRoot.setPromptText("n");
        screen.setStyle("-fx-font-alignment: center");
    }

    @FXML
    private void toggleNRootFocused() {
        if (NthRoot.getScene().getFocusOwner().equals(NthRoot)) NRootFocused = true;
        if (NthRoot.getScene().getFocusOwner().equals(screen)) NRootFocused = false;
    }

    @FXML
    private void printNumberToScreen(ActionEvent e) {
        if (NRootFocused) {
            if (e.getSource() == one) NthRoot.setText(NthRoot.getText() + "1");
            if (e.getSource() == two) NthRoot.setText(NthRoot.getText() + "2");
            if (e.getSource() == three) NthRoot.setText(NthRoot.getText() + "3");
            if (e.getSource() == four) NthRoot.setText(NthRoot.getText() + "4");
            if (e.getSource() == five) NthRoot.setText(NthRoot.getText() + "5");
            if (e.getSource() == six) NthRoot.setText(NthRoot.getText() + "6");
            if (e.getSource() == seven) NthRoot.setText(NthRoot.getText() + "7");
            if (e.getSource() == eight) NthRoot.setText(NthRoot.getText() + "8");
            if (e.getSource() == nine) NthRoot.setText(NthRoot.getText() + "9");
            if (e.getSource() == zero) NthRoot.setText(NthRoot.getText() + "0");
            if (e.getSource() == minus) {
                if (NthRoot.getText().equals("")) {
                    NthRoot.setText("- ");
                } else {

                    char[] chars = NthRoot.getText().toCharArray();
                    if (chars[chars.length - 1] == '-') {
                        String equasion = "";
                        for (int i = 0; i < chars.length - 1; i++) {
                            equasion += chars[i];
                        }
                        NthRoot.setText(equasion);

                    } else {
                        NthRoot.setText(NthRoot.getText() + "-");
                    }
                }
            }
            if (e.getSource() == backspace) {
                if (NthRoot.getText().equals("")) {
                } else {
                    char[] chars = NthRoot.getText().toCharArray();
                    String equasion = "";
                    if (chars[chars.length - 1] == ' ') {
                        for (int i = 0; i < chars.length - 3; i++) {
                            equasion += chars[i];
                        }
                    } else {
                        for (int i = 0; i < chars.length - 1; i++) {
                            equasion += chars[i];
                        }

                    }
                    NthRoot.setText(equasion);
                }
            }
        } else {
            if (e.getSource() == one) screen.setText(screen.getText() + "1");
            if (e.getSource() == two) screen.setText(screen.getText() + "2");
            if (e.getSource() == three) screen.setText(screen.getText() + "3");
            if (e.getSource() == four) screen.setText(screen.getText() + "4");
            if (e.getSource() == five) screen.setText(screen.getText() + "5");
            if (e.getSource() == six) screen.setText(screen.getText() + "6");
            if (e.getSource() == seven) screen.setText(screen.getText() + "7");
            if (e.getSource() == eight) screen.setText(screen.getText() + "8");
            if (e.getSource() == nine) screen.setText(screen.getText() + "9");
            if (e.getSource() == zero) screen.setText(screen.getText() + "0");
            if (e.getSource() == minus) {
                if (screen.getText().equals("")) {
                    screen.setText("- ");
                } else {

                    char[] chars = screen.getText().toCharArray();
                    if (chars[chars.length - 1] == '-') {
                        String equasion = "";
                        for (int i = 0; i < chars.length - 1; i++) {
                            equasion += chars[i];
                        }
                        screen.setText(equasion);

                    } else {
                        screen.setText(screen.getText() + "-");
                    }
                }
            }
            if (e.getSource() == backspace) {
                if (screen.getText().equals("")) {
                } else if (screen.getText().equals("INCORRECT INPUT FORMAT")) {
                    screen.setText("");
                }
                else {
                    char[] chars = screen.getText().toCharArray();
                    String equasion = "";
                    if (chars[chars.length - 1] == ' ') {
                        for (int i = 0; i < chars.length - 3; i++) {
                            equasion += chars[i];
                        }
                    } else {
                        for (int i = 0; i < chars.length - 1; i++) {
                            equasion += chars[i];
                        }

                    }
                    screen.setText(equasion);
                }
            }
        }
    }

    @FXML
    private void printToScreen(ActionEvent e) {
        if (screen.getText().equals("")) {
        } else {
            char[] chars = screen.getText().toCharArray();
            if (chars[chars.length - 1] != ' ') {
                String equasion = "";
                for (char c : chars) {
                    equasion += c;
                }
                equasion += " ";
                screen.setText(equasion);
            }

        }


        if (e.getSource() == add) screen.setText(screen.getText() + "+ ");
        if (e.getSource() == subtract) screen.setText(screen.getText() + "- ");
        if (e.getSource() == multiply) screen.setText(screen.getText() + "* ");
        if (e.getSource() == divide) screen.setText(screen.getText() + "/ ");
        if (e.getSource() == leftBracket) screen.setText(screen.getText() + "( ");
        if (e.getSource() == rightBracket) screen.setText(screen.getText() + ") ");
        if (e.getSource() == power) screen.setText(screen.getText() + "^ ");
        if (e.getSource() == radical) {
            screen.setText(screen.getText() + "^ ( 1 / " + NthRoot.getText() + " ) ");
            NthRoot.setText("");
            NRootFocused = false;
        }
        if (e.getSource() == percent) screen.setText(screen.getText() + "% ");
        if (e.getSource() == cancel) screen.setText("");

    }


    @FXML
    private void funcButton(ActionEvent e) {
        try {
            if (e.getSource() == equals) {
                //    System.out.println(RPNutils.convertToRPN(screen.getText()));
                double result = RPNutils.evaluate(RPNutils.convertToRPN(screen.getText()));
                screen.setText("" + result);
            }
            if (e.getSource() == point) screen.setText(screen.getText() + ".");
        } catch (RuntimeException re) {
            screen.setText("INCORRECT INPUT FORMAT");
        }
    }



    public TextArea getScreen() {
        return screen;
    }

    @FXML
    private void setTips() {
        minus.setTooltip(new Tooltip("Use minus before the number"));
        power.setTooltip(new Tooltip("power; e.g. 3^2 = 9"));
        radical.setTooltip(new Tooltip("radical; enter the root number in the main display, then click on the field to the left to focus, enter the factor and press the button."));
    }


}
