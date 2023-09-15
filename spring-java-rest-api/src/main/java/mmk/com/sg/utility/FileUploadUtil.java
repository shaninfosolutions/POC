package mmk.com.sg.utility;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import mmk.com.sg.data.entity.AnnexFile;
import mmk.com.sg.service.AnnexServiceImpl;

public class FileUploadUtil {
	@Value("${pdf.file.input}")
	private String pdfFileInput;
	
	@Value("${pdf.file.output}")
	private String pdfFileOutput;
	
	
	 public static String saveFile(String fileName, MultipartFile multipartFile)
	            throws IOException {
	        Path uploadPath = Paths.get("Files-Upload");         
	        if (!Files.exists(uploadPath)) {
	            Files.createDirectories(uploadPath);
	        }
	        String fileCode = RandomStringUtils.randomAlphanumeric(8);
	   
	        try (InputStream inputStream = multipartFile.getInputStream()) {
	            Path filePath = uploadPath.resolve(fileCode + "-" + fileName);
	            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
	            //update into DB
	        } catch (IOException ioe) {       
	            throw new IOException("Could not save file: " + fileName, ioe);
	        }
	         
	        return fileCode;
	    }
	 
	 public static String saveFileAnnex(String fileName, MultipartFile multipartFile)
	            throws IOException {
	        Path uploadPath = Paths.get("Annex-Upload");         
	        if (!Files.exists(uploadPath)) {
	            Files.createDirectories(uploadPath);
	        }
	      //  String fileCode = RandomStringUtils.randomAlphanumeric(8);
	        
	        try (InputStream inputStream = multipartFile.getInputStream()) {
	            Path filePath = uploadPath.resolve(fileName);
	            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
	            //update into DB
	          
	        } catch (IOException ioe) {       
	            throw new IOException("Could not save file: " + fileName, ioe);
	        }
	         
	        return "Annex-Upload/"+fileName;
	    }
	 
	 public static String saveFileDigitalInput(String fileName, MultipartFile multipartFile)
	            throws IOException {
	        Path uploadPath = Paths.get("Digital-Signature-Input");         
	        if (!Files.exists(uploadPath)) {
	            Files.createDirectories(uploadPath);
	        }
	      //  String fileCode = RandomStringUtils.randomAlphanumeric(8);
	        
	        try (InputStream inputStream = multipartFile.getInputStream()) {
	            Path filePath = uploadPath.resolve(fileName);
	            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
	            //update into DB
	          
	        } catch (IOException ioe) {       
	            throw new IOException("Could not save file: " + fileName, ioe);
	        }
	         
	        return "Digital-PDF-Input-Upload/"+fileName;
	    }

}
