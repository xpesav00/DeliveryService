/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.deliveryservice.restclient.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.ws.rs.core.Response;
import pa165.deliveryservice.restclient.api.PostmanClient;
import pa165.deliveryservice.restclient.entity.Postman;

/**
 *
 * @author John
 */
public class UpdatePostmanListener implements ActionListener {

    private final PostmanClient postmanClient;
    private static final Logger log = Logger.getLogger(CreatePostmanListener.class.getName());
    private final JTextField nameTF, surnameTF;
    private final JTable table;

    public UpdatePostmanListener(PostmanClient client, JTable table, JTextField txtPostmanName, JTextField txtPostmanSurname) {
        this.postmanClient = client;
        this.table = table;
        this.nameTF = txtPostmanName;
        this.surnameTF = txtPostmanSurname;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (table.getSelectedRow() == -1) {
            if (table.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Table is empty!");
                return;
            } else {
                JOptionPane.showMessageDialog(null, "First you must select some row!");
                return;
            }
        } else {
            String name = nameTF.getText();
            String surname = surnameTF.getText();
            if (name.isEmpty() || name == null) {
                Helper.markTextField(false, nameTF);
                Helper.showMessage("Name can't be empty.");
                return;
            } else {
                Helper.markTextField(true, nameTF);
            }
            if (surname.isEmpty() || surname == null) {
                Helper.markTextField(false, surnameTF);
                Helper.showMessage("Surname can't be empty.");
                return;
            } else {
                Helper.markTextField(true, surnameTF);
            }
            try {
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                int selectedRow = table.getSelectedRow();
                String valueAt = model.getValueAt(selectedRow, 0).toString();
                long id = Long.parseLong(valueAt);
                Postman postman = postmanClient.getPostman(id);
                postman.setFirstName(name);
                postman.setLastName(surname);
                Response response = postmanClient.updatePostman(postman);
            } catch (Exception ex) {
                log.log(Level.SEVERE, Arrays.toString(ex.getStackTrace()));
                JOptionPane.showMessageDialog(null, "Unexpected error occurred while updating postman! \n\n" + ex.getMessage(), "Unexpected Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
