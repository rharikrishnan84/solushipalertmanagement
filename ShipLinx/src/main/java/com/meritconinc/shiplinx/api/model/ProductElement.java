/**
 * @author selva
 */
package com.meritconinc.shiplinx.api.model;

import java.util.List;

public class ProductElement {
	private java.lang.String product_type;

 	public void setProduct_type(java.lang.String product_type) {
		this.product_type = product_type;
	}

	public java.lang.String getProduct_type() {
		return product_type;
	}

	private java.lang.String published_at;

 	public void setPublished_at(java.lang.String published_at) {
		this.published_at = published_at;
	}

	public java.lang.String getPublished_at() {
		return published_at;
	}

	private java.lang.String tags;

 	public void setTags(java.lang.String tags) {
		this.tags = tags;
	}

	public java.lang.String getTags() {
		return tags;
	}

	private java.lang.String handle;

 	public void setHandle(java.lang.String handle) {
		this.handle = handle;
	}

	public java.lang.String getHandle() {
		return handle;
	}

	private java.lang.String published_scope;

 	public void setPublished_scope(java.lang.String published_scope) {
		this.published_scope = published_scope;
	}

	public java.lang.String getPublished_scope() {
		return published_scope;
	}

	private java.lang.Object template_suffix;

 	public void setTemplate_suffix(java.lang.Object template_suffix) {
		this.template_suffix = template_suffix;
	}

	public java.lang.Object getTemplate_suffix() {
		return template_suffix;
	}

	private java.lang.String body_html;

 	public void setBody_html(java.lang.String body_html) {
		this.body_html = body_html;
	}

	public java.lang.String getBody_html() {
		return body_html;
	}

	private OptionElement[] options;

 	public void setOptions(OptionElement[] options) {
		this.options = options;
	}

	public OptionElement[] getOptions() {
		return options;
	}

	private java.lang.String vendor;

 	public void setVendor(java.lang.String vendor) {
		this.vendor = vendor;
	}

	public java.lang.String getVendor() {
		return vendor;
	}

	private java.lang.String created_at;

 	public void setCreated_at(java.lang.String created_at) {
		this.created_at = created_at;
	}

	public java.lang.String getCreated_at() {
		return created_at;
	}

	private List<VariantElement> variants;

  

	private ImageElement[] images;

 	public void setImages(ImageElement[] images) {
		this.images = images;
	}

	public ImageElement[] getImages() {
		return images;
	}

	private Long id;

	private java.lang.String updated_at;

 	public void setUpdated_at(java.lang.String updated_at) {
		this.updated_at = updated_at;
	}

	public java.lang.String getUpdated_at() {
		return updated_at;
	}

	private java.lang.String title;

 	public void setTitle(java.lang.String title) {
		this.title = title;
	}

	public java.lang.String getTitle() {
		return title;
	}

	private Image image;

 	public void setImage(Image image) {
		this.image = image;
	}

	public Image getImage() {
		return image;
	}

	public List<VariantElement> getVariants() {
		return variants;
	}

	public void setVariants(List<VariantElement> variants) {
		this.variants = variants;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}