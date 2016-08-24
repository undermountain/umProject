package dbsiter.table;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import common.base.FieldBase;
import common.consts.EDir;
import common.consts.Path;
import common.data.DataTableInfo;
import common.field.Hidden;
import common.field.Text;
import common.io.ClassSerializer;
import common.tag.ATag;
import common.type.KeyValue;
import common.validation.FileExist;
import common.validation.Required;
import common.web.ControllerBase;
import common.web.Model;

public class EditCR extends ControllerBase {

	@Override
	protected void doBefore() throws IOException {
		if(!request.getParameterMap().containsKey("tb")){
			response.sendRedirect("index");
			return;
		}
		if(checkParameter("disp")){
			tableDisplayChange(request.getParameter("disp"));
			response.sendRedirect("edit?tb=" + UrlEncode(request.getParameter("tb")));
			return;
		}
		model=setModel();
	}

	@Override
	protected void doGet() throws IOException {
		model.getField("name").setValue(request.getParameter("tb"));
		model.getField("nameold").setValue(request.getParameter("tb"));
	}

	@Override
	protected void doPost() throws IOException {
		DataTableInfo dti=new DataTableInfo();

		try {
			dti=ClassSerializer.deserialize(Path.getSavePath(getUserIdFillInZero(), EDir.db, request.getParameter("tb")));
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			model.addErrorMessage("テーブル名の変更に失敗しました。");
			return;
		}
		dti.name=model.getField("name").getStrValue();
		File file=new File(Path.getSavePath(getUserIdFillInZero(), EDir.db, request.getParameter("tb")));
		file.delete();
		ClassSerializer.serialize(dti, Path.getSavePath(getUserIdFillInZero(), EDir.db, dti.name));

		setMessage("テーブル名を「"+dti.name+"に変更しました。");
		response.sendRedirect("index");

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



        //テーブル名old
        Hidden nameold=new Hidden("nameold","");
        nameold.displayName="変更前のテーブル名";
        model.addField(nameold);


        //テーブル名変更用
        Text name=new Text("name","テーブル名");
        name.addValidation(new Required());
        //name.addValidation(new Comparison(EComparison.not,nameold));
        FileExist fileExist=new FileExist(Path.getSavePath(getUserIdFillInZero(), EDir.db)){

			@Override
			public boolean runValidateCondition(FieldBase field, Model model) {
				// TODO 自動生成されたメソッド・スタブ
				return !model.request.getParameter("nameold").equals(field.getValue());
			}

        };
        fileExist.errorMessage="指定された%sは既に登録されています";
        name.addValidation(fileExist);
        name.addCssClass("form-control");

        model.addField(name);

        //パブリックリンク
        String[] urls=request.getRequestURL().toString().split("/");
        String url=String.format("%s//%s/um/pub/dbsiter/table?u=%s&t=%s", urls[0],urls[2],getUserIdFillInZero(),UrlEncode(request.getParameter("tb")));
        ATag publicurl=new ATag(url, url);
        publicurl.setAttribute("target", "_blank");

        model.addElement("publicurl", publicurl);

        model.addData("panel", dti.display ? "panel-info":"panel-danger");

        ATag disp=new ATag("edit",dti.display ? "非公開にする":"公開する");
        disp.addCssClass("btn " + (dti.display ? "btn-danger":"btn-primary"));
        disp.addUrlParameter(new KeyValue("tb", UrlEncode(request.getParameter("tb"))));
        disp.addUrlParameter(new KeyValue("disp", dti.display ? "0":"1"));
        model.addElement("disp", disp);

        //リンク
        ATag editindex=new ATag("editindex", "列設定");
        editindex.addUrlParameter(new KeyValue("tb",request.getParameter("tb")));

        model.addElement("editindex",editindex);


    	//データ編集リンク
    	ATag dataindex=null;
    	if(dti.dataTable.columns==null || dti.dataTable.columns.length==0){
    		dataindex=new ATag("javascript:alert('列が登録されていません。\\n「列設定」から列を追加してください。')", "データ編集");
    	}else{
            dataindex=new ATag("dataindex", "データ編集");
            dataindex.addUrlParameter(new KeyValue("tb",request.getParameter("tb")));

    	}
    	 model.addElement("dataindex",dataindex);

//        ATag dataindex=new ATag("dataindex", "データ編集");
//        dataindex.addUrlParameter(new KeyValue("tb",request.getParameter("tb")));
//
//        model.addElement("dataindex",dataindex);

        //パンくず
        ATag back=new ATag("index","テーブル一覧");
        model.addElement("back",back);

        model.checkToken=true;

		return model;
	}


	private void tableDisplayChange(String parameter) throws IOException {
		DataTableInfo dti=null;
        try {
			dti=ClassSerializer.deserialize(Path.getSavePath(common.lib.Util.fillInZero(Integer.valueOf(model.getUserId()), 6), EDir.db, request.getParameter("tb")));
		} catch (NumberFormatException | ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			return;
		}
        if(parameter.equals("0")){
        	dti.display=false;
        }else if(parameter.equals("1")){
        	dti.display=true;
        }

        ClassSerializer.serialize(dti, Path.getSavePath(common.lib.Util.fillInZero(Integer.valueOf(model.getUserId()), 6), EDir.db, request.getParameter("tb")));

        return;

	}
}
