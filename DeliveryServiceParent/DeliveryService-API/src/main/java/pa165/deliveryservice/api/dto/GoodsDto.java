package pa165.deliveryservice.api.dto;

import java.util.Objects;

/**
 * DTO for goods entity
 *
 * @author Drimal
 */
public class GoodsDto  implements Cloneable{
    private long id;
    private long price;
    private String seller;
    private DeliveryDto delivery;

    public GoodsDto(){}
    
    public GoodsDto(long id, long price, String seller, DeliveryDto delivery) {
        this.id = id;
        this.price = price;
        this.seller = seller;
        this.delivery = delivery;
    }
    

    public long getId() {
        return id;
    }

    public void setId(long Id) {
        this.id = Id;
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

    public DeliveryDto getDelivery() {
        return delivery;
    }

    public void setDelivery(DeliveryDto delivery) {
        this.delivery = delivery;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass())  return false;

        GoodsDto goods = (GoodsDto) obj;
        
        if (this.id != goods.id)  return false;
        if (this.price != goods.price) return false;
        if (this.seller != null ? !seller.equals(goods.seller) : goods.seller != null) return false;
        if (this.delivery != null ? !delivery.equals(goods.delivery) :goods.delivery != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 97 * hash + (int) (this.price ^ (this.price >>> 32));
        hash = 97 * hash + Objects.hashCode(this.seller);
        hash = 97 * hash + Objects.hashCode(this.delivery);
        return hash;
    }
    
    @Override
    public String toString(){
        return "Goods: [id: "+getId()+", price: "+getPrice()+", seller: "+getSeller()+"]";
    }
}
