package mmk.com.sg.utility;

import org.springframework.beans.factory.annotation.Value;

import com.aspose.pdf.Document;
import com.aspose.pdf.PKCS7;
import com.aspose.pdf.TimestampSettings;
import com.aspose.pdf.facades.PdfFileSignature;


public class AsposeDigitalSign {
	
	@Value("${pdf.file.input}")
	private   String pdfFileInput;
	
	@Value("${pdf.file.output}")
	private   String pdfFileOutput;
	
	@Value("${pfx.file.location}")
	private  String pfxFile;
	
	@Value("${pfx.file.password}")
	private  String pfxFilePassword;
	
	@Value("${timestamp.tsr.url}")
	private  String timestampUrl;
	
	private static String _dataDir = "C:\\\\Users\\\\user\\\\Documents\\\\workspace-spring-tool-suite-4-4.18.1.RELEASE\\\\self-training\\\\Angular-Java-REST-API\\\\spring-java-rest-api\\\\Digital-Signature-Input\\\\";
	private static String _dataDirout = "C:\\\\Users\\\\user\\\\Documents\\\\workspace-spring-tool-suite-4-4.18.1.RELEASE\\\\self-training\\\\Angular-Java-REST-API\\\\spring-java-rest-api\\\\Digital-Signature-Output\\\\";
	
	
	public  static String SignDocument(String fileName,long version) {
	        String inFile = "C:\\Users\\user\\Documents\\workspace-spring-tool-suite-4-4.18.1.RELEASE\\self-training\\Angular-Java-REST-API\\spring-java-rest-api\\Digital-Signature-Input\\" +fileName;
	        System.out.print("Line 30 "+inFile);
	        String outFile = "C:\\Users\\user\\Documents\\workspace-spring-tool-suite-4-4.18.1.RELEASE\\self-training\\Angular-Java-REST-API\\spring-java-rest-api\\Digital-Signature-Output\\" +  "Signed "+version+"_"+fileName;
	        
	        System.out.print("Line 30 "+outFile);
	        Document document = new Document(inFile);
	        System.out.print("Begin");

	        PdfFileSignature signature = new PdfFileSignature(document);

	        PKCS7 pkcs = new PKCS7("C:\\mytutorial\\pdfsample\\TSA\\test1day_expired\\cert.pfx", "password1"); // Use PKCS7/PKCS7Detached
	        //com.aspose.pdf.DocMDPSignature docMdpSignature = new com.aspose.pdf.DocMDPSignature(pkcs, 
	        								//com.aspose.pdf.DocMDPAccessPermissions.FillingInForms);
	        pkcs.setUseLtv(false);
	       
	        signature.sign(1, "C2IS Digital Signature POC", "Singapore Custom", "Singapore",true, new java.awt.Rectangle(10, 10, 300, 50), pkcs);
	        
	        signature.save(outFile);
	        
	        String outFileName= "Signed "+version+"_"+fileName;

	        return outFileName;

	}
	
	 public static String SignWithTimeStampServer(String fileName,long version) {
		   	String inFile = _dataDir + fileName;
	        String outFile = _dataDirout +  "Signed tsr "+version+"_"+fileName;
	        Document document = new Document(inFile);
	        PdfFileSignature signature = new PdfFileSignature(document);
	        System.out.print("Begin");
	        PKCS7 pkcs = new PKCS7("C:\\mytutorial\\pdfsample\\TSA\\test1day_expired\\cert.pfx", "password1");
	        com.aspose.pdf.DocMDPSignature docMdpSignature = new com.aspose.pdf.DocMDPSignature(pkcs, 
	        						com.aspose.pdf.DocMDPAccessPermissions.FillingInForms);

	        TimestampSettings timestampSettings = new TimestampSettings("https://freetsa.org/tsr", ""); // User/Password can
	                                                                                                    // be omitted
	        pkcs.setTimestampSettings(timestampSettings);
	        pkcs.setUseLtv(true);
	        pkcs.setShowProperties(true);
	        java.awt.Rectangle rect = new java.awt.Rectangle(10, 10, 300, 50);
	        // Create any of the three signature types
	        signature.sign(1, "C2IS Digital Signature POC::TimeStampServer", "Singapore Custom", "Singapore", true, rect, pkcs);
	        signature.save(outFile);
	        
	        String outFileName= "Signed tsr "+version+"_"+fileName;
	        
	        return outFileName;
	        
	    }
	
	

}
