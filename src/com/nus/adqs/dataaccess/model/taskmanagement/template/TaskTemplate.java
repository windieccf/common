package com.nus.adqs.dataaccess.model.taskmanagement.template;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.nus.adqs.constant.ConstantScheduler;
import com.nus.adqs.constant.ConstantSchema;
import com.nus.adqs.dataaccess.model.base.BaseEntity;
import com.nus.adqs.dataaccess.model.taskmanagement.Task;
import com.nus.adqs.util.DateUtil;
import com.nus.adqs.util.StringUtil;


@SuppressWarnings("serial")
@Entity
@Table(name="TASK_TEMPLATE", schema=ConstantSchema.TMS)
@SequenceGenerator(name="taskTemplateSeq", schema=ConstantSchema.TMS, sequenceName="taskTemplateSeq")
public class TaskTemplate extends BaseEntity {
	
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO,generator="taskTemplateSeq")
	@Column(name="ID")
	private Long id;
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	@Override
	public Serializable getPk() {return this.getId();}
	
	@Column(name="TEMPLATE_NAME")
	private String templateName;
	public String getTemplateName() {return templateName;}
	public void setTemplateName(String templateName) {this.templateName = templateName;}

	@Column(name="NAME")
	private String name;
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	
	@Column(name="DESCRIPTION", length=1000)
	private String description;
	public String getDescription() {return description;}
	public void setDescription(String description) {this.description = description;}
	
	@Column(name="LAST_RUN_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastRunTime;
	public Date getLastRunTime() {return lastRunTime;}
	public void setLastRunTime(Date lastRunTime) {this.lastRunTime = lastRunTime;}

	@Column(name="NEXT_RUN_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date nextRunTime;
	public Date getNextRunTime() {return nextRunTime;}
	public void setNextRunTime(Date nextRunTime) {this.nextRunTime = nextRunTime;}
	
	@Column(name="TYPE", length=10)// one time, hourly, daily , weekly, or monthly
	private String type = ConstantScheduler.TYPE_ONE_TIME;
	public String getType() {return type;}
	public void setType(String type) {this.type = type;}
	
	@Column(name="RECUR") // used for hourly, or daily or weekly. recur every 3 weeks or 3 days or 3 hours
	private Long recur = 0L; // 0 for no recurring
	public Long getRecur() {return recur;}
	public void setRecur(Long recur) {this.recur = recur;}
	
	@Column(name="RUN_ON_MONTHS")// used for monthly
	private String runOnMonths=""; //  follow java.util.calendar January = 0
	public String getRunOnMonths() {return runOnMonths;}
	public void setRunOnMonths(String runOnMonths) {this.runOnMonths = runOnMonths;}
	public List<String> getRunOnMonthsAsList(){return (StringUtil.isEmpty(this.runOnMonths)) ? new ArrayList<String>() : Arrays.asList(this.runOnMonths.split(","));}
	
	@Column(name="RUN_ON_DAYS") // used for weekly or monthly
	private String runOnDays=""; // follow java.util.calendar Sunday = 1  , Saturday = 7
	public String getRunOnDays() {return runOnDays;}
	public void setRunOnDays(String runOnDays) {this.runOnDays = runOnDays;}
	public List<String> getRunOnDaysAsList(){return   (StringUtil.isEmpty(this.runOnDays)) ? new ArrayList<String>() : Arrays.asList(this.runOnDays.split(","));}
	
	@Column(name="RUN_ON_DATES") // used for monthly only, include 1st to 31st or last of the month with prefix LAST
	private String runOnDates="";
	public String getRunOnDates() {return runOnDates;}
	public void setRunOnDates(String runOnDates) {this.runOnDates = runOnDates;}
	public List<String> getRunOnDatesAsList(){return   (StringUtil.isEmpty(this.runOnDates)) ? new ArrayList<String>() : Arrays.asList(this.runOnDates.split(","));}

	@Column(name="RUN_ON_WEEKS") // used for monthly // not USED
	private String runOnWeeks; // FIRST|SECOND|THIRD|FOURTH
	public String getRunOnWeeks() {return runOnWeeks;}
	public void setRunOnWeeks(String runOnWeeks) {this.runOnWeeks = runOnWeeks;}
	
	@OneToMany(mappedBy="taskTemplate",fetch=FetchType.LAZY, cascade=CascadeType.ALL,orphanRemoval=true)
	private List<SubTaskTemplate> subTaskTemplates = new ArrayList<SubTaskTemplate>();
	public List<SubTaskTemplate> getSubTaskTemplates() {return subTaskTemplates;}
	public void setSubTaskTemplates(List<SubTaskTemplate> subTaskTemplates) {this.subTaskTemplates = subTaskTemplates;}
	public SubTaskTemplate addNewSubTaskTemplate(){return new SubTaskTemplate(this);}
	
	/************************************ TABLE JOIN ******************************************/
	
	@OneToMany(mappedBy="taskTemplate",fetch=FetchType.LAZY, cascade=CascadeType.ALL,orphanRemoval=true)
	private List<Task> tasks = new ArrayList<Task>();
	public List<Task> getTasks() {return tasks;}
	public void setTasks(List<Task> tasks) {this.tasks = tasks;}
	public Task addTask(Task task){
		task.setTaskTemplate(this);
		this.tasks.add(task);
		return task;
	}
	

	/************************************ TRANSIENT VARIABLE ******************************************/
	@Transient
	private Task transientTask;
	public Task getTransientTask() {return transientTask;}
	public void setTransientTask(Task transientTask) {this.transientTask = transientTask;}
	
	
	/************************************* UTILITY ***************************************/
	
	public boolean isOneTime(){return ConstantScheduler.TYPE_ONE_TIME.equals(this.type);}
	public boolean isHourly(){return ConstantScheduler.TYPE_HOURLY.equals(this.type);}
	public boolean isDaily(){return ConstantScheduler.TYPE_DAILY.equals(this.type);}
	public boolean isWeekly(){return ConstantScheduler.TYPE_WEEKLY.equals(this.type);}
	public boolean isMonthly(){return ConstantScheduler.TYPE_MONTHLY.equals(this.type);}
	
	
	
	public void resetNextRunTime(){
		Date runTime = (this.getNextRunTime() == null) ? DateUtil.getSystemDate() : this.getNextRunTime();
		long dateDifference =  DateUtil.getSystemDate().getTime() - runTime.getTime(); 
		if(dateDifference <=0)
			return;
		
		if(this.isHourly() || this.isDaily()){
			int recurringType = (this.isDaily()) ? Calendar.DATE: Calendar.HOUR;
			if(this.isHourly()){
				// need to adjust to the nearest hour
				Calendar cal = DateUtil.getCalendar(DateUtil.getSystemDate());
				Calendar runCal = DateUtil.getCalendar(runTime);
				runCal.set(Calendar.DATE, cal.get(Calendar.DATE));
				runCal.set(Calendar.MONTH, cal.get(Calendar.MONTH));
				runCal.set(Calendar.YEAR, cal.get(Calendar.YEAR));
				runTime = runCal.getTime();
				
				while(runTime.before(DateUtil.getSystemDate())){
					runTime = DateUtil.addTime(runTime, recurringType,  this.getRecur().intValue());
				}
			}else{
				runTime = DateUtil.addTime(runTime, recurringType,  this.getRecur().intValue());
			}

		}else if(this.isWeekly()){
			//runTime 
			Calendar runCal = DateUtil.getCalendar(runTime);
			Calendar cal = DateUtil.getCalendar(DateUtil.getSystemDate());
			
			runCal.set(Calendar.DATE, cal.get(Calendar.DATE));
			runCal.set(Calendar.MONTH, cal.get(Calendar.MONTH));
			runCal.set(Calendar.YEAR, cal.get(Calendar.YEAR));

			int dayOfWeek = runCal.get(Calendar.DAY_OF_WEEK);
			int runDate = -1;
			List<String> runOnDays = this.getRunOnDaysAsList();
			Collections.sort(runOnDays);
			for(String text : runOnDays){
				System.err.println(dayOfWeek +  "-"+text);
				if(dayOfWeek < Integer.parseInt(text)){
					runDate = Integer.parseInt(text) - dayOfWeek;
					break;
				}
			}
			// reach end of the week
			// check on the next week period
			if(runDate<0)
				runDate =  ( (this.getRecur().intValue() - 1) * 7 ) + (7 - dayOfWeek) + Integer.parseInt(runOnDays.get(0));
			
				runTime = DateUtil.addTime(runCal.getTime(), Calendar.DATE,  runDate);
		}else if(this.isMonthly()){
			
			Calendar runCal = DateUtil.getCalendar(runTime);
			Calendar cal = DateUtil.getCalendar(DateUtil.getSystemDate());
			
			runCal.set(Calendar.DATE, cal.get(Calendar.DATE));
			runCal.set(Calendar.MONTH, cal.get(Calendar.MONTH));
			runCal.set(Calendar.YEAR, cal.get(Calendar.YEAR));

			List<String> runOnMonths = this.getRunOnMonthsAsList();
			Collections.sort(runOnMonths);
			
			List<String> runOnDates = this.getRunOnDatesAsList();
			Collections.sort(runOnDates);
			
			int presetMonth = Integer.parseInt(runOnMonths.get(0));
			cal.set(Calendar.MONTH, presetMonth);
			
			int presetDate = (ConstantScheduler.LAST_DATE_OF_MONTH.equals(runOnDates.get(0))) ? cal.getActualMaximum(Calendar.DAY_OF_MONTH) : Integer.parseInt(runOnMonths.get(0));
			cal.set(Calendar.DATE, presetDate);
			
			for(String text : runOnMonths){
				cal.set(Calendar.MONTH, (Integer.parseInt(text)));
				for(String dateText : runOnDates){
					int lastDateOfMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
					if(ConstantScheduler.LAST_DATE_OF_MONTH.equals(dateText))
						cal.set(Calendar.DATE, lastDateOfMonth);
					else{
						int date = Integer.parseInt(dateText);
						if(lastDateOfMonth >= date)
							cal.set(Calendar.DATE, date);
					}
					if(cal.after(runCal))
						break;
				}
				if(cal.after(runCal))
					break;
			}
			
			if(cal.before(runCal)){
				cal.set(Calendar.MONTH, presetMonth);
				cal.set(Calendar.DATE, presetDate);
				cal.add(Calendar.YEAR, 1);
			}
			
			runCal.set(Calendar.DATE, cal.get(Calendar.DATE));
			runCal.set(Calendar.MONTH, cal.get(Calendar.MONTH));
			runCal.set(Calendar.YEAR, cal.get(Calendar.YEAR));
			
			runTime = runCal.getTime();
		}
		
		this.setNextRunTime(runTime);
	}
	
	

}
