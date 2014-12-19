///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package pa165.deliveryservice.restclient.listeners;
//
//import java.awt.Color;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.swing.JOptionPane;
//import javax.swing.JTextField;
//import javax.ws.rs.core.Response;
//import pa165.deliveryservice.restclient.api.CustomerClient;
//import pa165.deliveryservice.restclient.entity.Customer;
//
///**
// *
// * @author John
// */
//public class CreateCustomerListener implements ActionListener {
//    private final CustomerClient customerClient;
//    private static final Logger log = Logger.getLogger(CreateCustomerListener.class.getName());
//    private final JTextField txtCustomerName;
//    private final JTextField txtCustomerSurname;
//    private final JTextField txtCustomerCity;
//    private final JTextField txtCustomerStreet;
//    private final JTextField txtCustomerPostcode;
//
//    public CreateCustomerListener(CustomerClient customerClient, JTextField txtCustomerName, JTextField txtCustomerSurname, JTextField txtCustomerCity, JTextField txtCustomerStreet, JTextField txtCustomerPostcode) {
//        this.customerClient = customerClient;
//        this.txtCustomerName = txtCustomerName;
//        this.txtCustomerSurname = txtCustomerSurname;
//        this.txtCustomerCity = txtCustomerCity;
//        this.txtCustomerStreet = txtCustomerStreet;
//        this.txtCustomerPostcode = txtCustomerPostcode;
//    }
//
//    
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        if(txtCustomerName.getText().length() == 0 || "".equals(txtCustomerName.getText().trim())) {
//            txtCustomerName.setBackground(Color.red);
//            JOptionPane.showMessageDialog(null, "Customers name can not be empty.");
//            return;
//        }else{
//            txtCustomerName.setBackground(Color.white);
//        }
//        if(txtCustomerSurname.getText().length() == 0 || "".equals(txtCustomerSurname.getText().trim())) {
//            txtCustomerSurname.setBackground(Color.red);
//            JOptionPane.showMessageDialog(null, "Customers surname can not be empty.");
//            return;
//        }else{
//            txtCustomerSurname.setBackground(Color.white);
//        }
//        
//        if(txtCustomerCity.getText().length() < 2 || "".equals(txtCustomerCity.getText().trim())) {
//            txtCustomerCity.setBackground(Color.red);
//            JOptionPane.showMessageDialog(null, "City can not be empty.");
//            return;
//        }else{
//            txtCustomerCity.setBackground(Color.white);
//        }
//        
//        if(txtCustomerStreet.getText().length() < 2 || "".equals(txtCustomerStreet.getText().trim())) {
//            txtCustomerStreet.setBackground(Color.red);
//            JOptionPane.showMessageDialog(null, "Street can not be empty.");
//            return;
//        }else{
//            txtCustomerStreet.setBackground(Color.white);
//        }
//        
//        if(txtCustomerPostcode.getText().length() == 0 || "".equals(txtCustomerPostcode.getText().trim())) {
//            txtCustomerPostcode.setBackground(Color.red);
//            JOptionPane.showMessageDialog(null, "Postcode can not be empty.");
//            return;
//        }else if(!isNumeric(txtCustomerPostcode.getText())){
//            txtCustomerPostcode.setBackground(Color.red);
//            JOptionPane.showMessageDialog(null, "Postcode must be numeric value.");
//            return;
//        }else{
//            txtCustomerPostcode.setBackground(Color.white);
//        }
//        
//        Customer customer = new Customer();
//        customer.setFirstName(txtCustomerName.getText());
//        customer.setLastName(txtCustomerSurname.getText());
//        //TO DO
//        try{
//            Response response = customerClient.createCustomer(customer);
//        }catch(Exception ex) {
//            log.log(Level.SEVERE, ex.getStackTrace().toString());
//            JOptionPane.showMessageDialog(null, "Unexpected error occurred while creating customer! \n\n" + ex.getMessage(), "Unexpected Error", JOptionPane.ERROR_MESSAGE);
//        }
//    }
//    
//    private boolean isNumeric(String str) {
//        try{
//            int i = Integer.parseInt(str);
//        }catch(NumberFormatException nfe){
//            return false;
//        }
//        return true;
//    }
//}
