/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.deliveryservice.restclient.listeners;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTable;
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
    private static final Logger log = Logger.getLogger(GetAllPostmansListener.class.getName());
    private JTextField txtPostmanName;
    private JTextField txtPostmanSurname;
    private JTable table;

    public UpdatePostmanListener(PostmanClient postmanClient, JTable table, JTextField txtPostmanName, JTextField txtPostmanSurname) {
        this.postmanClient = postmanClient;
        this.table = table;
        this.txtPostmanName = txtPostmanName;
        this.txtPostmanSurname = txtPostmanSurname;
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
            if (txtPostmanName.getText().length() == 0 || "".equals(txtPostmanName.getText().trim())) {
                txtPostmanName.setBackground(Color.red);
                JOptionPane.showMessageDialog(null, "Postmans name can not be empty.");
                return;
            } else {
                txtPostmanName.setBackground(Color.white);
            }
            if (txtPostmanSurname.getText().length() == 0 || "".equals(txtPostmanSurname.getText().trim())) {
                txtPostmanSurname.setBackground(Color.red);
                JOptionPane.showMessageDialog(null, "Postmans surname can not be empty.");
                return;
            } else {
                txtPostmanSurname.setBackground(Color.white);
            }

            try {
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                Object valueAt = model.getValueAt(table.getSelectedRow(), 0);
                long id = (Long) valueAt;
                Postman postman = postmanClient.getPostman(id);
                postman.setFirstName(txtPostmanName.getText());
                postman.setLastName(txtPostmanSurname.getText());
                Response response = postmanClient.updateUser(postman);
            } catch (Exception ex) {
                log.log(Level.SEVERE, ex.getStackTrace().toString());
                JOptionPane.showMessageDialog(null, "Unexpected error occurred while updating postman! \n\n" + ex.getMessage(), "Unexpected Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
