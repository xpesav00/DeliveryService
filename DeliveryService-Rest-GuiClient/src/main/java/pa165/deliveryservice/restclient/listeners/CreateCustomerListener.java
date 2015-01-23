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
    private ResourceBundle bundle;
    
    public CreateCustomerListener(CustomerClient customerClient, JTable table, JTextField name, JTextField surname, JTextField city, JTextField street, JTextField postcode, ResourceBundle bundle) {
        this.customerClient = customerClient;
        getAllRecords = new GetAllRecords(null, customerClient, table, bundle);
        this.nameTF = name;
        this.surnameTF = surname;
        this.cityTF = city;
        this.streetTF = street;
        this.postcodeTF = postcode;
        this.bundle = bundle;
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
            Helper.showMessage(bundle.getString("create.name"));
            return;
        } else {
            Helper.markTextField(true, nameTF);
        }
        if (surname == null || surname.isEmpty()) {
            Helper.markTextField(false, surnameTF);
            Helper.showMessage(bundle.getString("create.name"));
            return;
        } else {
            Helper.markTextField(true, surnameTF);
        }
        if (city == null || city.length() < 2) {
            Helper.markTextField(false, cityTF);
            Helper.showMessage(bundle.getString("create.city"));
            return;
        } else {
            Helper.markTextField(true, cityTF);
        }
        if (street == null || street.length() < 2) {
            Helper.markTextField(false, streetTF);
            Helper.showMessage(bundle.getString("create.street"));
            return;
        } else {
            Helper.markTextField(true, streetTF);
        }
        if (postcode == null || postcode.isEmpty()) {
            Helper.markTextField(false, postcodeTF);
            Helper.showMessage(bundle.getString("create.postcode"));
            return;
        } else if (postcode.length() != 5 || !Helper.isNumeric(postcode)) {
            Helper.markTextField(false, postcodeTF);
            Helper.showMessage(bundle.getString("create.postcode.numeric"));
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
            JOptionPane.showMessageDialog(null, bundle.getString("customer.create.error")+" \n\n" + ex.getMessage(), bundle.getString("error.header"), JOptionPane.ERROR_MESSAGE);
        }
    }
}
