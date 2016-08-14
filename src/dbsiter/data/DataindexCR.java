package dbsiter.data;

import java.io.IOException;

import common.consts.EDir;
import common.consts.Path;
import common.data.DataTableInfo;
import common.io.ClassSerializer;
import common.tag.ATag;
import common.tag.ColumnInfo;
import common.tag.TableList;
import common.type.KeyValue;
import common.web.ControllerBase;
import common.web.Elementer;

public class DataindexCR extends ControllerBase {

	@Override
	protected void doBefore() throws IOException {

		if(!request.getParameterMap().containsKey("tb")){
			response.sendRedirect("index");
			return;
		}
		if(!setModel())return;
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

	private boolean setModel() throws IOException {

        model.title=lib.UMConst.SITENAME_DBSITER;
        model.heading="「"+request.getParameter("tb")+"」テーブル";

        DataTableInfo dti=null;
        try {
			dti=ClassSerializer.deserialize(Path.getSavePath(common.lib.Util.fillInZero(Integer.valueOf(model.getUserId()), 6), EDir.db, request.getParameter("tb")));
		} catch (NumberFormatException | ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			response.sendRedirect("index");
			return false;
		}
        //編集ボタンカラム追加
        dti.addColumn(" ");
        ColumnInfo colInfo=new ColumnInfo();
        colInfo.searchable=false;
        colInfo.orderable=false;
        if(dti.getColumnInfo(dti.columnInfos.size()-1)==null)dti.setColumnInfo(dti.columnInfos.size()-1,colInfo);

        KeyValue ptoken=model.createPToken();

        for(int i=0;i<dti.dataTable.rows.size();i++){

            ATag editLink=new ATag("dataedit","編集");
            editLink.addUrlParameter(new KeyValue("key",i));
            editLink.addUrlParameter(new KeyValue("tb",UrlEncode(request.getParameter("tb"))));
            editLink.addCssClass("btn btn-primary");


            ATag deleteLink=new ATag("datadelete","削除");
            deleteLink.addUrlParameter(new KeyValue("key",i));
            deleteLink.addUrlParameter(new KeyValue("tb",UrlEncode(request.getParameter("tb"))));
            deleteLink.addUrlParameter(ptoken);
            deleteLink.addCssClass("btn btn-primary");

        	//ボタングループ
            Elementer btns=new Elementer("div");
            btns.addCssClass("btn-group btn-group-sm");
            btns.setAttribute("role", "group");
            btns.addChild(editLink,deleteLink);

            dti.dataTable.rows.get(i)[dti.dataTable.columns.length-1]=btns.toHtml();
        }

        TableList listTb=new TableList(dti);
        listTb.addCssClass("table table-bordered");
        listTb.setId("list");

        listTb.setRaw(dti.dataTable.columns.length-1, true);

        model.addElement(listTb);

        ATag create=new ATag("datacreate","追加登録");
        create.addCssClass("btn btn-primary");
        create.addUrlParameter(new KeyValue("tb", UrlEncode(request.getParameter("tb"))));

        model.addElement("create", create);

        ATag back=new ATag("../table/index","テーブル一覧");
        //back.setClass("btn btn-default");
        back.addUrlParameter(new KeyValue("tb", UrlEncode(request.getParameter("tb"))));

        model.addElement("back", back);
        return true;
	}



}
