/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.deliveryservice.restclient.listeners;

import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author John
 */
public final class Helper {
    public static void markTextField(boolean valid, JTextField textField) {
        textField.setBackground(valid ? Color.white : Color.red);
    }

    public static void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    public static boolean isNumeric(String str) {
        try {
            int i = Integer.parseInt(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
