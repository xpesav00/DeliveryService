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
    private final PostmanClient postmanClient;
    private final javax.swing.JTable table;
    private static final Logger log = Logger.getLogger(GetAllPostmenListener.class.getName());
    
    public GetAllPostmenListener(PostmanClient postmanClient, JTable table) {
        this.postmanClient = postmanClient;
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
            List<Postman> allPostmen = postmanClient.getAllPostmen();
            for (Postman postman : allPostmen) {
                Vector<Object> row = new Vector<Object>();
                row.add(postman.getId());
                row.add(postman.getFirstName());
                row.add(postman.getLastName());
                rows.add(row);                
            }
            TableModel model = new DefaultTableModel(rows, columnNames);
            table.setModel(model);
        } catch (Exception ex) {
            log.log(Level.SEVERE, ex.getStackTrace().toString());
            JOptionPane.showMessageDialog(null, "Unexpected error occurred! \n\n" + ex.getMessage(), "Unexpected Error", JOptionPane.ERROR_MESSAGE);
        }
    }  
}
