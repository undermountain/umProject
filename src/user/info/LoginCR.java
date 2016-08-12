package user.info;

import java.io.IOException;
import java.sql.SQLException;

import common.data.DBManager;
import common.data.DataTable;
import common.field.Mail;
import common.field.Password;
import common.validation.Required;
import common.web.ControllerBase;
import common.web.Model;
import lib.DBLib;

public class LoginCR extends ControllerBase {

	@Override
	protected void doBefore() throws IOException {
		// TODO 自動生成されたメソッド・スタブ
		model=setModel();
	}

	@Override
	protected void doGet() throws IOException {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	protected void doPost() throws IOException {

		DBManager dbm=DBLib.GetDBManager();
		DataTable user=null;
		try {
			user=dbm.select("select id,mail,name from usertb where mail=? and password=?", new Object[]{model.getField("id").getValue(),model.getField("password").getValue()});
			if(user==null || user.rows.size()==0){
				model.addErrorMessage("IDもしくはパスワードが間違っています。");
				return;
			}
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			common.io.Util.errorLog(e.toString(), "ユーザ登録画面", "Step1");
			return;
		}finally {
			dbm.close();
		}
		setAuth(user.get(0, "id").toString(),user.get(0, "mail").toString(),user.get(0, "name")==null ? user.get(0, "mail").toString():user.get(0, "name").toString());

		String to=request.getParameter("to");
		if(to!=null && !to.equals("")){
			response.sendRedirect(to);
			return;
		}

		response.sendRedirect("/um/user/home/index");
	}

	@Override
	protected void doAfter() throws IOException {
		// TODO 自動生成されたメソッド・スタブ

	}



	public Model setModel() {

        model.title=lib.UMConst.SITENAME_DBSITER;
        model.heading="ログイン";

        model.checkToken=true;

        Mail mail=new Mail("id","ID(メールアドレス)");
        mail.setMaxlength(1000);
        mail.addValidation(new Required());
        mail.setClass("form-control");

        Password pass=new Password("password","パスワード");
        pass.setMaxlength(50);
        pass.addValidation(new Required());
        pass.setClass("form-control");

        model.addField(mail,pass);
		return model;

	}
}
