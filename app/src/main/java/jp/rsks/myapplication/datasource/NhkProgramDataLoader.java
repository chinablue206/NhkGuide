package jp.rsks.myapplication.datasource;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by rsk on 2016/09/18.
 */
public class NhkProgramDataLoader {
    private static final String apiKey = "?key=fCz1UEF3hiRhUqvMoAwqj7e9YnC5cudE";
    private static final String baseUrl = "http://api.nhk.or.jp/v2/pg/list/130/g1/2016-09-19.json";

    public NhkProgramList getProgramList () throws IOException {
        final OkHttpClient okHttpClient = new OkHttpClient();
        final String url = baseUrl + apiKey;
        final Request request = new Request.Builder().url(url).build();
        final Response response = okHttpClient.newCall(request).execute();

        return convertResponseString(response.body().string());
    }

    private NhkProgramList convertResponseString (final String response){
        final Gson gson = new Gson();
        return gson.fromJson(response, NhkProgramList.class);
    }

}
