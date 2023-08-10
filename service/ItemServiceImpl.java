package com.dogwalker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dogwalker.dao.ItemDAO;
import com.dogwalker.domain.ItemVO;

@Service("itemService")
public class ItemServiceImpl implements ItemService {
    
	private final ItemDAO itemDAO;

    @Autowired
    public ItemServiceImpl(ItemDAO itemDAO ) {
        this.itemDAO  = itemDAO ;
    } 

    @Override
    public List<ItemVO> getAllItemsList() {
        return itemDAO .getAllItemsList();
    }

    @Override
    public void insertItem(ItemVO item) {
    	itemDAO .insertItem(item);
    }

    @Override
    public void deleteItem(int it_num) {
    	itemDAO .deleteItem(it_num);
    }
    
    @Override
    public ItemVO getItem(int it_num) {
        return itemDAO .getItem(it_num);
    }
     
}

/**
package com.dogwalker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dogwalker.dao.ItemDAO;
import com.dogwalker.domain.ItemVO;

@Service("itemService")
public class ItemServiceImpl implements ItemService {
    
    private final ItemDAO itemDAO;

    @Autowired
    public ItemServiceImpl(ItemDAO itemDAO) {
        this.itemDAO = itemDAO;
    }

    @Override
    public List<ItemVO> getIAlltemList() {
        return itemDAO.getIAlltemList();
    }

    @Override
    public void insertItem(ItemVO item) {
        itemDAO.insertItem(item);
    }

    @Override
    public void deleteItem(int it_num) {
        itemDAO.deleteItem(it_num);
    }
}

**/