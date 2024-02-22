package com.tuhocjavaweb.paging;

import com.tuhocjavaweb.sort.Sorter;

public interface Pageble {
	Integer getPage();
	Integer getOffset();
	Integer getLimit();
	Sorter getSorter();
}
