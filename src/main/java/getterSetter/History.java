package getterSetter;

import java.sql.Timestamp;
import lombok.*;

@Data
@AllArgsConstructor
public class History {
	private int ID;
	private double LNT;			//37 - X좌표
	private double LAT;			//126.96675 - Y좌표
	private Timestamp InquiryDate;	//2023-03-20 10:58:16.0 - 작업일자

	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public double getLNT() {
		return LNT;
	}
	public void setLNT(double lNT) {
		this.LNT = lNT;
	}
	public double getLAT() {
		return LAT;
	}
	public void setLAT(double lAT) {
		this.LAT = lAT;
	}
	public Timestamp getInquiryDate() {
		return InquiryDate;
	}
	public void setInquiryDate(Timestamp inquiryDate) {
		InquiryDate = inquiryDate;
	}


}
