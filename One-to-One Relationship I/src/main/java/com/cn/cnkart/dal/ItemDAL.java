package com.cn.cnkart.dal;

import com.cn.cnkart.entity.Item;

public interface ItemDAL {

	Item getById(int id);

	void save(Item item);

	void delete(int id);

	void update(Item updateItem);

}
