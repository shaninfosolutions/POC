package mmk.com.sg.dto.model;

import java.io.Serializable;

public class PDFFileSignature implements Serializable{
	
	
	 	/**
	 * 
	 */
	private static final long serialVersionUID = 5048439507430547174L;
		private Long id;
	    private String annex;
	    private Long annexNo;
	    private Long recordingId;
	    private String fileName;
	    
	    
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getAnnex() {
			return annex;
		}
		public void setAnnex(String annex) {
			this.annex = annex;
		}
		public Long getAnnexNo() {
			return annexNo;
		}
		public void setAnnexNo(Long annexNo) {
			this.annexNo = annexNo;
		}
		public Long getRecordingId() {
			return recordingId;
		}
		public void setRecordingId(Long recordingId) {
			this.recordingId = recordingId;
		}
		public String getFileName() {
			return fileName;
		}
		public void setFileName(String fileName) {
			this.fileName = fileName;
		}    

}
