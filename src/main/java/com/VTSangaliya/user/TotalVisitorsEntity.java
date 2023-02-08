package com.VTSangaliya.user;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tbl_total_visitors")
public class TotalVisitorsEntity {
@Id
private int id;
private int totalVisitors;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getTotalVisitors() {
	return totalVisitors;
}
public void setTotalVisitors(int totalVisitors) {
	this.totalVisitors = totalVisitors;
}

}
