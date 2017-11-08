package data.clinet.manager.Impl;

import org.springframework.stereotype.Service;

import data.clinet.entity.Product;
import data.clinet.manager.ProductManager;
import data.common.manager.impl.BaseDaoImpl;


@Service(value="productManager")
public class ProductManagerImpl extends BaseDaoImpl implements ProductManager {

	@Override
	public void addProduct(Product product) {
		
		this.save(product);
	}

}