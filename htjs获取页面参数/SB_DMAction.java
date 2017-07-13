package htjs.web.hlwsb.actions.dm;

import htjs.base.ejbs.BaseManage;
import htjs.hlwsb.ejbs.dm.Dm;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.htjs.component.cache.Cache;
import net.htjs.platform.domain.exception.DelErrorException;
import net.htjs.platform.domain.exception.SaveException;
import net.htjs.platform.web.actions.BaseAction;
import net.htjs.platform.web.tools.JsonHelp;

import org.apache.log4j.Logger;

public class SB_DMAction extends BaseAction {
	private Logger log = Logger.getLogger(this.getClass());
	
	private Dm dm;
	private BaseManage baseManage;
	
	private static final String CACHENAME = "SB_SJ_CACHE"; //整个申报用的都是这个name，不允许修改
	
	public SB_DMAction(){
		dm = (Dm)JsonHelp.getBean("dmEJB");
		baseManage = (BaseManage)JsonHelp.getBean("baseManageEJB");
	}
	
	public String getSb_DM_WITH() {
		mapParam = JsonHelp.getReqParamMap();
		String sid = (String) mapParam.get("SID");
		//如果传过来的是减免税政策
//		if("dm.getDM_ZZS_JMSZC".equals(sid))
//		{
//			if((mapParam.get("JMZLB") != null) && (!"".equals(mapParam.get("JMZLB"))))
//			{
//				CACHEKEY = CACHEKEY + "_JMZLB_" + mapParam.get("JMZLB");
//			}else if((mapParam.get("XH") != null) && (!"".equals(mapParam.get("XH"))))
//			{
//				CACHEKEY = CACHEKEY + "_XH";
//			}
//		}
//		//如果传过来的是营改增税负应税项目
//		if("dm.getDM_YGZYSXM".equals(sid)){}
		Map retMap = new HashMap();
//		//因为时传过来的参数，不能在上部定义成静态常量，要在此处赋值
//		CACHEKEY = sid + CACHEKEY;
//		//按照name和key的方式从Cache中取数据
//		List list = Cache.getList(CACHENAME, CACHEKEY);
		//System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+CACHEKEY);
		//如果Cache中没有数据，就从数据库中取数据
//		if (list == null){
			try {
				list = baseManage.queryForList(sid, mapParam);
				//从数据库中将数据取出来后，放入缓存Cache中，以便第二次的时候直接从缓存中取数据
//				Cache.set(CACHENAME, CACHEKEY, list);
			} catch (RemoteException e) {
				log.error(e.getMessage(),e);
			}
//		}
		retMap.put("BODY", list);
		mapModel.put("data", retMap);
		return getResult();
	}
	
	public String getDM_ZZS_YPXH() {
		mapParam = JsonHelp.getReqParamMap();
		Map retMap = new HashMap();
		try {
			retMap = dm.getDM_ZZS_YPXH();
		} catch (RemoteException e) {
			log.error(e.getMessage(),e);
		}
		
		mapModel.put("data", retMap);
		return getResult();
	}
	
	public String getDM_NSRLX_XFSPM() {
		mapParam = JsonHelp.getReqParamMap();
		Map retMap = new HashMap();
		try {
			retMap = dm.getDM_NSRLX_XFSPM(mapParam);
		} catch (RemoteException e) {
			log.error(e.getMessage(),e);
		}
		
		mapModel.put("data", retMap);
		return getResult();
	}
	
	public String getDM_XFS_PZLB() {
		mapParam = JsonHelp.getReqParamMap();
		Map retMap = new HashMap();
		try {
			retMap = dm.getDM_XFS_PZLB(mapParam);
		} catch (RemoteException e) {
			log.error(e.getMessage(),e);
		}
		
		mapModel.put("data", retMap);
		return getResult();
	}
	
	public String getDM_GJHDQ() {
		mapParam = JsonHelp.getReqParamMap();
		Map retMap = new HashMap();
		String CACHEKEY = "";//可以在函数内改变
		//这个key和增值税一般纳税人减免税使用的是同一个代码查询，故key使用相同的，如果一般纳税人的已经缓存过，这边就可以直接取数据使用了
		CACHEKEY = "dm.getDM_GJHDQ";
		//按照name和key的方式从Cache中取数据
		List list = Cache.getList(CACHENAME, CACHEKEY);
		//如果Cache中没有数据，就从数据库中取数据
		if(list == null){
			try {
				Map getMap = dm.getDM_GJHDQ(mapParam);
				list = getMap.get("BODY")==null?(new ArrayList()):(List)getMap.get("BODY");
				//从数据库中将数据取出来后，放入缓存Cache中，以便第二次的时候直接从缓存中取数据
				Cache.set(CACHENAME, CACHEKEY, list);
			} catch (RemoteException e) {
				log.error(e.getMessage(),e);
			}
		}
		//将list重新放入到返回Map中
		retMap.put("BODY", list);
		mapModel.put("data", retMap);
		return getResult();
	}
	
	public String getDM_HB() {
		mapParam = JsonHelp.getReqParamMap();
		
		Map retMap = new HashMap();
		String CACHEKEY = "";//可以在函数内改变
		//这个key和增值税一般纳税人减免税使用的是同一个代码查询，故key使用相同的，如果一般纳税人的已经缓存过，这边就可以直接取数据使用了
		CACHEKEY = "dm.getDM_HB";
		//按照name和key的方式从Cache中取数据
		List list = Cache.getList(CACHENAME, CACHEKEY);
		//如果Cache中没有数据，就从数据库中取数据
		 if(list == null){
			try {
				Map getMap = dm.getDM_HB(mapParam);
				list = getMap.get("BODY")==null?(new ArrayList()):(List)getMap.get("BODY");
				//从数据库中将数据取出来后，放入缓存Cache中，以便第二次的时候直接从缓存中取数据
				Cache.set(CACHENAME, CACHEKEY, list);
			} catch (RemoteException e) {
				log.error(e.getMessage(),e);
			}
		  }
		//将list重新放入到返回Map中
		retMap.put("BODY", list);
		mapModel.put("data", retMap);
		return getResult();
	}
	 /**
     * 前台查询 企业所得税代码表（有效标志）
     * @Author wangyaolei
     * @ function:
     * @ returnType:String 
     * @return
     */
	public String getDM_QYSDS_ALL_YXBZ() {
		mapParam = JsonHelp.getReqParamMap();
		mapParam.put("YXBZ", "Y");
		Map retMap = new HashMap();
		try {
			retMap = dm.getDM_QYSDS_ALL(mapParam);
		} catch (RemoteException e) {
			log.error(e.getMessage(),e);
		}
		
		mapModel.put("data", retMap);
		return getResult();
	}
    /**
     * 后台查询 企业所得税代码表
     * @Author wangyaolei
     * @ function:
     * @ returnType:String 
     * @return
     */
	public String getDM_QYSDS_ALL() {
		mapParam = JsonHelp.getReqParamMap();
		Map retMap = new HashMap();
		try {
			retMap = dm.getDM_QYSDS_ALL(mapParam);
		} catch (RemoteException e) {
			log.error(e.getMessage(),e);
		}
		
		mapModel.put("data", retMap);
		return getResult();
	}
	/**
	 *  增加增值税代码表
	 * @Author wangyaolei
	 * @ function:
	 * @return   String
	 */
	public String insertDM_QYSDS_ALL() {
		mapParam = JsonHelp.getReqParamMap();
		try {
			 dm.insertDM_QYSDS_ALL(mapParam);
			 code = 1;
		} catch (RemoteException e) {
			log.error(e.getMessage(),e);
		}
		catch (SaveException e) {
            code = -1;
            msg = e.getMessage();
            log.error(e);
        } catch (Exception e) {
            code = -1;
            msg = e.getMessage();
            log.error(msg, e);
        }
		return getResult();
	}
	/**
	 *  更新增值税代码表
	 * @Author wangyaolei
	 * @ function:
	 * @return   String
	 */
	public String updateDM_QYSDS_ALL() {
		mapParam = JsonHelp.getReqParamMap();
		try {
			 dm.updateDM_QYSDS_ALL(mapParam);
			 code = 1;
		} catch (RemoteException e) {
			log.error(e.getMessage(),e);
		}
		catch (SaveException e) {
            code = -1;
            msg = e.getMessage();
            log.error(e);
        } catch (Exception e) {
            code = -1;
            msg = e.getMessage();
            log.error(msg, e);
        }
		return getResult();
	}
	/**
	 *  删除增值税代码表
	 * @Author wangyaolei
	 * @ function:
	 * @return   String
	 */
	public String delDM_QYSDS_ALL() {
		mapParam = JsonHelp.getReqParamMap();
		try {
			 dm.delDM_QYSDS_ALL(mapParam);
			 code = 2;
		} catch (RemoteException e) {
			log.error(e.getMessage(),e);
		}
		catch (DelErrorException e) {
            code = -1;
            msg = e.getMessage();
            log.error(e);
        } catch (Exception e) {
            code = -1;
            msg = e.getMessage();
            log.error(msg, e);
        }
		return getResult();
	}
	/**
	 *  后台查询增值税代码表
	 * @Author wangyaolei
	 * @ function:
	 * @return   String
	 */
	public String getDM_ZZS_ALL() {
		mapParam = JsonHelp.getReqParamMap();
		Map retMap = new HashMap();
		try {
			retMap = dm.getDM_ZZS_ALL(mapParam);
		} catch (RemoteException e) {
			log.error(e.getMessage(),e);
		}
		
		mapModel.put("data", retMap);
		return getResult();
	}
	/**
	 *  前台查询增值税代码表（有效标志为Y）
	 * @Author wangyaolei
	 * @ function:
	 * @return   String
	 */
	public String getDM_ZZS_ALL_YXBZ() {
		mapParam = JsonHelp.getReqParamMap();
		String CZMK = (String)mapParam.get("CZMK");
		String LB = (String)mapParam.get("LB");
		mapParam.put("YXBZ", "Y");
		//如果是成品油型号时，YXBZ的条件为空，避免历史数据不显示对应的油品型号代码名称 20161212 zzg
		if("CXDY".equals(CZMK) && "YPLB".equals(LB))
		{   
			mapParam.put("YXBZ", "");
		}
		Map retMap = new HashMap();
		try {
			retMap = dm.getDM_ZZS_ALL(mapParam);
		} catch (RemoteException e) {
			log.error(e.getMessage(),e);
		}
		
		mapModel.put("data", retMap);
		return getResult();
	}
	/**
	 *  增加增值税代码表
	 * @Author wangyaolei
	 * @ function:
	 * @return   String
	 */
	public String insertDM_ZZS_ALL() {
		mapParam = JsonHelp.getReqParamMap();
		try {
			 dm.insertDM_ZZS_ALL(mapParam);
			 code = 1;
		} catch (RemoteException e) {
			log.error(e.getMessage(),e);
		}
		catch (SaveException e) {
            code = -1;
            msg = e.getMessage();
            log.error(e);
        } catch (Exception e) {
            code = -1;
            msg = e.getMessage();
            log.error(msg, e);
        }
		return getResult();
	}
	/**
	 *  更新增值税代码表
	 * @Author wangyaolei
	 * @ function:
	 * @return   String
	 */
	public String updateDM_ZZS_ALL() {
		mapParam = JsonHelp.getReqParamMap();
		try {
			 dm.updateDM_ZZS_ALL(mapParam);
			 code = 1;
		} catch (RemoteException e) {
			log.error(e.getMessage(),e);
		}
		catch (SaveException e) {
            code = -1;
            msg = e.getMessage();
            log.error(e);
        } catch (Exception e) {
            code = -1;
            msg = e.getMessage();
            log.error(msg, e);
        }
		return getResult();
	}
	/**
	 *  删除增值税代码表
	 * @Author wangyaolei
	 * @ function:
	 * @return   String
	 */
	public String delDM_ZZS_ALL() {
		mapParam = JsonHelp.getReqParamMap();
		try {
			 dm.delDM_ZZS_ALL(mapParam);
			 code = 2;
		} catch (RemoteException e) {
			log.error(e.getMessage(),e);
		}
		catch (DelErrorException e) {
            code = -1;
            msg = e.getMessage();
            log.error(e);
        } catch (Exception e) {
            code = -1;
            msg = e.getMessage();
            log.error(msg, e);
        }
		return getResult();
	}
	/**
	 *  前台查询消费税代码表（有效标志为Y）
	 * @Author wangyaolei
	 * @ function:
	 * @return   String
	 */
	public String getDM_XFS_ALL_YXBZ() {
		mapParam = JsonHelp.getReqParamMap();
		mapParam.put("YXBZ", "Y");
		Map retMap = new HashMap();
		try {
			retMap = dm.getDM_XFS_ALL(mapParam);
		} catch (RemoteException e) {
			log.error(e.getMessage(),e);
		}
		
		mapModel.put("data", retMap);
		return getResult();
	}
	
	/**
	 * 增值税减免税政策代码表
	 */
	public String getDM_ZZS_JMSZC(){
		mapParam = JsonHelp.getReqParamMap();
		String isPrint = (String)mapParam.get("isPrint");
		String CACHEKEY = "";//可以在函数内改变
		if((mapParam.get("JMZLB") != null) && (!"".equals(mapParam.get("JMZLB"))))
		{
			CACHEKEY = CACHEKEY + "_JMZLB_" + mapParam.get("JMZLB");
		}else if((mapParam.get("XH") != null) && (!"".equals(mapParam.get("XH"))))
		{
			CACHEKEY = CACHEKEY + "_XH";
		}
		Map retMap = new HashMap();
		//这个key和增值税一般纳税人减免税使用的是同一个代码查询，故key使用相同的，如果一般纳税人的已经缓存过，这边就可以直接取数据使用了
		if(isPrint !=null && isPrint.equals("N")) {
			CACHEKEY = "dm.getDM_ZZS_JMSZC" + CACHEKEY;
		}else{
			CACHEKEY = "dm.getDM_ZZS_JMSZC_PRINT" + CACHEKEY;
		}
		//按照name和key的方式从Cache中取数据
		List list = Cache.getList(CACHENAME, CACHEKEY);
		//如果Cache中没有数据，就从数据库中取数据
		if(list == null){
			try {
				Map getMap = dm.getDM_ZZS_JMSZC(mapParam);
				list = getMap.get("BODY")==null?(new ArrayList()):(List)getMap.get("BODY");
				//从数据库中将数据取出来后，放入缓存Cache中，以便第二次的时候直接从缓存中取数据
				Cache.set(CACHENAME, CACHEKEY, list);
			} catch (RemoteException e) {
				log.error(e.getMessage(),e);
			} catch (SaveException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//将list重新放入到返回Map中
		retMap.put("BODY", list);
		mapModel.put("data", retMap);
		
		return getResult();
	}
	/**
	 * 企业所得税减免性质代码表
	 */
	public String getDM_QYSDS_JMSZC(){
		mapParam = JsonHelp.getReqParamMap();
		Map retMap = new HashMap();
		try {
			retMap = dm.getDM_QYSDS_JMSZC(mapParam);
		} catch (RemoteException e) {
			log.error(e.getMessage(),e);
		} catch (SaveException e) {
			e.printStackTrace();
		}
		mapModel.put("data", retMap);
		
		return getResult();
	}
	/**
	 * 消费税减免性质代码表
	 */
	public String getDM_XFS_JMXZ(){
		mapParam = JsonHelp.getReqParamMap();
		Map retMap = new HashMap();
		try {
			retMap = dm.getDM_XFS_JMXZ(mapParam);
		} catch (RemoteException e) {
			log.error(e.getMessage(),e);
		} catch (SaveException e) {
			e.printStackTrace();
		}
		mapModel.put("data", retMap);
		
		return getResult();
	}
	public String getDM_GY_SSXD(){
		mapParam = JsonHelp.getReqParamMap();
		Map retMap = new HashMap();
		try {
			retMap = dm.getDM_GY_SSXD(mapParam);
		} catch (RemoteException e) {
			log.error(e.getMessage(),e);
		} catch (SaveException e) {
			e.printStackTrace();
		}
		mapModel.put("data", retMap);
		
		return getResult();
	}
	
//	/**
//	 * 营改增试点应税项目明细表
//	 */
//	public String getDM_YGZYSXM(){
//		mapParam = JsonHelp.getReqParamMap();
//		Map retMap = new HashMap();
//		try {
//			retMap = dm.getDM_YGZYSXM(mapParam);
//		} catch (RemoteException e) {
//			log.error(e.getMessage(),e);
//		} catch (SaveException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		mapModel.put("data", retMap);
//
//		return getResult();
//	}
	
    /**
     * 后台查询 关联业务代码表
     * @Author wangyaolei
     * @ function:
     * @ returnType:String 
     * @return
     */
	public String getDM_GLYWWL_ALL() {
		mapParam = JsonHelp.getReqParamMap();
		Map retMap = new HashMap();
		try {
			retMap = dm.getDM_GLYWWL_ALL(mapParam);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		} 
		
		mapModel.put("data", retMap);
		return getResult();
	}
}
