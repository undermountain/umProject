package dbsiter.table;

import java.io.File;
import java.io.IOException;

import common.consts.EDir;
import common.web.ControllerBase;

public class DeleteCR extends ControllerBase {

	@Override
	protected void doBefore() throws IOException {
		if(!checkAuth())return;
		if(!request.getParameterMap().containsKey("tb")){
			response.sendRedirect("index");
			return;
		}
		File file=new File(common.consts.Path.getSavePath(getUserIdFillInZero(), EDir.db, request.getParameter("tb")));
		if(!file.exists()){
			response.sendRedirect("index");
			return;
		}
		file.delete();
		setMessage("テーブル「"+request.getParameter("tb")+"」を削除しました。");
		response.sendRedirect("index");
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
