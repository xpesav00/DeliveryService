package pa165.deliveryservice.entity;

import java.util.List;

public class Delivery {

    private long Id;
    private String name;
    private Postman postman;
    private List<Goods> packages;
    private Customer customer;
    private DeliveryStatus status;

    public Delivery() {
    }

    public long getId() {
        return Id;
    }

    public void setId(long Id) {
        this.Id = Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Postman getPostman() {
        return postman;
    }

    public void setPostman(Postman postman) {
        this.postman = postman;
    }

    public List<Goods> getPackages() {
        return packages;
    }

    public void setPackages(List<Goods> packages) {
        this.packages = packages;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public DeliveryStatus getStatus() {
        return status;
    }

    public void setStatus(DeliveryStatus status) {
        this.status = status;
    }

}