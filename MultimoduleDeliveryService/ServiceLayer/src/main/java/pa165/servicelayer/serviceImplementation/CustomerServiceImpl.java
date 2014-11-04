package pa165.servicelayer.serviceImplementation;

import java.util.List;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pa165.deliveryservice.daoImplementation.CustomerDaoImpl;
import pa165.deliveryservice.entity.Address;
import pa165.deliveryservice.entity.Customer;
import pa165.deliveryservice.entity.Delivery;
import pa165.servicelayer.dto.AddressDto;
import pa165.servicelayer.dto.CustomerDto;
import pa165.servicelayer.serviceInterface.CustomerService;

/**
 * Service layer for Customer entity.
 *
 * @author Jan Pešava
 */
@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDaoImpl customerDao;

    @Autowired
    private Mapper mapper;

    @Override
    public List<CustomerDto> getAllCustomers() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void updateCustomer(CustomerDto customer) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deleteCustomer(CustomerDto customer) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void createCustomer(String firstName, String lastname, Address address, List<Delivery> deliveries) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public CustomerDto findCustomer(long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public AddressDto getCustomerAddress(long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Id is zero or negative.");
        }
        Customer customer = customerDao.getCustomer(id);
        Address address = customer.getAddress();
       
        return mapper.map(address, AddressDto.class);        
    }
}
