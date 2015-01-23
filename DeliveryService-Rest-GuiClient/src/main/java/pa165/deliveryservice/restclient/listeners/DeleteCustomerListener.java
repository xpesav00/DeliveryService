package pa165.deliveryservice.restclient.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import pa165.deliveryservice.restclient.api.CustomerClient;

/**
 * Listener for delete customer operation
 *
 * @author John
 */
public class DeleteCustomerListener implements ActionListener {
    private final CustomerClient customerClient;
    private final JTable table;
    private ResourceBundle bundle;
    private static final Logger log = Logger.getLogger(DeleteCustomerListener.class.getName());

    public DeleteCustomerListener(CustomerClient customerClient, JTable table, ResourceBundle bundle) {
        this.customerClient = customerClient;
        this.table = table;
        this.bundle = bundle;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (table.getSelectedRow() == -1) {
            if (table.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, bundle.getString("table.empty"));
                return;
            } else {
                JOptionPane.showMessageDialog(null, bundle.getString("table.selection"));
                return;
            }
        }else{
           DefaultTableModel dtm = (DefaultTableModel) table.getModel();
                int dialogResult = JOptionPane.showConfirmDialog(null, bundle.getString("customer.delete.confirm"));
                if (dialogResult == JOptionPane.YES_OPTION) {
                    long id = Long.parseLong(dtm.getValueAt(table.getSelectedRow(), 0).toString());
                    try {
                        customerClient.deleteCustomer(id);
                        dtm.removeRow(table.getSelectedRow());
                    } catch (Exception ex) {
                        log.log(Level.SEVERE, ex.getStackTrace().toString());
                        JOptionPane.showMessageDialog(null, bundle.getString("delete.error")+" \n\n" + ex.getMessage(), bundle.getString("error.header"), JOptionPane.ERROR_MESSAGE);
                    }
                }
        }
    }
}
