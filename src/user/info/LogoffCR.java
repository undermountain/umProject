package user.info;

import java.io.IOException;

import common.web.ControllerBase;

public class LogoffCR extends ControllerBase {

	@Override
	protected void doBefore() throws IOException {
		// TODO 自動生成されたメソッド・スタブ
		removeAuth();
		response.sendRedirect(loginUrl);
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
