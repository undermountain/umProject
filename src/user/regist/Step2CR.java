package user.regist;

import java.io.IOException;

import common.web.ControllerBase;

public class Step2CR extends ControllerBase {

	@Override
	protected void doBefore() throws IOException {

        model.title=lib.UMConst.SITENAME_DBSITER;
        model.heading="ユーザー登録";

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
