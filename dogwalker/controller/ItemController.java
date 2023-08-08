package com.dogwalker.controller;

import com.dogwalker.domain.ItemVO;
import com.dogwalker.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }
    //상품리스트 페이지
    @GetMapping
    public String itemList(Model model) {
        List<ItemVO> items = itemService.getAllItemsList();
        model.addAttribute("items", items);
        return "items/it_list";  // itemList.jsp 파일로 이동 (View 이름을 반환)
    }
    
	//상품상세페이지
    @GetMapping("/it_detail/{it_num}")
    public String getItem(Model model, @PathVariable("it_num") int it_num) {
        ItemVO item = itemService.getItem(it_num);
        model.addAttribute("item", item);
        return "items/it_detail";
    }
	 
}    
