package logic;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Stateless;

import entity.Address;
import entity.Role;
import entity.TaxiOrder;
import entity.User;

/**
 * @author Sasha Avlasov
 * Session Bean implementation class OrderRegistrator
 */
@Stateless
@Local
public class OrderManeger implements OrderManegerLocal {

    /**
     * Default constructor. 
     */
    public OrderManeger() {
        // TODO Auto-generated constructor stub
    }
    @Override
	public boolean makeOrder(Address destination,Address origin,long phone){
    	if(!checkPhone(phone)){
    	User user = new User();
    	TaxiOrder order = new TaxiOrder();
    	user.setRole(Role.NOT_REGISER_USER);
    	user.setPhone(phone);
    	order.setCostumer(user);
    	order.setOrigin(origin);
    	order.setDestination(destination);
    	if(checkBlackList(phone)){
    		order.incruasePrice();
    	}
    	return true;
    	}
    	else return false;
    }
    private static boolean checkPhone(long phone){
    	/**
    	 * check phone in DB if exist 
    	 * return true;
    	 */
    	return false;
    }
    private static boolean checkBlackList(long phone){
    	/**
    	 * Check phone in black list 
    	 * if find return true;
    	 * 
    	 */
    	return false;
    }

}
