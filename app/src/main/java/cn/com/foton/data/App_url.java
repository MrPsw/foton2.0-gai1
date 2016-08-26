package cn.com.foton.data;

public class App_url {
	//613811987575150003
	//销售经理:AL@1110000249 密码:1
	//销售顾问:AL1@1110000249 密码:1
	//正式AL@2010011685
	//伽途业务经理:J1@2010011977,伽途业务员:J2@2010011977.
	//等级编号90010001
//8月25日 测试账号AL@2010004551

//	时代正式环境账号：S@1001100761   密码：pass_1234 
//	瑞沃正式环境账号：S@2010012339   密码：pass_1234
	/***
	 * 测试接口
	 */
static String url="http://61.233.8.200";
	/***
	 * 正式接口
	 */
	//static String url="http://cmms.foton.com.cn";
	/***
	 * 推送正式
	 */
	static String JpushUrl="http://dealerapp.foton.com.cn";
	/***
	 * 推送测试
	 */
	//static String JpushUrl="http://116.228.168.118:8081"

	
	/**
	 * 基础数据接口
	 */ 
	public static String URL=url+"/appCodeManager/basicsCode.html";
	/**
	 * 用户数据接口
	 */
	public static String LONG_URL=url+"/appUserManager/userInfo.html";
	/**
	 * 首页统计接口
	 */
	public static  String URLmain = url+"/appReportFormManager/reportFormHome.html";
	/**
	 * 未分配接口
	 */
	public static String ALLCLUE_URL1=url+"/appCustomerManager/customerQueryDis.html";//未分配
	/**
	 * 线索分配接口
	 */
	public static String ALLCLUE_URL3=url+"/appCustomerManager/customerDistribution.html";
	/**
	 * 销售顾问接口
	 */
	public static String URL3 = url+"/appSalesManManager/querySalesManByUser.html";
	/**
	 * 未跟进接口
	 */
	public static String ALLCLUE_URL2=url+"/appCustomerManager/customerQueryFollowUp.html";//未
	/**
	 * 待办任务接口，超时未跟进
	 */
	public static String QueryNeedTask=url+"/appCustomerManager/customerQueryNeedTask.html";
	/**
	 * 制定计划接口
	 */
	public static  String URLS = url+"/appPlanManager/planFormulate.html";
	/**
	 * 制定结果接口
	 */
	public static  String URLSP1 = url+"/appResultManager/resultFormulate.html";
	/**
	 * 跟进历史接口
	 */
	public static String resultHistory=url+"/appResultManager/resultHistory.html";
	/**
	 * 全部线索查询接口
	 */
	public  static final String ALL = url+"/appCustomerManager/customerQueryAll.html";
	/**
	 * 战败查询接口
	 */
	public static  String QueryFail = url+"/appCustomerManager/customerQueryFail.html";
	/**
	 *战败结果确定接口
	 */
	public  static final String Follow = url+"/appCustomerManager/customerFailConfirm.html";
	/**
	 * 线索操作接口
	 */
	public static String URL2=url+"/appCustomerManager/customerOperation.html";
	/**
	 * 报表查询接口
	 */
	public static final String statement=url+"/appReportFormManager/customerReportForm.html";
	/**
	 * 版本更新
	 */
	public static String JPush_Update=JpushUrl+"/api/PushManage/MobileVersion";
	/**
	 * 催办推送
	 */
	public static String JPush_cuiban = JpushUrl+"/api/PushManage/HastenProcess";
	/**
	 * 分配推送
	 */
	public	static String JPush_fenpei=JpushUrl+"/api/PushManage/AssignLeads";
	/**
	 * 新建线索推送
	 */
	public	static String JPush_add=JpushUrl+"/api/PushManage/AddNewLeads";	
	/**
	 * 战败推送
	 */
	public	static String JPush_Failt=JpushUrl+"/api/PushManage/FailureApply";	
		
		//private static final String URLt = url+"/appSalesManManager/querySalesManByUser.html";
	/**
	 * 消息列表
	 */
	public	static String JPush_smsList=JpushUrl+"/api/PushManage/GetLeadsMessageList";
	/**
	 * apk更新url
	 */
	public	static String JPush_APKurl=JpushUrl+"/package/FotonDealerAPPAndroid.apk";
	
}
