package jp.rsks.myapplication.datasource;

/**
 * Created by rsk on 2016/09/19.
 */
public enum NhkChannelList {

    SOUGOU("g1", "NHK 総合"),
    KYOIKU("e1", "NHK 教育"),
    BS1("s1", "NHK BS1"),
    BS3("s3", "NHK BSプレミアム");

    String id;
    String label;

    NhkChannelList(String id, String label){
        this.id = id;
        this.label = label;
    }

    public String getId() {return this.id;}
    public String getLabel() {return this.label;}

}
