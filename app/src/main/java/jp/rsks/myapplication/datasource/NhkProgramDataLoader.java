package jp.rsks.myapplication.datasource;

import com.google.gson.Gson;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import jp.rsks.myapplication.BuildConfig;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by rsk on 2016/09/18.
 */
public class NhkProgramDataLoader {

    private static final String apiKey = BuildConfig.APIKEY;
    private static final String apiKeyParam = "?key=" + apiKey;
    private static final String baseUrl = "http://api.nhk.or.jp/v2/pg/list/130/%s/%s.json";

    public NhkProgramList getProgramList () throws IOException {
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        final String today = sdf.format(new Date(System.currentTimeMillis()));

        return getProgramList("g1", today);
    }

    public NhkProgramList getProgramList (String chId) throws IOException {
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        final String today = sdf.format(new Date(System.currentTimeMillis()));

        return getProgramList(chId, today);
    }

    public NhkProgramList getProgramList (String chId, String date) throws IOException {
        final OkHttpClient okHttpClient = new OkHttpClient();
        final String url = String.format(baseUrl, chId, date) + apiKeyParam;
        final Request request = new Request.Builder().url(url).build();
        final Response response = okHttpClient.newCall(request).execute();

        return convertResponseString(response.body().string());
    }

    private NhkProgramList convertResponseString (final String response){
        final Gson gson = new Gson();
        NhkProgramList progList = gson.fromJson(response, NhkProgramList.class);

        if(progList.list.g1 != null) {
            progList.list.pl = progList.list.g1;
        } else if(progList.list.e1 != null){
            progList.list.pl = progList.list.e1;
        } else if(progList.list.s1 != null){
            progList.list.pl = progList.list.s1;
        } else if(progList.list.s3 != null){
            progList.list.pl = progList.list.s3;
        }

        return progList;
    }

}
