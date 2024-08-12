package com.servlet;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.dto.ProjectDto;
import com.service.ProjectService;

/**
 * Servlet implementation class ProjectServlet
 */
@WebServlet("/ProjectServlet")
public class ProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ServletConfig config;
	String Name = "";
	String path = "";

	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		this.config = config;

		path = config.getServletContext().getRealPath("/");
		System.out.println(path);
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProjectServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		ProjectDto dto = new ProjectDto();
		ProjectService ser = new ProjectService();

		File savesFile = null;
		FileItem item = null;

		if (ServletFileUpload.isMultipartContent(request)) {
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			List<FileItem> items = null;
			try {

				items = upload.parseRequest(request);

			} catch (Exception e) {
			}

			Iterator<FileItem> itr = items.iterator();

			while (itr.hasNext()) {
				item = itr.next();

				if (item.isFormField()) {

					String name = item.getFieldName();

					if (name.equals("Id"))
						dto.setId(Integer.parseInt(item.getString() == null ? "0" : item.getString().trim()));

					

					if (name.equals("Mobile_no"))
						dto.setMobile_no(item.getString() == null ? "0" : item.getString().trim());
					if (name.equals("Address"))
						dto.setAddress(item.getString() == null ? "" : item.getString().trim());

					if (name.equals("Gst_in"))
						dto.setGst_in(item.getString() == null ? "0" : item.getString().trim());

					if (name.equals("Status"))
						dto.setStatus(item.getString() == null ? "" : item.getString().trim());

					if (name.equals("Term_and_conditions"))
						dto.setTerm_and_conditions(item.getString() == null ? "" : item.getString().trim());

				} else {

					if (item.getSize() != 0) {
						if (item.getSize() < 3000000) {
							if (dto.getId() == 0) {

								int j = ser.maxId(request);
								savesFile = new File(path + "ProjectImage/" + j + ".jpg");

								try {
									item.write(savesFile);

									System.out.println(savesFile);
								} catch (Exception e2) {
								}

							} else {

								savesFile = new File(path + "ProjectImage/" + dto.getId() + ".jpg");
								try {
									item.write(savesFile);

								} catch (Exception e2) {
								}
							}

						}

					}

				}
			}
		}

		if (dto.getId() == 0) {

			boolean b = ser.insertProject(dto, request, config);

			if (b) {

				response.sendRedirect("index.jsp?msg=Yes");
			} else {

				response.sendRedirect("index.jsp?msg=No");

			}
		} else {
			boolean b = ser.UpdateProject(dto, request, config);

			if (b) {

				response.sendRedirect("index.jsp?msg=YesUp");
			} else {

				response.sendRedirect("index.jsp?msg=NoUp");

			}
		}

	}

}
