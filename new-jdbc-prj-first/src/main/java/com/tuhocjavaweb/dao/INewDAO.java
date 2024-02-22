package com.tuhocjavaweb.dao;

import java.util.List;

import com.tuhocjavaweb.model.NewModel;
import com.tuhocjavaweb.paging.Pageble;

public interface INewDAO extends GenericDAO<NewModel>{
	NewModel findOne(Long id);
	List<NewModel> findByCategoryId(Long categoryId);
	Long save(NewModel newModel);
	void update(NewModel updateNew);
	void delete(long id);
	List<NewModel> findAll(Pageble pageble);
	int getTotalItem();
}
