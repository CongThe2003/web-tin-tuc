package com.tuhocjavaweb.model;

public class CommentModel extends AbstractModel<CommentModel> {

	private String content;
	private Long userId;
	private Long newID;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getNewID() {
		return newID;
	}

	public void setNewID(Long newID) {
		this.newID = newID;
	}

}
