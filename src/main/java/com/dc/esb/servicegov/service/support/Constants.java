package com.dc.esb.servicegov.service.support;

/**
 * Created by vincentfxz on 15/7/12.
 */
public class Constants {
    public static final String STATE_PASS = "1";
    public static final String STATE_UNPASS = "2";

    public static final String DELTED_FALSE = "0";//0:未删除，1：已删除
    public static final String DELTED_TRUE = "1";

    public static final String INTERFACE_STATUS_TC = "0";//0投产  1废弃
    public static final String INTERFACE_STATUS_FQ = "1";


    public static final String INVOKE_TYPE_CONSUMER = "1";//接口映射类型，1：消费者，0：提供者
    public static final String INVOKE_TYPE_PROVIDER = "0";
    public static final String INVOKE_TYPE_STANDARD_Y = "0";//是否标准接口，1：否，0：是
    public static final String INVOKE_TYPE_STANDARD_N = "1";

    public static final String EXCEL_TEMPLATE_SERVICE = Constants.class.getResource("/").getPath() + "/template/excel_service_template.xls";
    public static final String EXCEL_TEMPLATE_SERVICE_VIEW = Constants.class.getResource("/").getPath() + "/template/excel_service_view_template.xls";
    public static final String EXCEL_TEMPLATE_INTERFACE = Constants.class.getResource("/").getPath() + "/template/excel_interface_template.xls";
    public static final String EXCEL_TEMPLATE_SYSTEM_REUSERATE = Constants.class.getResource("/").getPath() + "/template/excel_system_reuserate_template.xls";
    public static final String EXCEL_TEMPLATE_SERVICE_REUSERATE = Constants.class.getResource("/").getPath() + "/template/excel_service_reuserate_template.xls";
    public static final String EXCEL_TEMPLATE_RELEASE_COUNT = Constants.class.getResource("/").getPath() + "/template/excel_release_count_template.xls";
    public static final String EXCEL_TEMPLATE_RELEASE_STATE = Constants.class.getResource("/").getPath() + "/template/excel_release_state_template.xls";
    public static final String EXCEL_TEMPLATE_DATA_DICTIONARY= Constants.class.getResource("/").getPath() + "/template/excel_data_dictionary_template.xls";

    public static class Operation {
        public static final String OPT_STATE_UNAUDIT = "0";  //0.服务定义 1：审核通过，2：审核不通过, 3:已发布 4:已上线 5 已下线 6待审核
        public static final String OPT_STATE_PASS = "1";
        public static final String OPT_STATE_UNPASS = "2";
        public static final String OPT_STATE_REQUIRE_UNAUDIT = "6";

        public static final String LIFE_CYCLE_STATE_PUBLISHED = "3";
        public static final String LIFE_CYCLE_STATE_ONLINE = "4";
        public static final String LIFE_CYCLE_STATE_DISCHARGE = "5";

        public static String getStateName(String state){
            if(OPT_STATE_UNAUDIT.equals(state)){
                return "服务定义";
            }
            if(OPT_STATE_REQUIRE_UNAUDIT.equals(state)){
                return "待审核";
            }
            if(OPT_STATE_PASS.equals(state)){
                return "审核通过";
            }
            if(OPT_STATE_UNPASS.equals(state)){
                return "审核不通过";
            }
            if(LIFE_CYCLE_STATE_PUBLISHED.equals(state)){
                return "已发布";
            }
            if(LIFE_CYCLE_STATE_ONLINE.equals(state)){
                return "已上线";
            }
            if(LIFE_CYCLE_STATE_DISCHARGE.equals(state)){
                return "已下线";
            }
            return "";
        }
//    	public static final String LIFE_CYCLE_STATE_TEST = "3";//测试
//    	public static final String LIFE_CYCLE_STATE_STOP = "4";//停止
//    	public static final String LIFE_CYCLE_STATE_MATAIN = "5";//维护
//    	public static final String LIFE_CYCLE_STATE_BASELINE = "6";//基线
//    	public static final String LIFE_CYCLESTATE_EXPIRE = "7";//失效
//    	public static final String OPT_STATE_UNPASS = "8";


    }

    public static class Version {
        public static final String TYPE_ELSE = "0";  //0：非基线，1：基线
        public static final String TYPE_BASELINE = "1";

        public static final String STATE_USING = "0";//0:生效，1：废弃
        public static final String STATE_DROPPED = "1";

        public static final String TARGET_TYPE_BASELINE = "0";//基线
        public static final String TARGET_TYPE_OPERATION = "1";//场景
        public static final String TARGET_TYPE_PC = "2";//公共代码
        public static final String TARGET_TYPE_INTERFACE = "3";//接口

        public static final String OPT_TYPE_ADD = "0"; //操作类型，0：新增，1：修改，2：删除,3:发布
        public static final String OPT_TYPE_EDIT = "1";
        public static final String OPT_TYPE_DELETE = "2";
        public static final String OPT_TYPE_RELEASE = "3";

        public static final String COMPARE_TYPE0 = "0";//对比类型：0：当前版本VS历史版本， 1：历史版本VS历史版本,2:历史版本VS当前版本
        public static final String COMPARE_TYPE1 = "1";
        public static final String COMPARE_TYPE2 = "2";
    }

    public static class Metadata {
        public static final String STATUS_UNAUDIT = "未审核";
        public static final String STATUS_FORMAL = "正式";

        public static final String ARRAY_TYPE = "Array";
        public static final String STRUCT_TYPE = "Struct";
    }

}
