package dbsiter.table;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.ServletException;

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
import common.type.KeyValue;
import common.validation.Required;
import common.web.ControllerBase;
import common.web.Model;

public class EditfieldCR extends ControllerBase {

	@Override
	protected boolean runCustom() throws IOException, ServletException{
		if(!checkParameter("tb")){
			response.sendRedirect("index");
			return true;
		}

		if(request.getParameter("f")==null || request.getParameter("f").equals("")){
			response.sendRedirect("editindex?tb="+URLEncoder.encode(request.getParameter("tb"), "utf-8"));
			return true;
		}

		String path=Path.getSavePath(common.lib.Util.fillInZero(Integer.valueOf(getUserId()), 6), EDir.db, request.getParameter("tb"));
		DataTableInfo dti=null;
        try {
			dti=ClassSerializer.deserialize(path);
		} catch (NumberFormatException | ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			model.addErrorMessage("列の登録に失敗しました。");
			return true;
		}

        int fieldIndex = 0;

        FieldBase field=null;

        for(int i=0;i<dti.fieldList.size();i++){
        	if(dti.fieldList.get(i).displayName.equals(request.getParameter("f"))){
        		field=dti.fieldList.get(i);
        		fieldIndex=i;
        	}
        }

        setModel(field);

        if(!isPost()){


			Button btn=new Button("step1","次へ","buttonvlaue");

			model.addField(btn);
			return true;
        }

		if(checkParameter("step1")){

			if(!checkSetModel()){
				Button btn=new Button("step1","次へ","buttonvlaue");
				model.addField(btn);
				return true;
			}


			setModel1();

			if(model.getField("type").getStrValue().equals(field.getClass().getSimpleName()) && !checkParameter("step2"))
				common.field.Util.setFieldInfoInputs("0"/*model.getField("array").getStrValue()*/,model.getField("type").getStrValue(),model,field,dti.getColumnInfo(fieldIndex));
			else
				common.field.Util.setFieldInfoInputs("0"/*model.getField("array").getStrValue()*/,model.getField("type").getStrValue(),model,null,null);

			model.addField(new Hidden("step1","1"));

		}

		if(checkParameter("step2")){
			if(!checkSetModel())return true;

	        common.field.Util.editFieldInfoInputs("0"/*model.getField("array").getStrValue()*/,model.getField("type").getStrValue(),model,dti,model.getField("f").getStrValue());

	        ClassSerializer.serialize(dti, path);

	        String msg="列「"+model.getField("f").getValue()+"」を"
					+"変更しました。"
					+(model.getField("f").getValue().equals(model.getField("name").getValue()) ? "":"(変更後列名「"+model.getField("name").getValue()+"」)");
			setMessage(msg);
			response.sendRedirect("editindex?tb="+URLEncoder.encode(dti.name, "utf-8"));
	        return true;
		}

		Button btn=new Button("step2","変更","buttonvlaue");
		model.addField(btn);

		return true;
	}

	@Override
	protected void doBefore() throws IOException {

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

	private void setModel(FieldBase field) throws UnsupportedEncodingException {
		String tbName=request.getParameter("tb");

        model.title=lib.UMConst.SITENAME_DBSITER;
        model.heading="「"+tbName+"」テーブル 列編集";

        model.checkToken=true;

        //列追加Field宣言
        Hidden tb=new Hidden("tb",tbName);
        Hidden f=new Hidden("f",request.getParameter("f"));

        String oldname=field.displayName;

        Text name=new Text("name","列名");
        name.addValidation(new Required());
        name.addCssClass("form-control");

        name.addValidation(new common.base.ValidationBase(){

			@Override
			public boolean check(FieldBase field, Model model) {
				String path=Path.getSavePath(common.lib.Util.fillInZero(Integer.valueOf(getUserId()), 6), EDir.db, model.getField("tb").getStrValue());
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
		        	if(!oldname.equals(field.getValue()))
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
        type.addCssClass("form-control");

        /*
        Select array=new Select("array","入力数");
        array.addOptionItem("0", "単数入力");
        array.addOptionItem("1", "複数入力");
        array.addCssClass("form-control");
        */

        if(!isPost()){
	        name.setValue(field.displayName);
	        type.setValue(field.getClass().getSimpleName());
        }

        model.addFieldAll(tb,f,name,type);//,array);

        //Atag
        ATag back=new ATag("editindex","「"+tbName+"」テーブル編集");
        back.addUrlParameter(new KeyValue("tb",UrlEncode(tbName)));
        model.addElement("back", back);

	}

	private void setModel1() {
        model.getField("name").setAttribute("readonly", "readonly");
        model.getField("type").setAttribute("readonly", "readonly");
        //model.getField("array").setAttribute("readonly", "readonly");
	}
}
