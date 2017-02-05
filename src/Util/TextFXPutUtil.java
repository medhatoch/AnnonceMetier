/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import static java.awt.PageAttributes.MediaType.A;
import java.util.List;
import java.util.regex.Pattern;
import javafx.scene.control.Label;
import static javafx.scene.input.KeyCode.Z;
import javafx.scene.paint.Color;

/**
 *
 * @author Lenovo
 *
 */
public class TextFXPutUtil {

    //recive a list of Strings taped by user
    //verifie if the strings contain anything other then Letters
    //true if everything is a letter else false
    public static boolean onlyLetters(List<String> texts, int maxLetters, int minLetters) {
        if (!texts.stream().noneMatch((text) -> (!text.matches("[A-Za-z ]{" + minLetters + "," + maxLetters + "}")))) {
            return false;
        }
        return true;
    }

    public static boolean onlyLetters(String text, int maxLetters, int minLetters) {
        if (!text.matches("[A-Za-z ]{" + minLetters + "," + maxLetters + "}")) {
            return false;
        }
        return true;
    }

    public static boolean onlyLetters(String text) {
        if (!text.matches("[A-Za-z ]+")) {
            return false;
        }
        return true;
    }
    
    public static boolean isEmailAdresse(String text) {
        if (!text.matches("[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}")) {
            return false;
        }
        return true;
    }
    
    public static boolean isTel(String number) {
        if (!number.matches("06[0-9]{8}")) {
            return false;
        }
        return true;
    }
    

    //recive a list of numbers taped by user
    //verifie if the strings contain anything other then numbers
    //rtrue if everything is a number else false
    public static boolean onlyNumbers(List<String> numbers) {
        if (!numbers.stream().noneMatch((number) -> (!number.matches("[0-9]")))) {
            return false;
        }
        return true;
    }
    //recive a number taped by user
    //verifie if the strings contain anything other then numbers
    //rtrue if everything is a number else false
    public static boolean onlyNumbers(String number) {
        if (!number.matches("[0-9]+")) {
            return false;
        }
        return true;
    }
    
    public static boolean estprix(String number) {
        if (!number.matches("[0-9]+.[0-9]+") || !number.matches("[0-9]+")) {
            return false;
        }
        return true;
    }
    //
    public static void labelErrorMode(Label label, String errorMessege) {
        label.setTextFill(Color.RED);
        label.setText("\n" + errorMessege);
    }

    public static void labelSuccesMode(Label label, String succesMessege) {
        label.setTextFill(Color.GREEN);
        label.setText("\n" + succesMessege);
    }
    //@param Objectlist
    //return true if everything is null 
    public static boolean isAllNull(List<Object> lists) {
        if (!lists.stream().noneMatch((item) -> (item != null))) {
            return false;
        }
        return true;
    }

}
