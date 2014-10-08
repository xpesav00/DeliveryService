package entity;

import javax.persistence.*;

@Entity
public class Goods {
    @Id
    @GeneratedValue
    private long Id;
    
    @Column
    private long price;
    
    @Column
    private String seller;

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

    @Override
    public String toString(){
        return "Goods: id: "+getId()+", price: "+getPrice()+", seller: "+getSeller();
    }
}
