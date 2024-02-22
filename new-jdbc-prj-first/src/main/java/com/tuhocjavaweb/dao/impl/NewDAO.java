package com.tuhocjavaweb.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.tuhocjavaweb.dao.INewDAO;
import com.tuhocjavaweb.mapper.NewMapper;
import com.tuhocjavaweb.model.NewModel;
import com.tuhocjavaweb.paging.Pageble;

public class NewDAO extends AbstractDAO<NewModel> implements INewDAO {

	// Trả về một danh sách
	@Override
	public List<NewModel> findByCategoryId(Long categoryId) {
		String sql = "SELECT*FROM news WHERE categoryid = ?";
		return query(sql, new NewMapper(), categoryId);
	}

	@Override
	public Long save(NewModel newModel) {
		//String sql = "INSERT INTO news (title, content, categoryid) VALUES(?,?,?)";
		StringBuilder sql = new StringBuilder("INSERT INTO news (title, content,");
		sql.append(" thumbnail, shortdescription, categoryid, createddate, createdby)");
		sql.append("VALUES(?,?,?,?,?,?,?)");
		return insert(sql.toString(), newModel.getTitle(), newModel.getContent(),
				newModel.getThumbnail(), newModel.getShortDescription(), newModel.getCategoryId(),
				newModel.getCreatedDate(), newModel.getCreatedBy());
	}

	@Override
	public void update(NewModel updateNew) {
		/* StringBuilder sẽ cộng chuỗi vào nhau chứ ko tạo ra vùng nhớ mới để lưu trữ 
		chuỗi sau khi công như String (Tiết kiệm vùng nhớ)*/
		StringBuilder sql = new StringBuilder("UPDATE news SET title = ?, thumbnail = ?,");
		sql.append(" shortdescription = ?, content = ?, categoryid = ?,");
		sql.append(" createddate = ?, createdby = ?, modifieddate = ?, modifiedby = ? WHERE id = ?");
		update(sql.toString(), updateNew.getTitle(), updateNew.getThumbnail(), updateNew.getShortDescription(),
				updateNew.getContent(), updateNew.getCategoryId(), updateNew.getCreatedDate(),
				updateNew.getCreatedBy(), updateNew.getModifiedDate(),
				updateNew.getModifiedBy(), updateNew.getId());
		
	}

	@Override
	public void delete(long id) {
		String sql = "DELETE FROM news WHERE id = ?";
		update(sql,id);
	}
	 
	// FindOne trả về 1 kết quả
	@Override
	public NewModel findOne(Long id) {
		String sql = "SELECT*FROM news WHERE id = ?";
		List<NewModel> news = query(sql, new NewMapper(), id);
		return news.isEmpty() ? null : news.get(0);
	}

	@Override
	public int getTotalItem() {
		String sql = "SELECT count(*) FROM news";
		return count(sql);
	}

	@Override
	public List<NewModel> findAll(Pageble pageble) {
		//String sql = "SELECT * FROM news LIMIT ?, ?";
		StringBuilder sql = new StringBuilder("SELECT * FROM news");
		if(pageble.getSorter() != null && StringUtils.isNotBlank(pageble.getSorter().getSortName()) && StringUtils.isNotBlank(pageble.getSorter().getSortBy())) {
			sql.append(" ORDER BY "+ pageble.getSorter().getSortName() +" " + pageble.getSorter().getSortBy() + "");
		}
		if(pageble.getOffset() != null && pageble.getLimit() != null) {
			sql.append(" LIMIT "+ pageble.getOffset() + ", " + pageble.getLimit() + "");
		}
		return query(sql.toString(), new NewMapper());
	}

}
