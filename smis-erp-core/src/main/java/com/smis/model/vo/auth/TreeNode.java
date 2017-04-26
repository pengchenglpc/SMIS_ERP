package com.smis.model.vo.auth;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * 生成 机构——树形结构json字符串
 * @title: TreeNode
 * @description: 
 * @version: V2.1
 * @author chenzhenjun
 */
public class TreeNode implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer id; // 机构id
	private Integer mid; // 模块id
	private String name; // 机构名称
	private boolean open; //是否展开
	private boolean checked; //是否勾选
	private List<TreeNode> children; // 孩子节点的List

	public TreeNode() {
		super();
	}

	public TreeNode(Integer id, String name, boolean  open, List<TreeNode> children) {
		super();
		this.id = id;
		this.name = name;
		this.open = open;
		this.children = children;
	}

	public void addChild(TreeNode node) {

		if (this.children == null) {
			children = new ArrayList<TreeNode>();
			children.add(node);
		} else {
			children.add(node);
		}

	}

	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}
	
	public List<TreeNode> getChildren() {
		return children;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public Integer getMid() {
		return mid;
	}

	public void setMid(Integer mid) {
		this.mid = mid;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	
}