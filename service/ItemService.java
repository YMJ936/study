package com.dogwalker.service;

import com.dogwalker.domain.ItemVO;

import java.util.List;

public interface ItemService {
    void insertItem(ItemVO item);
    void deleteItem(int it_num);
	List<ItemVO> getAllItemsList();
	ItemVO getItem(int it_num);
}
