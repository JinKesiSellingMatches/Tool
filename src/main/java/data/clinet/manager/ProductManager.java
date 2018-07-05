package data.clinet.manager;

import data.clinet.entity.Product;
import data.common.manager.BaseDao;

public interface ProductManager extends BaseDao {
	
	
	public void addProduct(Product product);

}
