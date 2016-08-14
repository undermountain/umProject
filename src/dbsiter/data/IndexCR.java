package dbsiter.data;

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

public class IndexCR extends ControllerBase {

	@Override
	protected void doBefore() throws IOException {
		if(!checkAuth())return;
		setModel();
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


	private void setModel() {

        model.title=lib.UMConst.SITENAME_DBSITER;
        model.heading="データ一覧";


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

        DataTable dt=new DataTable("テーブル名","データ数");

        int size=dtiList.size();
        for(int i=0;i<size;i++){
        	int count=0;
        	ATag indexAtag=null;
        	if(dtiList.get(i).dataTable.columns==null || dtiList.get(i).dataTable.columns.length==0){
        		indexAtag=new ATag("javascript:alert('列が登録されていません。')", dtiList.get(i).name);
        	}else{
	            indexAtag=new ATag("dataindex", dtiList.get(i).name);
	            try {
					indexAtag.addUrlParameter(new KeyValue("tb",URLEncoder.encode(dtiList.get(i).name,"utf-8")));
				} catch (UnsupportedEncodingException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}

	        	if(dtiList.get(i).dataTable!=null && dtiList.get(i).dataTable.rows!=null){
	        		count=dtiList.get(i).dataTable.rows.size();
	        	}
        	}
        	//dt.addRow(dtiList.get(i).name,count);
        	dt.addRow(indexAtag.toHtml(),count);
        }
        TableList listTb=new TableList(dt,true);
        listTb.addCssClass("table table-bordered");
        //listTb.setFormat(0, "<a href='dataindex?tb=%1$s'>%1$s</a>");
        listTb.setRaw(0, true);
        model.addElement("list", listTb);

	}

}
