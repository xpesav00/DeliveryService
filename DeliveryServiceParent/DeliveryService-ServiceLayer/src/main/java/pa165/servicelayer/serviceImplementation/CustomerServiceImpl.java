package pa165.servicelayer.serviceImplementation;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.dozer.Mapper;
import org.dozer.MappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pa165.deliveryservice.api.CustomerService;
import pa165.deliveryservice.api.dto.AddressDto;
import pa165.deliveryservice.api.dto.CustomerDto;
import pa165.deliveryservice.api.dto.DeliveryDto;
import pa165.deliveryservice.daoInterface.CustomerDao;
import pa165.deliveryservice.entity.Address;
import pa165.deliveryservice.entity.Customer;
import pa165.deliveryservice.entity.Delivery;

/**
 * Service layer for Customer entity.
 *
 * @author Martin Dřímal
 */
@Service("customerService")
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private Mapper mapper;

    @PostConstruct
    public void preloadDB(){
        Address address = new Address();
        address.setCity("Brno");
        address.setStreet("Ulehla");
        address.setPostcode(14689);

        Customer cust = new Customer();
        cust.setFirstName("Bedrich");
        cust.setLastName("Navratil");
        cust.setAddress(address);

        customerDao.addCustomer(cust);
    }

    @Override
    @Secured("ROLE_ADMIN")
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

    @Override//TOTO uncomment
    //@Secured({"ROLE_USER", "ROLE_ADMIN"})
    public List<CustomerDto> getAllCustomers() {
        List<CustomerDto> resultList = new ArrayList<>();
        for (Customer customer : customerDao.getAllCustomers()) {
            resultList.add(mapper.map(customer, CustomerDto.class));
        }
        return resultList;
    }

    @Override
    @Secured("ROLE_ADMIN")
    public void updateCustomer(CustomerDto customer) {
        if (customer == null) {
            throw new NullPointerException("Goods can't be null.");
        }
        Customer customerA = convertCustomerDtoToCustomer(customer);

        customerDao.updateCustomer(customerA);
    }

    @Override
    @Secured("ROLE_ADMIN")
    public void deleteCustomer(CustomerDto customerDto) {
        if (customerDto == null) {
            throw new NullPointerException("Customer can't be null.");
        }
        Customer customer = convertCustomerDtoToCustomer(customerDto);

        customerDao.deleteCustomer(customer);
    }

    @Override
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public CustomerDto findCustomer(long id) {
        if (id < 0) {
            throw new IllegalArgumentException("Id can't be negative.");
        }
        Customer customer = customerDao.getCustomer(id);
        return mapper.map(customer, CustomerDto.class);
    }

    @Override
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
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
