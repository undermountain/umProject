package lib;

import java.io.IOException;

import common.consts.EDir;

public class UMConst {
	public final static String SITENAME_DBSITER="DBSiter";
	public static final String LOGIN_URL = "/um/user/info/login";

	public static void saveTable(String user,String fileName,Object table) throws IOException{
		common.io.ClassSerializer.serialize(table, common.consts.Path.getSavePath(user, EDir.db, fileName));
	}

	public static <T> T loadTable(String user,String fileName) throws ClassNotFoundException, IOException{
		return common.io.ClassSerializer.deserialize(common.consts.Path.getSavePath(user, EDir.db, fileName));
	}
}
