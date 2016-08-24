package dbsiter.table;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import common.base.FieldBase;
import common.consts.EDir;
import common.consts.Path;
import common.data.DataTable;
import common.data.DataTableInfo;
import common.field.Button;
import common.field.Hidden;
import common.io.ClassSerializer;
import common.tag.ATag;
import common.tag.ColumnInfo;
import common.tag.TableList;
import common.type.KeyValue;
import common.web.ControllerBase;
import common.web.Elementer;
import common.web.Model;

public class EditindexCR extends ControllerBase {

	@Override
	protected void doBefore() throws IOException {
		if(!request.getParameterMap().containsKey("tb")){
			response.sendRedirect("index");
			return;
		}
		model=setModel();

	}

	@Override
	protected void doGet() throws IOException {

	}

	@Override
	protected void doPost() throws IOException {
        DataTableInfo dti=null;
        try {
			dti=ClassSerializer.deserialize(Path.getSavePath(model.getUserIdFillInZero(), EDir.db, model.request.getParameter("tb")));
		} catch (NumberFormatException | ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			return;
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			return;
		}

        String[] newColumns=new String[dti.dataTable.columns.length];
        for(int i=0;i<dti.dataTable.columns.length;i++){
        	newColumns[Integer.valueOf(request.getParameter(dti.dataTable.columns[i]))]=dti.dataTable.columns[i];
        }


        List<ColumnInfo> newColumnInfos=new ArrayList<ColumnInfo>();
        for(int i=0;i<dti.dataTable.columns.length;i++){
        	newColumnInfos.add(dti.getColumnInfo(newColumns[i]));
        }


        List<FieldBase> newFields=new ArrayList<FieldBase>();
        for(int i=0;i<dti.dataTable.columns.length;i++){
        	int index=i;
        	newFields.add(dti.fieldList.stream().filter(m->m.displayName==newColumns[index]).findFirst().get());
        }


        for(int i=0;i<dti.dataTable.rows.size();i++){
        	Object[] newData=new Object[dti.dataTable.columns.length];
        	for(int ii=0;ii<dti.dataTable.columns.length;ii++){
        		newData[ii]=dti.dataTable.get(i, newColumns[ii]);
            }
        	dti.dataTable.rows.set(i, newData);
        }

        dti.dataTable.columns=newColumns;
        dti.columnInfos=newColumnInfos;
        dti.fieldList=newFields;

        ClassSerializer.serialize(dti, Path.getSavePath(model.getUserIdFillInZero(), EDir.db, model.request.getParameter("tb")));

        setMessage("列の並び替えが完了しました。");

        response.sendRedirect("editindex?tb="+UrlEncode(request.getParameter("tb")));
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

        model.heading="「"+model.request.getParameter("tb") +"」テーブル編集";

        DataTable dt=new DataTable("表示順","列名","型"," ");

        if(dti.fieldList==null)dti.fieldList=new ArrayList<FieldBase>();
        int size=dti.fieldList.size();
        for(int i=0;i<size;i++){
        	FieldBase field=dti.fieldList.get(i);
        	Elementer div=new Elementer("div");
        	div.setAttribute("class", "no");

        	Elementer span=new Elementer("span");
        	span.inner=String.valueOf(i+1);

        	Hidden no=new Hidden(field.displayName, String.valueOf(i));
        	div.addChild(span,no);

        	model.addField(no);

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

        	dt.addRow(div.toHtml(),field.displayName,common.field.Util.getFieldDisplayName(field),btns.toHtml());
        }
        TableList listTb=new TableList(dt,true);
        listTb.addCssClass("table table-bordered");
        listTb.setRaw(listTb.dataTable.columns.length-1, true);
        listTb.setRaw(0,true);
        listTb.setId("list");

        model.addElement("list", listTb);

        ATag createAtag=new ATag("createfield", "列追加");
        createAtag.addCssClass("btn btn-primary");
        createAtag.addUrlParameter(new KeyValue("tb", URLEncoder.encode(dti.name,"utf-8")));
        model.addElement("createlink", createAtag);


        //リンク
        ATag edit=new ATag("edit", "テーブル設定");
        edit.addUrlParameter(new KeyValue("tb",request.getParameter("tb")));

        model.addElement("edit",edit);

    	//データ編集リンク
    	ATag dataindex=null;
    	if(dti.dataTable.columns==null || dti.dataTable.columns.length==0){
    		dataindex=new ATag("javascript:alert('列が登録されていません。\\n「列設定」から列を追加してください。')", "データ編集");
    	}else{
            dataindex=new ATag("dataindex", "データ編集");
            dataindex.addUrlParameter(new KeyValue("tb",request.getParameter("tb")));

    	}
    	 model.addElement("dataindex",dataindex);


        //パンくず
        ATag back=new ATag("index","テーブル一覧");
        model.addElement("back",back);

        //並び替え実行ボタン
        Button btn =new Button("sort","並替");
        btn.setAttribute("disabled", "disabled");
        btn.setAttribute("class", "btn");

        model.addElement(btn);

        model.checkToken=true;

		return model;
	}

}
