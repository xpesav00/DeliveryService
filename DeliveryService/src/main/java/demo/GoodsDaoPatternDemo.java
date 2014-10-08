package demo;

import daoImplementation.GoodsDaoImpl;
import daoInterface.GoodsDao;
import entity.Address;
import entity.Goods;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.Assert;
import pa165.deliveryservice.DaoContext;

public class GoodsDaoPatternDemo {

    public static final void main(String[] args) throws SQLException{
        new AnnotationConfigApplicationContext(DaoContext.class);
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myUnit");
             
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
        
        printAllAddresses(emf);
        
        System.out.println("Add  "+goods2+" to database.");
        goodsDaoInterface.addGoods(goods2);
        System.out.println("Add  "+goods3+" to database.");
        goodsDaoInterface.addGoods(goods3);
        printAllAddresses(emf);
       
        System.out.println("Get all goods from database.");
        List<Goods> goods = goodsDaoInterface.getAllGoods();
        for (Iterator<Goods> it = goods.iterator(); it.hasNext();) {
            Goods g = it.next();
            System.out.println(g);
        }
        
        System.out.println("Update "+goods2+" in database.");
        goods2.setPrice(350);
        goodsDaoInterface.updateGoods(goods2);
        
        printAllAddresses(emf);
        
        System.out.println("Delete "+goods1+" from database.");
        goodsDaoInterface.deleteGoods(goods1);
        
        printAllAddresses(emf);
        
        System.out.println("Delete "+goods2+" from database.");
        goodsDaoInterface.deleteGoods(goods2);
        
        
        
        System.out.println("Delete "+goods3+" from database.");
        goodsDaoInterface.deleteGoods(goods3);
        
        System.out.println("Delete "+goods3+" from database.");
        goodsDaoInterface.deleteGoods(goods3);     
               
    }
    
    public static void printAllAddresses(EntityManagerFactory emf){
		System.out.println(" ********************************");
		System.out.println("        ADDRESS LIST      ");
		EntityManager em = emf.createEntityManager();
		List<Address> pets = em.createQuery("SELECT a from Address a",Address.class).getResultList();
		
		for (Address a : pets) {
			System.out.println(a);
		}
		
		em.close();
	}
}
