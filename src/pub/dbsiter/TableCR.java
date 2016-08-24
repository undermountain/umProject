package pub.dbsiter;

import java.io.IOException;

import javax.servlet.ServletException;

import common.consts.EDir;
import common.consts.Path;
import common.data.DataTableInfo;
import common.io.ClassSerializer;
import common.tag.TableList;
import common.web.ControllerBase;

public class TableCR extends ControllerBase {

	@Override
	protected boolean runCustom() throws IOException, ServletException {
        DataTableInfo dti=null;
        try {
			dti=ClassSerializer.deserialize(Path.getSavePath(request.getParameter("u"), EDir.db, request.getParameter("t")));
		} catch (NumberFormatException | ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			response.sendRedirect("index");
			return true;
		}

        if(!dti.display){

        	response.sendError(404, "非公開設定中");
        	return true;
        }

        TableList listTb=new TableList(dti);
        listTb.addCssClass("table table-bordered");
        listTb.setId("list");

        model.addElement("listTb", listTb);

        return view();
	}

	@Override
	protected void doBefore() throws IOException {
		// TODO 自動生成されたメソッド・スタブ

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
