package cafe.jjdev.mall.vo;

import java.util.List;

public class ProductCommon {
	private int productCommonNo;
	private int categoryNo;
	private String productCommonName;
	private int productCommonPrice;
	private String productCommonDescription;
	private String productCommonDate;
	private List<Product> productList;
		
	@Override
	public String toString() {
		return "ProductCommon [productCommonNo=" + productCommonNo + ", categoryNo=" + categoryNo
				+ ", productCommonName=" + productCommonName + ", productCommonPrice=" + productCommonPrice
				+ ", productCommonDescription=" + productCommonDescription + ", productCommonDate=" + productCommonDate
				+ ", productList=" + productList + "]";
	}
	public List<Product> getProductList() {
		return productList;
	}
	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}
	public int getProductCommonNo() {
		return productCommonNo;
	}
	public void setProductCommonNo(int productCommonNo) {
		this.productCommonNo = productCommonNo;
	}
	public int getCategoryNo() {
		return categoryNo;
	}
	public void setCategoryNo(int categoryNo) {
		this.categoryNo = categoryNo;
	}
	public String getProductCommonName() {
		return productCommonName;
	}
	public void setProductCommonName(String productCommonName) {
		this.productCommonName = productCommonName;
	}
	public int getProductCommonPrice() {
		return productCommonPrice;
	}
	public void setProductCommonPrice(int productCommonPrice) {
		this.productCommonPrice = productCommonPrice;
	}
	public String getProductCommonDescription() {
		return productCommonDescription;
	}
	public void setProductCommonDescription(String productCommonDescription) {
		this.productCommonDescription = productCommonDescription;
	}
	public String getProductCommonDate() {
		return productCommonDate;
	}
	public void setProductCommonDate(String productCommonDate) {
		this.productCommonDate = productCommonDate;
	}
}
