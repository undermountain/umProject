package dbsiter.table;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

import common.consts.EDir;
import common.consts.Path;
import common.data.DataTableInfo;
import common.io.ClassSerializer;
import common.web.ControllerBase;

public class DeletefieldCR extends ControllerBase {

	@Override
	protected void doBefore() throws IOException {
		if(!request.getParameterMap().containsKey("tb") && !request.getParameterMap().containsKey("f")){
			response.sendRedirect("index");
			return;
		}
		File file=new File(common.consts.Path.getSavePath(getUserIdFillInZero(), EDir.db, request.getParameter("tb")));
		if(!file.exists()){
			response.sendRedirect("index");
			return;
		}
		DataTableInfo dti=null;

		try {
			dti=ClassSerializer.deserialize(Path.getSavePath(getUserIdFillInZero(), EDir.db, request.getParameter("tb")));
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			response.sendRedirect("index");
			return;
		}
		dti.removeField(request.getParameter("f"));

		ClassSerializer.serialize(dti, Path.getSavePath(getUserIdFillInZero(), EDir.db, request.getParameter("tb")));

		setMessage("テーブル「"+request.getParameter("tb")+"」の「"+request.getParameter("f")+"」を削除しました。");
		response.sendRedirect("Editindex?tb="+URLEncoder.encode(request.getParameter("tb"), "utf-8"));
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
