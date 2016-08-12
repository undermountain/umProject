package dbsiter.data;

import java.io.IOException;
import java.net.URLEncoder;

import common.consts.EDir;
import common.consts.Path;
import common.data.DataTableInfo;
import common.io.ClassSerializer;
import common.web.ControllerBase;

public class DatadeleteCR extends ControllerBase {

	@Override
	protected void doBefore() throws IOException {

		if(!request.getParameterMap().containsKey("tb")){
			response.sendRedirect("index");
			return;
		}
		if(!request.getParameterMap().containsKey("key")){
			response.sendRedirect("dataindex?tb="+URLEncoder.encode(request.getParameter("tb"),"utf-8"));
			return;
		}
		if(!checkPToken()){
			response.sendRedirect("dataindex?tb="+URLEncoder.encode(request.getParameter("tb"),"utf-8"));
			return;
		}
		DataTableInfo dti=null;
		String path=Path.getSavePath(common.lib.Util.fillInZero(Integer.valueOf(model.getUserId()), 6), EDir.db, request.getParameter("tb"));
        try {
			dti=ClassSerializer.deserialize(path);
		} catch (NumberFormatException | ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			response.sendRedirect("dataindex?tb="+URLEncoder.encode(request.getParameter("tb"),"utf-8"));
			return;
		}

        dti.dataTable.rows.remove(Integer.valueOf(request.getParameter("key")).intValue());

        ClassSerializer.serialize(dti, path);

        setMessage("データを削除しました。");
        response.sendRedirect("dataindex?tb="+URLEncoder.encode(request.getParameter("tb"),"utf-8"));
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
