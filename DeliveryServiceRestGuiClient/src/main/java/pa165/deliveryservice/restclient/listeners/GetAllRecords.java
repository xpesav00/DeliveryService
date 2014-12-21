package pa165.deliveryservice.restclient.listeners;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import pa165.deliveryservice.rest.entity.Customer;
import pa165.deliveryservice.rest.entity.Postman;
import pa165.deliveryservice.restclient.api.CustomerClient;
import pa165.deliveryservice.restclient.api.PostmanClient;

/**
 * Object to retrieve rest entity from db
 *
 * @author John
 */
public class GetAllRecords {

    private final PostmanClient postmanClient;
    private final CustomerClient customerClient;
    private final JTable table;
    private static final Logger log = Logger.getLogger(GetAllRecords.class.getName());

    public GetAllRecords(PostmanClient postmanClient, CustomerClient customerClient, JTable table) {
        this.postmanClient = postmanClient;
        this.customerClient = customerClient;
        this.table = table;
    }

    public void getAll() {
        try {
            Vector<String> columnNames = new Vector<String>();
            Vector<Vector> rows = new Vector<Vector>();
            columnNames.add("Id");
            columnNames.add("Name");
            columnNames.add("Surname");
            if (customerClient != null) {
                columnNames.add("Address");
                List<Customer> allCustomers = customerClient.getAllCustomers();
                for (Customer customer : allCustomers) {
                    Vector<Object> row = new Vector<Object>();
                    row.add(customer.getId());
                    row.add(customer.getFirstName());
                    row.add(customer.getLastName());
                    row.add(customer.getAddress().getCity() + ", " + customer.getAddress().getStreet() + ", " + customer.getAddress().getPostcode());
                    rows.add(row);
                }
            } else {
                List<Postman> allPostmen = postmanClient.getAllPostmen();
                for (Postman postman : allPostmen) {
                    Vector<Object> row = new Vector<Object>();
                    row.add(postman.getId());
                    row.add(postman.getFirstName());
                    row.add(postman.getLastName());
                    rows.add(row);
                }
            }

            TableModel model = new DefaultTableModel(rows, columnNames) {
                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return false;
                }
            };
            TableColumnModel columnModel = table.getColumnModel();
            columnModel.getColumn(0).setMinWidth(50);
            columnModel.getColumn(0).setMaxWidth(50);
            columnModel.getColumn(0).setPreferredWidth(50);
            table.setModel(model);
            table.setColumnModel(columnModel);
            table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        } catch (Exception ex) {
            log.log(Level.SEVERE, Arrays.toString(ex.getStackTrace()));
            JOptionPane.showMessageDialog(null, "Unexpected error occurred! \n\n" + ex.getMessage(), "Unexpected Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
