package jp.rsks.myapplication.datasource;

import java.util.List;

/**
 * Created by rsk on 2016/09/19.
 */
public class NhkProgramList {

    public ProgramList list;
    public class ProgramList {
        public List<NhkProgram> g1;
    }
    public class NhkProgram {
        String id;
        String event_id;
        String start_time;
        String end_time;
        Area area;
        public Service service;
        public String title;
        public String subtitle;

        public class Area {
            String id;
            String name;
        }
        public class Service {
            String id;
            String name;
            public Logo_l logo_l;
            public class Logo_l {
                public String url;
            }
        }
    }
}
