/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.deliveryservice.restclient.listeners;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.ws.rs.core.Response;
import pa165.deliveryservice.restclient.api.PostmanClient;
import pa165.deliveryservice.restclient.entity.Postman;

/**
 *
 * @author John
 */
public class CreatePostmanListener implements ActionListener {
    private final PostmanClient postmanClient;
    private static final Logger log = Logger.getLogger(CreatePostmanListener.class.getName());
    private final javax.swing.JTextField txtPostmanName;
    private final javax.swing.JTextField txtPostmanSurname;

    public CreatePostmanListener(PostmanClient client,JTextField txtPostmanName, JTextField txtPostmanSurname) {
        this.postmanClient = client;
        this.txtPostmanName = txtPostmanName;
        this.txtPostmanSurname = txtPostmanSurname;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(txtPostmanName.getText().length() == 0 || "".equals(txtPostmanName.getText().trim())) {
            txtPostmanName.setBackground(Color.red);
            JOptionPane.showMessageDialog(null, "Postmans name can not be empty.");
            return;
        }else{
            txtPostmanName.setBackground(Color.white);
        }
        if(txtPostmanSurname.getText().length() == 0 || "".equals(txtPostmanSurname.getText().trim())) {
            txtPostmanSurname.setBackground(Color.red);
            JOptionPane.showMessageDialog(null, "Postmans surname can not be empty.");
            return;
        }else{
            txtPostmanSurname.setBackground(Color.white);
        }
        
        Postman postman = new Postman();
        postman.setFirstName(txtPostmanName.getText());
        postman.setLastName(txtPostmanSurname.getText());
        try{
            Response response = postmanClient.createPostman(postman);
        }catch(Exception ex) {
            log.log(Level.SEVERE, ex.getStackTrace().toString());
            JOptionPane.showMessageDialog(null, "Unexpected error occurred while creating postman! \n\n" + ex.getMessage(), "Unexpected Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    
}
