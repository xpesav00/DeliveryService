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
    @OneToMany(mappedBy="delivery")
    private List<Goods> goods;
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

    public List<Goods> getGoods() {
        return goods;
    }

    public void setGoods(List<Goods> goods) {
        this.goods = goods;
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
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.Id);
        return hash;
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
        return true;
    }

    @Override
    public String toString() {
        return "Delivery["+ Id.toString() + "] " + name + ", P:" + postman.getId() + ", G:{" + goods + "}, C:" + customer.getFirstName()+" "+customer.getLastName()+ ", S:" + status;
    }
}