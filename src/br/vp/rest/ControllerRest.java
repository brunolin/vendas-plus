package br.vp.rest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import org.apache.commons.io.IOUtils;

import br.vp.dto.LogadoDTO;

@Path("controller")
@Produces(MediaType.APPLICATION_JSON)
public class ControllerRest {
	
	/**
	 * Retorna os dados do usuários resgatados da sessão
	 * @param request
	 * @return
	 */
	@GET
	@Path("user")
	public LogadoDTO user(@Context HttpServletRequest request){		
		
		try{
			HttpSession session = request.getSession();
			LogadoDTO user = new LogadoDTO();
			
			user.setType(session.getAttribute("type").toString());
			user.setUsername(session.getAttribute("username").toString());
			return user;
			
		} catch(NullPointerException ex) {
			System.out.println(ex);
			return null;
		}
	}
	
	/**
	 * Tira o usuário da sessão
	 * @param request
	 * @return
	 */
	@GET
	@Path("logout")
	public boolean logout(@Context HttpServletRequest request){		
		
		try{
			HttpSession session = request.getSession();
			session.invalidate();
			
			return true;
			
		} catch(NullPointerException ex) {
			System.out.println(ex);
			return false;
		}
	}
	
	@POST
	@Path("/upload")
	@Consumes("multipart/form-data")
	public Response uploadFile(MultipartFormDataInput input) {

		String fileName = "";

		Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
		List<InputPart> inputParts = uploadForm.get("uploadedFile");

		for (InputPart inputPart : inputParts) {

		 try {

			MultivaluedMap<String, String> header = inputPart.getHeaders();
			fileName = getFileName(header);

			//convert the uploaded file to inputstream
			InputStream inputStream = inputPart.getBody(InputStream.class,null);

			byte [] bytes = IOUtils.toByteArray(inputStream);

			//constructs upload file path
			//fileName = fileName;

			writeFile(bytes,fileName);

			System.out.println("Done");

		  } catch (IOException e) {
			e.printStackTrace();
		  }

		}

		return Response.status(200)
		    .entity("uploadFile is called, Uploaded file name : " + fileName).build();

	}

	/**
	 * header sample
	 * {
	 * 	Content-Type=[image/png],
	 * 	Content-Disposition=[form-data; name="file"; filename="filename.extension"]
	 * }
	 **/
	//get uploaded filename, is there a easy way in RESTEasy?
	private String getFileName(MultivaluedMap<String, String> header) {

		String[] contentDisposition = header.getFirst("Content-Disposition").split(";");

		for (String filename : contentDisposition) {
			if ((filename.trim().startsWith("filename"))) {

				String[] name = filename.split("=");

				String finalFileName = name[1].trim().replaceAll("\"", "");
				return finalFileName;
			}
		}
		return "unknown";
	}

	//save to somewhere
	private void writeFile(byte[] content, String filename) throws IOException {

		File file = new File(filename);

		if (!file.exists()) {
			file.createNewFile();
		}

		FileOutputStream fop = new FileOutputStream(file);

		fop.write(content);
		fop.flush();
		fop.close();

	}
}