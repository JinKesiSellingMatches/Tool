package data.clinet.manager.Impl;

import org.springframework.stereotype.Service;

import data.clinet.entity.Client;
import data.clinet.manager.ClientManager;
import data.common.manager.impl.BaseDaoImpl;


@Service(value="clientManager")
public class ClientManagerImpl extends BaseDaoImpl implements ClientManager {

	@Override
	public void addClient(Client client) {
		
		this.save(client);
	}

}