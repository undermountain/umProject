package servlet;

import common.web.ServletBase;

public class PubServlet extends ServletBase {

	@Override
	public String globalSiteName() {
		// TODO 自動生成されたメソッド・スタブ
		return "UM";
	}

	@Override
	protected String siteName() {
		// TODO 自動生成されたメソッド・スタブ
		return "Pub";
	}

	@Override
	protected String defaultKindName() {
		// TODO 自動生成されたメソッド・スタブ
		return "Home";
	}

	@Override
	protected String defaultPageName() {
		// TODO 自動生成されたメソッド・スタブ
		return "Index";
	}

	@Override
	protected String loginUrl() {
		// TODO 自動生成されたメソッド・スタブ
		return lib.UMConst.LOGIN_URL;
	}

	@Override
	protected String defaultUseHeader() {
		// TODO 自動生成されたメソッド・スタブ
		return "pub";
	}

	@Override
	protected boolean authCheck() {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

}
