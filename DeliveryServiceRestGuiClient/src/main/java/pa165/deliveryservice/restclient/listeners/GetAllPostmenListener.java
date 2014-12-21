/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.deliveryservice.restclient.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import pa165.deliveryservice.restclient.api.PostmanClient;
import pa165.deliveryservice.rest.entity.Postman;

/**
 *
 * @author John
 */
public class GetAllPostmenListener implements ActionListener {
    private final GetAllRecords getAllRecords;
    
    public GetAllPostmenListener(PostmanClient postmanClient, JTable table) {
        getAllRecords = new GetAllRecords(postmanClient, null, table);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        getAllRecords.getAll();
    }  
}
