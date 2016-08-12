package dbsiter.setting;

import java.io.IOException;

import common.web.ControllerBase;
import common.web.Model;

public class IndexCR extends ControllerBase {

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
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	protected void doAfter() throws IOException {
		// TODO 自動生成されたメソッド・スタブ

	}


	public Model setModel() {

        model.title=lib.UMConst.SITENAME_DBSITER;
        model.heading="設定";
		return model;
	}

}
