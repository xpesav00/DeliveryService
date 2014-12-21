package pa165.deliveryservice.restclient.listeners;

import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * Class for common validation operation
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
