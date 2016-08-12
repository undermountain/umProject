package dbsiter.data;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import common.base.FieldBase;
import common.consts.EDir;
import common.consts.Path;
import common.data.DataTableInfo;
import common.field.Image;
import common.io.ClassSerializer;
import common.tag.ATag;
import common.tag.InputTable;
import common.type.KeyValue;
import common.web.ControllerBase;
import common.web.Util;

public class DataeditCR extends ControllerBase {

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
		if(!setModel()){
			response.sendRedirect("dataindex?tb="+URLEncoder.encode(request.getParameter("tb"),"utf-8"));
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
        int key=Integer.valueOf(request.getParameter("key"));
        for(int i=0;i<size;i++){
        	if(common.lib.Util.getClassName(model.getField(dti.fieldList.get(i).displayName)).equals(Image.class.getSimpleName())
        			&& model.getField(dti.fieldList.get(i).displayName).getValue()==null){

        	}else{
        		dti.dataTable.set(key,dti.fieldList.get(i).displayName,model.getField(dti.fieldList.get(i).displayName).getValue());
        	}
        }

        ClassSerializer.serialize(dti, path);

        setMessage("テーブル「"+dti.name+"」にデータが更新されました。");
		response.sendRedirect("dataindex?tb="+URLEncoder.encode(dti.name, "utf-8"));

	}

	@Override
	protected void doAfter() throws IOException {
		// TODO 自動生成されたメソッド・スタブ

	}


	private boolean setModel() throws UnsupportedEncodingException {

        model.title=lib.UMConst.SITENAME_DBSITER;
        model.heading="データ閲覧・入力";

        model.checkToken=true;

		DataTableInfo dti=null;
		String path=Path.getSavePath(common.lib.Util.fillInZero(Integer.valueOf(model.getUserId()), 6), EDir.db, request.getParameter("tb"));
        try {
			dti=ClassSerializer.deserialize(path);
		} catch (NumberFormatException | ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
        FieldBase[] fields=dti.fieldList.toArray(new FieldBase[dti.fieldList.size()]);

        //GETの場合
        if(request.getMethod().equals("GET")){
	        for(int i=0;i<fields.length;i++){
	        	fields[i].setValue((String)dti.dataTable.get(Integer.valueOf(request.getParameter("key")), fields[i].displayName));
	        }
        }

        model.addField(fields);

        InputTable it=new InputTable("更新", dti.fieldList.toArray(new FieldBase[dti.fieldList.size()]));

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
