package com.lyh.game.mongo.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.lyh.game.lib.sql.DbEntity;

/**
 * 每小局日志信息 logCode和url保存进DB时候自动赋值
 * @author Administrator
 */
@Document(collection="roundLog")
public class RoundLog extends DbEntity{
	
	private static final long serialVersionUID = -807453871836681499L;

	@Indexed
	private int logCode;

	private int roomCode;
	
	/**当前局玩家信息(座位顺序排列)**/
	private List<LogPlayer> players = new ArrayList<LogPlayer>();
	
	private int totalRound;
	
	private int curRound;
	
	/**每局回放文件路径**/
	private String url;
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getCurRound() {
		return curRound;
	}

	public void setCurRound(int curRound) {
		this.curRound = curRound;
	}

	public int getLogCode() {
		return logCode;
	}

	public void setLogCode(int logCode) {
		this.logCode = logCode;
	}

	public int getRoomCode() {
		return roomCode;
	}

	public void setRoomCode(int roomCode) {
		this.roomCode = roomCode;
	}

	public List<LogPlayer> getPlayers() {
		return players;
	}

	public void setPlayers(List<LogPlayer> players) {
		this.players = players;
	}

	public int getTotalRound() {
		return totalRound;
	}

	public void setTotalRound(int totalRound) {
		this.totalRound = totalRound;
	}

}
