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
import pa165.deliveryservice.rest.entity.Postman;

/**
 *
 * @author John
 */
public class CreatePostmanListener implements ActionListener {

    private final PostmanClient postmanClient;
    private static final Logger log = Logger.getLogger(CreatePostmanListener.class.getName());
    private final JTextField nameTF, surnameTF;
    private final GetAllRecords getAllRecords;
    
    public CreatePostmanListener(PostmanClient client,JTable table, JTextField txtPostmanName, JTextField txtPostmanSurname) {
        this.postmanClient = client;
        this.nameTF = txtPostmanName;
        this.surnameTF = txtPostmanSurname;
        getAllRecords = new GetAllRecords(postmanClient, null, table);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
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
            Postman postman = new Postman();
            postman.setFirstName(name);
            postman.setLastName(surname);
            Response response = postmanClient.createPostman(postman);
            getAllRecords.getAll();
        } catch (Exception ex) {
            log.log(Level.SEVERE, Arrays.toString(ex.getStackTrace()));
            JOptionPane.showMessageDialog(null, "Unexpected error occurred while updating postman! \n\n" + ex.getMessage(), "Unexpected Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
