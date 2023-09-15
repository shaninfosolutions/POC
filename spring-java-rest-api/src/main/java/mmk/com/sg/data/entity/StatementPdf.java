package mmk.com.sg.data.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
@Entity
@Table(name = "C2IS_STATEMENT_PDF")
public class StatementPdf implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1013466359777195445L;
	
	@Id
	@GeneratedValue(generator="C2IS_STATEMENT_PDF_SEQ")
	@SequenceGenerator(name="C2IS_STATEMENT_PDF_SEQ",sequenceName="C2IS_STATEMENT_PDF_SEQ", allocationSize=1)
	private long id;
	
	@Column(name = "RECORDING_ID")
	private Long recordingId;
	
	@Column(name = "FILENAME")
	private String fileName;
	
	@Column(name = "FILE_PATH")
	private String filePath;
	
	@Column(name = "FILE_VER_NO")
	private long fileVerNo;

	public StatementPdf() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public long getFileVerNo() {
		return fileVerNo;
	}

	public void setFileVerNo(long fileVerNo) {
		this.fileVerNo = fileVerNo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "StatementPdf [id=" + id + ", recordingId=" + recordingId + ", fileName=" + fileName + ", filePath="
				+ filePath + ", fileVerNo=" + fileVerNo + "]";
	}
	
	
	

}
