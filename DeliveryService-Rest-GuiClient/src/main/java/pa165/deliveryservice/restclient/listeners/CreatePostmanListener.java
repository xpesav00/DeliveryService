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
import javax.ws.rs.core.Response;
import pa165.deliveryservice.rest.entity.Postman;
import pa165.deliveryservice.restclient.api.PostmanClient;

/**
 * Listener for create postman operation
 * 
 * @author John
 */
public class CreatePostmanListener implements ActionListener {

    private final PostmanClient postmanClient;
    private static final Logger log = Logger.getLogger(CreatePostmanListener.class.getName());
    private final JTextField nameTF, surnameTF;
    private final GetAllRecords getAllRecords;
    private ResourceBundle bundle;

    public CreatePostmanListener(PostmanClient client,JTable table, JTextField txtPostmanName, JTextField txtPostmanSurname, ResourceBundle bundle) {
        this.postmanClient = client;
        this.nameTF = txtPostmanName;
        this.surnameTF = txtPostmanSurname;
        this.bundle = bundle;
        getAllRecords = new GetAllRecords(postmanClient, null, table, this.bundle);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String name = nameTF.getText();
        String surname = surnameTF.getText();
        if (name == null || name.isEmpty()) {
            Helper.markTextField(false, nameTF);
            Helper.showMessage(bundle.getString("create.name"));
            return;
        } else {
            Helper.markTextField(true, nameTF);
        }
        if (surname == null || surname.isEmpty()) {
            Helper.markTextField(false, surnameTF);
            Helper.showMessage(bundle.getString("create.surname"));
            return;
        } else {
            Helper.markTextField(true, surnameTF);
        }
        try {
            Postman postman = new Postman();
            postman.setFirstName(name);
            postman.setLastName(surname);
            Response response = postmanClient.createPostman(postman);
            getAllRecords.getAll();
        } catch (Exception ex) {
            log.log(Level.SEVERE, Arrays.toString(ex.getStackTrace()));
            JOptionPane.showMessageDialog(null, bundle.getString("postman.create.error")+" \n\n" + ex.getMessage(), bundle.getString("error.header"), JOptionPane.ERROR_MESSAGE);
        }
    }
}
