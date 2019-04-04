package com.utils;

import java.util.*;

/**
 * @Auther: yangbo
 * @Date: 2019/2/15
 * @Description:
 */
public class Page<T> {
    protected static final int DEFAULT_SHOW_PAGE_NUMBER_COUNT = 9;
    private int startIndex;
    private int pageSize = 20;
    private int totalCount;
    private Collection<T> results;
    private int[] showPageNumbers;
    private Page.InnerPage[] pages;

    public Page() {
        this.startIndex = -1;
        this.pageSize = 20;
        this.totalCount = 0;
        this.results = new ArrayList();
    }

    public Page(int startIndex, int pageSize, int totalCount, Collection<T> results) {
        this.startIndex = startIndex;
        this.pageSize = pageSize <= 0 ? 20 : pageSize;
        this.totalCount = totalCount;
        this.results = results;
    }

    public int[] getShowPageNumbers() {
        return this.getShowPageNumbers(9);
    }

    public int[] getShowPageNumbers(int showCount) {
        int currentPage = this.startIndex / this.pageSize;
        if (this.showPageNumbers == null) {
            int totalPageCount = this.getTotalPageCount();
            this.showPageNumbers = new int[totalPageCount > showCount ? showCount : totalPageCount];
            int firstShowPage;
            int max;
            if (totalPageCount > showCount) {
                firstShowPage = currentPage - showCount / 2;
                max = currentPage + showCount / 2;
                int i;
                if (firstShowPage > 0 && max < totalPageCount) {
                    i = 0;

                    for(max = this.showPageNumbers.length; i < max; ++i) {
                        this.showPageNumbers[i] = firstShowPage + i;
                    }
                } else if (firstShowPage > 0) {
                    i = 0;

                    for(max = this.showPageNumbers.length; i < max; ++i) {
                        this.showPageNumbers[i] = totalPageCount - showCount + i;
                    }
                } else {
                    i = 0;

                    for(max = this.showPageNumbers.length; i < max; this.showPageNumbers[i] = i++) {
                        ;
                    }
                }
            } else {
                firstShowPage = 0;

                for(max = this.showPageNumbers.length; firstShowPage < max; this.showPageNumbers[firstShowPage] = firstShowPage++) {
                    ;
                }
            }
        }

        return this.showPageNumbers;
    }

    public int getTotalPageCount() {
        return this.totalCount % this.pageSize == 0 ? this.totalCount / this.pageSize : this.totalCount / this.pageSize + 1;
    }

    public int getNextIndex() {
        return this.startIndex + this.pageSize;
    }

    public int getPreviousIndex() {
        int previousIndex = this.startIndex - this.pageSize;
        return previousIndex >= 0 ? previousIndex : 0;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Collection<T> getResults() {
        return this.results;
    }

    public List<T> getList() {
        return (List)(this.results == null ? null : (this.results instanceof List ? (List)this.results : new ArrayList(this.results)));
    }

    public Set<T> getSet() {
        return (Set)(this.results == null ? null : (this.results instanceof Set ? (Set)this.results : new HashSet(this.results)));
    }

    public List<T> getSortList() {
        List list = this.getList();
        if (list != null) {
            Collections.sort(list);
        }

        return list;
    }

    public void setResults(Collection<T> results) {
        this.results = results;
    }

    public int getStartIndex() {
        return this.startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getTotalCount() {
        return this.totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageCount() {
        return this.totalCount > 0 ? (this.totalCount - 1) / this.pageSize + 1 : 0;
    }

    public int getPage() {
        return this.startIndex >= 0 ? this.startIndex / this.pageSize + 1 : 0;
    }

    public Page.InnerPage[] getPages() {
        return this.pages == null ? (this.pages = this.generatePages()) : this.pages;
    }

    private Page.InnerPage[] generatePages() {
        int page = this.getPage() - 1;
        int from = page * this.pageSize;
        if (from > this.totalCount) {
            from = this.totalCount - this.pageSize;
            if (from < 0) {
                from = 0;
            }
        }

        int numberOfPages = (int)Math.ceil((double)((float)this.totalCount / (float)this.pageSize));
        Page.InnerPage[] pages = new Page.InnerPage[numberOfPages];

        for(int i = 0; i < pages.length; ++i) {
            pages[i] = new Page.InnerPage();
            pages[i].setFrom(i * this.pageSize + 1);
            pages[i].setSize(this.pageSize);
            pages[i].setTo((i + 1) * this.pageSize);
            if (from >= pages[i].getFrom() - 1 && from < pages[i].getTo()) {
                pages[i].setSelected(true);
            } else {
                pages[i].setSelected(false);
            }
        }

        if (numberOfPages > 0) {
            Page.InnerPage lastPage = pages[numberOfPages - 1];
            if (lastPage.getTo() > this.totalCount) {
                lastPage.setSize(this.totalCount - lastPage.getFrom());
                lastPage.setTo(this.totalCount);
            }
        }

        return pages;
    }

    public static class InnerPage {
        private int from;
        private int to;
        private int size;
        private boolean selected;

        public InnerPage() {
        }

        public int getFrom() {
            return this.from;
        }

        public void setFrom(int from) {
            this.from = from;
        }

        public boolean isSelected() {
            return this.selected;
        }

        public void setSelected(boolean selected) {
            this.selected = selected;
        }

        public int getSize() {
            return this.size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getTo() {
            return this.to;
        }

        public void setTo(int to) {
            this.to = to;
        }
    }
}

