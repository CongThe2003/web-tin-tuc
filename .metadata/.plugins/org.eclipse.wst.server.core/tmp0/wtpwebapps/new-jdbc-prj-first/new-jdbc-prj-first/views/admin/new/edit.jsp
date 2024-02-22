<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>
<c:url var="APIurl" value="/api-admin-new"/>
<c:url var="NewURL" value="/admin-new"/>
<!DOCTYPE html>
<html>
<head>
	<title>Chỉnh sửa bài viết</title>
</head>
<body>
<div class="main-content">
	<div class="main-content-inner">
		<div class="breadcrumbs" id="breadcrumbs">
			<script type="text/javascript">
				try{ace.settings.check('breadcrumbs','fixed')} catch(e){}
			</script>
			<ul class="breadcrumb">
				<li>
					<i class="ace-icon fa fa-home home-icon"></i>
					<a href="#">Trang chủ</a>
				</li>
				<li class="active">Chỉnh sửa bài viết</li>
			</ul>
		</div>
		<div class="page-content">
			<div class="row">
				<div class="col-xs-12">
					<c:if test="${not empty messageResponse}">
						<div class="alert alert-${alert}">
							${messageResponse}
						</div>
					</c:if>
					<form id="formSubmit">
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right">Thể loại</label>
							<div class="col-sm-9">
							<select class="form-control" id="categoryCode" name="categoryCode">
							<c:if test="${empty model.categoryCode}">
								<option value="">Chọn thể loại bài viết</option>
								<c:forEach var="item" items="${categories}">
									<option value="${item.code}">${item.name}</option>
								</c:forEach>
							</c:if>
							<c:if test="${not empty model.categoryCode}">
								<option value="">Chọn thể loại bài viết</option>
								<c:forEach var="item" items="${categories}">
									<option value="${item.code}" <c:if test="${item.code == model.categoryCode}">selected="selected"</c:if>>${item.name}</option>
								</c:forEach>
								
							</c:if>
							</select>
							</div>
						</div>
						<br/>
						<br/>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right">Tiêu đề</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" id="title" name="title" value="${model.title}"/>
							</div>
						</div>
						<br/>
						<br/>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right">Hình đại diện</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" id="thumbnail" name="thumbnail" value="${model.thumbnail}"/>
							</div>
						</div>
						<br/>
						<br/>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right">Mô tả ngắn</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" id="shortDescription" name="shortDescription" value="${model.shortDescription }"/>
							</div>
						</div>
						<br/>
						<br/>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right">Nội dung</label>
							<div class="col-sm-9">
								<!-- cách cũ không tiện ích và hợp lý -->
								<!-- <input type="text" class="form-control" id="content" name="content" value="${model.content }"/> --!>
								
								<!-- Cách này sẽ cho chúng ta một hộp text to và kết hợp với ckeditor để có được những chức năng chỉnh sửa văn bản -->
								<!-- Khi sử dụng thư viên ckeditor thì việc gắn kích thước ko còn quá quan trọng bởi ckeditor sẽ tự fill tràn div đó -->
								<textarea rows="" cols="" id="content" name="content" style="width: 942px; height:400px">${model.content}</textarea>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12">
								<c:if test="${not empty model.id }">
									<input type="button" class="btn btn-white btn-warning btn-bold" value="Cập nhật bài viết" id="btnAddOrUpdateNew"/>
								</c:if>
								<c:if test="${empty model.id }">
									<input type="button" class="btn btn-white btn-warning btn-bold" value="Thêm mới bài viết" id="btnAddOrUpdateNew"/>
								</c:if>
							</div>
						</div>
						<input type="hidden" value="${model.id}" id="id" name="id"/>
					</form>
					
				</div>
			</div>
		</div>
	</div>
</div>
<script>
	var editor ='';
	/* Tương tự như hàm main() bên Java nó sẽ giúp cho khi load trang này lên sẽ chạy vào đây đầu tiên */
	$(document).ready(function(){
		// editor lúc này sẽ được thay thế bằng editor mới mà chúng ta đã tải về để ở trong webapp
		editor = CKEDITOR.replace("content");
	});

	$('#btnAddOrUpdateNew').click(function (e) {
		e.preventDefault();
		// data lúc này là javascript object
		var data = {};
		var formData = $('#formSubmit').serializeArray();
		$.each(formData, function (i, v){
			data[""+v.name+""] = v.value;
		});
		data["content"] = editor.getData();
		var id = $('#id').val();
		if (id == ""){
			addNew(data);
		} else {
			updateNew(data);
		}
	});
	
	function addNew(data){
		$.ajax({
			url: '${APIurl}',
			type: 'POST',
			contentType: 'application/json',
			// data lúc này vẫn là javascript object nên ta sẽ sử dụng JSON.stringify để đổi sang kiểu JSON
			data: JSON.stringify(data),
			dataType: 'json',
			success: function (result) {
				window.location.href = "${NewURL}?type=edit&id="+result.id+"&message=insert_success";
			},
			error: function (error) {
				window.location.href = "${NewURL}?type=list&maxPageItem=2&page=1&message=error_system";
			}
		});
	}
	
	function updateNew(data){
		$.ajax({
			url: '${APIurl}',
			type: 'PUT',
			contentType: 'application/json',
			data: JSON.stringify(data),
			dataType: 'json',
			success: function (result) {
				window.location.href = "${NewURL}?type=edit&id="+result.id+"&message=update_success";
			},
			error: function (error) {
				window.location.href = "${NewURL}?type=list&maxPageItem=2&page=1&message=error_system";
			}
		});
	}
</script>
</body>
</html>