/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pa165.servicelayer.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import pa165.deliveryservice.entity.DeliveryStatus;

/**
 *
 * @author Drimal
 */
public class DeliveryDto {
    private long id;
    private String name;
    private PostmanDto postman;
    private List<GoodsDto> goods = new ArrayList<>();
    private CustomerDto customer;
    private DeliveryStatus status;

    public DeliveryDto() {
    }

    public DeliveryDto(long id, String name, PostmanDto postman, List<GoodsDto> goods, CustomerDto customer, DeliveryStatus status) {
        this.id = id;
        this.name = name;
        this.postman = postman;
        this.goods = goods;
        this.customer = customer;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PostmanDto getPostman() {
        return postman;
    }

    public void setPostman(PostmanDto postman) {
        this.postman = postman;
    }

    public List<GoodsDto> getGoods() {
        return goods;
    }

    public void setGoods(List<GoodsDto> goods) {
        this.goods = goods;
    }

    public CustomerDto getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDto customer) {
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
        int hash = 7;
        hash = 31 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 31 * hash + Objects.hashCode(this.name);
        hash = 31 * hash + Objects.hashCode(this.postman);
        hash = 31 * hash + Objects.hashCode(this.goods);
        hash = 31 * hash + Objects.hashCode(this.customer);
        hash = 31 * hash + Objects.hashCode(this.status);
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
        final DeliveryDto other = (DeliveryDto) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.postman, other.postman)) {
            return false;
        }
        if (!Objects.equals(this.goods, other.goods)) {
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
    public String toString() {
        return "Delivery ["+ id + "] " + name + ", P:" + postman.getFirstName() +" "+postman.getLastName()+ ", C:" + customer.getFirstName() +" "+customer.getLastName()+ ", G:{" + goods + "}, S:" + status;
    }
    
    
}
