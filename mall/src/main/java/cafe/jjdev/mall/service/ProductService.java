package cafe.jjdev.mall.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cafe.jjdev.mall.mapper.CategoryMapper;
import cafe.jjdev.mall.mapper.ProductCommonMapper;
import cafe.jjdev.mall.vo.Category;
import cafe.jjdev.mall.vo.ProductCommon;

@Service
@Transactional
public class ProductService {
	@Autowired
	private ProductCommonMapper productCommonMapper;
	@Autowired
	private CategoryMapper categoryMapper;
	
	public Map<String, Object> getProductCommonList(Map<String, Object> map) {
		final int ROW_PER_PAGE = 10;
		int currentPage = (int)map.get("currentPage");
		int categoryNo = (int)map.get("categoryNo");
		final int BEGIN_ROW = (currentPage-1)*ROW_PER_PAGE;
		map.put("beginRow", BEGIN_ROW);
		map.put("rowPerPage", ROW_PER_PAGE);
		List<ProductCommon> list = productCommonMapper.selectProductListByCategoryNo(map);
		final int PRODUCT_COUNT = productCommonMapper.selectProductCommonCount(categoryNo);
		int currentTenPage = currentPage/10;
		if(currentPage%10 == 0) {
			currentTenPage--;
		}
		int lastPage = PRODUCT_COUNT/ROW_PER_PAGE;
		if(PRODUCT_COUNT%ROW_PER_PAGE !=0) {
			lastPage++;
		}
		int lastTenPage = lastPage/10;
		if(lastTenPage%10 == 0) {
			lastTenPage--;
		}
		int repetitionPage = 10;
		if((lastPage - currentTenPage*10) < 10) {
			repetitionPage = lastPage % 10 ;
		}		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		List<Category> categoryList = categoryMapper.selectCategoryList();
		returnMap.put("list", list);
		returnMap.put("currentTenPage", currentTenPage);
		returnMap.put("lastTenPage", lastTenPage);
		returnMap.put("repetitionPage", repetitionPage);
		returnMap.put("productCount", PRODUCT_COUNT);
		returnMap.put("categoryList", categoryList);
		return returnMap;
	}
	
	public Map<String, Object> getProduct(int productCommonNo) {
		List<Category> categoryList = categoryMapper.selectCategoryList();
		ProductCommon productCommon = productCommonMapper.selectProductByProductCommonNo(productCommonNo);
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("categoryList", categoryList);
		returnMap.put("productCommon", productCommon);
		returnMap.put("categoryNo", productCommon.getProductCommonNo());
		return returnMap;
	}
}
