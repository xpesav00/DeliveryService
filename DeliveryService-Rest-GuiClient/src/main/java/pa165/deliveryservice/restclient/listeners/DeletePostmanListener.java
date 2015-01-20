package pa165.deliveryservice.restclient.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import pa165.deliveryservice.restclient.api.PostmanClient;

/**
 * Listener for delete postman operation
 *
 * @author John
 */
public class DeletePostmanListener implements ActionListener {
    private final PostmanClient postmanClient;
    private final JTable table;
    private static final Logger log = Logger.getLogger(DeletePostmanListener.class.getName());

    public DeletePostmanListener(PostmanClient postmanClient, JTable table) {
        this.postmanClient = postmanClient;
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
        } else {
            DefaultTableModel dtm = (DefaultTableModel) table.getModel();
            int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this postman??");
            if (dialogResult == JOptionPane.YES_OPTION) {
                long id = Long.parseLong(dtm.getValueAt(table.getSelectedRow(), 0).toString());
                try {
                    postmanClient.deletePostman(id);
                    dtm.removeRow(table.getSelectedRow());
                } catch (Exception ex) {
                    log.log(Level.SEVERE, Arrays.toString(ex.getStackTrace()));
                    JOptionPane.showMessageDialog(null, "Unexpected error occurred while deleting! \n\n" + ex.getMessage(), "Unexpected Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }         
    }    
}