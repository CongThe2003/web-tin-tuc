package com.tuhocjavaweb.service;

import java.util.List;

import com.tuhocjavaweb.model.NewModel;
import com.tuhocjavaweb.paging.Pageble;

public interface INewService {
	List<NewModel> findByCategoryId(Long categoryId);
	NewModel save(NewModel newModel);
	NewModel update(NewModel updateNew);
	void delete(long[] ids);
	List<NewModel> findAll(Pageble pageble);
	int getTotalItem();
	NewModel findOne(Long id);
}
