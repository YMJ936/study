package com.dogwalker.dao;

import com.dogwalker.domain.ItemVO;
import java.util.List;

public interface ItemDAO {

    public List<ItemVO> getAllItemsList();

    public int insertItem(ItemVO itemVO);

    public int deleteItem(int it_num);
    
    public ItemVO getItem(int it_num);
}
