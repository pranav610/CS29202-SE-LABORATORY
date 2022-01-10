import java.util.*;

/**
 * Entities
 */
public class Entities {
    int id;
    String name;

    // public Entities(int idInput, String nameInput){
    //     id = idInput;
    //     name = nameInput;
    // } 
}

class Product extends Entities{
    Manufacturer manufacturer;
    // public Product(int idInput, String nameInput, Manufacturer mfInput){
    //     super(idInput, nameInput);
    //     manufacturer = mfInput;
    // }
}

class Manufacturer extends Entities{
    ArrayList<Product> product_list;
    public Manufacturer(){
        product_list = new ArrayList<Product>();
    }
}

class Customer extends Entities{
    int zip;
    ArrayList<Product> product_list;
    public Customer(){
        product_list = new ArrayList<Product>();
    }
}

class ShopsNWarehouses extends Entities{
    int zip;
    ArrayList<Tuple> prodList;
    public ShopsNWarehouses(){
        prodList = new ArrayList<Tuple>();
    }
}

class DeliveryAgent extends Entities{
    int zip;
    int prodDelivered;
    public DeliveryAgent(){
        prodDelivered = 0;
    }
}

class Tuple{
    int prodCount;
    Product product;
}