package mmk.com.sg.data.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "C2IS_CHANGE_SUGGESTION")
public class StatementChgSuggestion implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1035232422580685919L;
	
	@Id
	@GeneratedValue(generator="C2IS_STATEMENT_SEQ")
	@SequenceGenerator(name="C2IS_STATEMENT_SEQ",sequenceName="C2IS_STATEMENT_SEQ", allocationSize=1)
	private Long id;
	
	
	@Column(name = "RECORDING_ID")
	private Long recordingId;
	
	@Column(name = "SUGGESTION_THREAD_ID")
	private String suggestionThreadId;
	
	@Lob
    @Column(name = "SUGGESTIONG_THREAD")
	private String suggestionThread;
	
	
	@Column(name = "CREATED_DT")
	private Date createdDt;
	
	@Column(name = "LAST_UPDATED_DT")
	private Date lastUpdatedDt;
	
	

	public StatementChgSuggestion() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public StatementChgSuggestion(Long id, Long recordingId, String suggestionThread) {
		super();
		this.id = id;
		this.recordingId = recordingId;
		this.suggestionThread = suggestionThread;
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

	public String getSuggestionThread() {
		return suggestionThread;
	}

	public void setSuggestionThread(String suggestionThread) {
		this.suggestionThread = suggestionThread;
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

	

	public String getSuggestionThreadId() {
		return suggestionThreadId;
	}


	public void setSuggestionThreadId(String suggestionThreadId) {
		this.suggestionThreadId = suggestionThreadId;
	}

	


	@Override
	public String toString() {
		return "StatementChgSuggestion [id=" + id + ", recordingId=" + recordingId + ", suggestionThread="
				+ suggestionThread + "]";
	}
	
	
	
	

}
