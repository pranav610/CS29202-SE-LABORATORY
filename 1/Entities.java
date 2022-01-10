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
    // public static void main(String[] args) {
    //     String name1 = "pranav";
    //     String name2;
    //     Scanner myobj = new Scanner(System.in);
    //     name2=myobj.nextLine();
    //     if(name1.equals(name2)){
    //         System.out.println(name1);
    //         System.out.println(name2);
    //         System.out.println("Oppp");
    //     }
    //     myobj.close();
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
    ArrayList<Tuple> product_list;
    public Customer(){
        product_list = new ArrayList<Tuple>();
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