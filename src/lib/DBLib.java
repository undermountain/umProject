package lib;

import common.data.DBManager;

public class DBLib {
	public static DBManager GetDBManager(){
		return new DBManager("127.0.0.1", "umDB", "postgres", "asdfasdf");
	}
}
