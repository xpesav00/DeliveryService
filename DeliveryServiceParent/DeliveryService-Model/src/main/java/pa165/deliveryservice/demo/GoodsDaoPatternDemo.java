package pa165.deliveryservice.demo;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import pa165.deliveryservice.daoImplementation.GoodsDaoImpl;
import pa165.deliveryservice.daoInterface.GoodsDao;
import pa165.deliveryservice.entity.Goods;

/**
 * Demo class for goodsDaoImplementation
 *
 * @author Martin Drimal
 */
public class GoodsDaoPatternDemo {
    
    public static final void main(String[] args) throws SQLException{
 
        GoodsDao goodsDaoInterface = new GoodsDaoImpl();
        
        Goods goods1 = new Goods();
        goods1.setPrice(100);
        goods1.setSeller("CZC.cz");
        
        Goods goods2 = new Goods();
        goods2.setPrice(450);
        goods2.setSeller("Alfacomp.cz");
        
        Goods goods3 = new Goods();
        goods3.setPrice(12000);
        goods3.setSeller("Electroworld");
        
        System.out.println("Add  "+goods1+" to database.");
        goodsDaoInterface.addGoods(goods1);
        
        printAllGoods(goodsDaoInterface.getAllGoods());
        
        System.out.println("Add  "+goods2+" to database.");
        goodsDaoInterface.addGoods(goods2);
        System.out.println("Add  "+goods3+" to database.");
        goodsDaoInterface.addGoods(goods3);
        
        printAllGoods(goodsDaoInterface.getAllGoods());
       
        System.out.println("Get all goods from database.");
        List<Goods> goods = goodsDaoInterface.getAllGoods();
        
        
        System.out.println("Update "+goods2+" in database.");
        goods2.setPrice(350);
        goodsDaoInterface.updateGoods(goods2);
        
        printAllGoods(goodsDaoInterface.getAllGoods());
        
        System.out.println("Delete "+goods1+" from database.");
        goodsDaoInterface.deleteGoods(goods1);
        
        printAllGoods(goodsDaoInterface.getAllGoods());
        
        System.out.println("Delete "+goods2+" from database.");
        goodsDaoInterface.deleteGoods(goods2);
        
        printAllGoods(goodsDaoInterface.getAllGoods());
        
        System.out.println("Delete "+goods3+" from database.");
        goodsDaoInterface.deleteGoods(goods3);
        
        printAllGoods(goodsDaoInterface.getAllGoods());
        
        System.out.println("Delete "+goods3+" from database.");
        
        goodsDaoInterface.deleteGoods(goods3);
        
        
        printAllGoods(goodsDaoInterface.getAllGoods());
   
        goodsDaoInterface.closeConnection();
    }
    
    public static void printAllGoods(List<Goods> goods){
        System.out.println("**************************");
        System.out.println("        LIST GOODS        ");
        for (Iterator<Goods> it = goods.iterator(); it.hasNext();) {
            Goods g = it.next();
            System.out.println(g);
        }
    }
}
