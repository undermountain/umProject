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

        model.heading="「"+dti.name +"」テーブル構成編集";



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

        //リンク
        ATag editfield=new ATag("editfield", "列編集");
        editfield.addUrlParameter(new KeyValue("tb",request.getParameter("tb")));

        model.addElement("editfield",editfield);

        //パンくず
        ATag back=new ATag("index","テーブル一覧");
        model.addElement("back",back);

        model.checkToken=true;

		return model;
	}

}
