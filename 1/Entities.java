import java.util.*;
// basic classes to save data associated with various enities

public class Entities {
    int id;
    String name;
}

// use of class inheritence for same attributes like name and id
class Product extends Entities{
    Manufacturer manufacturer;
}

class Manufacturer extends Entities{
    ArrayList<Product> product_list; // list of products manufactured by the manufacturer
    public Manufacturer(){
        product_list = new ArrayList<Product>();
    }
}

class Customer extends Entities{
    int zip;
    ArrayList<Tuple> product_list; // list of product ordered by customer with number of copies (order history)
    public Customer(){
        product_list = new ArrayList<Tuple>();
    }
}

class ShopsNWarehouses extends Entities{
    int zip;
    ArrayList<Tuple> prodList; // shop inventory
    public ShopsNWarehouses(){
        prodList = new ArrayList<Tuple>();
    }
}

class DeliveryAgent extends Entities{
    int zip;
    int prodDelivered; // count of product delivered
    public DeliveryAgent(){
        prodDelivered = 0;
    }
}

// tuple class to store count of products and an object of Product class
class Tuple{ 
    int prodCount;
    Product product;
}