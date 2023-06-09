package mission2prj;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import getterSetter.Homepage;

public class main {

	public static void main(String[] args) throws ClassNotFoundException, IOException, SQLException {
//		DbInsert insert = new DbInsert();
		Homepage homepage = new Homepage();
//		insert.dbInsert(homepage);
//		DbSelect select = new DbSelect();
//		Homepage homepage = new Homepage();
//		select.dbSelect(homepage);
//		totalCount total = new totalCount();
//		total.total();
//		selectWifiDistance distance = new selectWifiDistance();
//		distance.selectWifiDistance("32", "128");
//		DbDelete delete = new DbDelete();
//		delete.dbDelete();
		selectWifiDistance wifi = new selectWifiDistance();
		wifi.selectWifiDistance("32.2", "123.33");
	}
}
