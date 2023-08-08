package com.dogwalker.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.session.SqlSession;
import com.dogwalker.domain.ItemVO;
import java.util.List;

@Repository
public class ItemDAOImpl implements ItemDAO {

    @Autowired
    private SqlSession sqlSession;
    
    private static final String NAMESPACE = "com.dogwalker.mappers.ItemMapper";

    @Override
    public List<ItemVO> getAllItemsList() {
        return sqlSession.selectList(NAMESPACE + ".getAllItemsList");
    }

    @Override
    public int insertItem(ItemVO itemVO) {
        return sqlSession.insert(NAMESPACE + ".insertItem", itemVO);
    }

    @Override
    public int deleteItem(int it_num) {
        return sqlSession.delete(NAMESPACE + ".deleteItem", it_num);
    }

    @Override
    public ItemVO getItem(int it_num) {
        return sqlSession.selectOne(NAMESPACE + ".getItem", it_num);
    }
}
