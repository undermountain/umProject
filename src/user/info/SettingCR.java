package user.info;

import java.io.IOException;
import java.sql.SQLException;

import common.data.DBManager;
import common.field.Password;
import common.validation.Comparison;
import common.validation.EComparison;
import common.validation.Required;
import common.web.ControllerBase;
import common.web.Model;
import lib.DBLib;

public class SettingCR extends ControllerBase {

	@Override
	protected void doBefore() throws IOException {
		if(!checkAuth())return;
		model=setModel();

	}

	@Override
	protected void doGet() throws IOException {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	protected void doPost() throws IOException {

		DBManager dbm=DBLib.GetDBManager();
		try {
			if(dbm.executeReadInt("select count(*) from usertb where mail=?", new Object[]{request.getSession().getAttribute("mail")})==0){
				response.sendRedirect(loginUrl);
				return;
			}

			dbm.execute("update usertb set password=? where mail=?",model.getField("password1").getValue(),request.getSession().getAttribute("mail"));
			response.sendRedirect(loginUrl);

		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			common.io.Util.errorLog(e.toString(), "ユーザ登録画面", "Step1");
			return;
		}finally {
			dbm.close();
		}

		model.removeAuth();
	}

	@Override
	protected void doAfter() throws IOException {
		// TODO 自動生成されたメソッド・スタブ

	}

	public Model setModel() {

        model.title=lib.UMConst.SITENAME_DBSITER;
        model.heading="ユーザー設定";

        model.checkToken=true;

        Password pass=new Password("password1","パスワード");
        pass.setMaxlength(50);
        pass.addValidation(new Required());

        Password pass2=new Password("password2", "パスワード確認用");
        pass2.required=true;
        pass2.addValidation(new Comparison(EComparison.equal,pass));

        model.addField(pass,pass2);
		return model;

	}
}
