package com.sql.mongodb;

import java.util.List;

import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.query.Query;

import com.lib.sql.DbEntity;



/**
 * ClassName:IEntityDAO <br/>
 * Function: TODO (数据库接口). <br/>
 * Reason: TODO (). <br/>
 * Date: 2014-12-11 下午3:47:33 <br/>
 * 
 * @author lyh
 * @version
 * @see
 */
public interface IEntityDAO {
	
	public void save(DbEntity entity);
	
	public void delete(DbEntity entity);
	
	public void saveOrUpdate(DbEntity entity);
	
	public void updateFinal(DbEntity entity);
	
	public <T> T findById(Class<T> entity, Long id);
	
	public <T> List<T> findAll(Class<T> c);
	
	public <T> List<T> findByProperty(Class<T> c, String propertyName, Object value);
	
	public <T> List<T> findByProperties(Class<T> c, List<String> propertyName, List<Object> value);
	
	public <T> List<T> findAndSort(Class<T> c, Query query, String sortKey, Direction direction);
	
}
