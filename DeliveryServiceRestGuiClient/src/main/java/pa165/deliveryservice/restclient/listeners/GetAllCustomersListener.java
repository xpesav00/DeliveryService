/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.deliveryservice.restclient.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import pa165.deliveryservice.restclient.api.CustomerClient;
import pa165.deliveryservice.rest.entity.Customer;

/**
 *
 * @author John
 */
public class GetAllCustomersListener implements ActionListener {
    private final CustomerClient customerClient;
    private final JTable table;
    private static final Logger log = Logger.getLogger(GetAllCustomersListener.class.getName());

    public GetAllCustomersListener(CustomerClient customerClient, JTable table) {
        this.customerClient = customerClient;
        this.table = table;
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Vector<String> columnNames = new Vector<String>();
            Vector<Vector> rows = new Vector<Vector>();
            columnNames.add("Id");
            columnNames.add("Name");
            columnNames.add("Surname");
            columnNames.add("Address");
            List<Customer> allCustomers = customerClient.getAllCustomers();
            for (Customer customer : allCustomers) {
                Vector<Object> row = new Vector<Object>();
                row.add(customer.getId());
                row.add(customer.getFirstName());
                row.add(customer.getLastName());
                row.add(customer.getAddress());
                rows.add(row);                
            }
            TableModel model = new DefaultTableModel(rows, columnNames){
                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return false;
                }
            };
            table.setModel(model);
        } catch (Exception ex) {
            log.log(Level.SEVERE, Arrays.toString(ex.getStackTrace()));
            JOptionPane.showMessageDialog(null, "Unexpected error occurred! \n\n" + ex.getMessage(), "Unexpected Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
