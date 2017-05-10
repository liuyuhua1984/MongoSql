
package com.mongo.login.domain;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.lib.sql.DbEntity;

/** 
 * GameServerInfo <br/> 
 * Function: TODO (游戏服务器信息). <br/> 
 * Reason:   TODO (). <br/> 
 * Date:     2017年3月21日 下午4:59:04 <br/> 
 * @author   lyh 
 * @version   
 * @see       
 */
@Document(collection="game_server_info")
public class GameServerInfo extends DbEntity {

	/****/  
	private static final long serialVersionUID = -229485199228963551L;
	
	/**服务器名称**/  
	private String serverName;
	/**主机名**/  
	private String hostName;
	/**主机端口**/  
	private int port;
	
	/**游戏类型 1表示麻将**/  
	@Indexed
	private int gameType;
	
	/**服务器状态 1正常,2维护,**/  
	private int serverStatus;
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}

	public int getServerStatus() {
		return serverStatus;
	}
	public void setServerStatus(int serverStatus) {
		this.serverStatus = serverStatus;
	}
	public int getGameType() {
		return gameType;
	}
	public void setGameType(int gameType) {
		this.gameType = gameType;
	}
	public String getServerName() {
		return serverName;
	}
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	
}
  