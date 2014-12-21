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
import pa165.deliveryservice.rest.entity.Address;
import pa165.deliveryservice.rest.entity.Customer;
import pa165.deliveryservice.restclient.api.CustomerClient;

/**
 *
 * @author John
 */
public class UpdateCustomerListener implements ActionListener {
    private final CustomerClient customerClient;
    private static final Logger log = Logger.getLogger(UpdateCustomerListener.class.getName());
    private final JTable table;
    private final JTextField nameTF, surnameTF, cityTF, streetTF, postcodeTF;
    private final GetAllRecords getAllRecords;

    public UpdateCustomerListener(CustomerClient customerClient, JTable table, JTextField name, JTextField surname, JTextField city, JTextField street, JTextField postcode) {
        this.customerClient = customerClient;
        this.table = table;
        this.nameTF = name;
        this.surnameTF = surname;
        this.cityTF = city;
        this.streetTF = street;
        this.postcodeTF = postcode;
        getAllRecords = new GetAllRecords(null, customerClient, table);
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
            String city = cityTF.getText();
            String street = streetTF.getText();
            String postcode = postcodeTF.getText();
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
            if (city.length() < 2 || city == null) {
                Helper.markTextField(false, cityTF);
                Helper.showMessage("City must be filled in (at least 2 characters).");
                return;
            } else {
                Helper.markTextField(true, cityTF);
            }
            if (street.length() < 2 || street == null) {
                Helper.markTextField(false, streetTF);
                Helper.showMessage("Street must be filled in (at least 2 characters).");
                return;
            } else {
                Helper.markTextField(true, streetTF);
            }
            if (postcode.isEmpty() || postcode == null) {
                Helper.markTextField(false, postcodeTF);
                Helper.showMessage("Surname can't be empty.");
                return;
            } else if (postcode.length() != 5 || !Helper.isNumeric(postcode)) {
                Helper.markTextField(false, postcodeTF);
                Helper.showMessage("Postcode must be numeric value (exact 5 numbers).");
                return;
            } else {
                Helper.markTextField(true, postcodeTF);
            }
            try {
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                String valueAt = model.getValueAt(table.getSelectedRow(), 0).toString();
                long id = Long.parseLong(valueAt);
                Customer customer = customerClient.getCustomer(id);                
                customer.setFirstName(name);
                customer.setLastName(surname);
                Address address = new Address();
                address.setCity(city);
                address.setStreet(street);
                address.setPostcode(Integer.parseInt(postcode));
                customer.setAddress(address);
                Response response = customerClient.updateCustomer(customer);
                getAllRecords.getAll();
            } catch (Exception ex) {
                log.log(Level.SEVERE, Arrays.toString(ex.getStackTrace()));
                JOptionPane.showMessageDialog(null, "Unexpected error occurred while updating postman! \n\n" + ex.getMessage(), "Unexpected Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
