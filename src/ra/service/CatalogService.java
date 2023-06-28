package ra.service;

import ra.model.Catalog;
import ra.model.Product;

import java.util.ArrayList;
import java.util.List;

public class CatalogService implements IService<Catalog, Integer> {
    private List<Catalog> catalogs = new ArrayList<>();
    @Override
    public List<Catalog> getAll() {
        return catalogs;
    }

    @Override
    public Catalog findById(Integer id) {
        for (Catalog catalog : catalogs
        ) {
            if (catalog.getCatalogId() == (id)) {
                return catalog;
            }
        }
        return null;
    }

    @Override
    public void save(Catalog catalog) {
        if(findById(catalog.getCatalogId())==null){
            catalogs.add(catalog);

        }else {
            catalogs.set(catalogs.indexOf(catalog),catalog);
        }
    }

    @Override
    public void delete(Integer deleteId) {
        if(findById(deleteId) == null){
            System.err.println("ID không tồn tại");
            return;
        }
        catalogs.remove(findById(deleteId));
    }
    public void delete(List<Product> products, Integer deleteId){
        boolean check = true;
        if(findById(deleteId) == null){
            System.err.println("ID không tồn tại");
            return;

        }
        for (Product product : products) {
            if(product ==null){
                catalogs.remove(findById(deleteId));
            }  else {
                System.out.println("Không thể xóa catalog vì có product");
                break;
            }
        }

    }
}
