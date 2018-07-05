package data.clinet.manager;

import data.clinet.entity.Client;
import data.common.manager.BaseDao;

public interface ClientManager extends BaseDao {
	
	/**
	 * 客户信息
	 * @param client
	 */
	public void addClient(Client client);

}
