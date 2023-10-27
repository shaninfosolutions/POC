package mmk.com.sg.utility;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
 
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

public class FileDownloadUtil {
	private Path foundFile;
    
    public Resource getFileAsResource(String fileCode) throws IOException {
        Path dirPath = Paths.get("Files-Upload");
        
        Files.list(dirPath).forEach(file -> {
            if (file.getFileName().toString().startsWith(fileCode)) {
                foundFile = file;
                return;
            }
        });
 
        if (foundFile != null) {
            return new UrlResource(foundFile.toUri());
        }
         
        return null;
    }
    
    public Resource getFileAsResourceByFileName(String filename,String filePath){
   
    	try {
    		return new UrlResource(Paths.get(filePath)
					.resolve(filename)
					.toUri());
    		
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
    	
    	return null;
    	
    }
    
   
}
