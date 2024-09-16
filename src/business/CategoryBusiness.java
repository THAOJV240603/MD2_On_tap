package business;

import enity.Category;
import util.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoryBusiness {

    // Lấy về danh sách
    public static List<Category> getAll(){
        List<Category> categories = new ArrayList<>();
        Connection connection = null;
        try {
            // b1 Tạo đối tượng kết nối
            connection = ConnectionDB.openConnection();
            // b2 viết câu lệnh truy vấn
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM category");
            // b3 thực thi
            ResultSet resultSet = statement.executeQuery();
            // b4 duyêt dữ liệu từ resulSet và gán vào categories
            while (resultSet.next()){
                // duyệt từng dòng dữ liệu trong resulSet
                Category category = new Category();
                category.setId(resultSet.getInt("id"));
                category.setName(resultSet.getString("name"));
                category.setStatus(resultSet.getBoolean("status"));
                categories.add(category);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection);
        }

        return categories;
    }

    // tìm về bản ghi theo id
    public static Category findById(int id){
        Category category = null;
        Connection connection = null;
        try {
            connection = ConnectionDB.openConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM category WHERE id = ?");
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            category = new Category();
            int count = 0;
            while (resultSet.next()){
                count++;
                category.setId(resultSet.getInt("id"));
                category.setName(resultSet.getString("name"));
                category.setStatus(resultSet.getBoolean("status"));
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

        return category;
    }
    // Thêm mới
    public static boolean create(Category category){
        Connection connection = null;

        try {
            // tạo đối tượng kết nối
            connection = ConnectionDB.openConnection();
            // Tạo đối tượng PreparedStatement
            String sql = "insert into category(name,status) value(?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            // set các giá trị của tham số trong sql của prepareStatement
            statement.setString(1,category.getName());
            statement.setBoolean(2,category.isStatus());
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
    public static boolean update(Category category){
        Connection connection = null;

        try {
            // tạo đối tượng kết nối
            connection = ConnectionDB.openConnection();
            // Tạo đối tượng PreparedStatement
            String sql = "UPDATE category SET name = ?, status=? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            // set các giá trị của tham số trong sql của prepareStatement
            statement.setString(1,category.getName());
            statement.setBoolean(2,category.isStatus());
            statement.setInt(3,category.getId());
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
            String sql = "DELETE FROM category WHERE id = ?";
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

    // Phương thức tìm danh mục theo tên danh mục
    public static List<Category> searchCategoriesByName(String keyword){
        List<Category> categories = new ArrayList<>();
        Connection connection = null;
        try {
            // b1 Tạo đối tượng kết nối
            connection = ConnectionDB.openConnection();
            String sql = "SELECT * FROM category where name LIKE ? ";
            // b2 viết câu lệnh truy vấn
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,"%"+keyword+"%");
            // b3 thực thi
            ResultSet resultSet = statement.executeQuery();
            // b4 duyệt dữ liệu từ resulSet và gán vào categories
            while (resultSet.next()){
                // duyệt từng dòng dữ liệu trong resulSet
                Category category = new Category();
                category.setId(resultSet.getInt("id"));
                category.setName(resultSet.getString("name"));
                category.setStatus(resultSet.getBoolean("status"));
                categories.add(category);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection);
        }

        return categories;
    }

}