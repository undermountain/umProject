package user.regist;

import java.io.IOException;
import java.sql.SQLException;

import common.data.DBManager;
import common.field.Mail;
import common.field.Password;
import common.lib.MailG;
import common.tag.InputTable;
import common.validation.Comparison;
import common.validation.EComparison;
import common.validation.Required;
import common.validation.StringLength;
import common.validation.StringPattern;
import common.web.ControllerBase;
import lib.DBLib;

public class Step1CR extends ControllerBase {

	@Override
	protected void doBefore() throws IOException {

        model.title=lib.UMConst.SITENAME_DBSITER;
        model.heading="ユーザー登録";

        model.checkToken=true;

		//field
        Mail mail=new Mail("mail", "メールアドレス");
        mail.setImeModeOff();
        mail.required=true;
        mail.addValidation(new Required());
        mail.addValidation(new StringLength(null, 1000));
        mail.addValidation(new StringPattern(".*@.*\\..*", "「%s」はメールアドレスを入力してください。"));
        mail.setMaxlength(1000);
        mail.setPattern(".*@.*\\..*");
        mail.addCssClass("form-control");

        Password pass=new Password("password1", "パスワード");
        pass.required=true;
        pass.addValidation(new Required());
        pass.addValidation(new StringLength(4,50));
        pass.setMinlength(4);
        pass.setMaxlength(50);
        pass.addCssClass("form-control");

        Password pass2=new Password("password2", "パスワード確認用");
        pass2.required=true;
        pass2.addValidation(new Comparison(EComparison.equal,pass));
        pass2.addCssClass("form-control");



        model.addFieldAll(mail,pass,pass2);
        model.addElement("inputdiv", new InputTable("登録",mail,pass,pass2));
	}

	@Override
	protected void doGet() throws IOException {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	protected void doPost() throws IOException {

		//ok
		DBManager dbm=DBLib.GetDBManager();

		String token=common.lib.Util.createToken();

		try {
			if(dbm.executeReadInt("select count(*) from usertb where mail=?",new Object[]{ model.getField("mail").getValue()})>0){
				model.addErrorMessage(String.format("「%s」は既に登録されています。",model.getField("mail").getValue()));
				return;
			}
			if(dbm.executeReadInt("select count(*) from tempusertb where mail=?",new Object[]{model.getField("mail").getValue()})>0){
				dbm.execute("update tempusertb set password=?,registdate=CURRENT_TIMESTAMP,token=? where  mail=?",model.getField("password1").getValue(),token,model.getField("mail").getValue());
			}else{
				dbm.execute("insert into tempusertb(mail,password,registdate,token)values(?,?,CURRENT_TIMESTAMP,?)",model.getField("mail").getValue(),model.getField("password1").getValue(),token);
			}
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			common.io.Util.errorLog(e.toString(), "ユーザ登録画面", "Step1");
			return;
		}finally {
			dbm.close();
		}

		//send mail
		MailG mail=new MailG("undermountain.g","kingchan512276");
		mail.from="undermountain.g@gmail.com";
		mail.fromName="DBSiter";

		String body="ご登録ありがとうございます。\r\n下記URLにアクセスして登録を完了してください。\r\n";
		body+=request.getScheme()+"://"+common.web.Util.getDomain(request)+"/um/user/regist/Step3?t="+token;
		mail.sendMail(model.getField("mail").getStrValue(), "DBSiter登録メール", body);
		response.sendRedirect("Step2");
	}

	@Override
	protected void doAfter() throws IOException {
		// TODO 自動生成されたメソッド・スタブ

	}

}
