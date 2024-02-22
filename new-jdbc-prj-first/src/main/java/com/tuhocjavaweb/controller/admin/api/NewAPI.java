package com.tuhocjavaweb.controller.admin.api;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tuhocjavaweb.model.NewModel;
import com.tuhocjavaweb.model.UserModel;
import com.tuhocjavaweb.service.INewService;
import com.tuhocjavaweb.utils.HttpUtil;
import com.tuhocjavaweb.utils.SessionUtil;

@WebServlet(urlPatterns = { "/api-admin-new" })
public class NewAPI extends HttpServlet {

	@Inject
	private INewService newService;

	private static final long serialVersionUID = -7516160823911207673L;

	// Thêm bài viết nằm trong hàm này
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Đối tượng hỗ trợ chuyển từ json qua chuỗi và từ chuỗi về json
		ObjectMapper mapper = new ObjectMapper();

		// request định nghĩa dữ liệu nhận vào
		// Định nghĩa tiếng việt để khi gửi từ postman là tiếng việt server sẽ nhận ra
		request.setCharacterEncoding("UTF-8");

		// responsee định nghĩa dữ liệu trả ra
		// Định nghĩa kiểu dữ liệu để trả về client
		response.setContentType("application/json");

		NewModel newModel = HttpUtil.of(request.getReader()).toModel(NewModel.class);
		newModel.setCreatedBy(((UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL")).getUserName());
		newModel = newService.save(newModel);
		mapper.writeValue(response.getOutputStream(), newModel);
	}

	// Sửa bài viết nằm trong hàm này
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Đối tượng hỗ trợ chuyển từ json qua chuỗi và từ chuỗi về json
		ObjectMapper mapper = new ObjectMapper();

		// request định nghĩa dữ liệu nhận vào
		// Định nghĩa tiếng việt để khi gửi từ postman là tiếng việt server sẽ nhận ra
		request.setCharacterEncoding("UTF-8");

		// responsee định nghĩa dữ liệu trả ra
		// Định nghĩa kiểu dữ liệu để trả về client
		response.setContentType("application/json");

		NewModel updateNew = HttpUtil.of(request.getReader()).toModel(NewModel.class);
		updateNew.setModifiedBy(((UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL")).getUserName());
		updateNew = newService.update(updateNew);
		mapper.writeValue(response.getOutputStream(), updateNew);
	}

	// Xóa bài viết nằm trong hàm này
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Đối tượng hỗ trợ chuyển từ json qua chuỗi và từ chuỗi về json
		ObjectMapper mapper = new ObjectMapper();

		// request định nghĩa dữ liệu nhận vào
		// Định nghĩa tiếng việt để khi gửi từ postman là tiếng việt server sẽ nhận ra
		request.setCharacterEncoding("UTF-8");

		// responsee định nghĩa dữ liệu trả ra
		// Định nghĩa kiểu dữ liệu để trả về client
		response.setContentType("application/json");
		NewModel deleteNew = HttpUtil.of(request.getReader()).toModel(NewModel.class);
		newService.delete(deleteNew.getIds());
		mapper.writeValue(response.getOutputStream(), "{}");
	}

	private void saveOrUpdate() {

	}
}
