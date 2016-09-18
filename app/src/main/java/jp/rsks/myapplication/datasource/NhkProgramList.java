package jp.rsks.myapplication.datasource;

import java.util.List;

/**
 * Created by rsk on 2016/09/19.
 */
public class NhkProgramList {

    ProgramList list;
    public class ProgramList {
        List<NhkProgram> g1;
    }
    public class NhkProgram {
        String id;
        String event_id;
        String start_time;
        String end_time;
        Area area;
        Service service;
        String title;
        String subtitle;

        public class Area {
            String id;
            String name;
        }
        public class Service {
            String id;
            String name;
            Logo_l logo_l;
            public class Logo_l {
                String url;
            }
        }
    }
}
