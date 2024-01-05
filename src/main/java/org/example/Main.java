package org.example;

import org.example.manager.CategoryManager;
import org.example.manager.ProductManager;
import org.example.model.Category;
import org.example.model.Product;

import java.util.Scanner;

public class Main implements Commands {
    private static CategoryManager categoryManager = new CategoryManager();
    private static ProductManager productManager = new ProductManager();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean isRun = true;
        while (isRun) {
            Commands.printAllCommands();
            String command = scanner.nextLine();
            switch (command) {
                case EXIT:
                    isRun = false;
                    break;
                case ADD_CATEGORY:
                    addCategory();
                    break;
                case EDIT_CATEGORY_BY_ID:
                    editCategoryById();
                    break;
                case DELETE_CATEGORY_BY_ID:
                    deleteCategoryById();
                    break;
                case ADD_PRODUCT:
                    addProduct();
                    break;
                case EDIT_PRODUCT_BY_ID:
                    editProductById();
                    break;
                case DELETE_PRODUCT_BY_ID:
                    deleteProductById();
                    break;
                case PRINT_SUM_OF_PRODUCTS:
                    System.out.println(productManager.printSumOfProducts());
                    break;
                case PRINT_MAX_OF_PRICE_PRODUCT:
                    System.out.println(productManager.printProductWithMaxPrice());
                    break;
                case PRINT_MIN_OF_PRICE_PRODUCT:
                    System.out.println(productManager.printProductWithMinPrice());
                    break;
                case PRINT_AVG_OF_PRODUCT:
                    System.out.println(productManager.printProductAvgPrice());
                    break;
                default:
                    System.out.println("Incorrect Command please try again");
                    break;
            }

        }

    }

    private static void deleteProductById() {
        System.out.println("Please input product id for delete");
        int productId = Integer.parseInt(scanner.nextLine());
        productManager.deleteProductById(productId);
    }

    private static void editProductById() {
        System.out.println("Please input product new name");
        String productName = scanner.nextLine();
        System.out.println("Please input new description for product");
        String description = scanner.nextLine();
        System.out.println("Please input new price for product");
        double price = Double.parseDouble(scanner.nextLine());
        System.out.println("Please input new quantity of product for buy");
        int quantity = Integer.parseInt(scanner.nextLine());
        System.out.println("Please input new category id");
        categoryManager.getAllCategories();
        int categoryId = Integer.parseInt(scanner.nextLine());
        Category category = categoryManager.getCategoryById(categoryId);
        Product product = new Product(productName, description, price, quantity, category);
        productManager.updateProductById(product);
    }

    private static void addProduct() {
        System.out.println("Please input product name");
        String productName = scanner.nextLine();
        System.out.println("Please input description for product");
        String description = scanner.nextLine();
        System.out.println("Please input price for product");
        double price = Double.parseDouble(scanner.nextLine());
        System.out.println("Please input quantity of product for buy");
        int quantity = Integer.parseInt(scanner.nextLine());
        System.out.println("Please input category id");
        categoryManager.getAllCategories();
        int categoryId = Integer.parseInt(scanner.nextLine());
        Category category = categoryManager.getCategoryById(categoryId);
        Product product = new Product(productName, description, price, quantity, category);
        productManager.add(product);
    }

    private static void deleteCategoryById() {
        System.out.println("Please input category id for delete");
        int categoryId = Integer.parseInt(scanner.nextLine());
        categoryManager.deleteCategoryById(categoryId);
    }

    private static void editCategoryById() {
        System.out.println("Please input category id for update ");
        int categoryId = Integer.parseInt(scanner.nextLine());
        System.out.println("Plaese input new category name for update");
        String categoryName = scanner.nextLine();
        Category category = new Category(categoryId, categoryName);
        categoryManager.updateCategoryById(category);
    }

    private static void addCategory() {
        System.out.println("Please input name for category ");
        String categoryName = scanner.nextLine();
        Category category = new Category(categoryName);
        categoryManager.add(category);
        System.out.println("Category was created");
    }
}