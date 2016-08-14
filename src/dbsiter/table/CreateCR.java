package dbsiter.table;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import common.consts.EDir;
import common.consts.Path;
import common.data.DataTableInfo;
import common.field.Text;
import common.io.ClassSerializer;
import common.tag.ATag;
import common.validation.FileExist;
import common.validation.Required;
import common.web.ControllerBase;

public class CreateCR extends ControllerBase {

	public CreateCR() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

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

		DataTableInfo dti=new DataTableInfo();
		dti.name=model.getField("name").getValue().toString();

		//カスタマーの保存領域に"dti"をシリアライズする。
		String path=Path.getSavePath(getUserIdFillInZero(), EDir.db);
		File file=new File(path);
		if(!file.exists())file.mkdirs();

		ClassSerializer.serialize(dti, path+File.separator+ dti.name);

		//todo 次のページへ
		setMessage("データテーブル「"+dti.name+"」が作成されました。");
		response.sendRedirect("editindex?tb="+URLEncoder.encode(dti.name, "utf-8"));
	}

	@Override
	protected void doAfter() throws IOException {
		// TODO 自動生成されたメソッド・スタブ

	}


	public void setModel() throws UnsupportedEncodingException {


        model.title=lib.UMConst.SITENAME_DBSITER;
        model.heading="テーブル作成";

        Text name=new Text("name","テーブル名称");
        name.addValidation(new Required());
        FileExist fileExist=new FileExist(Path.getSavePath(getUserIdFillInZero(), EDir.db));
        fileExist.errorMessage="指定された%sは既に登録されています";
        name.addValidation(fileExist);
        name.addCssClass("form-control");
        model.addField(name);

        ATag back=new ATag("../table/index","テーブル一覧");


        model.addElement("back",back);
	}


}
