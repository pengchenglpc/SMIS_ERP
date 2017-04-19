package com.smis.model.vo.base;

import java.util.List;

/**
 * 分页VO
 * @author dell
 *
 */
public class PageVo<T> {
	private Integer pageIndex;	//当前页
	private Integer pageSize;	//每页显示条数
	private Integer totalSize;	//总条数
	private Integer currentIndex;	//当前索引
	private List<T> data;	//数据
	public Integer getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getTotalSize() {
		return totalSize;
	}
	public void setTotalSize(Integer totalSize) {
		this.totalSize = totalSize;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	public Integer getCurrentIndex() {
		this.currentIndex = pageIndex * pageSize;
		return currentIndex;
	}
	
}
