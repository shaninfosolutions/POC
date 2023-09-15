package mmk.com.sg.data.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "C2IS_STATEMENT")
public class Statement implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5110293830097914959L;

	@Id
	@GeneratedValue(generator="C2IS_STATEMENT_SEQ")
	@SequenceGenerator(name="C2IS_STATEMENT_SEQ",sequenceName="C2IS_STATEMENT_SEQ", allocationSize=1)
	private Long id;
	
	
	@Column(name = "STATEMENT_NO")
	private String statementNo;
	
	@Column(name = "CASE_FILE_NO")
	private String caseFileNo;
	
	@Column(name = "LO_INCHARGE")
	private String personInCharge;
	
	@Column(name = "OFFENDER")
	private String offender;
	
	@Column(name = "STAGE")
	private String stage;
	
	@Lob
    @Column(name = "content")
    private String   content;
	
	@Column(name = "CREATED_DT")
	private Date createdDt;
	
	@Column(name = "LAST_UPDATED_DT")
	private Date lastUpdatedDt;

	public Statement() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Statement( String statementNo, String caseFileNo, String personInCharge, String offender,
			String content,String stage) {
		super();
		this.statementNo = statementNo;
		this.caseFileNo = caseFileNo;
		this.personInCharge = personInCharge;
		this.offender = offender;
		this.content=content;
		this.stage = stage;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatementNo() {
		return statementNo;
	}

	public void setStatementNo(String statementNo) {
		this.statementNo = statementNo;
	}

	public String getCaseFileNo() {
		return caseFileNo;
	}

	public void setCaseFileNo(String caseFileNo) {
		this.caseFileNo = caseFileNo;
	}

	public String getPersonInCharge() {
		return personInCharge;
	}

	public void setPersonInCharge(String personInCharge) {
		this.personInCharge = personInCharge;
	}

	public String getOffender() {
		return offender;
	}

	public void setOffender(String offender) {
		this.offender = offender;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}
	

	public Date getCreatedDt() {
		return createdDt;
	}

	public void setCreatedDt(Date createdDt) {
		this.createdDt = createdDt;
	}

	public Date getLastUpdatedDt() {
		return lastUpdatedDt;
	}

	public void setLastUpdatedDt(Date lastUpdatedDt) {
		this.lastUpdatedDt = lastUpdatedDt;
	}
	
	

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Statement [id=" + id + ", statementNo=" + statementNo 
				+ ", caseFileNo=" + caseFileNo
				+ ", personInCharge=" + personInCharge 
				+ ", offender=" + offender + ", stage=" + stage+ 
				"]";
	}
	
	
}
