/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.deliveryservice.restclient.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import pa165.deliveryservice.restclient.api.CustomerClient;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author John
 */
public class DeleteCustomerListener implements ActionListener {
    private final CustomerClient customerClient;
    private final JTable table;
    private static final Logger log = Logger.getLogger(DeleteCustomerListener.class.getName());

    public DeleteCustomerListener(CustomerClient customerClient, JTable table) {
        this.customerClient = customerClient;
        this.table = table;
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
    }else{
       DefaultTableModel dtm = (DefaultTableModel) table.getModel();
            int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this postman??");
            if (dialogResult == JOptionPane.YES_OPTION) {
                long id = Long.parseLong(dtm.getValueAt(table.getSelectedRow(), 0).toString());
                try {
                    customerClient.deleteCustomer(id);
                    dtm.removeRow(table.getSelectedRow());
                } catch (Exception ex) {
                    log.log(Level.SEVERE, ex.getStackTrace().toString());
                    JOptionPane.showMessageDialog(null, "Unexpected error occurred while deleting! \n\n" + ex.getMessage(), "Unexpected Error", JOptionPane.ERROR_MESSAGE);
                }
            }           
    }
    }  
}
