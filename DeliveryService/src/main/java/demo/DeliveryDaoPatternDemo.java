package demo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pa165.deliveryservice.DaoContext;

public class DeliveryDaoPatternDemo {

    public void main() {
        new AnnotationConfigApplicationContext(DaoContext.class);
        throw new UnsupportedOperationException("The method is not implemented yet.");
    }

}
