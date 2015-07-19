package com.nus.adqs.dataaccess.model.taskmanagement;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.nus.adqs.constant.ConstantSchema;
import com.nus.adqs.dataaccess.model.base.BaseEntity;

@SuppressWarnings("serial")
@Entity
@Table(name="SCHEDULE_DETAIL")
@SequenceGenerator(name="scheduleDetailSeq", schema=ConstantSchema.COMMON, sequenceName="scheduleDetailSeq")
public class ScheduleDetail extends BaseEntity {
	

	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO,generator="scheduleDetailSeq")
	@Column(name="ID")
	private Integer id;
	public Integer getId() {return id;}
	public void setId(Integer id) {this.id = id;}
	@Override
	public Serializable getPk() {return this.getId();}
	
	@Column(name="INTERVAL")
	private String interval;
	public String getInterval() {return interval;}
	public void setInterval(String interval) {this.interval = interval;}

	@Column(name="DAY")
	private String day;
	public String getDay() {return day;}
	public void setDay(String day) {this.day = day;}
	
	@Column(name="TIME")
	private String time;
	public String getTime() {return time;}
	public void setTime(String time) {this.time = time;}
	
	@Column(name="NEXT_SCHEDULE_DATETIME") 
	@Temporal(TemporalType.TIMESTAMP)
	private Date nextScheduleDateTime;
	public Date getNextScheduleDateTime() {return nextScheduleDateTime;}
	public void setNextScheduleDateTime(Date nextScheduleDateTime) {this.nextScheduleDateTime = nextScheduleDateTime;}
	
}
