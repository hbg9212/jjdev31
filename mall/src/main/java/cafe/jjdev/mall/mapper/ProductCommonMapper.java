package cafe.jjdev.mall.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import cafe.jjdev.mall.vo.ProductCommon;

@Mapper
public interface ProductCommonMapper {
	public List<ProductCommon> selectProductListByCategoryNo(Map<String, Object> map);
	public ProductCommon selectProductByProductCommonNo(int productCommonNo);
	public int selectProductCommonCount(Map<String, Object> map);
}
