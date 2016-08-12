package dbsiter.data;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import common.base.FieldBase;
import common.consts.EDir;
import common.consts.Path;
import common.data.DataTableInfo;
import common.io.ClassSerializer;
import common.tag.ATag;
import common.tag.InputTable;
import common.type.KeyValue;
import common.web.ControllerBase;
import common.web.Util;

public class DatacreateCR extends ControllerBase {

	@Override
	protected void doBefore() throws IOException {

		if(!checkParameter("tb")){
			response.sendRedirect("index");
			return;
		}
		if(!setModel()){
			response.sendRedirect("Dataindex?tb="+URLEncoder.encode(request.getParameter("tb"),"utf-8"));
			return;
		}
	}

	@Override
	protected void doGet() throws IOException {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	protected void doPost() throws IOException {

		DataTableInfo dti=null;
		String path=Path.getSavePath(common.lib.Util.fillInZero(Integer.valueOf(model.getUserId()), 6), EDir.db, request.getParameter("tb"));
        try {
			dti=ClassSerializer.deserialize(path);
		} catch (NumberFormatException | ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			model.addErrorMessage("登録に失敗しました。");
			return;
		}
        int size=dti.fieldList.size();
        Object[] rowData=new Object[size];
        for(int i=0;i<size;i++){
        	rowData[i]=model.getField(dti.dataTable.columns[i]).getValue();
        }
        dti.dataTable.addRow(rowData);

        ClassSerializer.serialize(dti, path);

        setMessage("テーブル「"+dti.name+"」にデータが登録されました。");
		response.sendRedirect("dataindex?tb="+URLEncoder.encode(dti.name, "utf-8"));
	}

	@Override
	protected void doAfter() throws IOException {
		// TODO 自動生成されたメソッド・スタブ

	}


	private boolean setModel() throws UnsupportedEncodingException {
        model.title=lib.UMConst.SITENAME_DBSITER;
        model.heading="データ追加登録";

        model.checkToken=true;

        DataTableInfo dti=null;
        try {
			dti=ClassSerializer.deserialize(Path.getSavePath(common.lib.Util.fillInZero(Integer.valueOf(model.getUserId()), 6), EDir.db, request.getParameter("tb")));
		} catch (NumberFormatException | ClassNotFoundException | IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			return false;
		}

    	model.addField(dti.fieldList.toArray(new FieldBase[dti.fieldList.size()]));

        InputTable it=new InputTable("登録", dti.fieldList.toArray(new FieldBase[dti.fieldList.size()]));

        model.addElement("inputtable", it);

        ATag back1=new ATag("../table/index","テーブル一覧");
        back1.addUrlParameter(new KeyValue("tb",Util.urlEncode(request.getParameter("tb"))));

        model.addElement("back1",back1);

        ATag back2=new ATag("dataindex","「"+request.getParameter("tb")+"」テーブル");
        back2.addUrlParameter(new KeyValue("tb",Util.urlEncode(request.getParameter("tb"))));

        model.addElement("back2",back2);

        return true;
	}


}
