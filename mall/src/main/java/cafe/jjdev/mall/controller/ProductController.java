package cafe.jjdev.mall.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cafe.jjdev.mall.service.ProductService;

@Controller
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@GetMapping("/product/productList")
	public String getProductList(Model model
			,@RequestParam(value="currentPage", required = false, defaultValue = "1") int currentPage
			,@RequestParam(value="categoryNo", required = true) int categoryNo
			,@RequestParam(value="searchWord", required = false, defaultValue = "") String searchWord) {
		Map<String, Object> inputMap = new HashMap<String, Object>();
		inputMap.put("categoryNo", categoryNo);
		inputMap.put("currentPage", currentPage);
		inputMap.put("searchWord", "%" + searchWord + "%");
		Map<String, Object> map = productService.getProductCommonList(inputMap);
		model.addAttribute("list", map.get("list"));
		model.addAttribute("currentTenPage", map.get("currentTenPage"));
		model.addAttribute("lastTenPage", map.get("lastTenPage"));
		model.addAttribute("repetitionPage", map.get("repetitionPage"));
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("productCount", map.get("productCount"));
		model.addAttribute("category", map.get("categoryList"));
		model.addAttribute("categoryNo", categoryNo);
		model.addAttribute("searchWord", searchWord);
		return "/product/productList";
	}
	
	@GetMapping("/product/getProduct")
	public String getProduct(Model model
			,@RequestParam(value="currentPage", required = false, defaultValue = "1") int currentPage
			,@RequestParam(value="searchWord", required = false, defaultValue = "") String searchWord
			,@RequestParam(value="productCommonNo", required = true) int productCommonNo) {
		Map<String, Object> map = productService.getProduct(productCommonNo);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("searchWord", searchWord);
		model.addAttribute("productCommon", map.get("productCommon"));
		model.addAttribute("category", map.get("categoryList"));
		model.addAttribute("categoryNo", map.get("categoryNo"));
		return "/product/getProduct";
	}
}
