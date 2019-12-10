package org.forbes.comm.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/***
 * TreeModel概要说明：权限树
 * @author Huanghy
 */
public class TreeModel implements Serializable{
	
	private static final long serialVersionUID = 4013193970046502756L;

	private String key;
	
	private String title;
	
	private String slotTitle;
	
	private Boolean leaf;
	
	private String icon;
	
	private Map<String,String> scopedSlots;
	
	public Map<String, String> getScopedSlots() {
		return scopedSlots;
	}

	public void setScopedSlots(Map<String, String> scopedSlots) {
		this.scopedSlots = scopedSlots;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	

	/** 
	 * @return leaf 
	 */
	public Boolean getLeaf() {
		return leaf;
	}

	/** 
	 * @param leaf 要设置的 leaf 
	 */
	public void setLeaf(Boolean leaf) {
		this.leaf = leaf;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	private List<TreeModel> children;

	public List<TreeModel> getChildren() {
		return children;
	}

	public void setChildren(List<TreeModel> children) {
		this.children = children;
	}

	public TreeModel() {
		
	}
	
	public TreeModel(Long id,String icon,Long parentId,String name,String leaf) {
		this.key = String.valueOf(id);
		this.icon = icon;
		this.parentId = String.valueOf(parentId);
		this.title = name;
		this.slotTitle =  name;
		this.value = String.valueOf(id);
		this.leaf = Boolean.parseBoolean(leaf);
		this.label = name;
		if(!this.leaf) {
			this.children = new ArrayList<TreeModel>();
		}
	}
	 
	 public TreeModel(String key,String parentId,String slotTitle,String icon,Boolean isLeaf) {
    	this.key = key;
    	this.parentId = parentId;
    	this.icon=icon;
    	this.slotTitle =  slotTitle;
    	Map<String,String> map = new HashMap<String,String>();
    	map.put("title", "hasDatarule");
    	this.scopedSlots = map;
    	this.leaf = isLeaf;
    	this.value = key;
    	if(!isLeaf) {
    		this.children = new ArrayList<TreeModel>();
    	}
    }
	 
	 private String parentId;
		
	private String label;
	
	private String value;
	
	
	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	public String getSlotTitle() {
		return slotTitle;
	}

	public void setSlotTitle(String slotTitle) {
		this.slotTitle = slotTitle;
	}
}
