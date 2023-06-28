package ra.service;

import ra.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductService implements IService<Product,String> {
    private List<Product> products = new ArrayList<>();
    @Override
    public List<Product> getAll() {
        return products;
    }

    @Override
    public Product findById(String id) {
        for (Product product : products
        ) {
            if (product.getProductId().equals(id)) {
                return product;
            }
        }
        return null;
    }

    @Override
    public void save(Product product) {
        if(findById(product.getProductId())==null){
            products.add(product);

        }else {
            products.set(products.indexOf(product),product);
        }
    }

    @Override
    public void delete(String deleteId) {
        if(findById(deleteId) == null){
            System.err.println("ID không tồn tại");
            return;
        }
        products.remove(findById(deleteId));
    }

}
