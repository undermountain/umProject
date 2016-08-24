package pub.home;

import java.io.IOException;

import common.web.ControllerBase;

public class InfomationCR extends ControllerBase {

	@Override
	protected void doBefore() throws IOException {
		setModel();

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


	private void setModel() {
		model.heading="Infomation";

	}


}
