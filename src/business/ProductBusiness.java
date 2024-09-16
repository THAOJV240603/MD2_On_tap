package business;

import enity.Product;
import enity.Category;
import util.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductBusiness {

    // Lấy về danh sách
    public static List<Product> getAll(){
        List<Product> products = new ArrayList<>();
        Connection connection = null;
        try {
            // b1 Tạo đối tượng kết nối
            connection = ConnectionDB.openConnection();
            // b2 viết câu lệnh truy vấn
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM product");
            // b3 thực thi
            ResultSet resultSet = statement.executeQuery();
            // b4 duyêt dữ liệu từ resulSet và gán vào products
            while (resultSet.next()){
                // duyệt từng dòng dữ liệu trong resulSet
                Product product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
                product.getPrice(resultSet.getFloat("price"));
                product.getSale_price(resultSet.getFloat("sale_price"));
                product.getImage(resultSet.getString("image"));
                product.getCategoryId(resultSet.getInt("category_id"));
                product.setStatus(resultSet.getBoolean("status"));
                products.add(product);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection);
        }

        return products;
    }

    // tìm về bản ghi theo id
    public static Product findById(int id){
        Product product = null;
        Connection connection = null;
        try {
            connection = ConnectionDB.openConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM product WHERE id = ?");
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            product = new Product();
            int count = 0;
            while (resultSet.next()){
                count++;
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
                product.getPrice(resultSet.getFloat("price"));
                product.getSale_price(resultSet.getFloat("sale_price"));
                product.getImage(resultSet.getString("image"));
                product.getCategoryId(resultSet.getInt("category_id"));
                product.setStatus(resultSet.getBoolean("status"));
            }
            System.out.println(count);
            if(count == 0){
                return null;
            }

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection);
        }

        return product;
    }
    // Thêm mới
    public static boolean create(Product product){
        Connection connection = null;

        try {
            // tạo đối tượng kết nối
            connection = ConnectionDB.openConnection();
            // Tạo đối tượng PreparedStatement
            String sql = "insert into product(name,price,sale_price,image,category_id,status) value(?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            // set các giá trị của tham số trong sql của prepareStatement
            statement.setString(1,product.getName());
            statement.setFloat(2,product.getPrice());
            statement.setFloat(3,product.getSale_price());
            statement.setString(4,product.getImage());
            statement.setInt(5,product.getCategoryId());
            statement.setBoolean(6,product.isStatus());
            //thực thi câu truy vấn
            statement.executeUpdate();
            return true;
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection);
        }

        return false;
    }

    // Sửa
    public static boolean update(Product product){
        Connection connection = null;

        try {
            // tạo đối tượng kết nối
            connection = ConnectionDB.openConnection();
            // Tạo đối tượng PreparedStatement
            String sql = "UPDATE product SET name = ?, price = ?, sale_price = ?, image = ?, category_id = ?, status=? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            // set các giá trị của tham số trong sql của prepareStatement
            statement.setString(1,product.getName());
            statement.setFloat(2,product.getPrice());
            statement.setFloat(3,product.getSale_price());
            statement.setString(4,product.getImage());
            statement.setInt(5,product.getCategoryId());
            statement.setBoolean(6,product.isStatus());
            statement.setInt(7,product.getId());

            //thực thi câu truy vấn
            statement.executeUpdate();
            return true;
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection);
        }

        return false;
    }
    // Xóa
    public static boolean delete(int id){
        Connection connection = null;
        try {
            // tạo đối tượng kết nối
            connection = ConnectionDB.openConnection();
            // Tạo đối tượng PreparedStatement
            String sql = "DELETE FROM product WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            // set các giá trị của tham số trong sql của prepareStatement
            statement.setInt(1,id);
            //thực thi câu truy vấn
            statement.executeUpdate();
            return true;
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection);
        }

        return false;
    }

    // Phương thức tìm sản phẩm theo tên sản phẩm
    public static List<Product> searchProductByName(String keyword){
        List<Product> products = new ArrayList<>();
        Connection connection = null;
        try {
            // b1 Tạo đối tượng kết nối
            connection = ConnectionDB.openConnection();
            String sql = "SELECT * FROM product where name LIKE ? ";
            // b2 viết câu lệnh truy vấn
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,"%"+keyword+"%");
            // b3 thực thi
            ResultSet resultSet = statement.executeQuery();
            // b4 duyệt dữ liệu từ resulSet và gán vào products
            while (resultSet.next()){
                // duyệt từng dòng dữ liệu trong resulSet
                Product product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
                product.setPrice(resultSet.getFloat("price"));
                product.setSale_price(resultSet.getFloat("sale_price"));
                product.setImage(resultSet.getString("image"));
                product.setCategoryId(resultSet.getInt("category_id"));
                product.setStatus(resultSet.getBoolean("status"));
                products.add(product);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection);
        }

        return products;
    }

}