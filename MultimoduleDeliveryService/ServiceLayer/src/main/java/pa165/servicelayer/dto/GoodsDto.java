/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pa165.servicelayer.dto;



/**
 * DTO for goods entity
 *
 * @author Drimal
 */
public class GoodsDto {
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
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + (int) (this.id ^ (this.id >>> 32));
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
        final GoodsDto other = (GoodsDto) obj;

        return this.id != other.id;
    }

   

    @Override
    public String toString(){
        return "Goods: [id: "+getId()+", price: "+getPrice()+", seller: "+getSeller()+"]";
    }
}
