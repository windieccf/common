package com.nus.adqs.service.task.impl;

import java.io.File;
import java.nio.file.Files;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;

import com.nus.adqs.base.form.BaseForm;
import com.nus.adqs.constant.ConstantStatus;
import com.nus.adqs.dataaccess.model.security.user.UserInfo;
import com.nus.adqs.dataaccess.model.taskmanagement.SubTask;
import com.nus.adqs.dataaccess.model.taskmanagement.SubTaskAssignment;
import com.nus.adqs.dataaccess.model.taskmanagement.SubTaskDocument;
import com.nus.adqs.dataaccess.model.taskmanagement.SubTaskSubmission;
import com.nus.adqs.dataaccess.model.taskmanagement.Task;
import com.nus.adqs.dataaccess.persistence.EmLocator;
import com.nus.adqs.dataaccess.persistence.SystemSetupHome;
import com.nus.adqs.dataaccess.scalar.form.FilePartEntity;
import com.nus.adqs.dataaccess.scalar.form.ManagerDashboardEntity;
import com.nus.adqs.dataaccess.scalar.form.UserDashboardEntity;
import com.nus.adqs.dataaccess.scalar.form.UserSubTaskEntity;
import com.nus.adqs.dataaccess.scalar.form.UserTaskStatusEntity;
import com.nus.adqs.dataaccess.scalar.result.TaskDueEntity;
import com.nus.adqs.delegate.user.UserDelegate;
import com.nus.adqs.exception.ValidationException;
import com.nus.adqs.service.impl.BaseServiceImpl;
import com.nus.adqs.service.task.TaskService;
import com.nus.adqs.util.BeanUtil;
import com.nus.adqs.util.DateUtil;
import com.nus.adqs.util.StringUtil;
import com.nus.adqs.util.mail.MailUtil;

public class TaskServiceImpl extends BaseServiceImpl<Task> implements TaskService{

	private static String LATEST_SUBMISSION_QUERY;
	static{
		LATEST_SUBMISSION_QUERY = new StringBuffer()
									.append("SELECT * FROM ( ")
									.append("	SELECT TS.TASK_ID, TA.ID , I.FULL_NAME, TA.SUBMITTED_DATETIME ")
									.append("	FROM TMS.SUB_TASK_ASGN TA ") 
									.append("	INNER JOIN COMMON.USER_INFO I ON I.USER_ACCOUNT_ID = TA.USER_ACCOUNT_ID  ") 
									.append("	INNER JOIN TMS.SUB_TASK TS ON TS.ID = TA.SUB_TASK_ID ") 
									.append("	WHERE TA.STATUS_01 = ''"+ConstantStatus.TMS_TASK_USER_SUBMITTED+"'' " ) 
									.append("	AND TS.TASK_ID = {0} ") 
									.append("	ORDER BY TA.SUBMITTED_DATETIME ASC ,  I.FULL_NAME ASC  LIMIT 3 ") 
									.append(" ) AS X ")
									.toString();
		
		
		
		
		
	}
	
	@Override
	public void validate(BaseForm<Task> form) throws ValidationException,Exception {
		// Kenny or who ever, please fill in this portion
	}

	
	
	
	/***************************************** FOR STATISTIC PURPOSE *********************************************************/
	
	@Override
	public List<ManagerDashboardEntity> doRetrieveManagmentDashboard(Long userId){
		return this.doRetrieveManagmentDashboard(userId, null);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public int doCountUserTaskStatusByType(Long taskId, String type){
		try{
			
			StringBuffer mySql = new StringBuffer()
			.append("	SELECT COUNT(1) ")
			.append("		FROM TMS.SUB_TASK_ASGN A ")
			.append("		INNER JOIN TMS.SUB_TASK S ON S.ID = A.SUB_TASK_ID ")
			.append("		INNER JOIN TMS.TASK T ON T.ID = S.TASK_ID ")
			.append("		INNER JOIN COMMON.USER_ACCOUNT U ON U.ID = A.USER_ACCOUNT_ID ")
			.append("	WHERE 1=1 ")
			.append("	AND T.ID =:taskId") 
			.append("	AND A.STATUS_01=:status01");  
			
			Query query = EmLocator.getEm().createNativeQuery(mySql.toString());
			query.setParameter("taskId", taskId);
			query.setParameter("status01", type);
			query.setMaxResults(1);
			
			List<Object> results = query.getResultList();
			if(!results.isEmpty())
				return BeanUtil.getAsInteger(results.get(0));
		}catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public ManagerDashboardEntity doRetrieveUserTaskStatusByType( int rowPerPage, int startIndex , Long taskId, String type){
		ManagerDashboardEntity entity = new ManagerDashboardEntity();
		try{
			
			StringBuffer mySql = new StringBuffer()
								.append("	SELECT T.NAME AS TASK_NAME , S.ID AS SUB_TASK_ID , S.NAME AS SUB_TASK_NAME , U.USERNAME, A.SUBMITTED_DATETIME, A.INPROGRESS_DATETIME, A.PENDING_DATETIME, A.STATUS_01 ,A.ID")
								.append("		FROM TMS.SUB_TASK_ASGN A ")
								.append("		INNER JOIN TMS.SUB_TASK S ON S.ID = A.SUB_TASK_ID ")
								.append("		INNER JOIN TMS.TASK T ON T.ID = S.TASK_ID ")
								.append("		INNER JOIN COMMON.USER_ACCOUNT U ON U.ID = A.USER_ACCOUNT_ID ")
								.append("	WHERE 1=1 ")
								.append("	AND T.ID =:taskId") 
								.append("	AND A.STATUS_01=:status01");    
			//
			if(ConstantStatus.TMS_TASK_USER_PENDING.equals(type))
				mySql.append(" ORDER BY S.START_DATETIME ASC ");
			else if(ConstantStatus.TMS_TASK_USER_INPROGRESS.equals(type))
				mySql.append(" ORDER BY A.INPROGRESS_DATETIME DESC ");
			else if(ConstantStatus.TMS_TASK_USER_SUBMITTED.equals(type))
				mySql.append(" ORDER BY A.SUBMITTED_DATETIME DESC ");
			else if(ConstantStatus.TMS_TASK_USER_REJECTED.equals(type))
				mySql.append(" ORDER BY S.START_DATETIME ASC ");
			
			
			Query query = EmLocator.getEm().createNativeQuery(mySql.toString());
			query.setParameter("taskId", taskId);
			query.setParameter("status01", type);
			query.setMaxResults(rowPerPage);
			query.setFirstResult(startIndex);
			
			
			List<Object> results = query.getResultList();
			for(Object obj : results){
				Object[] objs = (Object[]) obj;
			
				int i = 0;
				UserTaskStatusEntity subEntity = entity.addNewUserStatus();
				entity.setTaskName(BeanUtil.getAsString(objs[i++]));
				subEntity.setSubTaskId(BeanUtil.getAsLong(objs[i++]));
				subEntity.setSubTaskName(BeanUtil.getAsString(objs[i++]));
				subEntity.setUserName(BeanUtil.getAsString(objs[i++]));
				subEntity.setSubmittedDateTime((Date)objs[i++]);
				subEntity.setInprogressDateTime((Date)objs[i++]);
				subEntity.setPendingDateTime((Date)objs[i++]);
				i++;
				subEntity.setSubTaskAssignId(BeanUtil.getAsLong(objs[i++]));
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		
		return entity;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ManagerDashboardEntity> doRetrieveManagmentDashboard(Long userId, Long taskId){
		List<ManagerDashboardEntity> entities = new ArrayList<ManagerDashboardEntity>();
		
		
		try{
			// 1. ensure parameter is complete
			Map<String,Object> parameterMap = new HashMap<String,Object>();
			List<String> conditions = new ArrayList<String>();
			if(taskId!=null){
				conditions.add(" T.ID =:taskId ");
				parameterMap.put("taskId", taskId);
			}
			if(userId!=null){
				conditions.add(" T.CREATED_BY_ID =:currentUser ");
				parameterMap.put("currentUser", userId);
			}
				
			// generate the SQL for main dashboard
			StringBuffer taskSql = new StringBuffer()
						.append(" SELECT X.ID, X.NAME, X.TASK_START_TIME, X.TASK_END_TIME, X.INDIVIDUAL_STATUS AS STATUS, SUM(X.COUNT) AS COUNT ")
						.append("	FROM ( ")
						.append("		SELECT T.ID , T.NAME, T.START_DATETIME AS TASK_START_TIME , T.END_DATETIME AS TASK_END_TIME,  ") 
						.append("			TS.STATUS_01 AS SUB_TASK_STATUS, TS.START_DATETIME, TS.END_DATETIME, ") 
						.append("			(CASE WHEN TS.STATUS_01 = '"+ConstantStatus.TMS_TASK_CONSOLIDATED+"' THEN '"+ConstantStatus.TMS_TASK_CONSOLIDATED+"' ELSE TA.STATUS_01 END ) AS INDIVIDUAL_STATUS,  ")
						.append("			COUNT(1) AS COUNT ")
						.append("		FROM TMS.SUB_TASK_ASGN TA ")
						.append("		INNER JOIN TMS.SUB_TASK TS ON TA.SUB_TASK_ID = TS.ID ")
						.append("		RIGHT OUTER JOIN TMS.TASK T ON T.ID = TS.TASK_ID ") 
						.append("		WHERE 1=1 ")
						.append( ((conditions.isEmpty())? "" : " AND " + StringUtils.join(conditions, " AND "))  )
						.append("		GROUP BY T.ID, T.NAME, T.START_DATETIME, T.END_DATETIME,  ") 
						.append("		TS.STATUS_01, TS.START_DATETIME, TS.END_DATETIME, TA.STATUS_01 ") 
						.append("	) AS X ")
						.append("	GROUP BY X.ID, X.NAME, X.TASK_START_TIME, X.TASK_END_TIME, X.INDIVIDUAL_STATUS ") 
						.append("	ORDER BY X.TASK_START_TIME ASC, X.NAME ASC ") 
						.append("");
			
			// 3. perform call and retrieve result from database
			Query query = EmLocator.getEm().createNativeQuery(taskSql.toString());
			if(!parameterMap.isEmpty()){
				for(String key : parameterMap.keySet()){
					query.setParameter(key, parameterMap.get(key));
				}
			}
			
			
			
			List<Object> results = query.getResultList();
			if(!results.isEmpty()){
				
				// 4. store the return result to bean
				List<String> lastSubmissionQuery = new ArrayList<String>();
				Map<Long,ManagerDashboardEntity> managementMap =  new LinkedHashMap<Long,ManagerDashboardEntity>();
				
				ManagerDashboardEntity entity = null;
				for(Object obj : results){
					Object[] objs = (Object[]) obj;
					int i = 0;
					Long id = BeanUtil.getAsLong(objs[i++]);
					
					// 4.1 ensure query for individual submission is in place
					if(entity == null){
						entity = new ManagerDashboardEntity();
						lastSubmissionQuery.add(MessageFormat.format(LATEST_SUBMISSION_QUERY, id));
					}else if(!entity.getTaskId().equals(id)){
						managementMap.put(entity.getTaskId(), entity);
						entity = new ManagerDashboardEntity();
						lastSubmissionQuery.add(MessageFormat.format(LATEST_SUBMISSION_QUERY, id));
					}
					
					entity.setTaskId(id);
					entity.setTaskName(BeanUtil.getAsString(objs[i++]));
					entity.setStartDate((Date) objs[i++] );
					entity.setEndDate((Date) objs[i++] );
					entity.setCount(BeanUtil.getAsString(objs[i++]), BeanUtil.getAsInteger(objs[i++]));
		 		}
				
				managementMap.put(entity.getTaskId(), entity);
				
				// 4.2 perform call to the individual submission query
				String submissionQuery = StringUtils.join(lastSubmissionQuery, " UNION ALL ");
				query = EmLocator.getEm().createNativeQuery(submissionQuery);
				List<Object> submissionResult = query.getResultList();
				
				// 4.3 massage the date
				for(Object obj : submissionResult){
					Object[] objs = (Object[]) obj;
					if(!managementMap.containsKey(BeanUtil.getAsLong(objs[0])))
						continue;
					
					entity = managementMap.get(BeanUtil.getAsLong(objs[0])); // retrieve based on task ID
					entity.addNewSubmission(BeanUtil.getAsLong(objs[1]), BeanUtil.getAsString(objs[2]), (Date)objs[3]);
				}
				
				// 4.4 merge the result 
				entities.addAll(managementMap.values());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		
		return entities;
	}




	@Override
	public void saveTask(Task task, Map<String, String[]> parameters, List<FilePartEntity> files) throws Exception{
		
		try{
			//validation?
			
			EmLocator.getEm().persist(task);
			
			ArrayList<String> subTaskId = new ArrayList<String>( Arrays.asList(parameters.get("subTaskId")) );
			ArrayList<String> subTaskName = new ArrayList<String>( Arrays.asList(parameters.get("subTaskName")) );
			ArrayList<String> subTaskStartDate = new ArrayList<String>( Arrays.asList(parameters.get("subTaskStartDateTime")) );
			ArrayList<String> subTaskEndDate = new ArrayList<String>( Arrays.asList(parameters.get("subTaskEndDateTime")) );
			ArrayList<String> subTaskDescription = new ArrayList<String>( Arrays.asList(parameters.get("subTaskDescription")) );
			Long currentUserId = task.getModifiedById();

			task.markSubTaskDelete();
			Date earliestStartDate = null;
			Date latestEndDate = null;
			
			int subTaskCount = subTaskId.size();
			for (int i = 1 ; i < subTaskCount; i++) { 
				Long selectedSubTaskId = (StringUtil.isEmpty(subTaskId.get(i)) ? null : Long.parseLong(subTaskId.get(i))) ;
				SubTask subTask = task.retrieveOrAddNewSubTaskById(selectedSubTaskId);
				if (!subTask.isPkSet()) {
					subTask.setCreatedById(currentUserId);
					subTask.setCreatedDateTime(DateUtil.getSystemDate());
				}
				subTask.setModifiedById(currentUserId);
				subTask.setModifiedDateTime(DateUtil.getSystemDate());

				EmLocator.getEm().persist(subTask);
				EmLocator.getEm().flush();
				
				Date startDate = DateUtil.convertStringToDate(subTaskStartDate.get(i), "d/M/y");
				Date endDate = DateUtil.convertStringToDate(subTaskEndDate.get(i), "d/M/y");
				subTask.setStatus(ConstantStatus.ACTIVE);
				subTask.setName(subTaskName.get(i));
				subTask.setDescription(subTaskDescription.get(i));
				subTask.setStartDateTime(startDate);
				subTask.setEndDateTime(endDate);
				if (earliestStartDate == null) {
					earliestStartDate = startDate;
					latestEndDate = endDate;
				} else {
					if (startDate.before(earliestStartDate))
						earliestStartDate = startDate;
					if (endDate.after(latestEndDate))
						latestEndDate = endDate;
				}
				
				subTask.markAssignmentDelete();
				String[] subTaskAssignmentUserNames = parameters.get("subTaskAssignedTo"+i);
				if(subTaskAssignmentUserNames!=null){
					for(int x = 0; x < subTaskAssignmentUserNames.length; x++){
						String username = subTaskAssignmentUserNames[x];
						SubTaskAssignment subTaskAssignment = subTask.retrieveOrAddNewSubTaskAssignmentByUserName(username);
						if(!subTaskAssignment.isPkSet()) {
							subTaskAssignment.setUserAccount(UserDelegate.getInstance().doGetUserAccountByUserName(username));
							subTaskAssignment.setCreatedById(currentUserId);
							subTaskAssignment.setCreatedDateTime(DateUtil.getSystemDate());
						}
						subTaskAssignment.setModifiedById(currentUserId);
						subTaskAssignment.setModifiedDateTime(DateUtil.getSystemDate());

						subTaskAssignment.setStatus(ConstantStatus.ACTIVE);
						EmLocator.getEm().persist(subTaskAssignment);
					}
				}
				EmLocator.getEm().persist(subTask);
				
				FilePartEntity userFile = (i-1 < files.size() ) ?  files.get(i-1) : null;
				if(userFile != null && !StringUtil.isEmpty(userFile.getFileName())){
					SubTaskDocument subTaskDocument = subTask.addNewDocument();
					if(!subTaskDocument.isPkSet()) {
						subTaskDocument.setCreatedById(currentUserId);
						subTaskDocument.setCreatedDateTime(DateUtil.getSystemDate());
					}
					subTaskDocument.setModifiedById(currentUserId);
					subTaskDocument.setModifiedDateTime(DateUtil.getSystemDate());
					
					String templatePath = StringUtil.generateSecureFilePath(userFile, subTask.getId());
					subTaskDocument.setTemplatePath(templatePath);
					subTaskDocument.setName(userFile.getFileName());
					Files.copy(userFile.getFile().toPath(), (new File(templatePath)).toPath());
					EmLocator.getEm().persist(subTaskDocument);
				}
				
				
			}
			task.setStartDateTime(earliestStartDate);
			task.setEndDateTime(latestEndDate);
			
			EmLocator.getEm().persist(task);
			EmLocator.getEm().flush();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<UserDashboardEntity> doRetrieveUserDashboardByType(int rowPerPage, int startIndex, String type, Long userId) {
		List<UserDashboardEntity> entities = new ArrayList<UserDashboardEntity>();
		try{
			//Check task and sub task status = 'Active'
			StringBuffer mySql = new StringBuffer()
								.append("	SELECT T.NAME AS TASK_NAME , S.NAME AS SUB_TASK_NAME , U.USERNAME, A.SUBMITTED_DATETIME, A.INPROGRESS_DATETIME, A.PENDING_DATETIME, A.STATUS_01, A.SUB_TASK_ID AS SUB_TASK_ID, A.ID AS ASSIGN_ID ")
								.append("		FROM TMS.SUB_TASK_ASGN A ")
								.append("		INNER JOIN TMS.SUB_TASK S ON S.ID = A.SUB_TASK_ID ")
								.append("		INNER JOIN TMS.TASK T ON T.ID = S.TASK_ID ")
								.append("		INNER JOIN COMMON.USER_ACCOUNT U ON U.ID = A.USER_ACCOUNT_ID ")
								.append("	WHERE 1=1 ")
								.append("   and S.status= '" + ConstantStatus.ACTIVE + "'")
								.append("   and (T.status= '" + ConstantStatus.ACTIVE + "' OR (T.status= '" + ConstantStatus.TMS_TASK_CONSOLIDATED + "' AND T.modified_datetime > NOW() - interval '3 months'))")
								.append("	AND S.STATUS_01 <> '" + ConstantStatus.TMS_TASK_DRAFT + "'")
								.append("	AND U.ID =:userId") 
								.append("	AND A.STATUS_01=:status01");    
			
			if(ConstantStatus.TMS_TASK_USER_PENDING.equals(type))
				mySql.append(" ORDER BY S.START_DATETIME ASC ");
			else if(ConstantStatus.TMS_TASK_USER_INPROGRESS.equals(type))
				mySql.append(" ORDER BY A.INPROGRESS_DATETIME DESC ");
			else if(ConstantStatus.TMS_TASK_USER_SUBMITTED.equals(type))
				mySql.append(" ORDER BY A.SUBMITTED_DATETIME DESC ");
			else if(ConstantStatus.TMS_TASK_USER_REJECTED.equals(type))
				mySql.append(" ORDER BY S.START_DATETIME ASC ");
			
			
			Query query = EmLocator.getEm().createNativeQuery(mySql.toString());
			query.setParameter("userId", userId);
			query.setParameter("status01", type);
			query.setMaxResults(rowPerPage);
			query.setFirstResult(startIndex);
			
			
			List<Object> results = query.getResultList();
			for(Object obj : results){
				UserDashboardEntity entity = new UserDashboardEntity();
				Object[] objs = (Object[]) obj;
			
				int i = 0;
				entity.setTaskName(BeanUtil.getAsString(objs[i++]));
				entity.setSubTaskName(BeanUtil.getAsString(objs[i++]));
				entity.setUserName(BeanUtil.getAsString(objs[i++]));
				entity.setSubmittedDateTime((Date)objs[i++]);
				entity.setInprogressDateTime((Date)objs[i++]);
				entity.setPendingDateTime((Date)objs[i++]);
				entity.setStatus(BeanUtil.getAsString(objs[i++]));
				entity.setSubTaskId(BeanUtil.getAsLong(objs[i++]));
				entity.setSubTaskAsgnId(BeanUtil.getAsLong(objs[i++]));
				entities.add(entity);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		
		return entities;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<UserDashboardEntity> doRetrieveUserDashboard(Long userId) {
		List<UserDashboardEntity> entities = new ArrayList<UserDashboardEntity>();
		try{
				
			// generate the SQL for main dashboard
			StringBuffer taskSql = new StringBuffer()
						.append(" SELECT TA.STATUS_01, COUNT(1) ")
						.append("FROM TMS.SUB_TASK_ASGN TA ")
						.append("INNER JOIN TMS.SUB_TASK TS ON TA.SUB_TASK_ID = TS.ID ") 
						.append("INNER JOIN TMS.TASK T ON T.ID = TS.TASK_ID ") 
						.append("INNER JOIN COMMON.USER_ACCOUNT U ON U.ID = TA.USER_ACCOUNT_ID WHERE 1=1 ")
						.append("AND U.ID =:userId ")
						.append("AND TS.STATUS_01 <> '" + ConstantStatus.TMS_TASK_DRAFT + "'")
						.append("   AND T.status= '" + ConstantStatus.ACTIVE + "'")
						.append("   AND TS.status= '" + ConstantStatus.ACTIVE + "'")
						.append("GROUP BY TA.STATUS_01")
						.append("");
			
			// perform call and retrieve result from database
			Query query = EmLocator.getEm().createNativeQuery(taskSql.toString());
			query.setParameter("userId", userId);
			
			
			List<Object> results = query.getResultList();
			if(!results.isEmpty()){
				
				UserDashboardEntity entity = new UserDashboardEntity();
				for(Object obj : results){
					Object[] objs = (Object[]) obj;
					entity.setCount(BeanUtil.getAsString(objs[0]), BeanUtil.getAsInteger(objs[1]));
		 		}
				entities.add(entity);
				
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		
		return entities;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public int doCountUserTaskStatusByUserIdType(Long userId, String type){
		try{
			
			StringBuffer mySql = new StringBuffer()
			.append("	SELECT COUNT(1) ")
			.append("		FROM TMS.SUB_TASK_ASGN A ")
			.append("		INNER JOIN TMS.SUB_TASK S ON S.ID = A.SUB_TASK_ID ")
			.append("		INNER JOIN TMS.TASK T ON T.ID = S.TASK_ID ")
			.append("		INNER JOIN COMMON.USER_ACCOUNT U ON U.ID = A.USER_ACCOUNT_ID ")
			.append("	WHERE 1=1 ")
			.append("   and T.status= '" + ConstantStatus.ACTIVE + "'")
			.append("   and S.status= '" + ConstantStatus.ACTIVE + "'")
			.append("	AND U.ID =:userId") 
			.append("	AND A.STATUS_01=:status01");  
			
			Query query = EmLocator.getEm().createNativeQuery(mySql.toString());
			query.setParameter("userId", userId);
			query.setParameter("status01", type);
			query.setMaxResults(1);
			
			List<Object> results = query.getResultList();
			if(!results.isEmpty())
				return BeanUtil.getAsInteger(results.get(0));
		}catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public UserSubTaskEntity doRetrieveUserSubTask(Long subtaskAssignId){
		UserSubTaskEntity entity = new UserSubTaskEntity();
		
		try{
			
			StringBuffer mySql = new StringBuffer()
			.append("	SELECT A.id as SUB_TASK_ASGN_ID,S.id as SUB_TASK_ID,T.id as TASK_ID,")
			.append("       S.name as SUB_TASK_NAME, S.description as SUB_TASK_DESC,")
			.append("       S.start_datetime as SUB_TASK_START_DATE, S.end_datetime as SUB_TASK_END_DATE,")
			.append("       A.status_01 as SUB_TASK_STATUS, T.name as TASK_NAME, T.description as TASK_DESCRIPTION,")
			.append("       T.start_datetime as TASK_START_DATE, T.end_datetime as TASK_END_DATE, U.username as USER_NAME,")
			.append("       U.id as USER_ID,")
			.append("       (Select name from tms.sub_task_doc where sub_task_id = A.SUB_TASK_ID and A.id in (select sub_task_asgn_id from tms.sub_task_subs where status = '" + ConstantStatus.ACTIVE + "')")
		    .append("                              and status = '" + ConstantStatus.ACTIVE + "' order by modified_datetime DESC LIMIT 1) as TempateName,")
			.append("       (Select COUNT(1) from tms.sub_task_doc where sub_task_id = A.SUB_TASK_ID ")
		    .append("                              and status = '" + ConstantStatus.ACTIVE + "') as TempateCount")
		    .append("	FROM TMS.SUB_TASK_ASGN A ")
			.append("	    INNER JOIN TMS.SUB_TASK S ON S.ID = A.SUB_TASK_ID ")
			.append("		INNER JOIN TMS.TASK T ON T.ID = S.TASK_ID ")
			.append("		INNER JOIN COMMON.USER_ACCOUNT U ON A.user_account_id = U.id ")
			.append("	WHERE 1=1 ")
			.append("	AND A.ID =:subtaskAssignId");
			
			Query query = EmLocator.getEm().createNativeQuery(mySql.toString());
			query.setParameter("subtaskAssignId", subtaskAssignId);
			query.setMaxResults(1);
			
			List<Object> results = query.getResultList();
				
			for(Object obj : results){
				Object[] objs = (Object[]) obj;
				
				int i = 0;			
				entity.setSubtaskAssignId((long)BeanUtil.getAsInteger(objs[i++]));
				entity.setSubtaskId((long)BeanUtil.getAsInteger(objs[i++]));
				entity.setTaskId((long)BeanUtil.getAsInteger(objs[i++]));
				entity.setSubtaskName(BeanUtil.getAsString(objs[i++]));
				entity.setSubtaskDesc(BeanUtil.getAsString(objs[i++]));
				entity.setSubtaskStartDate((Date)objs[i++]);
				entity.setSubtaskEndDate((Date)objs[i++]);	
				entity.setSubtaskAssgnStatus(BeanUtil.getAsString(objs[i++]));
				entity.setTaskName(BeanUtil.getAsString(objs[i++]));
				entity.setTaskDesc(BeanUtil.getAsString(objs[i++]));
				entity.setTaskStartDate((Date)objs[i++]);
				entity.setTaskEndDate((Date)objs[i++]);
				entity.setUserName(BeanUtil.getAsString(objs[i++]));
				entity.setUserAccountId((long)BeanUtil.getAsInteger(objs[i++]));
				entity.setTemplateName(BeanUtil.getAsString(objs[i++]));
				entity.setTemplateCount(BeanUtil.getAsInteger(objs[i++]));
				}
				
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return entity;
	}
	
	@Override
	public void doSaveUserSubTask(UserSubTaskEntity userSubtaskEntity,FilePartEntity file) throws Exception{
		try{
			Long subtaskassgnId = userSubtaskEntity.getSubtaskAssignId();
			Long useraccountId = userSubtaskEntity.getUserAccountId();
			SubTaskAssignment subtaskassgn = EmLocator.getEm().find(SubTaskAssignment.class, subtaskassgnId);
			
			// Update SubTaskAssignment
			subtaskassgn.setModifiedById(useraccountId);
			subtaskassgn.setModifiedDateTime(DateUtil.getSystemDate());
			subtaskassgn.setStatus01(userSubtaskEntity.getSubtaskAssgnStatus());
				
			if(userSubtaskEntity.getSubtaskAssgnStatus().equals(ConstantStatus.TMS_TASK_USER_INPROGRESS)){
			    subtaskassgn.setInProgressDateTime(DateUtil.getSystemDate());
			}
						
			// Save the uploaded file to SubTaskSubmission
			if(file != null && !StringUtil.isEmpty(file.getFileName())){
				SubTaskSubmission subTaskSubmission = subtaskassgn.addNewSubmission();
				
				String templatePath = StringUtil.generateSecureFilePath(file, subtaskassgnId);
				subTaskSubmission.setSubTaskAssignmentId(subtaskassgnId);
				subTaskSubmission.setCreatedDateTime(DateUtil.getSystemDate());
				subTaskSubmission.setCreatedById(useraccountId);
				subTaskSubmission.setModifiedById(useraccountId);
				subTaskSubmission.setModifiedDateTime(DateUtil.getSystemDate());
				subTaskSubmission.setDocumentPath(templatePath);
				subTaskSubmission.setStatus(ConstantStatus.ACTIVE);
				Files.copy(file.getFile().toPath(), (new File(templatePath)).toPath());
				EmLocator.getEm().persist(subTaskSubmission);
				
				subtaskassgn.setStatus01(ConstantStatus.TMS_TASK_USER_SUBMITTED);
				subtaskassgn.setSubmittedDateTime(DateUtil.getSystemDate());
			}

			EmLocator.getEm().persist(subtaskassgn);
			
			EmLocator.getEm().flush();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public UserSubTaskEntity doRetrieveLastUploadFile(Long subtaskAssignId){
		UserSubTaskEntity entity = new UserSubTaskEntity();
	
        try{
			
			StringBuffer mySql = new StringBuffer()
			.append("	SELECT S.ID as ID,S.document_path as DOC_PATH,D.name as NAME")
			.append("      FROM TMS.SUB_TASK_SUBS AS S")
			.append("      INNER JOIN TMS.SUB_TASK_ASGN AS A ON S.sub_task_asgn_id = A.ID")
			.append("      INNER JOIN TMS.SUB_TASK_DOC AS D ON A.sub_task_id = D.sub_task_id")
			.append("	WHERE 1=1 ")
			.append("   AND S.status= '" + ConstantStatus.ACTIVE + "'")
			.append("	AND S.sub_task_asgn_id =:subtaskAssignId")
			.append("   ORDER BY S.modified_datetime desc,D.modified_datetime desc limit 1");
			
			Query query = EmLocator.getEm().createNativeQuery(mySql.toString());
			query.setParameter("subtaskAssignId", subtaskAssignId);
			//query.setMaxResults(1);
			
			List<Object> results = query.getResultList();
				
			for(Object obj : results){
				Object[] objs = (Object[]) obj;
				
				int i = 0;			
				entity.setSubtaskAssignId((long)BeanUtil.getAsInteger(objs[i++]));
				entity.setUploadFilePath(BeanUtil.getAsString(objs[i++]));
                entity.setTemplateName(BeanUtil.getAsString(objs[i++]));				
				}		
				
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return entity;
	}
	
	@Override
	public void doLaunchSubTask(Task task, SubTask subTask){
		subTask.setStatus01(ConstantStatus.TMS_TASK_LAUNCH);
		EmLocator.getEm().persist(subTask);
		EmLocator.getEm().flush();

		for (SubTaskAssignment subTaskAssignment:subTask.getAssignments()) {
			UserInfo userInfo = subTaskAssignment.getUserAccount().getUserInfo();
			StringBuffer emailContent = new StringBuffer()
						.append("Hi, "+userInfo.getFullName()+",<br />")
						.append("A new sub-task has been launched: \""+subTask.getName()+"\" ("+task.getName()+")<br />")
						.append("Deadline : "+DateUtil.convertDateToString(subTask.getEndDateTime(), "dd-MMM-yyyy")+"<br />")
						.append(
							(new Date()).compareTo(task.getEndDateTime()) > 0 ?
								"Please complete the task and submit as soon as possible. The due date is already over. Thank You.<br />"
								:
								"Please complete the task and submit before the above deadline. Thank You.<br />"
						)
						.append("<br />")
						.append("Please do not reply to this reminder as it is automated and is unable to receive replies.<br />")
						.append("UserName: "+userInfo.getUserAccount().getUsername()+"<br />")
						.append("URL: <a href=\""+SystemSetupHome.getParam().getSystemAddress()+"/task/user/workview/edit?selectedid="+subTaskAssignment.getId()+"\">"+SystemSetupHome.getParam().getSystemAddress()+"/task/user/workview/edit?selectedid="+subTaskAssignment.getId()+"</a><br />")
						.append("<br />")
						.append("Yours sincerely,<br />")
						.append("SST Task Administrator<br />");
			
			try {
				MailUtil
					.create(SystemSetupHome.getParam().getEmailHost())
					.port(SystemSetupHome.getParam().getEmailPort())
					.enableTtls()
					.credential(SystemSetupHome.getParam().getEmailUsername(), SystemSetupHome.getParam().getEmailPassword())
					.from(SystemSetupHome.getParam().getEmailSender())
					.addReceipients(userInfo.getEmail())
					.subject("Sub-task reminder: "+subTask.getName()+" ("+task.getName()+")")
					.content(emailContent.toString())
					.send();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<TaskDueEntity> doRetrieveDueSubTask(long interval){
		List<TaskDueEntity> entities = new ArrayList<TaskDueEntity>();
		try{
			StringBuffer mySql = new StringBuffer()
								.append(" SELECT X.TASK_ID, X.SUB_TASK_ID, ")
								.append("	X.TASK_NAME, X.SUB_TASK_NAME , X.END_DATETIME, X.STATUS_01, X.INTERVAL,  ") 
								.append("	array_to_string(array_agg(X.EMAIL),',')  ")
								.append(" FROM ( ")
								.append("		SELECT T.ID AS TASK_ID, S.ID AS SUB_TASK_ID , T.NAME AS TASK_NAME, S.NAME AS SUB_TASK_NAME , ") 
								.append("			S.END_DATETIME, A.STATUS_01, I.EMAIL, EXTRACT(DAY  FROM (S.END_DATETIME - CURRENT_DATE) ) AS INTERVAL ")
								.append("		FROM TMS.SUB_TASK S ") 
								.append("		INNER JOIN TMS.TASK T ON T.ID = S.TASK_ID ") 
								.append("		INNER JOIN TMS.SUB_TASK_ASGN A ON A.SUB_TASK_ID = S.ID ") 
								.append("		INNER JOIN COMMON.USER_ACCOUNT U ON U.ID = A.USER_ACCOUNT_ID ") 
								.append("		INNER JOIN COMMON.USER_INFO I ON I.USER_ACCOUNT_ID = U.ID ") 
								.append("		WHERE 1=1  ") 
								.append("		AND A.STATUS_01 = '"+ConstantStatus.TMS_TASK_USER_PENDING+"' OR A.STATUS_01 = '"+ConstantStatus.TMS_TASK_USER_REJECTED+"' ")
								.append(" ) AS X WHERE X.INTERVAL <:interval ")
								.append("	GROUP BY X.TASK_NAME, X.SUB_TASK_NAME , X.END_DATETIME, X.STATUS_01, X.INTERVAL, X.TASK_ID, X.SUB_TASK_ID") 
						.append("");
			
			List objects = EmLocator.getEm().createNativeQuery(mySql.toString())
								.setParameter("interval", interval)
								.getResultList();
				
			for(Object obj : objects){
				Object[] objs = (Object[])obj;
				TaskDueEntity entity = new TaskDueEntity();
				
				int i = 0;
				entity.setTaskId(BeanUtil.getAsLong(objs[i++]));
				entity.setSubTaskId(BeanUtil.getAsLong(objs[i++]));
				entity.setTaskName(BeanUtil.getAsString(objs[i++]));
				entity.setSubTaskName(BeanUtil.getAsString(objs[i++]));
				entity.setDueDate( (Date)objs[i++]);
				entity.setStatus(BeanUtil.getAsString(objs[i++]));
				entity.setInterval(BeanUtil.getAsLong(objs[i++]));
				entity.setEmailAddresses(BeanUtil.getAsString(objs[i++]));
				entities.add(entity);
			}
			
			
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return entities;
	}

}
