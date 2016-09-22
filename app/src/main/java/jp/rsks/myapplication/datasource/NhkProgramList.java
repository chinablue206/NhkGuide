package jp.rsks.myapplication.datasource;

import java.io.Serializable;
import java.util.List;

/**
 * Created by rsk on 2016/09/19.
 */
public class NhkProgramList {

    public ProgramList list;
    public class ProgramList {
        public List<NhkProgram> pl;
        public List<NhkProgram> g1;
        public List<NhkProgram> e1;
        public List<NhkProgram> s1;
        public List<NhkProgram> s3;

    }
    public class NhkProgram implements Serializable {
        String id;
        String event_id;
        String start_time;
        String end_time;
        Area area;
        public Service service;
        public String title;
        public String subtitle;
        public String content;

        public class Area implements Serializable{
            String id;
            String name;
        }
        public class Service implements  Serializable{
            String id;
            String name;
            public Logo_l logo_l;
            public class Logo_l implements Serializable{
                public String url;
            }
        }
    }
}
