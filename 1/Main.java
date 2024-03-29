import java.util.*; // To use inbuilt classes like scanner, array list


public class Main {

  public static void main(String[] args) {
    // Array lists to store list of various entities
    // And count variables to keep account of how many entities were entered to assign ID
    ArrayList<Manufacturer> manufacturerList;
    manufacturerList = new ArrayList<Manufacturer>();
    int mfCount = 0;

    ArrayList<Customer> customerList;
    customerList = new ArrayList<Customer>();
    int custCount = 0;

    ArrayList<Product> productList;
    productList = new ArrayList<Product>();
    int prodCount = 0;

    ArrayList<ShopsNWarehouses> shopsList;
    shopsList = new ArrayList<ShopsNWarehouses>();
    int shopCount = 0;

    ArrayList<DeliveryAgent> delvAgentList;
    delvAgentList = new ArrayList<DeliveryAgent>();
    int delvCount = 0;

    ArrayList<Customer> orderList;
    orderList = new ArrayList<Customer>();

    Scanner scannerObj = new Scanner(System.in);

    System.out.println("\n-----The Master Interface-----");
    System.out.println("Welcome!!!");

    int choice = 0;
    int id, nCopies;
    // do while loop, the loop will terminate only if 11 is entered
    do {
      // Set of Instructions to operate the Interface
      System.out.println("\nChoice-1:  Create an Entity");
      System.out.println("Choice-2:  Delete an Entity");
      System.out.println("Choice-3:  Print an Entity");
      System.out.println("Choice-4:  Add a Product to Manufacturer");
      System.out.println("Choice-5:  Add a Product to a Shop");
      System.out.println("Choice-6:  Add an Order of a Product from a Customer");
      System.out.println("Choice-7:  Process an Order");
      System.out.println("Choice-8:  List all the Purchases made by a Customer");
      System.out.println("Choice-9:  List Inventory of a Shop");
      System.out.println("Choice-10: List all the Products made by a Manufacturer");
      System.out.println("Choice-11: Close the Program\n");
      System.out.println("Please Enter Choice Number...");

      choice = scannerObj.nextInt(); // input from user for variable choice
      switch (choice) {
        case 1: // Create an Entity
          System.out.println("\nChoice-1: Create a Manufacturer");
          System.out.println("Choice-2: Create a Customer");
          System.out.println("Choice-3: Create a Product");
          System.out.println("Choice-4: Create a Shop or Warehouse");
          System.out.println("Choice-5: Create a Delivery Agent\n");
          System.out.println("Please Enter Choice Number...");
          choice = scannerObj.nextInt();
          switch (choice) {
            case 1: // Create a Manufacturer
              Manufacturer newManufacturer;
              newManufacturer = new Manufacturer(); // Initialisation of object 
              createManufacturer(newManufacturer, scannerObj); // this function will take input from user and assign attributes to object
              newManufacturer.id = 100+mfCount++; // ID generation
              manufacturerList.add(newManufacturer); // adding to array list
              System.out.printf("\nManufacturer Added Successfully!!! with ID: %d\n",newManufacturer.id);
              break;

            case 2: // Create a Customer
              Customer newCustomer;
              newCustomer = new Customer(); // Initialisation of object
              createCustomer(newCustomer, scannerObj); // this function will take input from user and assign attributes to object
              newCustomer.id = 200+custCount++; // ID generation
              customerList.add(newCustomer); // adding to array list
              System.out.printf("\nCustomer Added Successfully!!! with ID: %d\n",newCustomer.id);
              break;

            case 3: // Create a Product
              Product newProduct;
              newProduct = new Product(); // Initialisation of object
              createProduct(newProduct, scannerObj); // this function will take input from user and assign attributes to object
              newProduct.id = 300+prodCount++; // ID generation
              productList.add(newProduct); // adding to array list
              System.out.printf("\nProduct Added Successfully!!! with ID: %d\n", newProduct.id);
              break;

            case 4: // Create a Shop
              ShopsNWarehouses newShop;
              newShop = new ShopsNWarehouses(); // Initialisation of object
              createShop(newShop, scannerObj); // this function will take input from user and assign attributes to object
              newShop.id = 400+shopCount++; // ID generation
              shopsList.add(newShop); // adding to array list
              System.out.printf("\nShop/Warehouse Added Successfully!!! with ID: %d\n", newShop.id);
              break;

            case 5: // Create a Delivery Agent
              DeliveryAgent newDeliveryAgent; 
              newDeliveryAgent = new DeliveryAgent(); // Initialisation of object
              createDelivery(newDeliveryAgent, scannerObj); // this function will take input from user and assign attributes to object
              newDeliveryAgent.id = 500+delvCount++; // ID generation
              delvAgentList.add(newDeliveryAgent); // adding to array list
              System.out.printf("\nDelivery Agent Added Successfully!!! with ID: %d\n", newDeliveryAgent.id);
              break;

            default:
              break;
          }
          break;

        case 2: // Delete an Entity
          System.out.println("\nChoice-1: Delete a Manufacturer");
          System.out.println("Choice-2: Delete a Customer");
          System.out.println("Choice-3: Delete a Product");
          System.out.println("Choice-4: Delete a Shop/Warehouse");
          System.out.println("Choice-5: Delete a Delivery Agent\n");
          System.out.println("Please Enter Choice Number...");
          choice = scannerObj.nextInt();
          switch (choice) {
            case 1: // Delete a Manufacturer
              // manufacturer will be deleted it from the list of manufactures and also its association with the product 
              System.out.println("\nChoice-1: Search by ID");
              System.out.println("Choice-2: Search by Name");
              System.out.println("Please Enter Choice Number...");
              choice = scannerObj.nextInt();
              switch (choice) {
                case 1: // delete using ID
                  System.out.println("\nEnter the Manufacturer ID: ");
                  id = scannerObj.nextInt();
                  int out = searchMf(manufacturerList, id); // this function will return index of the required of manufacturer and -1 in case it is not found
                  if (out != -1) {
                    Manufacturer emptyManufacturer;
                    emptyManufacturer = new Manufacturer(); // to assign it with the product related to this manufacturer
                    for (int i = 0; i < manufacturerList.get(out).product_list.size(); i++) {
                      productList.get(searchProd(productList, manufacturerList.get(out).product_list.get(i).id)).manufacturer = emptyManufacturer;
                    }
                    manufacturerList.remove(out);
                    System.out.println("Manufacture Deleted Successfully!!!");
                  } else {
                    System.out.println("Please Enter a correct Manufacturer ID");
                  }
                  break; 
                case 2: // delete using name
                  String name;
                  scannerObj.nextLine();
                  System.out.println("Enter the Manufacturer Name: ");
                  name = scannerObj.nextLine();
                  out = searchMf(manufacturerList, name); // this function will return index of the required of manufacturer and -1 in case it is not found
                  if (out != -1) {
                    Manufacturer emptyManufacturer;
                    emptyManufacturer = new Manufacturer(); // to assign it with the product related to this manufacturer
                    for (int i = 0; i < manufacturerList.get(out).product_list.size(); i++) {
                      productList.get(searchProd(productList, manufacturerList.get(out).product_list.get(i).id)).manufacturer = emptyManufacturer;
                    }
                    manufacturerList.remove(out);
                    System.out.println("Manufacture Deleted Successfully!!!");
                  } else {
                    System.out.println("Please Enter a correct Manufacturer Name");
                  }
                  break;
                default: 
                  break;
              }
              break;

            case 2: // Delete a Customer
              // Customer and its purchase history will be deleted from the customer list
              System.out.println("\nChoice-1: Search by ID");
              System.out.println("Choice-2: Search by Name");
              System.out.println("Please Enter Choice Number...");
              choice = scannerObj.nextInt();
              switch (choice) {
                case 1: // delete using ID
                  System.out.println("\nEnter the Customer ID: ");
                  id = scannerObj.nextInt();
                  int out = searchCust(customerList, id); // this function will return index of the required of customer and -1 in case it is not found
                  if (out != -1) {
                    customerList.remove(out);
                    System.out.println("Customer Deleted Successfully!!!");
                  } else {
                    System.out.println("Please Enter a correct Customer ID");
                  }
                  break;
                case 2: // delete using name
                  String name;
                  scannerObj.nextLine();
                  System.out.println("Enter the Customer Name: ");
                  name = scannerObj.nextLine();
                  out = searchCust(customerList, name); // this function will return index of the required of customer and -1 in case it is not found
                  if (out != -1) {
                    customerList.remove(out);
                    System.out.println("Customer Deleted Successfully!!!");
                  } else {
                    System.out.println("Please Enter a correct Customer Name");
                  }
                  break;
                default:
                  break;
              }
              break;

            case 3: // Delete a Product
              // product will be deleted from product list as well as from its manufacturer and all shop's inventories
              System.out.println("\nChoice-1: Search by ID");
              System.out.println("Choice-2: Search by Name");
              System.out.println("Please Enter Choice Number...");
              choice = scannerObj.nextInt();
              switch (choice) {
                case 1: // delete using ID
                  System.out.println("\nEnter the Product ID: ");
                  id = scannerObj.nextInt();
                  int out = searchProd(productList, id);
                  if (out != -1) {
                    int out1 = searchMf(manufacturerList, productList.get(out).manufacturer.id);
                    manufacturerList.get(out1).product_list
                        .remove(searchProd(manufacturerList.get(out1).product_list, id)); // product removal from manufacturer
                    // product removal from all the shops
                    for (int i = 0; i < shopsList.size(); i++) {
                      for (int j = 0; j < shopsList.get(i).prodList.size(); j++) {
                        if (shopsList.get(i).prodList.get(j).product.id == id) {
                          shopsList.get(i).prodList.remove(j);
                        }
                      }
                    }
                    productList.remove(out);
                    System.out.println("Product Deleted Successfully!!!");
                  } else {
                    System.out.println("Please Enter a correct Product ID");
                  }
                  break;
                case 2: // delete using name
                  String name;
                  scannerObj.nextLine();
                  System.out.println("Enter the Product Name: ");
                  name = scannerObj.nextLine();
                  out = searchProd(productList, name);
                  // same as delete using id
                  if (out != -1) {
                    int out1 = searchMf(manufacturerList, productList.get(out).manufacturer.id);
                    manufacturerList.get(out1).product_list
                        .remove(searchProd(manufacturerList.get(out1).product_list, name));
                    for (int i = 0; i < shopsList.size(); i++) {
                      for (int j = 0; j < shopsList.get(i).prodList.size(); j++) {
                        if (shopsList.get(i).prodList.get(j).product.name.equals(name)) {
                          shopsList.get(i).prodList.remove(j);
                        }
                      }
                    }
                    productList.remove(out);
                    System.out.println("Product Deleted Successfully!!!");
                  } else {
                    System.out.println("Please Enter a correct Product Name");
                  }
                  break;
                default:
                  break;
              }
              break;

            case 4: // Delete a Shop
              // shop will be deleted with all its inventory 
              System.out.println("\nChoice-1: Search by ID");
              System.out.println("Choice-2: Search by Name");
              System.out.println("Please Enter Choice Number...");
              choice = scannerObj.nextInt();
              switch (choice) {
                case 1: // delete using ID
                  System.out.println("\nEnter the Shop/Warehouse ID: ");
                  id = scannerObj.nextInt();
                  int out = searchShop(shopsList, id);
                  if (out != -1) {
                    shopsList.remove(out);
                    System.out.println("Shop/Warehouse Deleted Successfully!!!");
                  } else {
                    System.out.println("Please Enter a correct Shop/Warehouse ID");
                  }
                  break;
                case 2: // delete using name
                  String name;
                  scannerObj.nextLine();
                  System.out.println("Enter the Shop/Warehouse Name: ");
                  name = scannerObj.nextLine();
                  out = searchShop(shopsList, name);
                  if (out != -1) {
                    shopsList.remove(out);
                    System.out.println("Customer Deleted Successfully!!!");
                  } else {
                    System.out.println("Please Enter a correct Customer Name");
                  }
                  break;
                default:
                  break;
              }
              break;

            case 5: // Delete a Delivery Agent
              // agent will be deleted
              System.out.println("\nChoice-1: Search by ID");
              System.out.println("Choice-2: Search by Name");
              System.out.println("Please Enter Choice Number...");
              choice = scannerObj.nextInt();
              switch (choice) {
                case 1: // delete using ID
                  System.out.println("\nEnter the Delivery Agent ID: ");
                  id = scannerObj.nextInt();
                  int out = searchDelv(delvAgentList, id);
                  if (out != -1) {
                    delvAgentList.remove(out);
                    System.out.println("Delivery Agent Deleted Successfully!!!");
                  } else {
                    System.out.println("Please Enter a correct Delivery ID");
                  }
                  break;
                case 2: // delete using name
                  String name;
                  scannerObj.nextLine();
                  System.out.println("Enter the Delivery Agent Name: ");
                  name = scannerObj.nextLine();
                  out = searchDelv(delvAgentList, name);
                  if (out != -1) {
                    delvAgentList.remove(out);
                    System.out.println("Delivery Agent Deleted Successfully!!!");
                  } else {
                    System.out.println("Please Enter a correct Delivery Agent Name");
                  }
                  break;
                default:
                  break;
              }
              break;

            default:
              break;
          }
          break;

        case 3: // dispaly a entity
          // all lists of entities are iterated and properties like name, id, zip are printed
          // functions are written for reusibility
          System.out.println("\nChoice-1: Display List of Manufacturers");
          System.out.println("Choice-2: Display List of Customers");
          System.out.println("Choice-3: Display List of Products");
          System.out.println("Choice-4: Display List of Shops and Warehouses");
          System.out.println("Choice-5: Display List of Delivery Agents\n");
          System.out.println("Please Enter Choice Number...");
          choice = scannerObj.nextInt();
          switch (choice) {
            case 1:
              printMfList(manufacturerList);
              break;

            case 2:
              printCustList(customerList);
              break;

            case 3:
              printProdList(productList);
              break;

            case 4:
              printShopsList(shopsList);
              break;

            case 5:
              printAgentList(delvAgentList);
              break;

            default:
              break;
          }
          break;

        case 4: // addition of  product to manufacturer
          // id_4_1 and ind_4_1 are variables to store id and index wherever required
          int id_4_1,id_4_2;
          int ind_4_1, ind_4_2;
          printProdList(productList); // to show available options
          System.out.println("Enter ID of the Desired Product, If the Product is not in the List Enter -1 & add it using Create an Entity"); // as no id can have -1 value
          id_4_1 = scannerObj.nextInt();
          ind_4_1 = searchProd(productList,id_4_1); // function will return index if the product id is present or will return -1
          if(ind_4_1!=-1){
            printMfList(manufacturerList); // to show available manufacturers
            System.out.println("Enter ID of the Desired Manufacturer, If the Manufacturer is not in the List Enter -1 & add it using Create an Entity"); // as no id can have -1 value
            id_4_2 = scannerObj.nextInt();
            ind_4_2 = searchMf(manufacturerList, id_4_2); // function will return index if manufacturer id is present or will return -1
            if(ind_4_2!=-1){
              manufacturerList.get(ind_4_2).product_list.add(productList.get(ind_4_1)); // adding product to manufacturer
              productList.get(ind_4_1).manufacturer = manufacturerList.get(ind_4_2); // adding manufacturer to the product
              System.out.println("\nProduct Added to the Manufacturer Successfully!!!");
            }
            else{
              System.out.println("Please Enter a correct Manufacturer ID");
              break;
            }
          }else{
            System.out.println("Please Enter a correct Product ID");
            break;
          }
          break;

        case 5: // addition of product to shop
          printProdList(productList); // display available products
          System.out.println(
              "Enter ID of the Desired Product, If the Product is not in the List Enter -1 & add it using Create an Entity"); // as no id can have -1 value 
          int id_5_1, id_5_2, ind_5_1, ind_5_2;
          id_5_1 = scannerObj.nextInt();
          // checking for if entered id is present or not
          if (searchProd(productList, id_5_1) == -1) {
            System.out.println("Please Enter a correct Product ID");
            break;
          }
          System.out.println("Enter the Number of Copies");
          nCopies = scannerObj.nextInt();
          printShopsList(shopsList); // display available shops
          System.out.println(
              "Enter ID of the Desired Shop/Warehouse, If the Shop/Warehouse is not in the List Enter -1 & add it using Create an Entity"); // as no id can have -1 value
          id_5_2 = scannerObj.nextInt();
          ind_5_1 = searchShop(shopsList, id_5_2); // search for shop with given id
          if (ind_5_1 == -1) {
            System.out.println("Please Enter a correct Shop/Warehouse ID");
            break;
          }
          ind_5_2 = searchTuple(shopsList.get(ind_5_1).prodList, id_5_1); // check if the product is already available in the sjop
          if(ind_5_2!=-1){
            shopsList.get(ind_5_1).prodList.get(ind_5_2).prodCount+=nCopies; // if already available then just increase its count
          }else{ // if not already available then add the product to the inventory list
            Tuple newTuple_5;
            newTuple_5 = new Tuple();
            newTuple_5.product = productList.get(searchProd(productList, id_5_1));
            newTuple_5.prodCount = nCopies;
            shopsList.get(searchShop(shopsList, id_5_2)).prodList.add(newTuple_5);
          }
          System.out.println("\nProduct added Successfully!!!");
          break;

        case 6: // order from a customer
          int id_6_1, id_6_2;
          int ind_6_1;
          printCustList(customerList); // display available customers
          System.out.println(
              "Enter ID of the Desired Customer, If the Customer is not in the List Enter -1 & add it using Create an Entity"); // as no id can have -1 value
          id_6_1 = scannerObj.nextInt();
          ind_6_1 = searchCust(customerList, id_6_1); // checking for correct id
          if (ind_6_1 == -1) {
            System.out.println("Please Enter a correct Customer ID");
            break;
          }
          printProdList(productList); // diplay available products
          System.out.println("Enter ID of the Desired Product");
          id_6_2 = scannerObj.nextInt();
          if (searchProd(productList, id_6_2) == -1) {
            System.out.println("Please Enter a correct Product ID");
            break;
          }
          System.out.println("Enter the Number of Copies");
          nCopies = scannerObj.nextInt();
          Customer newCustomer_6; // customer object for adding it to order list
          newCustomer_6 = new Customer();
          newCustomer_6.id = customerList.get(ind_6_1).id;
          newCustomer_6.zip = customerList.get(ind_6_1).zip;
          newCustomer_6.name = customerList.get(ind_6_1).name;
          // tuple to add order details in the product list of customer
          Tuple newTuple_6;
          newTuple_6 = new Tuple();
          newTuple_6.prodCount = nCopies;
          newTuple_6.product = productList.get(searchProd(productList, id_6_2));
          newCustomer_6.product_list.add(newTuple_6);
          // product list with only 1 element
          orderList.add(newCustomer_6);
          System.out.println("\nOrder Added Successfully!!!");
          break;

        case 7: // order processing
          // 1.checking of pending order, 2.selecting one order, 3.checking for avaibility of shop in same zip code
          // 4.checking for avaibility of product with required quantity in the shop
          // 5.checking for avaibility of delivery agent in the customer's zip code

          // step-1 checking of pending order
          System.out.println("List of Pending Orders: "); 
          for (int i = 0; i < orderList.size(); i++) {
            System.out.printf("\nOrder No.%d:\n", i + 1);
            System.out.printf("Customer ID: %d, ", orderList.get(i).id);
            System.out.printf("Name: %s, ", orderList.get(i).name);
            System.out.printf("Zip Code: %d\n", orderList.get(i).zip);
            for (int j = 0; j < orderList.get(i).product_list.size(); j++) {
              System.out.printf("Product ID: %d, Product Name: %s, No. of Copies: %d\n",
                  orderList.get(i).product_list.get(j).product.id, orderList.get(i).product_list.get(j).product.name,
                  orderList.get(i).product_list.get(j).prodCount);
            }
          }
          if(orderList.size()==0){
            System.out.println("This List is Empty");
          }

          // step-2 selecting one order
          System.out.println("Select Order Number to be Proceed: ");
          int ordNum = scannerObj.nextInt();

          boolean shopReady = false, delvReady = false;
          int shopInd = 0, delvInd = 0, prodInd = 0;
          int minDelvery = Integer.MAX_VALUE;
          
          for (int i = 0; i < shopsList.size(); i++) {
            //step-3 checking for avaibility of shop in same zip code
            if (shopsList.get(i).zip != orderList.get(ordNum - 1).zip) {
              continue;
            }
            //step-4 checking for avaibility of product with required quantity in the shop
            for (int j = 0; j < shopsList.get(i).prodList.size(); j++) {
              // product checking
              if (shopsList.get(i).prodList.get(j).product.id == orderList.get(ordNum - 1).product_list
                  .get(0).product.id) {
                // quantity checking
                if (shopsList.get(i).prodList.get(j).prodCount >= orderList.get(ordNum - 1).product_list
                    .get(0).prodCount) {
                  shopReady = true;
                  shopInd = i; // save info about shop index 
                  prodInd = j; // save info about product index
                  break;
                }
              }
            }
            if (shopReady) {
              break;
            }
          }

          // step-5 checking for avaibility of delivery agent in the customer's zip code
          // minDelvery variable will ensure that order is given to agent with minimum deliveries
          for (int i = 0; i < delvAgentList.size(); i++) {
            if (delvAgentList.get(i).zip == orderList.get(ordNum - 1).zip
                && delvAgentList.get(i).prodDelivered < minDelvery) {
              delvReady = true;
              delvInd = i; // save index of delivery agent
              minDelvery = delvAgentList.get(i).prodDelivered;
            }
          }

          // if shop and delivery agent conditions are satisfied then go for placing order
          if (delvReady && shopReady) {
            System.out.println("\nOrder Placed Successfully!!!");
            System.out.println("Order Details:");
            System.out.printf("Customer ID: %d, ", orderList.get(ordNum - 1).id);
            System.out.printf("Name: %s, ", orderList.get(ordNum - 1).name);
            System.out.printf("Zip Code: %d\n", orderList.get(ordNum - 1).zip);
            System.out.printf("Product ID: %d, Product Name: %s, No. of Copies: %d\n",
                orderList.get(ordNum - 1).product_list.get(0).product.id,
                orderList.get(ordNum - 1).product_list.get(0).product.name,
                orderList.get(ordNum - 1).product_list.get(0).prodCount);
            System.out.printf("Delivery Agent ID: %d, Name: %s\n", delvAgentList.get(delvInd).id,
                delvAgentList.get(delvInd).name);
            System.out.printf("Shop ID: %d, Name: %s\n", shopsList.get(shopInd).id, shopsList.get(shopInd).name);
            delvAgentList.get(delvInd).prodDelivered++; // add order delivered for the agent
            customerList.get(searchCust(customerList, orderList.get(ordNum - 1).id)).product_list
                .add(orderList.get(ordNum - 1).product_list.get(0)); // add order in customer's history
            shopsList.get(shopInd).prodList.get(prodInd).prodCount -= orderList.get(ordNum - 1).product_list
                .get(0).prodCount; // update shop's inventory
            if(shopsList.get(shopInd).prodList.get(prodInd).prodCount==0){
              shopsList.get(shopInd).prodList.remove(prodInd);
            }
            orderList.remove(ordNum - 1); // remove order from the order list
          } else { // showing reasons behind order failure
            if (!shopReady) {
              System.out.println("Product Demand can't be Satisfied");
            } else {
              System.out.println("Delivery Agent not Available");
            }
          }
          break;

        case 8: // list all purchases made by customer
          // as customer purchase history is well maintained while order processing we can easily print it using traversing the list
          int id_8_1;
          printCustList(customerList); // available customers
          System.out.println(
              "Enter ID of the Desired Customer, If the Customer is not in the List Enter -1 & add it using Create an Entity");
          id_8_1 = scannerObj.nextInt();
          if (searchCust(customerList, id_8_1) == -1) {
            System.out.println("Please Enter a correct Customer ID");
            break;
          }
          System.out.println("Customer's Purchase History:");
          for (int i = 0; i < customerList.get(searchCust(customerList, id_8_1)).product_list.size(); i++) {
            System.out.printf("Order No.%d:\n", i + 1);
            System.out.printf("Product ID: %d, Name: %s\n",
                customerList.get(searchCust(customerList, id_8_1)).product_list.get(i).product.id,
                customerList.get(searchCust(customerList, id_8_1)).product_list.get(i).product.name);
            System.out.printf("Number of Copies: %d\n",
                customerList.get(searchCust(customerList, id_8_1)).product_list.get(i).prodCount);
          }
          if(customerList.get(searchCust(customerList, id_8_1)).product_list.size()==0){
            System.out.println("This List is Empty");
          }

          break;

        case 9: // list of shop inventory
          // it is also well maintained while processing order and adding product to the shop or deleting product, so to print 
          // just iteration through list is sufficient
          int id_9_1;
          printShopsList(shopsList);
          System.out.println(
              "Enter ID of the Desired Shop/Warehouse, If the Shop/Warehouse is not in the List Enter -1 & add it using Create an Entity");
          id_9_1 = scannerObj.nextInt();
          if (searchShop(shopsList, id_9_1) == -1) {
            System.out.println("Please Enter a correct Shop/Warehouse ID");
            break;
          }
          System.out.println("Inventory:");
          for (int i = 0; i < shopsList.get(searchShop(shopsList, id_9_1)).prodList.size(); i++) {
            System.out.printf("%d. Product ID: %d, Name: %s\n",i+1,
                shopsList.get(searchShop(shopsList, id_9_1)).prodList.get(i).product.id,
                shopsList.get(searchShop(shopsList, id_9_1)).prodList.get(i).product.name);
            System.out.printf("   Number of Copies: %d\n",
            shopsList.get(searchShop(shopsList, id_9_1)).prodList.get(i).prodCount);
          }
          if(shopsList.get(searchShop(shopsList, id_9_1)).prodList.size()==0){
            System.out.println("This List is Empty");
          }
          break;

        case 10: // products made by manufacturer
          // maintained while adding product to manufacturer and deleting product
          // iteration through list is sufficient
          int id_10;
          printMfList(manufacturerList);
          System.out.println(
              "Enter ID of the Desired Manufacturer, If the Manufacturer is not in the List Enter -1 & add it using Create an Entity");
          id_10 = scannerObj.nextInt();
          if (searchMf(manufacturerList, id_10) == -1) {
            System.out.println("Please Enter a correct Manufacturer ID");
            break;
          }
          System.out.println("List of All Products:");
          for (int i = 0; i < manufacturerList.get(searchMf(manufacturerList, id_10)).product_list.size(); i++) {
            System.out.printf("%d. Product ID: %d, Name: %s\n",i+1,
                manufacturerList.get(searchMf(manufacturerList, id_10)).product_list.get(i).id,
                manufacturerList.get(searchMf(manufacturerList, id_10)).product_list.get(i).name);
          }
          if(manufacturerList.get(searchMf(manufacturerList, id_10)).product_list.size()==0){
            System.out.println("This List is Empty");
          }
          break;

        case 11: // exit from the program
          break;

        default: // in case of wrong input
          System.out.println("Wrong Choice Entered, Please Try Again Carefully");
          break;
      }
    } while (choice != 11);
    System.out.println("\nThank You!!!\n");
    scannerObj.close();
  }

  // create functions for taking input and saving them in new entities

  static void createManufacturer(Manufacturer input, Scanner scannerObj) {
    String name;
    scannerObj.nextLine();
    System.out.println("Enter the Manufacturer Name: ");
    name = scannerObj.nextLine();
    input.name = name;
  }

  static void createCustomer(Customer input, Scanner scannerObj) {
    int zip;
    String name;
    scannerObj.nextLine();
    System.out.println("Enter the Customer Name: ");
    name = scannerObj.nextLine();
    System.out.println("Enter the Customers Zip Code: ");
    zip = scannerObj.nextInt();
    input.name = name;
    input.zip = zip;
  }

  static void createProduct(Product input, Scanner scannerObj) {
    String name;
    scannerObj.nextLine();
    System.out.println("Enter the Product Name: ");
    name = scannerObj.nextLine();
    input.name = name;
  }

  static void createShop(ShopsNWarehouses input, Scanner scannerObj) {
    int zip;
    String name;
    scannerObj.nextLine();
    System.out.println("Enter the Shop/Warehouse Name: ");
    name = scannerObj.nextLine();
    System.out.println("Enter the Shop/Warehouse Zip Code: ");
    zip = scannerObj.nextInt();
    input.name = name;
    input.zip = zip;
  }

  static void createDelivery(DeliveryAgent input, Scanner scannerObj) {
    int zip;
    String name;
    scannerObj.nextLine();
    System.out.println("Enter the Delivery Name: ");
    name = scannerObj.nextLine();
    System.out.println("Enter the Delivery Agent Zip Code: ");
    zip = scannerObj.nextInt();
    input.name = name;
    input.zip = zip;
  }

  // printing functions for iterating through array and printing information
  static void printMfList(ArrayList<Manufacturer> input) {
    System.out.println("\nList of all Manufactures:");
    int count = 1;
    for (Manufacturer i : input) {
      System.out.printf("Details of Manufacturer No.%d:\n", count++);
      System.out.printf("ID: %d\n", i.id);
      System.out.printf("Name: %s\n", i.name);
    }
    if(input.size()==0){
      System.out.println("This List is Empty");
    }
  }

  static void printCustList(ArrayList<Customer> input) {
    System.out.println("\nList of all Customers:");
    int count = 1;
    for (Customer i : input) {
      System.out.printf("Details of Customer No.%d:\n", count++);
      System.out.printf("ID: %d\n", i.id);
      System.out.printf("Name: %s\n", i.name);
      System.out.printf("Zip Code: %d\n", i.zip);
    }
    if(input.size()==0){
      System.out.println("This List is Empty");
    }
  }

  static void printProdList(ArrayList<Product> input) {
    System.out.println("\nList of all Products:");
    int count = 1;
    for (Product i : input) {
      System.out.printf("Details of Product No.%d:\n", count++);
      System.out.printf("ID: %d\n", i.id);
      System.out.printf("Name: %s\n", i.name);
    }
    if(input.size()==0){
      System.out.println("This List is Empty");
    }
  }

  static void printShopsList(ArrayList<ShopsNWarehouses> input) {
    System.out.println("\nList of all Shops/Warehouses:");
    int count = 1;
    for (ShopsNWarehouses i : input) {
      System.out.printf("Details of Shop/Warehouse No.%d:\n", count++);
      System.out.printf("ID: %d\n", i.id);
      System.out.printf("Name: %s\n", i.name);
      System.out.printf("Zip Code: %d\n", i.zip);
    }
    if(input.size()==0){
      System.out.println("This List is Empty");
    }
  }

  static void printAgentList(ArrayList<DeliveryAgent> input) {
    System.out.println("\nList of all Delivery Agents:");
    int count = 1;
    for (DeliveryAgent i : input) {
      System.out.printf("Details of Delivery Agent No.%d:\n", count++);
      System.out.printf("ID: %d\n", i.id);
      System.out.printf("Name: %s\n", i.name);
      System.out.printf("Zip Code: %d\n", i.zip);
      System.out.printf("Products Deliverd: %d\n", i.prodDelivered);
    }
    if(input.size()==0){
      System.out.println("This List is Empty");
    }
  }

  // searching functions to iterate through lists and returning index corresponding to id or name
  static int searchMf(ArrayList<Manufacturer> input, int id) {
    for (int i = 0; i < input.size(); i++) {
      if (id == input.get(i).id) {
        return i;
      }
    }
    return -1;
  }

  static int searchMf(ArrayList<Manufacturer> input, String name) {
    for (int i = 0; i < input.size(); i++) {
      if (name.equals(input.get(i).name)) {
        return i;
      }
    }
    return -1;
  }

  static int searchCust(ArrayList<Customer> input, int id) {
    for (int i = 0; i < input.size(); i++) {
      if (id == input.get(i).id) {
        return i;
      }
    }
    return -1;
  }

  static int searchCust(ArrayList<Customer> input, String name) {
    for (int i = 0; i < input.size(); i++) {
      if (name.equals(input.get(i).name)) {
        return i;
      }
    }
    return -1;
  }

  static int searchProd(ArrayList<Product> input, int id) {
    for (int i = 0; i < input.size(); i++) {
      if (id == input.get(i).id) {
        return i;
      }
    }
    return -1;
  }

  static int searchProd(ArrayList<Product> input, String name) {
    for (int i = 0; i < input.size(); i++) {
      if (name.equals(input.get(i).name)) {
        return i;
      }
    }
    return -1;
  }

  static int searchShop(ArrayList<ShopsNWarehouses> input, int id) {
    for (int i = 0; i < input.size(); i++) {
      if (id == input.get(i).id) {
        return i;
      }
    }
    return -1;
  }

  static int searchShop(ArrayList<ShopsNWarehouses> input, String name) {
    for (int i = 0; i < input.size(); i++) {
      if (name.equals(input.get(i).name)) {
        return i;
      }
    }
    return -1;
  }

  static int searchDelv(ArrayList<DeliveryAgent> input, int id) {
    for (int i = 0; i < input.size(); i++) {
      if (id == input.get(i).id) {
        return i;
      }
    }
    return -1;
  }

  static int searchDelv(ArrayList<DeliveryAgent> input, String name) {
    for (int i = 0; i < input.size(); i++) {
      if (name.equals(input.get(i).name)) {
        return i;
      }
    }
    return -1;
  }

  static int searchTuple(ArrayList<Tuple> input, int id){
    for(int i=0; i<input.size();i++){
      if(input.get(i).product.id==id){
        return i;
      }
    }
    return -1;
  }

  // used while checking manufacturer already exists or not while adding product to manufacturer
  static int addProdToMf(Product newProduct, ArrayList<Manufacturer> mflist, Manufacturer mfInput) {
    if (searchMf(mflist, mfInput.id) != -1) {
      mflist.get(searchMf(mflist, mfInput.id)).product_list.add(newProduct);
    } else {
      System.out.println("Manufacturer doesn't exist, Add it using Create an Entity");
    }
    return searchMf(mflist, mfInput.id);
  }
}