package hpsiter.home;

import java.io.IOException;

import common.web.ControllerBase;

public class IndexCR extends ControllerBase {

	@Override
	protected void doBefore() throws IOException {
		// TODO 自動生成されたメソッド・スタブ
		setModel();
	}

	private void setModel() {
		model.heading="HPSiter Top";

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
