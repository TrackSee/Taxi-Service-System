package logic;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;

import entity.Address;
import entity.Role;
import entity.TaxiOrder;
import entity.User;

/**
 * @author Sasha Avlasov
 * Session Bean implementation class OrderRegistrator
 */
@Singleton
@LocalBean
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

}
