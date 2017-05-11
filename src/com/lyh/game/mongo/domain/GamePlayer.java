
package com.lyh.game.mongo.domain;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * ClassName:Role <br/>
 * Function: TODO (角色数据库数据). <br/>
 * Reason: TODO (). <br/>
 * Date: 2015-7-3 下午4:34:45 <br/>
 * 
 * @author lyh
 * @version
 * @see
 */
@Document(collection="game_player")
public class GamePlayer extends com.lyh.game.lib.sql.DbEntity {
	
	/****/
	private static final long serialVersionUID = 3684576532542034529L;
	
	/** 用户名 **/
	@Indexed
	private String openId;

	/**游戏币**/  
	private int money;

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}
	
}
