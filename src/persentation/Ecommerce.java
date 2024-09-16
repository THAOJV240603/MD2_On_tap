package persentation;

import business.CategoryBusiness;
import business.ProductBusiness;
import enity.Category;
import enity.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ecommerce {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        do{
            System.out.println("==============MENU==============\n" +
                    "1. Quản lý danh mục\n" +
                    "2. Quản lý sản phẩm\n" +
                    "3. Thoát");
            System.out.println("Mời bạn chọn 1 - 3");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    boolean check = true;
                    do{
                        System.out.println("=======CATEGORY MANAGER========\n" +
                                "1. Thêm mới danh mục\n" +
                                "2. Hiển thị danh sách\n" +
                                "3. Sửa danh mục\n" +
                                "4. Xóa danh mục\n" +
                                "5. Hiển thị danh sách bao gồm số lượng sản phẩm theo danh mục \n" +
                                "6. Thoát (Trở về Menu chính)");
                        System.out.println("Mời bạn chọn 1 - 6");
                        int choice1 = Integer.parseInt(sc.nextLine());
                        switch (choice1) {
                            case 1:
                                Ecommerce.createCategory(sc);
                                break;
                            case 2:
                                Ecommerce.findAllCategory();
                                break;
                            case 3:
                                Ecommerce.updateCategory(sc);
                                break;
                            case 4:
                                Ecommerce.deleteCategory(sc);
                                break;
                            case 5:
                                break;
                            case 6:
                                check = false;
                                break;
                            default:
                                System.out.println("Sai lựa chọn");

                        }
                    }while(check);
                    break;
                case 2:
                    boolean check1 = true;
                    do{
                        System.out.println("========PRODUCT MANAGER========\n" +
                                "1. Thêm mới sản phẩm\n" +
                                "2. Hiển thị danh sách\n" +
                                "3. Sửa sản phẩm \n" +
                                "4. Xóa sản phẩm\n" +
                                "5. Tìm kiếm sản phẩm theo tên\n" +
                                "6. Thoát (Trở về Menu chính)");
                        System.out.println("Mời bạn chọn 1 - 6");
                        int choice2 = Integer.parseInt(sc.nextLine());
                        switch (choice2) {
                            case 1:
                                Ecommerce.createProduct(sc);
                                break;
                            case 2:
                                Ecommerce.findAllProduct();
                                break;
                            case 3:
                                Ecommerce.updateProduct(sc);
                                break;
                            case 4:
                                Ecommerce.deleteProduct(sc);
                                break;
                            case 5:
                                Ecommerce.searchProduct(sc);
                                break;
                            case 6:
                                check1 = false;
                        }
                    }while(check1);
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.out.println("Sai lựa chọn");
            }
        }while(true);


    }

    //////////// Danh mục//////////

    //Tạo mới danh mục
    public static void createCategory(Scanner sc){
        Category category = new Category();
        category.inputData(sc);
        CategoryBusiness.create(category);
    }

    //Danh sách danh mục
    public static void findAllCategory(){
        List<Category> categories = CategoryBusiness.getAll();
        for (Category category : categories) {
            category.displayData();
        }
    }
    //Sửa danh mục
    public static void updateCategory(Scanner sc){
        // Bước 1. Nhập Id Cần sửa
        System.out.println("Nhập vào mã danh mục cần sửa:");
        int idEdit = Integer.parseInt(sc.nextLine());
        // Hiển thị danh mục tìm được
        Category category = CategoryBusiness.findById(idEdit);
        // show danh mục
        if(category != null){
            System.out.println("Thông tin danh mục:");
            category.displayData();
            // hỏi người dùng muốn update trường nào và nhập thông tin
            boolean isExit = true;
            do {
                System.out.println("Chọn trường muốn cập nhật");
                System.out.println("1. Sửa tên danh mục");
                System.out.println("2. Sửa trạng thái");
                System.out.println("3. Thoát ");
                int choice = Integer.parseInt(sc.nextLine());
                switch (choice){
                    case 1:
                        System.out.println("Nhập vào tên mới:");
                        category.setName(sc.nextLine());
                        break;
                    case 2:
                        System.out.println("Nhập vào trạng thái:");
                        category.setStatus(Boolean.parseBoolean(sc.nextLine()));
                        break;
                    case 3:
                        isExit = false;
                        break;
                    default:
                        System.out.println("Sai lựa chọn");
                }
            }while (isExit);
            // thực cập nhật trong database gọi đến update của CategoryBusiness
            boolean result = CategoryBusiness.update(category);
            if(result){
                System.out.println("Cập nhật thành công");
            } else {
                System.err.println("Cập nhật thất bại ");
            }
        } else {
            System.out.println("Không tìm thấy danh mục");
        }

    }

    //Xóa danh mục
    public static void deleteCategory(Scanner sc){
        System.out.println("Nhập vào mã danh mục cần xóa:");
        int id = Integer.parseInt(sc.nextLine());
        if(CategoryBusiness.findById(id) != null){
            boolean result = CategoryBusiness.delete(id);
            if(result){
                System.out.println("Xóa thành công ");
            } else {
                System.err.println("Xóa thất bại");
            }
        } else {
            System.err.println("Mã danh mục không tồn tại");
        }
    }

    //Tìm kiếm danh mục theo tên danh mục
    public static void searchCategory(Scanner sc){
        System.out.println("Nhập tên danh mục cần tìm kiếm:");
        String keyword = sc.nextLine();
        List<Category> categories = CategoryBusiness.searchCategoriesByName(keyword);
        for (Category category : categories) {
            category.displayData();
        }
    }


//////////// Sản phẩm //////////

    //Thêm mới sản phẩm
    public static void createProduct(Scanner sc){
        Product product = new Product();
        product.inputData(sc);
        ProductBusiness.create(product);
    }

    //Danh sách sản phẩm
    public static void findAllProduct(){
        List<Product> products = ProductBusiness.getAll();
        for (Product product : products) {
            product.displayData();
        }
    }

    //Sửa sản phầm
    public static void updateProduct(Scanner sc){
        // Bước 1. Nhập Id Cần sửa
        System.out.println("Nhập vào mã sản phẩm cần sửa:");
        int idEdit = Integer.parseInt(sc.nextLine());
        // Hiển thị danh mục tìm được
        Product product = ProductBusiness.findById(idEdit);
        // show danh mục
        if(product != null){
            System.out.println("Thông tin danh mục:");
            product.displayData();
            // hỏi người dùng muốn update trường nào và nhập thông tin
            boolean isExit = true;
            do {
                System.out.println("Chọn trường muốn cập nhật");
                System.out.println("1. Sửa tên sản phẩm");
                System.out.println("2. Sửa giá");
                System.out.println("3. Sửa giá khuyến mãi");
                System.out.println("4. Sửa hình ảnh");
                System.out.println("5. Sửa mã danh mục");
                System.out.println("6. Sửa trạng thái");
                System.out.println("7. Thoát ");
                int choice = Integer.parseInt(sc.nextLine());
                switch (choice){
                    case 1:
                        System.out.println("Nhập vào tên mới:");
                        product.setName(sc.nextLine());
                        break;
                    case 2:
                        System.out.println("Nhập vào giá mới:");
                        product.setPrice(Float.parseFloat(sc.nextLine()));
                        break;
                    case 3:
                        System.out.println("Nhập vào giá khuyến mãi mới:");
                        product.setSale_price(Float.parseFloat(sc.nextLine()));
                        break;
                    case 4:
                        System.out.println("Nhập vào hình ảnh mới:");
                        product.setImage(sc.nextLine());
                        break;
                    case 5:
                        System.out.println("Nhập vào mã danh mục mới:");
                        product.setCategoryId(Integer.parseInt(sc.nextLine()));
                        break;
                    case 6:
                        System.out.println("Nhập vào trạng thái:");
                        product.setStatus(Boolean.parseBoolean(sc.nextLine()));
                        break;
                    case 7:
                        isExit = false;
                        break;
                    default:
                        System.out.println("Sai lựa chọn");
                }
            }while (isExit);
            // thực cập nhật trong database gọi đến update của ProductBusiness
            boolean result = ProductBusiness.update(product);
            if(result){
                System.out.println("Cập nhật thành công");
            } else {
                System.err.println("Cập nhật thất bại ");
            }
        } else {
            System.out.println("Không tìm thấy sản phẩm");
        }

    }

    //Xóa sản phẩm
    public static void deleteProduct(Scanner sc){
        System.out.println("Nhập vào mã sản phẩm cần xóa:");
        int id = Integer.parseInt(sc.nextLine());
        if(ProductBusiness.findById(id) != null){
            boolean result = ProductBusiness.delete(id);
            if(result){
                System.out.println("Xóa thành công ");
            } else {
                System.err.println("Xóa thất bại");
            }
        } else {
            System.err.println("Mã sản phẩm không tồn tại");
        }
    }

    //Tìm kiếm sản phẩm theo tên
    public static void searchProduct(Scanner sc) {
        System.out.println("Nhập tên sản phẩm cần tìm kiếm:");
        String keyword = sc.nextLine();
        List<Product> products = ProductBusiness.searchProductByName(keyword);
        for (Product product : products) {
            product.displayData();
        }
    }
}
