package enity;

import java.util.Scanner;

public class Product {
    private int id;
    private String name;
    private float price;
    private float sale_price;
    private String image;
    private int categoryId;
    private boolean status;

    public Product() {
    }

    public Product(int id, String name, float price, float sale_price, String image, int categoryId, boolean status) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.sale_price = sale_price;
        this.image = image;
        this.categoryId = categoryId;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return this.price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getSale_price() {
        return sale_price;
    }

    public void setSale_price(float sale_price) {
        this.sale_price = sale_price;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void inputData(Scanner scanner){
        System.out.println("Nhập tên sản phẩm");
        this.name = scanner.nextLine();
        System.out.println("Nhập giá");
        this.price = Float.parseFloat(scanner.nextLine());
        System.out.println("Nhập giá khuyến mãi");
        this.sale_price = Float.parseFloat(scanner.nextLine());
        System.out.println("Nhập hình ảnh");
        this.image = scanner.nextLine();
        System.out.println("Nhập mã danh mục");
        this.categoryId = Integer.parseInt(scanner.nextLine());
        System.out.println("Nhập trạng thái");
        this.status = Boolean.parseBoolean(scanner.nextLine());
    }

    public void displayData(){
        System.out.println("Thông tin sản phẩm:");
        System.out.println("ID : " + this.id);
        System.out.println("Tên sản phẩm : " + this.name);
        System.out.println("Giá : " + this.price);
        System.out.println("Nhập giá khuyến mãi : " + this.sale_price);
        System.out.println("Hình ảnh : " + this.image);
        System.out.println("Mã danh mục : " + this.categoryId);
        System.out.println("Trạng thái : " + (this.status ? "Hoạt động" : "Không hoạt động"));
        System.out.println("\n");
    }
}
