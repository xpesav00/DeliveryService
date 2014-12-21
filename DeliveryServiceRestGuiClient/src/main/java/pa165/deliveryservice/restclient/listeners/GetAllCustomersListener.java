/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.deliveryservice.restclient.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTable;
import pa165.deliveryservice.restclient.api.CustomerClient;

/**
 *
 * @author John
 */
public class GetAllCustomersListener implements ActionListener {
    private final GetAllRecords getAllRecords;
    
    public GetAllCustomersListener(CustomerClient customerClient, JTable table) {
        getAllRecords = new GetAllRecords(null, customerClient, table);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        getAllRecords.getAll();
    }
    
}
