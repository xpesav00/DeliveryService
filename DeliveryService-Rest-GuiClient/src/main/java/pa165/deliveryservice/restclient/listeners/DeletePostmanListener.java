package pa165.deliveryservice.restclient.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;
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
    private ResourceBundle bundle;
    private static final Logger log = Logger.getLogger(DeletePostmanListener.class.getName());

    public DeletePostmanListener(PostmanClient postmanClient, JTable table, ResourceBundle bundle) {
        this.postmanClient = postmanClient;
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
        } else {
            DefaultTableModel dtm = (DefaultTableModel) table.getModel();
            int dialogResult = JOptionPane.showConfirmDialog(null, bundle.getString("postman.delete.confirm"));
            if (dialogResult == JOptionPane.YES_OPTION) {
                long id = Long.parseLong(dtm.getValueAt(table.getSelectedRow(), 0).toString());
                try {
                    postmanClient.deletePostman(id);
                    dtm.removeRow(table.getSelectedRow());
                } catch (Exception ex) {
                    log.log(Level.SEVERE, Arrays.toString(ex.getStackTrace()));
                    JOptionPane.showMessageDialog(null, bundle.getString("delete.error")+" \n\n" + ex.getMessage(), bundle.getString("error.header"), JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}