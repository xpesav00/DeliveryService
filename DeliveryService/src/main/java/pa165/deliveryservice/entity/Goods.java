package pa165.deliveryservice.entity;

import javax.persistence.*;

/**
 * Entity reprezents goods
 * 
 * @author Martin Drimal
 */
@Entity
public class Goods {
    @Id
    @GeneratedValue
    private long Id;
    
    @Column
    private long price;
    
    @Column(length = 20)
    private String seller;
    
    @ManyToOne
    private Delivery delivery;

    public Goods() {
    }

    public long getId() {
        return Id;
    }

    public void setId(long Id) {
        this.Id = Id;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + (int) (this.Id ^ (this.Id >>> 32));
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
        final Goods other = (Goods) obj;
        return true;
    }

    @Override
    public String toString(){
        return "Goods: [id: "+getId()+", price: "+getPrice()+", seller: "+getSeller()+"]";
    }
}