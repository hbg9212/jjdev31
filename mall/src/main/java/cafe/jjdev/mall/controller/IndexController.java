package cafe.jjdev.mall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import cafe.jjdev.mall.service.CategoryService;
import cafe.jjdev.mall.vo.Category;

@Controller
public class IndexController {
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/")	
	public String index(Model model) {
		System.out.println("[IndexController]\t index.jsp로 이동");
		List<Category> category = categoryService.getCategoryList();
		model.addAttribute("category", category);
		return "index";
	}
}
