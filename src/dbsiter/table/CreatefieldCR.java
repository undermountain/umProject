package dbsiter.table;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import common.base.FieldBase;
import common.consts.EDir;
import common.consts.Path;
import common.data.DataTableInfo;
import common.field.Button;
import common.field.Hidden;
import common.field.Select;
import common.field.Text;
import common.io.ClassSerializer;
import common.tag.ATag;
import common.tag.InputTable;
import common.type.KeyValue;
import common.validation.Required;
import common.web.ControllerBase;
import common.web.Model;

public class CreatefieldCR extends ControllerBase {

	@Override
	protected void doBefore() throws IOException {
		if(!checkParameter("tb")){
			response.sendRedirect("index");
		}
		setModel();
		if(checkParameter("step1")){
			setModel1();
		}

		Button btn=new Button("step1","送信","1");
		model.addField(btn);
		InputTable it=new InputTable((FieldBase[])model.fieldList.toArray());
		model.addElement("inputtable", it);
	}



	@Override
	protected void doGet() throws IOException {
		setModel();

	}

	@Override
	protected void doPost() throws IOException {
		if(request.getParameter("step").equals("1")){
			setModel1();
		}else if(request.getParameter("step").equals("2")){
			setModel2();
		}

	}

	@Override
	protected void doAfter() throws IOException {
		// TODO 自動生成されたメソッド・スタブ

	}



	public void setModel() throws UnsupportedEncodingException {
		String tbName=request.getParameter("tb");

        model.title=lib.UMConst.SITENAME_DBSITER;
        model.heading="「"+tbName+"」テーブル 列追加";

        model.checkToken=true;

        //列追加Field宣言
        Hidden tb=new Hidden("tb",tbName);
        Hidden step=new Hidden("step1","1");

        Text name=new Text("name","列名");
        name.addValidation(new Required());
        name.setClass("form-control");

        name.addValidation(new common.base.ValidationBase(){

			@Override
			public boolean check(FieldBase field, Model model) {
				String path=Path.getSavePath(common.lib.Util.fillInZero(Integer.valueOf(getUserId()), 6), EDir.db, model.getField("tb").getValue());
				DataTableInfo dti=null;
		        try {
					dti=ClassSerializer.deserialize(path);
				} catch (NumberFormatException | ClassNotFoundException | IOException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
					model.addErrorMessage("列の登録に失敗しました。");
					return true;
				}
		        if(dti.dataTable.columns!=null)
			        for(int i=0;i<dti.dataTable.columns.length;i++){
			        	if(dti.dataTable.columns[i].equals(field.getValue())){
			        		this.errorMessage=String.format("「%s」は既に登録されています。", field.getValue());
			        		return false;
			        	}
			        }
				return true;
			}
        });



        Select type=new Select("type","データ型");
        common.field.Util.setFieldOptionItem(type);
        type.setClass("form-control");

        model.addField(tb,step,name,type);



        //Atag
        ATag back=new ATag("editindex","「"+tbName+"」テーブル構成編集");
        back.addUrlParameter(new KeyValue("tb",UrlEncode(tbName)));
        model.addElement("back", back);

	}

	private void setModel1() {
        model.getField("name").setAttribute("readonly", "readonly");
        model.getField("type").setAttribute("readonly", "readonly");


	}

	private void setModel2() throws IOException {
		String path=Path.getSavePath(common.lib.Util.fillInZero(Integer.valueOf(getUserId()), 6), EDir.db, model.getField("tb").getValue());
		DataTableInfo dti=null;
        try {
			dti=ClassSerializer.deserialize(path);
		} catch (NumberFormatException | ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			model.addErrorMessage("列の登録に失敗しました。");
			return;
		}
        FieldBase field=common.field.Util.getFieldInstance(model.getField("name").getValue(),model.getField("type").getValue());
        dti.addField(field);




        ClassSerializer.serialize(dti, path);

        setMessage(String.format("列「%s」を登録しました。",model.getField("name").getValue()));
        response.sendRedirect("editindex?tb="+URLEncoder.encode(model.getField("tb").getValue(), "utf-8"));

	}
}
