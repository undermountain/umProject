package dbsiter.table;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

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

public class IndexCR extends ControllerBase {

	@Override
	protected void doBefore() throws IOException {

		model=getModel();

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
	private Model getModel() throws UnsupportedEncodingException {

        model.title=lib.UMConst.SITENAME_DBSITER;
        model.heading="テーブル一覧";

        File tableFiles=new File(Path.getSavePath(common.lib.Util.fillInZero(Integer.valueOf(getUserId()), 6), EDir.db));
        String[] tableNames=tableFiles.list();
        List<DataTableInfo> dtiList=new ArrayList<DataTableInfo>();

        for(int i=0;i<tableNames.length;i++){
        	try {
				dtiList.add((DataTableInfo) ClassSerializer.deserialize(Path.getSavePath(common.lib.Util.fillInZero(Integer.valueOf(getUserId()), 6), EDir.db, tableNames[i])));
			} catch (NumberFormatException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			} catch (IOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
        }

        DataTable dt=new DataTable("テーブル名","データ数","列数"," ");

        int size=dtiList.size();
        for(int i=0;i<size;i++){

        	int count=0;
        	//データ編集リンク
        	ATag dataEdit=null;
        	if(dtiList.get(i).dataTable.columns==null || dtiList.get(i).dataTable.columns.length==0){
        		dataEdit=new ATag("javascript:alert('列が登録されていません。')", "データ編集");
        	}else{
	            dataEdit=new ATag("../data/dataindex", "データ編集");

				dataEdit.addUrlParameter(new KeyValue("tb",URLEncoder.encode(dtiList.get(i).name,"utf-8")));


	        	if(dtiList.get(i).dataTable!=null && dtiList.get(i).dataTable.rows!=null){
	        		count=dtiList.get(i).dataTable.rows.size();
	        	}
        	}
        	dataEdit.addCssClass("btn btn-primary");

        	//テーブル構造編集リンク
        	ATag tableEdit=new ATag("editindex","構造編集");
        	tableEdit.addUrlParameter(new KeyValue("tb",URLEncoder.encode(dtiList.get(i).name,"utf-8")));
        	tableEdit.addCssClass("btn btn-primary");

        	//削除リンク
        	ATag deleteLink=new ATag("delete","削除");
            deleteLink.addUrlParameter(new KeyValue("tb",URLEncoder.encode(dtiList.get(i).name,"utf-8")));
            deleteLink.addUrlParameter(model.createPToken());
            deleteLink.setAttribute("onclick", "return confirm('テーブルを削除しますか？')");
            deleteLink.addCssClass("btn btn-primary");

            //ボタングループ
            Elementer btns=new Elementer("div");
            btns.addCssClass("btn-group btn-group-sm");
            btns.setAttribute("role", "group");
            btns.addChild(dataEdit,tableEdit,deleteLink);

        	dt.addRow(dtiList.get(i).name,count
        			,dtiList.get(i).fieldList==null ? 0:dtiList.get(i).fieldList.size()
        					,btns.toHtml());
        }
        TableList listTb=new TableList(dt,true);
        listTb.addCssClass("table table-bordered");
        listTb.setRaw(3, true);


        model.addElement("list", listTb);

		return model;
	}


}
