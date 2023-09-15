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
@Table(name = "C2IS_CHANGE_COMMENT")
public class StatementChgComment implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2756336122595637704L;
	@Id
	@GeneratedValue(generator="C2IS_CHANGE_COMMENT_SEQ")
	@SequenceGenerator(name="C2IS_CHANGE_COMMENT_SEQ",sequenceName="C2IS_CHANGE_COMMENT_SEQ", allocationSize=1)
	private Long id;
	
	@Column(name = "RECORDING_ID")
	private Long recordingId;
	
	@Column(name="COMMENT_THREAD_ID")
	private String commentThreadId;
	
	@Column(name="COMMENT_ID")
	private String commentId;
	
	@Lob
    @Column(name = "COMMENT_THREAD")
	private String commentThread;
	
	@Column(name = "RESOLVED_BY")
	private String resolvedBy;
	
	
	@Column(name = "RESOLVED_AT")
	private Date resolvedAt;
	
	@Column(name = "CREATED_DT")
	private Date createdDt;
	
	@Column(name = "LAST_UPDATED_DT")
	private Date lastUpdatedDt;


	public StatementChgComment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StatementChgComment(Long id, Long recordingId, String commentThread, String resolvedBy, Date resolvedAt,
			Date createdDt, Date lastUpdatedDt) {
		super();
		this.id = id;
		this.recordingId = recordingId;
		this.commentThread = commentThread;
		this.resolvedBy = resolvedBy;
		this.resolvedAt = resolvedAt;
		this.createdDt = createdDt;
		this.lastUpdatedDt = lastUpdatedDt;
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

	public String getCommentThread() {
		return commentThread;
	}

	public void setCommentThread(String commentThread) {
		this.commentThread = commentThread;
	}
	

	public String getCommentThreadId() {
		return commentThreadId;
	}

	public void setCommentThreadId(String commentThreadId) {
		this.commentThreadId = commentThreadId;
	}

	public String getResolvedBy() {
		return resolvedBy;
	}

	public void setResolvedBy(String resolvedBy) {
		this.resolvedBy = resolvedBy;
	}

	public Date getResolvedAt() {
		return resolvedAt;
	}

	public void setResolvedAt(Date resolvedAt) {
		this.resolvedAt = resolvedAt;
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

	public String getCommentId() {
		return commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "StatementChgComment [id=" + id + ", recordingId=" + recordingId + ", commentThread=" + commentThread
				+ ", resolvedBy=" + resolvedBy + ", resolvedAt=" + resolvedAt + ", createdDt=" + createdDt
				+ ", lastUpdatedDt=" + lastUpdatedDt + "]";
	}
	
}
