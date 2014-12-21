package pa165.deliveryservice.restclient.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.ws.rs.core.Response;
import pa165.deliveryservice.rest.entity.Address;
import pa165.deliveryservice.rest.entity.Customer;
import pa165.deliveryservice.restclient.api.CustomerClient;

/**
 * Listener for create customer operation
 * 
 * @author John
 */
public class CreateCustomerListener implements ActionListener {

    private final CustomerClient customerClient;
    private static final Logger log = Logger.getLogger(CreateCustomerListener.class.getName());
    private final JTextField nameTF, surnameTF, cityTF, streetTF, postcodeTF;
    private final GetAllRecords getAllRecords;
    
    public CreateCustomerListener(CustomerClient customerClient, JTable table, JTextField name, JTextField surname, JTextField city, JTextField street, JTextField postcode) {
        this.customerClient = customerClient;
        getAllRecords = new GetAllRecords(null, customerClient, table);
        this.nameTF = name;
        this.surnameTF = surname;
        this.cityTF = city;
        this.streetTF = street;
        this.postcodeTF = postcode;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String name = nameTF.getText();
        String surname = surnameTF.getText();
        String city = cityTF.getText();
        String street = streetTF.getText();
        String postcode = postcodeTF.getText();
        if (name == null || name.isEmpty()) {
            Helper.markTextField(false, nameTF);
            Helper.showMessage("Name can't be empty.");
            return;
        } else {
            Helper.markTextField(true, nameTF);
        }
        if (surname == null || surname.isEmpty()) {
            Helper.markTextField(false, surnameTF);
            Helper.showMessage("Surname can't be empty.");
            return;
        } else {
            Helper.markTextField(true, surnameTF);
        }
        if (city == null || city.length() < 2) {
            Helper.markTextField(false, cityTF);
            Helper.showMessage("City must be filled in (at least 2 characters).");
            return;
        } else {
            Helper.markTextField(true, cityTF);
        }
        if (street == null || street.length() < 2) {
            Helper.markTextField(false, streetTF);
            Helper.showMessage("Street must be filled in (at least 2 characters).");
            return;
        } else {
            Helper.markTextField(true, streetTF);
        }
        if (postcode == null || postcode.isEmpty()) {
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
            Customer customer = new Customer();
            customer.setFirstName(name);
            customer.setLastName(surname);
            Address address = new Address();
            address.setCity(city);
            address.setStreet(street);
            address.setPostcode(Integer.parseInt(postcode));
            customer.setAddress(address);
            Response response = customerClient.createCustomer(customer);
            getAllRecords.getAll();
        } catch (Exception ex) {
            log.log(Level.SEVERE, Arrays.toString(ex.getStackTrace()));
            JOptionPane.showMessageDialog(null, "Unexpected error occurred while updating postman! \n\n" + ex.getMessage(), "Unexpected Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
