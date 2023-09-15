package mmk.com.sg.dto.model;

public class FileUploadResponse {
	 	
	    
	    private Long id;
	    private String annex;
	    private Long annexNo;
	    private String description;
	    private Long recordingId;
	    private String fileName;
	    private String downloadUri;
	    private long size;
	    
		public String getFileName() {
			return fileName;
		}
		public void setFileName(String fileName) {
			this.fileName = fileName;
		}
		public String getDownloadUri() {
			return downloadUri;
		}
		public void setDownloadUri(String downloadUri) {
			this.downloadUri = downloadUri;
		}
		public long getSize() {
			return size;
		}
		public void setSize(long size) {
			this.size = size;
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
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public Long getRecordingId() {
			return recordingId;
		}
		public void setRecordingId(Long recordingId) {
			this.recordingId = recordingId;
		}

		
	    
}
