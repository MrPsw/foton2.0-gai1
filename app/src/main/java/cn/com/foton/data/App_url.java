package cn.com.foton.data;

public class App_url {
	//613811987575150003
	//���۾���:AL@1110000249 ����:1
	//���۹���:AL1@1110000249 ����:1
	//��ʽAL@2010011685
	//٤;ҵ����:J1@2010011977,٤;ҵ��Ա:J2@2010011977.
	//�ȼ����90010001
//8��25�� �����˺�AL@2010004551

//	ʱ����ʽ�����˺ţ�S@1001100761   ���룺pass_1234 
//	������ʽ�����˺ţ�S@2010012339   ���룺pass_1234
	/***
	 * ���Խӿ�
	 */
static String url="http://61.233.8.200";
	/***
	 * ��ʽ�ӿ�
	 */
	//static String url="http://cmms.foton.com.cn";
	/***
	 * ������ʽ
	 */
	static String JpushUrl="http://dealerapp.foton.com.cn";
	/***
	 * ���Ͳ���
	 */
	//static String JpushUrl="http://116.228.168.118:8081"

	
	/**
	 * �������ݽӿ�
	 */ 
	public static String URL=url+"/appCodeManager/basicsCode.html";
	/**
	 * �û����ݽӿ�
	 */
	public static String LONG_URL=url+"/appUserManager/userInfo.html";
	/**
	 * ��ҳͳ�ƽӿ�
	 */
	public static  String URLmain = url+"/appReportFormManager/reportFormHome.html";
	/**
	 * δ����ӿ�
	 */
	public static String ALLCLUE_URL1=url+"/appCustomerManager/customerQueryDis.html";//δ����
	/**
	 * ��������ӿ�
	 */
	public static String ALLCLUE_URL3=url+"/appCustomerManager/customerDistribution.html";
	/**
	 * ���۹��ʽӿ�
	 */
	public static String URL3 = url+"/appSalesManManager/querySalesManByUser.html";
	/**
	 * δ�����ӿ�
	 */
	public static String ALLCLUE_URL2=url+"/appCustomerManager/customerQueryFollowUp.html";//δ
	/**
	 * ��������ӿڣ���ʱδ����
	 */
	public static String QueryNeedTask=url+"/appCustomerManager/customerQueryNeedTask.html";
	/**
	 * �ƶ��ƻ��ӿ�
	 */
	public static  String URLS = url+"/appPlanManager/planFormulate.html";
	/**
	 * �ƶ�����ӿ�
	 */
	public static  String URLSP1 = url+"/appResultManager/resultFormulate.html";
	/**
	 * ������ʷ�ӿ�
	 */
	public static String resultHistory=url+"/appResultManager/resultHistory.html";
	/**
	 * ȫ��������ѯ�ӿ�
	 */
	public  static final String ALL = url+"/appCustomerManager/customerQueryAll.html";
	/**
	 * ս�ܲ�ѯ�ӿ�
	 */
	public static  String QueryFail = url+"/appCustomerManager/customerQueryFail.html";
	/**
	 *ս�ܽ��ȷ���ӿ�
	 */
	public  static final String Follow = url+"/appCustomerManager/customerFailConfirm.html";
	/**
	 * ���������ӿ�
	 */
	public static String URL2=url+"/appCustomerManager/customerOperation.html";
	/**
	 * �����ѯ�ӿ�
	 */
	public static final String statement=url+"/appReportFormManager/customerReportForm.html";
	/**
	 * �汾����
	 */
	public static String JPush_Update=JpushUrl+"/api/PushManage/MobileVersion";
	/**
	 * �߰�����
	 */
	public static String JPush_cuiban = JpushUrl+"/api/PushManage/HastenProcess";
	/**
	 * ��������
	 */
	public	static String JPush_fenpei=JpushUrl+"/api/PushManage/AssignLeads";
	/**
	 * �½���������
	 */
	public	static String JPush_add=JpushUrl+"/api/PushManage/AddNewLeads";	
	/**
	 * ս������
	 */
	public	static String JPush_Failt=JpushUrl+"/api/PushManage/FailureApply";	
		
		//private static final String URLt = url+"/appSalesManManager/querySalesManByUser.html";
	/**
	 * ��Ϣ�б�
	 */
	public	static String JPush_smsList=JpushUrl+"/api/PushManage/GetLeadsMessageList";
	/**
	 * apk����url
	 */
	public	static String JPush_APKurl=JpushUrl+"/package/FotonDealerAPPAndroid.apk";
	
}
