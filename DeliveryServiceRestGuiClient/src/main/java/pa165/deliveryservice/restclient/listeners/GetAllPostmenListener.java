package pa165.deliveryservice.restclient.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTable;
import pa165.deliveryservice.restclient.api.PostmanClient;

/**
 * Listener for get all postman operation
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
