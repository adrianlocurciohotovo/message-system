package observer;

public class Client {

    public static void main(String[] args) {
    	Order order1 = new Order("100");
    	
    	PriceObserver priceObserver = new PriceObserver();
    	
    	order1.attach(priceObserver);
    	
    	order1.addItem(50);
    	
    	
    	System.out.println(order1);
    	
    	
    	order1.addItem(179);
    	
    	
    	System.out.println("**************");
    	
    	
    	System.out.println(order1);
    }
}
