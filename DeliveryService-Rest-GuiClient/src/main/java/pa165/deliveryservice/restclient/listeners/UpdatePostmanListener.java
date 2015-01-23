package pa165.deliveryservice.restclient.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.ws.rs.core.Response;
import pa165.deliveryservice.rest.entity.Postman;
import pa165.deliveryservice.restclient.api.PostmanClient;

/**
 * Listener for update postman operation
 *
 * @author John
 */
public class UpdatePostmanListener implements ActionListener {

    private final PostmanClient postmanClient;
    private static final Logger log = Logger.getLogger(CreatePostmanListener.class.getName());
    private final JTextField nameTF, surnameTF;
    private final JTable table;
    private ResourceBundle bundle;
    private final GetAllRecords getAllRecords;

    public UpdatePostmanListener(PostmanClient client, JTable table, JTextField txtPostmanName, JTextField txtPostmanSurname, ResourceBundle bundle) {
        this.postmanClient = client;
        this.table = table;
        this.nameTF = txtPostmanName;
        this.surnameTF = txtPostmanSurname;
        this.bundle = bundle;
        getAllRecords = new GetAllRecords(postmanClient, null, table, bundle);
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
            String name = nameTF.getText();
            String surname = surnameTF.getText();
            if (name.isEmpty() || name == null) {
                Helper.markTextField(false, nameTF);
                Helper.showMessage(bundle.getString("create.name"));
                return;
            } else {
                Helper.markTextField(true, nameTF);
            }
            if (surname.isEmpty() || surname == null) {
                Helper.markTextField(false, surnameTF);
                Helper.showMessage(bundle.getString("create.surname"));
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
                getAllRecords.getAll();
            } catch (Exception ex) {
                log.log(Level.SEVERE, Arrays.toString(ex.getStackTrace()));
                JOptionPane.showMessageDialog(null, bundle.getString("postman.update.error")+" \n\n" + ex.getMessage(), bundle.getString("error.header"), JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
