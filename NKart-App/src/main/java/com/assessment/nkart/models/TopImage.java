package com.assessment.nkart.models;

public class TopImage {

    private String url;
    private Boolean isProductListing;
    private Integer productId;
    private Integer subCategoryId;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getIsProductListing() {
        return isProductListing;
    }

    public void setIsProductListing(Boolean isProductListing) {
        this.isProductListing = isProductListing;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(Integer subCategoryId) {
        this.subCategoryId = subCategoryId;
    }
}
