package com.utils.content;

public class ErrorCode {

	// 接收数据异常处理结果
	public final static String[] API_SYSTEM_ERROR = new String[] { "1000", "服务端出错" };
	public final static String[] API_POST_ERROR = new String[] { "1001", "服务器不支持该数据类型" };
	public final static String[] API_EX_ERROR = new String[] { "1002", "服务端处理异常" };
	public final static String[] API_SIGN_ERROR = new String[] { "1003", "签名失败" };
	public final static String[] API_PARAM_NULL = new String[] { "1004", "参数及值提交有误" };
	public final static String[] API_DATA_NULL = new String[] { "1005", "data参数值不能为空" };
	public final static String[] API_TIME_OUT = new String[] { "1006", "服务器超时" };
	public final static String[] API_METHOD_ERROR = new String[] { "1007", "调用方法不存在" };
	public final static String[] API_NODATA_ERROR = new String[] { "1008", "没有到查询数据" }; 

	// 处理成功
	public final static String[] API_SUCCESS = new String[] { "2000", "处理成功" };

	// 应用级处理异常
	public final static String[] API_FORMAT_ERROR = new String[] { "5000", "JSON对象数据结构验证不通过" };
	public final static String[] API_CONVERT_ERROR = new String[] { "5001", "转换JSON对象数据结构出错" };
	public final static String[] API_MUST_NULL = new String[] { "5002", "必填项不能为空" };
	public final static String[] API_LONG_ERROR = new String[] { "5003", "数据长度错误" };
	public final static String[] API_FILE_ERROR = new String[] { "5004", "文件格式错误" };
	public final static String[] API_OTHER_ERROR = new String[] { "5005", "其他错误" };
	public final static String[] API_TYPE_ERROR = new String[] { "5006", "数据类型错误" };

	// 业务级异常错误
	
	//phone 
	//2.2.1. 类目列表查询
	
	
	public final static String[] CATEGORYINFO_DISPLAY_DATA_NOAVAIL = new String[] { "2211", "父类目参数无效" };
	public final static String[] CATEGORYINFO_PAGESIZE_DATA_OVER = new String[] { "2212", "查询页码超出有效范围" };
	public final static String[] CATEGORYINFO_PARENT_ID_ERROR = new String[] { "2213", "父类目ID不正确，没有该类目" };
	public final static String[] CATEGORYINFO_PARENT_ID_NOFINDCHLIREN  = new String[] { "2214", "父ID下没有子类目" };
	public final static String[] CATEGORYINFO_DATA_NOFIND  = new String[] { "2215", "必填参数为空" };
	
	
	//2.4. 商品信息
	
	//2.4.1. 商品列表查询
	public final static String[] GOODSLIST_DATA_PARAMETER_ERROR  =  new String[] { "2411", "参数数据有误" };
	public final static String[] GOODSLIST_DATA_PARAMETER_PAGESIZE_OVER  =  new String[] { "2412", "页码超出记录总数" };
	public final static String[] GOODSLIST_DATA_PARAMETER_NO_CATE  =  new String[] { "2413", "没有查询的类目" };
	public final static String[] GOODSLIST_DATA_PARAMETER_NO_BRAND  =  new String[] { "2414", "没有查询的品牌" };
	public final static String[] GOODSLIST_DATA_PARAMETER_NO_PROMOTION_TYPE  =  new String[] { "2415", "没有查询的促销类型" };
	public final static String[] GOODSLIST_DATA_PARAMETER_NO_ORDERBY_TYPE  =  new String[] { "2416", "没有该排序类型" };
	
	//2.4.2. 商品详情查询
	public final static String[] GOODSDETAIL_DATA_PARAMETER_ERROR  =  new String[] { "2421", "该商品信息已失效" };
	public final static String[] GOODSDETAIL_DATA_NO_PROMO  =  new String[] { "2422", "橱窗信息填写有误或者没有对应的橱窗信息" };
	public final static String[] GOODSDETAIL_DATA_NO_CATE  =  new String[] { "2423", "类目信息填写有误或者没有对应的类目信息" };
	public final static String[] GOODSDETAIL_DATA_NO_BRAND  =  new String[] { "2424", "品牌信息填写有误没有对应的品牌信息" };
	public final static String[] GOODSDETAIL_DATA_NO_MAPPING  =  new String[] { "2425", "商品类目信息不对应（该类目下没有该商品）或者品牌信息不对应（该品牌下没有该商品）" };
	public final static String[] GOODSDETAIL_DATA_NO_SEARCH =  new String[] { "2426", "搜索信息填写有误,该关键字下没有相关信息" };
	
	//基础信息
	public final static String[] PHONEINFO_DATA_NOFIND = new String[] { "2141", "没有客服中心电话号码信息" };
	
	
	//订单 
	
	public final static String[] ORDER_BUYERID_AND_WTCCARDNO_NULL = new String[] { "2611", "客户编号或屈臣氏会员编号必须有一项不能为空" };
	public final static String[] ORDER_PHONE_AND_TEL_NULL = new String[] { "2625", "联系手机或联系电话必须有一项不能为空" };
	public final static String[] ORDER_QUERY_NO_USERINFO = new String[] { "2631", "没有查询用户信息" };
	public final static String[] ORDER_QUERY_PARAM_INVALID = new String[] { "2632", "查询订单条件参数无效" };
	
	
	
}
