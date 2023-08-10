package com.dogwalker.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.dogwalker.domain.ItemVO;
import com.dogwalker.service.ItemService;

@Controller
@RequestMapping("/admin")
public class AdminItemController {

    private final ItemService itemService;
    
    @Autowired
    ServletContext servletContext;
    
    @Autowired
    public AdminItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/admin_itlist")
    public String itemList(Model model) {
        List<ItemVO> adminItems = itemService.getAllItemsList();
        model.addAttribute("adminItems", adminItems);
        return "admin/admin_itlist";  // admin_itlist.jsp 파일로 이동 (View 이름을 반환)
    }
    

    @PostMapping("/addProduct")
    public String addProduct(@ModelAttribute("itemVO") ItemVO item, @RequestParam("insertImg") MultipartFile file) {
        System.out.println("값 들어옴");
        if (!file.isEmpty()) {
            String relativePath = "/resources/upload/ItemImg/";  // 상대 경로
            String uploadDir = servletContext.getRealPath(relativePath);
            String originalFileName = file.getOriginalFilename();
            String savedFileName = System.currentTimeMillis() + "_" + originalFileName; 

            File uploadFile = new File(uploadDir, savedFileName);
            try {
                file.transferTo(uploadFile);  // 실제 파일 저장
                item.setIt_img(relativePath + savedFileName);  // DB에 저장될 상대 경로 설정
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
       
        itemService.insertItem(item);
        return "redirect:/admin/admin_itlist";
    }

    
    @PostMapping
    public String insertItem(ItemVO item) {
        itemService.insertItem(item);
        return "redirect:/admin";
    }

    @DeleteMapping("/{it_num}")
    public String deleteItem(@PathVariable int it_num) {
        itemService.deleteItem(it_num);
        return "redirect:/admin";  // 상품 삭제 후 상품 리스트 페이지로 리다이렉트
    }
   
}
