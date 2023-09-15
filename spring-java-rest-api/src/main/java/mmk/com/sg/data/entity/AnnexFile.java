package mmk.com.sg.data.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "C2IS_DIGITAL_FILES_ANNEX")
public class AnnexFile implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4333083225107486227L;
	@Id
	@GeneratedValue(generator="C2IS_DIGITAL_FILES_ANNEX_SEQ")
	@SequenceGenerator(name="C2IS_DIGITAL_FILES_ANNEX_SEQ",sequenceName="C2IS_DIGITAL_FILES_ANNEX_SEQ", allocationSize=1)
	private long id;
	
	@Column(name = "RECORDING_ID")
	private Long recordingId;
	
	@Column(name = "ANNEX")
	private String annex;
	
	@Column(name = "ANNEX_NO")
	private Long annexNo;
	
	@Column(name = "FILENAME")
	private String fileName;
	
	@Column(name = "FILESIZE")
	private Long  fileSize;
	
	@Column(name = "DESCRIPTION")
	private String description;

	@Lob
    @Column(name = "OFFICIER_SIGNATURE")
    private String   officierSignature;
	
	@Lob
    @Column(name = "WITNESS_SIGNATURE")
    private String   witnessSignature;
	
	@Lob
    @Column(name = "INTERPRETER_SIGNATURE")
    private String   interpreterSignature;


	public AnnexFile() {
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

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}
	
	

	public String getOfficierSignature() {
		return officierSignature;
	}

	public void setOfficierSignature(String officierSignature) {
		this.officierSignature = officierSignature;
	}

	public String getWitnessSignature() {
		return witnessSignature;
	}

	public void setWitnessSignature(String witnessSignature) {
		this.witnessSignature = witnessSignature;
	}

	public String getInterpreterSignature() {
		return interpreterSignature;
	}

	public void setInterpreterSignature(String interpreterSignature) {
		this.interpreterSignature = interpreterSignature;
	}

	@Override
	public String toString() {
		return "AnnexFile [id=" + id + ", recordingId=" + recordingId + ", annex=" + annex + ", annexNo=" + annexNo
				+ ", fileName=" + fileName + ", description=" + description + "]";
	}

}
