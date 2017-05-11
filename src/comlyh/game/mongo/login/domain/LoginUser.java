
package comlyh.game.mongo.login.domain;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.lyh.game.lib.sql.DbEntity;

/** 
 * ClassName:LoginUser <br/> 
 * Function: TODO (用户数据). <br/> 
 * Reason:   TODO (). <br/> 
 * Date:     2017年3月21日 上午11:49:23 <br/> 
 * @author   lyh 
 * @version   
 * @see       
 */
@Document(collection="user")
public class LoginUser extends DbEntity {

	/****/  
	private static final long serialVersionUID = -1404031409387257607L;
	
	/**帐号**/  
	@Indexed
	private String openId;


	/** token刷新标识,微信号 **/
	private String refreshToken;
	
	/** 渠道号 **/
	private String channleId;// 渠道号

	/** 版本号 **/
	private String version;// 版本号
	/** 国际移动电话设备识别码 **/
	private String phoneType;// 机型,游戏机型
	
	/** 国际移动电话设备识别码 **/
	private String imei;// 国际移动电话设备识别码
	
	/** 电话号码 **/
	private long phoneNum;// 电话号码
	
	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public String getChannleId() {
		return channleId;
	}

	public void setChannleId(String channleId) {
		this.channleId = channleId;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getPhoneType() {
		return phoneType;
	}

	public void setPhoneType(String phoneType) {
		this.phoneType = phoneType;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public long getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(long phoneNum) {
		this.phoneNum = phoneNum;
	}
	
}
  