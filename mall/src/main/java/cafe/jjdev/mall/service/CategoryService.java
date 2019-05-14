package cafe.jjdev.mall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cafe.jjdev.mall.mapper.CategoryMapper;
import cafe.jjdev.mall.vo.Category;

@Service
public class CategoryService {
	@Autowired
	private CategoryMapper categoryMapper;
	
	public List<Category> getCategoryList(){
		return categoryMapper.selectCategoryList();
	}
}
