package pa165.servicelayer.serviceImplementation;

import java.util.ArrayList;
import java.util.List;
import org.dozer.Mapper;
import org.dozer.MappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pa165.deliveryservice.daoImplementation.CustomerDaoImpl;
import pa165.deliveryservice.entity.Address;
import pa165.deliveryservice.entity.Customer;
import pa165.deliveryservice.entity.Delivery;
import pa165.servicelayer.dto.AddressDto;
import pa165.servicelayer.dto.CustomerDto;
import pa165.servicelayer.dto.DeliveryDto;
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
    public void createCustomer(String firstName, String lastName, AddressDto address, List<DeliveryDto> deliveries) {
        if (firstName.isEmpty()) {
            throw new IllegalArgumentException("First name can't be nempty.");
        }
        if (lastName.isEmpty()) {
            throw new IllegalArgumentException("First name can't be nempty.");
        }
        if (address == null) {
            throw new NullPointerException("Address can't be null.");
        }
        if (deliveries == null) {
            throw new NullPointerException("Deliveries can't be null.");
        }
        if (deliveries.isEmpty()) {
            throw new IllegalArgumentException("Deliveries can't be empty.");
        }
        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setAddress(mapper.map(address, Address.class));

        List<Delivery> deliveryList = new ArrayList<>();
        for (DeliveryDto deliveryDto : deliveries) {
            deliveryList.add(mapper.map(deliveryDto, Delivery.class));
        }
        customer.setDeliveries(deliveryList);

        customerDao.addCustomer(customer);
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        List<CustomerDto> resultList = new ArrayList<>();
        for (Customer customer : customerDao.getAllCustomers()) {
            resultList.add(mapper.map(customer, CustomerDto.class));
        }
        return resultList;
    }

    @Override
    public void updateCustomer(CustomerDto customer) {
        if (customer == null) {
            throw new NullPointerException("Goods can't be null.");
        }
        Customer customerA = convertCustomerDtoToCustomer(customer);

        customerDao.updateCustomer(customerA);
    }

    @Override
    public void deleteCustomer(CustomerDto customerDto) {
        if (customerDto == null) {
            throw new NullPointerException("Customer can't be null.");
        }
        Customer customer = convertCustomerDtoToCustomer(customerDto);

        customerDao.deleteCustomer(customer);
    }

    @Override
    public CustomerDto findCustomer(long id) {
        if (id < 0) {
            throw new IllegalArgumentException("Id can't be negative.");
        }
        Customer customer = customerDao.getCustomer(id);
        return mapper.map(customer, CustomerDto.class);
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

    private Customer convertCustomerDtoToCustomer(CustomerDto customer) throws MappingException {
        Customer customerA = new Customer();
        customerA.setId(customer.getId());
        customerA.setAddress(mapper.map(customer.getAddress(), Address.class));
        customerA.setFirstName(customer.getFirstName());
        customerA.setLastName(customer.getLastName());

        List<Delivery> deliveries = new ArrayList<>();
        for (DeliveryDto deliveryDto : customer.getDeliveries()) {
            deliveries.add(mapper.map(customer.getDeliveries(), Delivery.class));
        }
        return customerA;
    }
}
