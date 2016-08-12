package dbsiter.table;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import common.base.FieldBase;
import common.consts.EDir;
import common.consts.Path;
import common.data.DataTableInfo;
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
	protected void doBefore() throws IOException {

		if(request.getParameter("tb")==null || request.getParameter("tb").equals("")){
			response.sendRedirect("index");
			return;
		}
		if(request.getParameter("f")==null || request.getParameter("f").equals("")){
			response.sendRedirect("editindex?tb="+URLEncoder.encode(request.getParameter("tb"), "utf-8"));
			return;
		}

		setModel();

	}

	@Override
	protected void doGet() throws IOException {
		if(!setModelOnGet()){
			response.sendRedirect("editindex?tb="+URLEncoder.encode(request.getParameter("tb"), "utf-8"));
			return;
		}
	}

	@Override
	protected void doPost() throws IOException {
		DataTableInfo dti=null;
		String path=Path.getSavePath(common.lib.Util.fillInZero(Integer.valueOf(model.getUserId()), 6), EDir.db, model.getField("tb").getValue());
		//Path.getSavePath(common.lib.Util.fillInZero(Integer.valueOf(getUserId()), 6), EDir.db,dti.name);
		try {
			dti=ClassSerializer.deserialize(path);
		} catch (NumberFormatException | ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			model.addErrorMessage("変更に失敗しました。");
			return;
		}

        dti.editField(model.getField("f").getValue(), common.field.Util.getFieldInstance(model.getField("name").getValue(),model.getField("type").getValue()));

		//カスタマーの保存領域に"dti"をシリアライズする。

		ClassSerializer.serialize(dti, path);

		String msg="列「"+model.getField("f").getValue()+"」を"
				+"変更しました。"
				+(model.getField("f").getValue().equals(model.getField("name").getValue()) ? "":"(変更後列名「"+model.getField("name").getValue()+"」)");
		setMessage(msg);
		response.sendRedirect("editindex?tb="+URLEncoder.encode(dti.name, "utf-8"));

	}

	@Override
	protected void doAfter() throws IOException {
		// TODO 自動生成されたメソッド・スタブ

	}


	private void setModel() throws UnsupportedEncodingException {
		String tbName=request.getParameter("tb");
		String fieldName=request.getParameter("f");

        model.title=lib.UMConst.SITENAME_DBSITER;
        model.heading="「"+tbName+"」テーブル 列追加";

        model.checkToken=true;

        //列追加Field宣言
        Hidden tb=new Hidden("tb",tbName);
        Hidden f=new Hidden("f",fieldName);

        Text name=new Text("name","列名");
        name.addValidation(new Required());
        name.setClass("form-control");

        name.addValidation(new common.base.ValidationBase(){

			@Override
			public boolean check(FieldBase field, Model model) {
				if(!model.request.getParameter("f").equals(field.getValue())){
					String path=Path.getSavePath(common.lib.Util.fillInZero(Integer.valueOf(getUserId()), 6), EDir.db, model.getField("tb").getValue());
					DataTableInfo dti=null;
			        try {
						dti=ClassSerializer.deserialize(path);
					} catch (NumberFormatException | ClassNotFoundException e) {
						// TODO 自動生成された catch ブロック
						e.printStackTrace();
						model.addErrorMessage("列の登録に失敗しました。");
						return true;
					} catch (IOException e) {
						// TODO 自動生成された catch ブロック
						e.printStackTrace();
					}
			        for(int i=0;i<dti.dataTable.columns.length;i++){
			        	if(dti.dataTable.columns[i].equals(field.getValue())){
			        		this.errorMessage=String.format("「%s」は既に登録されています。", field.getValue());
			        		return false;
			        	}
			        }
					return true;
				}
				return true;
			}
        });


        Select type=new Select("type","データ型");
        common.field.Util.setFieldOptionItem(type);
        type.setClass("form-control");

        model.addField(tb,f,name,type);

        //Atag
        ATag back=new ATag("editindex","「"+tbName+"」テーブル構成編集");
        back.addUrlParameter(new KeyValue("tb",UrlEncode(tbName)));
        model.addElement("back", back);

	}

	private boolean setModelOnGet() {

		DataTableInfo dti=null;
        try {
			dti=ClassSerializer.deserialize(Path.getSavePath(common.lib.Util.fillInZero(Integer.valueOf(model.getUserId()), 6), EDir.db, model.getField("tb").getValue()));
		} catch (NumberFormatException | ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

        FieldBase field=null;
        int size=dti.fieldList.size();
        for(int i=0;i<size;i++){
        	if(dti.fieldList.get(i).displayName.equals(model.getField("f").getValue())){
        		field=dti.fieldList.get(i);
        		break;
        	}
        }
        if(field==null)return false;

        model.getField("name").setValue(field.displayName);
        model.getField("type").setValue(common.lib.Util.getClassName(field));

        return true;
	}
}
