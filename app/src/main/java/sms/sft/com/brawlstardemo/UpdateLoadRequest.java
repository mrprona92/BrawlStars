package sms.sft.com.brawlstardemo;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;


import java.io.File;
import java.util.List;

import sms.sft.com.appbase.util.FileUtils;
import sms.sft.com.appbase.util.service.TaskRequest;
import sms.sft.com.brawlstardemo.dao.DatabaseManager;

/**
 * Created by ABadretdinov
 * 20.08.2015
 * 16:59
 */
public class UpdateLoadRequest extends TaskRequest<String> {

    private Context mContext;

    public UpdateLoadRequest(Context context) {
        super(String.class);
        this.mContext = context;
    }

    @Override
    public String loadData() throws Exception {
        DatabaseManager manager = DatabaseManager.getInstance(mContext);

        SQLiteDatabase db = manager.openDatabase();

        LocalUpdateService localUpdateService = BeanContainer.getInstance().getLocalUpdateService();
        AssetManager assetManager = mContext.getAssets();
        String[] files = assetManager.list("updates");
        for (String fileName : files) {
            int fileVersion = Integer.valueOf(fileName.split("\\.")[0]);
            String sql = FileUtils.getTextFromAsset(mContext, "updates" + File.separator + fileName);
            localUpdateService.update(mContext, sql, fileVersion);
        }
        return "";
    }
}
