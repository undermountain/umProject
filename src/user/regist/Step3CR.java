package user.regist;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import common.consts.EDir;
import common.data.DBManager;
import common.data.DataTable;
import common.web.ControllerBase;
import lib.DBLib;

public class Step3CR extends ControllerBase {

	@Override
	protected void doBefore() throws IOException {


        model.title=lib.UMConst.SITENAME_DBSITER;
        model.heading="ユーザー登録";


        DBManager dbm=DBLib.GetDBManager();
        DataTable dt;
        int id;
		try {
			dt = dbm.select("select * from tempusertb where token=?", new Object[]{request.getParameter("t")});

			if(dt.rows.size()==0){
				model.addErrorMessage("指定されたURLは期限切れ、もしくは誤っています。");
				model.addData("msg","登録を確認してください。");
	        	return;
			}

			dbm.execute("insert into usertb(mail,password,registdate,name)values(?,?,CURRENT_TIMESTAMP,?)", dt.get(0, "mail"),dt.get(0, "password"),dt.get(0, "mail"));

			id=dbm.executeReadInt("select id from usertb where mail=?", new Object[]{ dt.get(0, "mail")});

		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			model.addErrorMessage("指定されたURLは期限切れ、もしくは誤っています。");
			model.addData("msg","登録を確認してください。");
			return;
		}finally {
			dbm.close();
		}


        //フォルダ作成
        String ud=common.consts.Path.CUSTOMER_ROOT+common.lib.Util.fillInZero(id, 6);
        File userDir=new File(ud);
        userDir.mkdir();
        for(int i=0;i<EDir.values().length;i++){
        	File subDir=new File(ud+File.separator+EDir.values()[i].name());
        	subDir.mkdir();
        }


        model.addData("msg", dt.get(0, "mail")+"様<br>登録が完了いたしました。<br><a href=\"/um/user/info/login\">ログインページ</a>からログインしてください。");

	}

	@Override
	protected void doGet() throws IOException {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	protected void doPost() throws IOException {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	protected void doAfter() throws IOException {
		// TODO 自動生成されたメソッド・スタブ

	}

}
