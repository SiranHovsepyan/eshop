package org.example.manager;

import org.example.db.DbConnectionProvider;
import org.example.model.Category;
import org.example.model.Product;


import java.sql.*;

public class ProductManager {
    private Connection connection = DbConnectionProvider.getInstance().getConnection();
    CategoryManager categoryManager = new CategoryManager();

    public void add(Product product) {
        String query = "INSERT into product(name,description,price,quantity,category_id)VALUES (?,?,?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, product.getName());
            ps.setString(2, product.getDescription());
            ps.setDouble(3, product.getPrice());
            ps.setInt(4, product.getQuantity());
            ps.setInt(5, product.getCategory().getId());
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                product.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Product getProductById(int id) {
        String sql = "SELECT * FROM product WHERE id =" + id;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                double price = resultSet.getDouble("price");
                int quantity = resultSet.getInt("quantity");
                int categoryId = resultSet.getInt("category_id");
                Category category = categoryManager.getCategoryById(categoryId);
                return new Product(id, name, description, price, quantity, category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void updateProductById(Product product) {
        if (getProductById(product.getId()) == null) {
            System.out.println("Product with id = " + product.getId() + "does not exists");
            return;
        }
        String query = "UPDATE product SET name = ?, description = ?,price=?,quantity=?,category_id=? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, product.getName());
            ps.setString(2, product.getDescription());
            ps.setDouble(3, product.getPrice());
            ps.setInt(4, product.getQuantity());
            ps.setInt(5, product.getCategory().getId());
            ps.setInt(6, product.getId());
            ps.executeUpdate();
            System.out.println("Product was edited!!!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteProductById(int id) {
        if (getProductById(id) == null) {
            System.out.println("Product with id = " + id + "does not exists");
            return;
        }
        String sql = "DELETE FROM product WHERE id = " + id;
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("Product was removed!!!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int printSumOfProducts() {
        String sql = "SELECT SUM(quantity) FROM product";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                int sum = resultSet.getInt(1);
                return sum;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public double printProductWithMaxPrice() {
        String sql = "SELECT MAX(price) FROM product";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                int max = resultSet.getInt(1);
                return max;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
     return 0;
    }

    public double printProductWithMinPrice() {
        String sql = "SELECT MIN(price) FROM product";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                int min = resultSet.getInt(1);
                return min;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public double printProductAvgPrice() {
        String sql = "SELECT AVG(price) FROM product";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                int average = resultSet.getInt(1);
                return average;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
