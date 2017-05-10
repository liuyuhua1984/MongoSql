package com.mongo.game.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import com.lib.sql.DbEntity;

/**
 * 每盘日志   logCode保存自动赋值 roomCode创建自动赋值
 * @author Administrator
 */
@Document(collection="roomLog")
public class RoomLog extends DbEntity{

	private static final long serialVersionUID = 398155693699077753L;

	@Indexed
	private int logCode;

	private int roomCode;
	
	/**当前局玩家信息(座位顺序排列)**/
	private List<LogPlayer> players = new ArrayList<LogPlayer>();
	
	private int totalRound;
	
	/**每局回放码**/
	private List<Integer> roundList = new ArrayList<>();
	
	private String playerIdString;
	
	public String getPlayerIdString() {
		return playerIdString;
	}

	public void setPlayerIdString(String playerIdString) {
		this.playerIdString = playerIdString;
	}

	public List<Integer> getRoundList() {
		return roundList;
	}
	
	public int getRoomCode() {
		return roomCode;
	}

	public void setRoomCode(int roomCode) {
		this.roomCode = roomCode;
	}
	
	public int getTotalRound() {
		return totalRound;
	}

	public void setTotalRound(int totalRound) {
		this.totalRound = totalRound;
	}

	public List<LogPlayer> getPlayers() {
		return players;
	}

	public void setPlayers(List<LogPlayer> players) {
		this.players = players;
	}
	
	public int getLogCode() {
		return logCode;
	}
	
	public void setLogCode(int logCode) {
		this.logCode = logCode;
	}
}
