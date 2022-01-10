import java.util.*;

/**
 * Main
 */
public class Main {

  public static void main(String[] args) {
    ArrayList<Manufacturer> manufacturerList;
    manufacturerList = new ArrayList<Manufacturer>();
    ArrayList<Customer> customerList;
    customerList = new ArrayList<Customer>();
    ArrayList<Product> productList;
    productList = new ArrayList<Product>();
    ArrayList<ShopsNWarehouses> shopsList;
    shopsList = new ArrayList<ShopsNWarehouses>();
    ArrayList<DeliveryAgent> delvAgentList;
    delvAgentList = new ArrayList<DeliveryAgent>();
    Scanner scannerObj = new Scanner(System.in);
    ArrayList<Customer> orderList;
    orderList = new ArrayList<Customer>();
    System.out.println("\n-----Java-----");
    System.out.println("Welcome!!");

    int choice = 0;
    int id, nCopies;

    do {
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

      choice = scannerObj.nextInt();
      switch (choice) {
        case 1:
          System.out.println("\nChoice-1: Create a Manufacturer");
          System.out.println("Choice-2: Create a Customer");
          System.out.println("Choice-3: Create a Product");
          System.out.println("Choice-4: Create a Shop or Warehouse");
          System.out.println("Choice-5: Create a Delivery Agent\n");
          System.out.println("Please Enter Choice Number...");
          choice = scannerObj.nextInt();
          switch (choice) {
            case 1:
              Manufacturer newManufacturer;
              newManufacturer = new Manufacturer();
              createManufacturer(newManufacturer, scannerObj);
              manufacturerList.add(newManufacturer);
              System.out.println("\nManufacturer Added Successfully!!\n");
              break;

            case 2:
              Customer newCustomer;
              newCustomer = new Customer();
              createCustomer(newCustomer, scannerObj);
              customerList.add(newCustomer);
              System.out.println("\nCustomer Added Successfully!!\n");
              break;

            case 3:
              Product newProduct;
              newProduct = new Product();
              createProduct(newProduct, scannerObj);
              productList.add(newProduct);
              System.out.println("\nProduct Added Successfully!!\n");
              addProdToMf(newProduct, manufacturerList, newProduct.manufacturer);
              break;

            case 4:
              ShopsNWarehouses newShop;
              newShop = new ShopsNWarehouses();
              createShop(newShop, scannerObj);
              shopsList.add(newShop);
              System.out.println("\nShop/Warehouse Added Successfully!!\n");
              break;

            case 5:
              DeliveryAgent newDeliveryAgent;
              newDeliveryAgent = new DeliveryAgent();
              createDelivery(newDeliveryAgent, scannerObj);
              delvAgentList.add(newDeliveryAgent);
              System.out.println("\nDelivery Agent Added Successfully!!\n");
              break;

            default:
              break;
          }
          break;

        case 2:
          System.out.println("\nChoice-1: Delete a Manufacturer");
          System.out.println("Choice-2: Delete a Customer");
          System.out.println("Choice-3: Delete a Product");
          System.out.println("Choice-4: Delete a Shop/Warehouse");
          System.out.println("Choice-5: Delete a Delivery Agent\n");
          System.out.println("Please Enter Choice Number...");
          choice = scannerObj.nextInt();
          switch (choice) {
            case 1:
              System.out.println("\nChoice-1: Search by ID");
              System.out.println("Choice-2: Search by Name");
              System.out.println("Please Enter Choice Number...");
              choice = scannerObj.nextInt();
              switch (choice) {
                case 1:
                  System.out.println("\nEnter the Manufacturer Id: ");
                  id = scannerObj.nextInt();
                  int out = searchMf(manufacturerList, id);
                  if (out != -1) {
                    for (int i = 0; i < manufacturerList.get(out).product_list.size(); i++) {
                      productList.remove(searchProd(productList, manufacturerList.get(out).product_list.get(i).id));
                    }
                    manufacturerList.remove(out);
                    System.out.println("Manufacture Deleted Successfully!!!");
                  } else {
                    System.out.println("Please Enter a correct Manufacturer ID");
                  }
                  break;
                case 2:
                  String name;
                  scannerObj.nextLine();
                  System.out.println("Enter the Manufacturer Name: ");
                  name = scannerObj.nextLine();
                  out = searchMf(manufacturerList, name);
                  if (out != -1) {
                    for (int i = 0; i < manufacturerList.get(out).product_list.size(); i++) {
                      productList.remove(searchProd(productList, manufacturerList.get(out).product_list.get(i).id));
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

            case 2:
              System.out.println("\nChoice-1: Search by ID");
              System.out.println("Choice-2: Search by Name");
              System.out.println("Please Enter Choice Number...");
              choice = scannerObj.nextInt();
              switch (choice) {
                case 1:
                  System.out.println("\nEnter the Customer Id: ");
                  id = scannerObj.nextInt();
                  int out = searchCust(customerList, id);
                  if (out != -1) {
                    customerList.remove(out);
                    System.out.println("Customer Deleted Successfully!!!");
                  } else {
                    System.out.println("Please Enter a correct Customer ID");
                  }
                  break;
                case 2:
                  String name;
                  scannerObj.nextLine();
                  System.out.println("Enter the Customer Name: ");
                  name = scannerObj.nextLine();
                  out = searchCust(customerList, name);
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

            case 3:
              System.out.println("\nChoice-1: Search by ID");
              System.out.println("Choice-2: Search by Name");
              System.out.println("Please Enter Choice Number...");
              choice = scannerObj.nextInt();
              switch (choice) {
                case 1:
                  System.out.println("\nEnter the Product Id: ");
                  id = scannerObj.nextInt();
                  int out = searchProd(productList, id);
                  if (out != -1) {
                    int out1 = searchMf(manufacturerList, productList.get(out).manufacturer.id);
                    manufacturerList.get(out1).product_list
                        .remove(searchProd(manufacturerList.get(out1).product_list, id));
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
                    System.out.println("Please Enter a correct Product Id");
                  }
                  break;
                case 2:
                  String name;
                  scannerObj.nextLine();
                  System.out.println("Enter the Product Name: ");
                  name = scannerObj.nextLine();
                  out = searchProd(productList, name);
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

            case 4:
              System.out.println("\nChoice-1: Search by ID");
              System.out.println("Choice-2: Search by Name");
              System.out.println("Please Enter Choice Number...");
              choice = scannerObj.nextInt();
              switch (choice) {
                case 1:
                  System.out.println("\nEnter the Shop/Warehouse Id: ");
                  id = scannerObj.nextInt();
                  int out = searchShop(shopsList, id);
                  if (out != -1) {
                    shopsList.remove(out);
                    System.out.println("Shop/Warehouse Deleted Successfully!!!");
                  } else {
                    System.out.println("Please Enter a correct Shop/Warehouse ID");
                  }
                  break;
                case 2:
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

            case 5:
              System.out.println("\nChoice-1: Search by ID");
              System.out.println("Choice-2: Search by Name");
              System.out.println("Please Enter Choice Number...");
              choice = scannerObj.nextInt();
              switch (choice) {
                case 1:
                  System.out.println("\nEnter the Delivery Agent Id: ");
                  id = scannerObj.nextInt();
                  int out = searchDelv(delvAgentList, id);
                  if (out != -1) {
                    delvAgentList.remove(out);
                    System.out.println("Delivery Agent Deleted Successfully!!!");
                  } else {
                    System.out.println("Please Enter a correct Delivery ID");
                  }
                  break;
                case 2:
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

        case 3:
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

        case 4:
          Product newProduct;
          newProduct = new Product();
          createProduct(newProduct, scannerObj);
          productList.add(newProduct);
          System.out.println("\nProduct Added to Manufacturer Successfully!!\n");
          addProdToMf(newProduct, manufacturerList, newProduct.manufacturer);
          break;

        case 5:
          printProdList(productList);
          System.out.println(
              "Enter ID of the Desired Product, If the Product is not in the List add it using Create an Entity");
          int id_5_1, id_5_2;
          id_5_1 = scannerObj.nextInt();
          if (searchProd(productList, id_5_1) == -1) {
            System.out.println("Please Enter a correct Product ID");
            break;
          }
          System.out.println("Enter the Number of Copies");
          nCopies = scannerObj.nextInt();
          printShopsList(shopsList);
          System.out.println(
              "Enter ID of the Desired Shop/Warehouse, If the Shop/Warehouse is not in the List add it using Create an Entity");
          id_5_2 = scannerObj.nextInt();
          if (searchShop(shopsList, id_5_2) == -1) {
            System.out.println("Please Enter a correct Shop/Warehouse ID");
            break;
          }
          Tuple newTuple_5;
          newTuple_5 = new Tuple();
          newTuple_5.product = productList.get(searchProd(productList, id_5_1));
          newTuple_5.prodCount = nCopies;
          shopsList.get(searchShop(shopsList, id_5_2)).prodList.add(newTuple_5);
          break;

        case 6:
          int id_6_1, id_6_2;
          printCustList(customerList);
          System.out.println(
              "Enter ID of the Desired Customer, If the Customer is not in the List add it using Create an Entity");
          id_6_1 = scannerObj.nextInt();
          if (searchCust(customerList, id_6_1) == -1) {
            System.out.println("Please Enter a correct Customer ID");
            break;
          }
          printProdList(productList);
          System.out.println("Enter ID of the Desired Product");
          id_6_2 = scannerObj.nextInt();
          if (searchProd(productList, id_6_2) == -1) {
            System.out.println("Please Enter a correct Product ID");
            break;
          }
          System.out.println("Enter the Number of Copies");
          nCopies = scannerObj.nextInt();
          Customer newCustomer_6;
          newCustomer_6 = new Customer();
          newCustomer_6 = customerList.get(searchCust(customerList, id_6_1));
          Tuple newTuple_6;
          newTuple_6 = new Tuple();
          newTuple_6.prodCount = nCopies;
          newTuple_6.product = productList.get(searchProd(productList, id_6_2));
          newCustomer_6.product_list.add(newTuple_6);
          orderList.add(newCustomer_6);
          break;

        case 7:
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
          System.out.println("Select Order Number to be Proceed: ");
          int ordNum = scannerObj.nextInt();
          int out = searchProd(productList, orderList.get(ordNum-1).id);
          if (out != -1) {
            int out1 = searchMf(manufacturerList, productList.get(out).manufacturer.id);
            manufacturerList.get(out1).product_list
                .remove(searchProd(manufacturerList.get(out1).product_list, id));
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
            System.out.println("Please Enter a correct Product Id");
          }
          break;

        case 8:

          break;

        case 9:

          break;

        case 10:

          break;

        case 11:

          break;

        default:
          System.out.println("Wrong Choice Entered, Please Try Again Carefully");
          break;
      }
    } while (choice != 11);
    System.out.println("\nThank You!!!\n");
    scannerObj.close();
  }

  static void createManufacturer(Manufacturer input, Scanner scannerObj) {
    int id;
    String name;
    System.out.println("\nEnter the Manufacturer Id: ");
    id = scannerObj.nextInt();
    scannerObj.nextLine();
    System.out.println("Enter the Manufacturer Name: ");
    name = scannerObj.nextLine();
    input.id = id;
    input.name = name;
  }

  static void createCustomer(Customer input, Scanner scannerObj) {
    int id, zip;
    String name;
    System.out.println("\nEnter the Customer Id: ");
    id = scannerObj.nextInt();
    scannerObj.nextLine();
    System.out.println("Enter the Customer Name: ");
    name = scannerObj.nextLine();
    System.out.println("Enter the Customers Zip Code: ");
    zip = scannerObj.nextInt();
    input.id = id;
    input.name = name;
    input.zip = zip;
  }

  static void createProduct(Product input, Scanner scannerObj) {
    int id;
    String name;
    Manufacturer mfName;
    mfName = new Manufacturer();
    System.out.println("\nEnter the Product Id: ");
    id = scannerObj.nextInt();
    scannerObj.nextLine();
    System.out.println("Enter the Product Name: ");
    name = scannerObj.nextLine();
    createManufacturer(mfName, scannerObj);
    input.id = id;
    input.name = name;
    input.manufacturer = mfName;
  }

  static void createShop(ShopsNWarehouses input, Scanner scannerObj) {
    int id, zip;
    String name;
    System.out.println("\nEnter the Shop/Warehouse Id: ");
    id = scannerObj.nextInt();
    scannerObj.nextLine();
    System.out.println("Enter the Shop/Warehouse Name: ");
    name = scannerObj.nextLine();
    System.out.println("Enter the Shop/Warehouse Zip Code: ");
    zip = scannerObj.nextInt();
    input.id = id;
    input.name = name;
    input.zip = zip;
  }

  static void createDelivery(DeliveryAgent input, Scanner scannerObj) {
    int id, zip;
    String name;
    System.out.println("\nEnter the Delivery Agent Id: ");
    id = scannerObj.nextInt();
    scannerObj.nextLine();
    System.out.println("Enter the Delivery Name: ");
    name = scannerObj.nextLine();
    System.out.println("Enter the Delivery Agent Zip Code: ");
    zip = scannerObj.nextInt();
    input.id = id;
    input.name = name;
    input.zip = zip;
  }

  static void printMfList(ArrayList<Manufacturer> input) {
    System.out.println("\nList of all Manufactures:");
    int count = 1;
    for (Manufacturer i : input) {
      System.out.printf("Details of Manufacturer No.%d:\n", count++);
      System.out.printf("ID: %d\n", i.id);
      System.out.printf("Name: %s\n", i.name);
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
  }

  static void printProdList(ArrayList<Product> input) {
    System.out.println("\nList of all Products:");
    int count = 1;
    for (Product i : input) {
      System.out.printf("Details of Product No.%d:\n", count++);
      System.out.printf("ID: %d\n", i.id);
      System.out.printf("Name: %s\n", i.name);
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
  }

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

  static void addProdToMf(Product newProduct, ArrayList<Manufacturer> mflist, Manufacturer mfInput) {
    if (searchMf(mflist, mfInput.id) != -1) {
      mflist.get(searchMf(mflist, mfInput.id)).product_list.add(newProduct);
    } else {
      mfInput.product_list.add(newProduct);
      mflist.add(mfInput);
    }
  }
}