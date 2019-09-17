package com.compasso.backend.app.rest.dto.architecture;

import com.compasso.backend.app.rest.pattern.AbstractRestDTO;

public class PageRequestDTO extends AbstractRestDTO {

    private Integer pageNumber;

    private Integer pageSize;

    private Integer offSet;

    private SortDTO sort;

    private PageRequestDTO next;

    private PageRequestDTO previousOrFirst;

    private PageRequestDTO first;

    boolean hasPrevious;

    public PageRequestDTO() {
    }

    public PageRequestDTO(Integer pageNumber, Integer pageSize) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    public PageRequestDTO(Integer pageNumber, Integer pageSize, SortDTO sort) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.sort = sort;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getOffSet() {
        return offSet;
    }

    public void setOffSet(Integer offSet) {
        this.offSet = offSet;
    }

    public SortDTO getSort() {
        return sort;
    }

    public void setSort(SortDTO sort) {
        this.sort = sort;
    }

    public PageRequestDTO getNext() {
        return next;
    }

    public void setNext(PageRequestDTO next) {
        this.next = next;
    }

    public PageRequestDTO getPreviousOrFirst() {
        return previousOrFirst;
    }

    public void setPreviousOrFirst(PageRequestDTO previousOrFirst) {
        this.previousOrFirst = previousOrFirst;
    }

    public PageRequestDTO getFirst() {
        return first;
    }

    public void setFirst(PageRequestDTO first) {
        this.first = first;
    }

    public boolean isHasPrevious() {
        return hasPrevious;
    }

    public void setHasPrevious(boolean hasPrevious) {
        this.hasPrevious = hasPrevious;
    }
}
