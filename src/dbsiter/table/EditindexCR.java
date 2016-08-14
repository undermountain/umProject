package dbsiter.table;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import common.base.FieldBase;
import common.consts.EDir;
import common.consts.Path;
import common.data.DataTable;
import common.data.DataTableInfo;
import common.io.ClassSerializer;
import common.tag.ATag;
import common.tag.TableList;
import common.type.KeyValue;
import common.web.ControllerBase;
import common.web.Elementer;
import common.web.Model;

public class EditindexCR extends ControllerBase {

	@Override
	protected void doBefore() throws IOException {
		model=setModel();

	}

	@Override
	protected void doGet() throws IOException {

	}

	@Override
	protected void doPost() throws IOException {

	}

	@Override
	protected void doAfter() throws IOException {
		// TODO 自動生成されたメソッド・スタブ

	}

	public Model setModel() throws UnsupportedEncodingException {
        DataTableInfo dti=null;
        try {
			dti=ClassSerializer.deserialize(Path.getSavePath(model.getUserIdFillInZero(), EDir.db, model.request.getParameter("tb")));
		} catch (NumberFormatException | ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

        model.heading="「"+dti.name +"」テーブル構成編集";

        DataTable dt=new DataTable("列名","型"," ");

        if(dti.fieldList==null)dti.fieldList=new ArrayList<FieldBase>();
        int size=dti.fieldList.size();
        for(int i=0;i<size;i++){
        	FieldBase field=dti.fieldList.get(i);

            ATag editLink=new ATag("editfield","編集");
            editLink.addUrlParameter(new KeyValue("f",field.displayName));
            editLink.addUrlParameter(new KeyValue("tb", URLEncoder.encode(dti.name,"utf-8")));
            editLink.addCssClass("btn btn-primary");

            ATag deleteLink=new ATag("deletefield","削除");
            deleteLink.addUrlParameter(new KeyValue("f",field.displayName));
            deleteLink.addUrlParameter(new KeyValue("tb", URLEncoder.encode(dti.name,"utf-8")));
            deleteLink.addUrlParameter(model.createPToken());
            deleteLink.addCssClass("btn btn-primary");

          //ボタングループ
            Elementer btns=new Elementer("div");
            btns.addCssClass("btn-group btn-group-sm");
            btns.setAttribute("role", "group");
            btns.addChild(editLink,deleteLink);

        	dt.addRow(field.displayName,common.field.Util.getFieldDisplayName(field),btns.toHtml());
        }
        TableList listTb=new TableList(dt,true);
        listTb.addCssClass("table table-bordered");
        listTb.setRaw(listTb.dataTable.columns.length-1, true);


        model.addElement("list", listTb);

        ATag createAtag=new ATag("createfield", "列追加");
        createAtag.addCssClass("btn btn-primary");
        createAtag.addUrlParameter(new KeyValue("tb", URLEncoder.encode(dti.name,"utf-8")));
        model.addElement("createlink", createAtag);


        //リンク
        ATag edit=new ATag("edit", "テーブル編集");
        edit.addUrlParameter(new KeyValue("tb",request.getParameter("tb")));

        model.addElement("edit",edit);


        //パンくず
        ATag back=new ATag("index","テーブル一覧");
        model.addElement("back",back);

        model.checkToken=true;

		return model;
	}

}
