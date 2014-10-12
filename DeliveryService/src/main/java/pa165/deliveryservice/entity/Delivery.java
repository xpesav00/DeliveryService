package pa165.deliveryservice.entity;

import java.util.List;
import java.util.Objects;
import javax.persistence.*;

/**
 * Delivery entity
 * @author Jan Šťastný
 */
@Entity
public class Delivery {
    
    public static final int NAME_LENGTH = 13;
    @Id
    @GeneratedValue
    private Long Id;    
    @Column(nullable=false,length=NAME_LENGTH)
    private String name;
    @ManyToOne
    private Postman postman;
    @OneToMany(mappedBy="deliveryId")
    private List<Goods> packages;    
    @ManyToOne
    private Customer customer;
    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;
    
    
    public Delivery() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Delivery other = (Delivery) obj;
        if (!Objects.equals(this.Id, other.Id)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.postman, other.postman)) {
            return false;
        }
        if (!Objects.equals(this.packages, other.packages)) {
            return false;
        }
        if (!Objects.equals(this.customer, other.customer)) {
            return false;
        }
        if (this.status != other.status) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.Id);
        hash = 71 * hash + Objects.hashCode(this.name);
        hash = 71 * hash + Objects.hashCode(this.postman);
        hash = 71 * hash + Objects.hashCode(this.packages);
        hash = 71 * hash + Objects.hashCode(this.customer);
        hash = 71 * hash + (this.status != null ? this.status.hashCode() : 0);
        return hash;
    }

    

    @Override
    public String toString() {
        return "Delivery["+ Id.toString() + "] " + name + ", P:" + postman + ", G:{" + packages + "}, C:" + customer + ", S:" + status;
    }
    
    

}
