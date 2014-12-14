package pa165.servicelayer.serviceImplementation;

import java.util.ArrayList;
import java.util.List;
import org.dozer.DozerBeanMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;
import pa165.deliveryservice.daoInterface.CustomerDao;
import pa165.deliveryservice.entity.Address;
import pa165.deliveryservice.entity.Customer;
import pa165.deliveryservice.entity.Delivery;
import pa165.deliveryservice.api.dto.AddressDto;
import pa165.deliveryservice.api.dto.CustomerDto;
import pa165.deliveryservice.api.dto.DeliveryDto;
import pa165.deliveryservice.api.CustomerService;

/**
 * Tests of Customer Service layer, using mock DAO layer.
 *
 * @author Martin Nekula
 */
@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceImplTest extends AbstractIntegrationTest {

    private CustomerService customerService;
    private CustomerDto customerDto;
    private Customer customer;
    private DozerBeanMapper mapper;

    @Mock
    CustomerDao dao;

    public CustomerServiceImplTest() {
    }

    @Before
    public void setUp() {
        customerService = new CustomerServiceImpl();
        mapper = new DozerBeanMapper();
        //sets Mock CustomerDao to customerService field
        ReflectionTestUtils.setField(customerService, "customerDao", dao);
        ReflectionTestUtils.setField(customerService, "mapper", mapper);

        customer = getTestCustomerInstance("");
        customer.setId(1);
        customerDto = mapper.map(customer, CustomerDto.class);

        when(dao.getCustomer(customer.getId())).thenReturn(customer);
    }

    @After
    public void tearDown() {
        customerService = null;
        customerDto = null;
        customer = null;
        mapper = null;
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddCustomer() {
        customerService.createCustomer("Jan", "Jeliman", new AddressDto(), new ArrayList<DeliveryDto>());
        verify(dao).addCustomer(customer);
    }

    @Test(expected = NullPointerException.class)
    public void testAddCustomerNullArgument() {
        customerService.createCustomer(null, null, null, null);
    }

    @Test
    public void testDeleteCustomer() {
        customerService.deleteCustomer(customerDto);
        verify(dao).deleteCustomer(customer);
    }

    @Test(expected = NullPointerException.class)
    public void testDeleteCustomerNullArgument() {
        customerService.deleteCustomer(null);
    }

    @Test
    public void testFindCustomer() {
        customerService.findCustomer(customer.getId());
        verify(dao).getCustomer(customer.getId());
    }

    @Test
    public void testUpdateCustomer() {
        customerService.updateCustomer(customerDto);
        verify(dao).updateCustomer(customer);
    }

    @Test(expected = NullPointerException.class)
    public void testUpdateCustomerNullArgument() {
        customerService.updateCustomer(null);
    }

    @Test
    public void testGetAllCustomers() {
       customerService.getAllCustomers();
        verify(dao).getAllCustomers();
    }

    public static Customer getTestCustomerInstance(String suffix) {
        String firstName = "Radoslav" + suffix;
        String lastname = "Doktorov" + suffix;
        Address address = new Address();
        address.setCity("Broumov" + suffix);
        address.setPostcode(1337);
        address.setStreet("Rusova 5" + suffix);
        List<Delivery> deliveries = new ArrayList<>();

        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastname);
        customer.setAddress(address);
        customer.setDeliveries(deliveries);

        return customer;
    }
}
