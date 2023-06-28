package ra.run;

import ra.config.InputMethods;
import ra.model.CartItem;
import ra.model.Catalog;
import ra.model.Product;
import ra.service.CartService;
import ra.service.CatalogService;
import ra.service.ProductService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BookManagement {
    private final static CatalogService catalogService = new CatalogService();
    private final static ProductService productService = new ProductService();
    private final static CartService cartService = new CartService();
    private static List<Catalog> catalogs = catalogService.getAll();
    private static List<Product> products = productService.getAll();
    private static List<CartItem> cartItems = cartService.getAll();

    public static void main(String[] args) {

        boolean loop = true;
        while (loop) {
            System.out.println("**************************BASIC-MENU**************************\n" +
                    "1. Quản lý danh mục\n" +
                    "2. Quản lý sản phẩm\n" +
                    "3. Dành cho người dùng (***)\n" +
                    "4. Thoát\n");
            System.out.println("Nhập lựa chọn: ");
            int choice = InputMethods.getInteger();
            switch (choice) {
                case 1:
                    catalogMenu();
                    break;
                case 2:
                    productMenu();
                    break;
                case 3:
                    userMenu();
                    break;
                case 4:
                    loop = false;
                    System.out.println("Thank you!");
                    System.exit(0);
                    break;
                default:
                    System.err.println(InputMethods.ERROR_ALERT);

            }
        }
    }

    // ****************************CATALOG*********************
    public static void catalogMenu() {
        while (true) {
            System.out.println("********************CATALOG-MANAGEMENT********************\n" +
                    "1. Nhập số danh mục thêm mới và nhập thông tin cho từng danh mục [5 điểm]\n" +
                    "2. Hiển thị thông tin tất cả các danh mục [5 điểm]\n" +
                    "3. Sửa tên danh mục theo mã danh mục [5 điểm]\n" +
                    "4. Xóa danh muc theo mã danh mục (lưu ý ko xóa khi có sản phẩm) [5 điểm]\n" +
                    "5. Quay lại");
            System.out.println("Nhập lựa chọn: ");
            int choice = InputMethods.getInteger();
            switch (choice) {
                case 1:
                    addNewCatalog();
                    break;
                case 2:
                    showAllCatalog();
                    break;
                case 3:
                    editCatalog();
                    break;
                case 4:
                    deleteCatalog();
                    break;
                case 5:
                    break;
                default:
                    System.err.println(InputMethods.ERROR_ALERT);
            }
            if (choice == 5) {
                break;
            }
        }
    }

    public static void addNewCatalog() {
        System.out.println("Nhập số lượng thêm mới: ");
        int sl = InputMethods.getInteger();
        for (int i = 1; i <= sl; i++) {
            System.out.println("Nhập thông tin catalog " + i);
            Catalog catalog = new Catalog();
            catalog.inputData();
            catalogService.save(catalog);
            System.out.println("Thêm mới thành công");
        }
    }

    public static void showAllCatalog() {
        if (catalogs.size() == 0) {
            System.out.println("Danh sách trống");
        }
        for (Catalog catalog : catalogs) {
            System.out.println(catalog.toString());
        }
    }

    public static void editCatalog() {
        System.out.println("Nhập ID muốn sửa");
        Integer editId = InputMethods.getInteger();
        Catalog editCatalog = catalogService.findById(editId);
        if (editId == null) {
            System.err.println("Không tìm thấy catalog ");
            return;
        }
        System.out.println(editCatalog);
        editCatalog.inputData();
        catalogService.save(editCatalog);
        System.out.println("Cập nhật thành công");
    }

    public static void deleteCatalog() {
        System.out.println("nhập ID muốn xóa ");
        int deleteId = InputMethods.getInteger();
        catalogService.delete(deleteId);
        System.out.println("Xóa thành công");
    }

    // ****************************PRODUCT*********************
    public static void productMenu() {
        while (true) {
            System.out.println("********************PRODUCT-MANAGEMENT********************\n" +
                    "1. Nhập số sản sản phẩm và nhập thông tin sản phẩm [5 điểm]\n" +
                    "2. Hiển thị thông tin các sản phẩm [5 điểm]\n" +
                    "3. Sắp xếp sản phẩm theo giá giảm dần [5 điểm]\n" +
                    "4. Xóa sản phẩm theo mã [5 điểm]\n" +
                    "5. Tìm kiếm sách theo tên sách [10 điểm]\n" +
                    "6. Thay đổi thông tin của sách theo mã sách [10 điểm]\n" +
                    "7. Quay lại");
            System.out.println("Nhập lựa chọn: ");
            int choice = InputMethods.getInteger();
            switch (choice) {
                case 1:
                    addNewProduct();
                    break;
                case 2:
                    showAllProduct();
                    break;
                case 3:
                    sortByDESPrice();
                    break;
                case 4:
                    deleteProduct();
                    break;
                case 5:
                    searchByName();
                    break;
                case 6:
                    editProduct();
                    break;
                case 7:
                    break;
                default:
                    System.err.println(InputMethods.ERROR_ALERT);
            }
            if (choice == 7) {
                break;
            }
        }
    }

    public static void addNewProduct() {
        if (catalogs.isEmpty()) {
            System.err.println("Không có catalog, thêm mới trước");
            return;
        }
        System.out.println("Nhập số lượng thêm mới: ");
        int sl = InputMethods.getInteger();
        for (int i = 1; i <= sl; i++) {
            System.out.println("Nhập thông tin product " + i);
            Product product = new Product();
            product.inputData(catalogs);
            productService.save(product);
            System.out.println("Thêm mới thành công");
        }
    }

    public static void showAllProduct() {
        if (products.size() == 0) {
            System.out.println("Danh sách trống");
        }
        for (Product product : products) {
            System.out.println(product.toString());
        }
    }

    public static void editProduct() {
        System.out.println("Nhập ID muốn sửa");
        String editId = InputMethods.getString();
        Product editProduct = productService.findById(editId);
        if (editId == null) {
            System.err.println("Không tìm thấy product ");
            return;
        }
        System.out.println(editProduct);
        editProduct.editData(catalogs);
        productService.save(editProduct);
        System.out.println("Cập nhật thành công");
    }

    public static void deleteProduct() {
        System.out.println("nhập ID muốn xóa ");
        String deleteId = InputMethods.getString();
        productService.delete(deleteId);
        System.out.println("Xóa thành công");
    }

    public static void sortByDESPrice() {
        List<Product> sortProducts = new ArrayList<>(products);
        Collections.sort(sortProducts);
        System.out.println("Danh sách sản phẩm theo giá giảm dần: " + "\n" + sortProducts);
    }

    public static void searchByName() {
        System.out.println("Nhập vào từ khóa: ");
        boolean check = true;
        String keyword = InputMethods.getString();
        for (Product product : products) {
            if (product.getProductName().toLowerCase().contains(keyword)) {
                System.out.println(product.toString());
                check = false;
            }
        }
        if (check) {
            System.err.println("Không tìm thấy sản phẩm");
        }
    }

    // ****************************USER*********************
    public static void userMenu() {
        while (true) {
            System.out.println("**************************MENU-USER**************************\n" +
                    "1. Xem danh sách sản phẩm\n" +
                    "2. Thêm vào giỏ hàng\n" +
                    "3. Xem tất cả sản phẩm giỏ hàng\n" +
                    "4. Thay đổi số lượng sản phẩm trong giỏ hàng\n" +
                    "5. Xóa 1 sản phẩm trong giỏ hàng\n" +
                    "6. Xóa toàn bộ sản phẩm trong giỏ hàng\n" +
                    "7. Quay lại");
            System.out.println("Nhập lựa chọn: ");
            int choice = InputMethods.getInteger();
            switch (choice) {
                case 1:
                    showProducts();
                    break;
                case 2:
                    addToCart();
                    break;
                case 3:
                    showCart();
                    break;
                case 4:
                    editQuantity();
                    break;
                case 5:
                    deleteOnCart();
                    break;
                case 6:
                    deleteAll();
                    break;
                case 7:
                    break;
                default:
                    System.err.println(InputMethods.ERROR_ALERT);
            }
            if (choice == 7) {
                break;
            }
        }
    }

    public static void showProducts() {
        for (Product product : products) {
            if (product.isStatus()) {
                System.out.println(product);
            }
        }
    }

    public static void addToCart() {
        System.out.println("Nhập ID sản phẩm muốn thêm vào giỏ hàng");
        String buyId = InputMethods.getProductId();
        if (productService.findById(buyId) == null || !productService.findById(buyId).isStatus()) {
            System.out.println("Không tìm thấy sản phẩm");
            return;
        }
        boolean check = true;
        for (CartItem item : cartItems) {
            if (item.getProduct() != null) {
                item.setQuantity(item.getQuantity() + 1);
                item.getProduct().setStock(item.getProduct().getStock() - 1);
                check = false;
                System.out.println("Sản phẩm đã tồn tại, đã thêm số lượng");
                break;
            }

            if (!check) {
                break;
            }
        }

            if (check){
                CartItem item = new CartItem();
                    item.setCartItemId(cartService.getNewId());
                    Product product = productService.findById(buyId);
                    item.setProduct(product);
                    System.out.println("Nhập số lượng muốn mua: ");
                    int quantity = InputMethods.getInteger();
                    if (quantity < product.getStock()) {
                        item.setQuantity(quantity);
                        product.setStock(product.getStock() - quantity);
                        cartItems.add(item);
                        System.out.println("Đã thêm vào giỏ hàng");
                    } else {
                        System.err.println("Quá số lượng cho phép");
                    }

            }
        }

    public static void editQuantity() {
        System.out.println("Nhập ID sản phẩm muốn sửa số lượng: ");
        Integer editId = InputMethods.getInteger();
        CartItem editItem = cartService.findById(editId);
        Product product = editItem.getProduct();
        System.out.println(editItem);
        System.out.println("Nhập số lượng muốn sửa: ");
        int quantity = InputMethods.getInteger();
        if (quantity < product.getStock()) {
            editItem.setQuantity(quantity);
            product.setStock(product.getStock() - quantity);
            cartItems.set(cartItems.indexOf(editItem), editItem);
        } else {
            System.err.println("Quá số lượng cho phép");
        }
    }
    public static void showCart() {
        if (cartItems.size() == 0) {
            System.out.println("Danh sách trống");
        }
        for (CartItem cartItem : cartItems) {
            System.out.println(cartItem);
        }
    }

    public static void deleteOnCart() {
        System.out.println("nhập ID muốn xóa ");
        Integer deleteId = InputMethods.getInteger();
        cartService.delete(deleteId);
        System.out.println("Xóa sản phẩm thành công");
    }

    public static void deleteAll() {
        cartItems.clear();
        System.out.println("Đã xóa toàn bộ sản phẩm");
    }
}

