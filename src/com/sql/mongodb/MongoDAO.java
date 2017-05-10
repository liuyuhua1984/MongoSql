package com.sql.mongodb;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.lib.sql.DbEntity;



/**
 * ClassName:MongoDao <br/>
 * Function: TODO (). <br/>
 * Reason: TODO (). <br/>
 * Date: 2014-12-1 上午9:34:29 <br/>
 * 
 * @author lyh
 * @version
 * @see
 */
@Component
public class MongoDAO implements IEntityDAO {
	private static Log log = LogFactory.getLog(MongoDAO.class);
	@Autowired
	public MongoTemplate mongodb;
	
	/**
	 * beforeSave:(). <br/>
	 * TODO().<br/>
	 * 保存之前
	 * 
	 * @author lyh
	 * @param transientInstance
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 */
	protected void beforeSave(DbEntity transientInstance) throws Exception {
		
	}
	
	/**
	 * afterSave:(). <br/>
	 * TODO().<br/>
	 * 保存之后
	 * 
	 * @author lyh
	 * @param transientInstance
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 */
	protected void afterSave(DbEntity transientInstance) throws Exception {
		
	}
	
	@Override
	public void save(DbEntity entity) {
		// TODO Auto-generated method stub
		mongodb.insert(entity);
	}
	
	
	@Override
	public void delete(DbEntity entity) {
		// TODO Auto-generated method stub
		mongodb.remove(entity);
	}
	
	@Override
	public void saveOrUpdate(DbEntity entity) {
		// TODO Auto-generated method stub
		mongodb.save(entity);
	}
	

	
	@Override
	public void updateFinal(DbEntity entity) {
		// TODO Auto-generated method stub
		try {
			DbEntity copy = null;
			try {
				copy = entity.getClass().newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			}
			BeanUtils.copyProperties(entity, copy);
			
			Query query = new Query(Criteria.where("_id").is(entity.getId()));
			Update update = new Update();
			@SuppressWarnings("rawtypes")
			Class c = copy.getClass();
			Field[] fields = c.getDeclaredFields();
			for (Field field : fields) {
				PropertyDescriptor pd = null;
				pd = new PropertyDescriptor(field.getName(), c);
				Method getMethod = pd.getReadMethod();// 获得get方法
				Object o = getMethod.invoke(copy);
				update.set(field.getName(), o);
			}
			mongodb.updateFirst(query, update, copy.getClass());
		} catch (IllegalArgumentException e) {
			log.error("更新数据库对象出错:::", e);
		} catch (IllegalAccessException e) {
			log.error("更新数据库对象出错111:::", e);
		} catch (IntrospectionException e) {
			log.error("更新数据库对象出错222:::", e);
		} catch (InvocationTargetException e) {
			log.error("更新数据库对象出错333:::", e);
		}
	}
	
	/**
	 * 分页
	 * @param index 第几页
	 * @param total 总页数
	 * @param entity 
	 * @param sortKey 排序key
	 * @param direction 排序类型
	 * @return
	 */
	public <T> List<T> getPage(int index,int total,Class<T> entity,String sortKey, Direction direction){
        Query query = new Query();
        int skip = (index-1)*total;  
        query.with(new Sort(direction, sortKey));
        query.skip(skip);// skip相当于从那条记录开始  
        query.limit(total);// 从skip开始,取多少条记录  
        return mongodb.find(query, entity);    
	}

	
	@Override
	public <T> T findById(Class<T> entity, Long id) {
		// TODO Auto-generated method stub
		try {
			Query query = new Query(Criteria.where("_id").is(id));
			return mongodb.findOne(query, entity);
		} catch (Exception e) {
			log.error("查找数据库数据有问题:::" + id, e);
		}
		return null;
	}
	public <T> List<T> findByRegex(Class<T> entity,String propertyName,String value){
		Query query = new Query();  
		query.addCriteria(Criteria.where(propertyName).regex(".*?" +value+ ".*"));  
		return mongodb.find(query, entity);
	}
	
	@Override
	public <T> List<T> findAll(Class<T> entity) {
		try {
			// TODO Auto-generated method stub
			return mongodb.findAll(entity);
		} catch (Exception e) {
			log.error("查找数据库文档数据有问题:::", e);
		}
		return null;
	}
	
	
	@Override
	public <T> List<T> findByProperty(Class<T> entity, String propertyName, Object value) {
		// TODO Auto-generated method stub
		try {
			Query query = new Query(Criteria.where(propertyName).is(value));
			return mongodb.find(query, entity);
		} catch (Exception e) {
			log.error("根据一条属性查找文档数据有问题:::", e);
		}
		return null;
	}
	
	@Override
	public <T> List<T> findByProperties(Class<T> entity, List<String> propertyName, List<Object> value) {
		// TODO Auto-generated method stub
		// Criteria criatira = new Criteria();
		// criatira.andOperator(Criteria.where("userName").is("admin"), Criteria.where("password").is("f818fa8cf51ca364f367f0046bd014ff"));
		// template.find(new Query(criatira), User.class);
		try {
			Query query = new Query();
			Criteria ca = null;
			for (int i = 0; i < propertyName.size(); i++) {
				String key = propertyName.get(i);
				Object val = value.get(i);
				if (i == 0) {
					ca = Criteria.where(key).is(val);
				} else {
					ca.and(key).is(val);
				}
			}
			query.addCriteria(ca);
			return this.mongodb.find(query, entity);
		} catch (Exception e) {
			log.error("根据多条属性查找文档数据有问题:::", e);
		}
		return null;
		
	}
	
	public static void main(String args[]) {
//		ChapterScore cs = new ChapterScore();
//		cs.setCommonScore(1000);
//		cs.setCreateTime(new Date(System.currentTimeMillis()));
//		cs.setUpdateTime(new Date(System.currentTimeMillis()));
//		cs.setDifficultScore(1000000);
//		cs.setRank(1);
//		cs.setRoleName("苹果");
//		cs.setSimpleScore(10555);
//		cs.setUserName("dwdddwww");
//		@SuppressWarnings("rawtypes")
//		Class c = (Class) cs.getClass();
//		Field[] fields = c.getDeclaredFields();
//		for (Field field : fields) {
//			PropertyDescriptor pd = null;
//			try {
//				pd = new PropertyDescriptor(field.getName(), c);
//			} catch (IntrospectionException e) {
//				e.printStackTrace();
//			}
//			Method getMethod = pd.getReadMethod();// 获得get方法
//			try {
//				Object o = getMethod.invoke(cs);
//				System.err.println("::" + o);
//			} catch (IllegalAccessException
//			        | IllegalArgumentException
//			        | InvocationTargetException e) {
//				e.printStackTrace();
//			}// 执行get方法返回一个Object
//		}
	}
	
	@Override
	public <T> List<T> findAndSort(Class<T> c, Query query, String sortKey, Direction direction) {
		// TODO Auto-generated method stub
		// query.addCriteria(Criteria.where("channel").is(channel).and("visitDate").gte(startDate).lte(endDate).and("url")
		// .in(urlList));
		// Direction direction=true?Direction.ASC:Direction.DESC;
		try {
			if (direction != null && sortKey != null) {
				query.with(new Sort(direction, sortKey));
			}
			return mongodb.find(query, c);
		} catch (Exception e) {
			log.error("根据查询属性查找文档数据有问题:::", e);
		}
		return null;
	}
}
