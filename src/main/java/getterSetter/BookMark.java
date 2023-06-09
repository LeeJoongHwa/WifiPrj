package getterSetter;

import java.sql.*;

public class BookMark {
	private int ID;
	private String BookName;
	private int Sequence;
	private Timestamp CreateDate;
	private Timestamp UpdateDate;
	private String X_SWIFI_MGR_NO;
	private String X_SWIFI_MAIN_NM;
	
	public String getX_SWIFI_MAIN_NM() {
		return X_SWIFI_MAIN_NM;
	}
	public void setX_SWIFI_MAIN_NM(String x_SWIFI_MAIN_NM) {
		X_SWIFI_MAIN_NM = x_SWIFI_MAIN_NM;
	}
	public String getX_SWIFI_MGR_NO() {
		return X_SWIFI_MGR_NO;
	}
	public void setX_SWIFI_MGR_NO(String x_SWIFI_MGR_NO) {
		X_SWIFI_MGR_NO = x_SWIFI_MGR_NO;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getBookName() {
		return BookName;
	}
	public void setBookName(String bookName) {
		BookName = bookName;
	}
	public int getSequence() {
		return Sequence;
	}
	public void setSequence(int sequence) {
		Sequence = sequence;
	}
	public Timestamp getCreateDate() {
		return CreateDate;
	}
	public void setCreateDate(Timestamp createDate) {
		CreateDate = createDate;
	}
	public Timestamp getUpdateDate() {
		return UpdateDate;
	}
	public void setUpdateDate(Timestamp updateDate) {
		UpdateDate = updateDate;
	}
}
