/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.deliveryservice.restclient.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import pa165.deliveryservice.restclient.api.PostmanClient;
import pa165.deliveryservice.restclient.entity.Postman;

/**
 *
 * @author John
 */
public class GetAllPostmansListener implements ActionListener {
    private PostmanClient client;
    private javax.swing.JTable table;

    public GetAllPostmansListener(PostmanClient client, JTable table) {
        this.client = client;
        this.table = table;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        try{
        Vector columnNames = new Vector();
        Vector rows = new Vector();
        columnNames.add("Id");
        columnNames.add("Name");
        columnNames.add("Surname");
        for (Postman postman : client.getAllPostmen()) {
            Vector row = new Vector();
            row.add(Long.valueOf(postman.getId()));
            row.add(postman.getFirstName());
            row.add(postman.getLastName());
            rows.add(row);
        }
        TableModel model = new DefaultTableModel(columnNames, rows);        
        table.setModel(model);
        }catch(Exception ex) {
            JOptionPane.showMessageDialog(null, "Unexpected error occurred. \n\n"+ex.getMessage(),"Unexpected Error",JOptionPane.ERROR_MESSAGE);
        }
    }  
}
