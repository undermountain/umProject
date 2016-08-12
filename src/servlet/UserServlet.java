package servlet;

//import javax.servlet.annotation.WebServlet;

import common.web.ServletBase;

//@WebServlet("/user/*")
public class UserServlet extends ServletBase {

	@Override
	protected String siteName() {
		// TODO 自動生成されたメソッド・スタブ
		return "User";
	}

	@Override
	protected String defaultKindName() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	protected String defaultPageName() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	protected String loginUrl() {
		// TODO 自動生成されたメソッド・スタブ
		return lib.UMConst.LOGIN_URL;
	}

	@Override
	public String globalSiteName() {
		// TODO 自動生成されたメソッド・スタブ
		return "UM";
	}

	@Override
	protected String defaultUseHeader() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	protected boolean authCheck() {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

}
