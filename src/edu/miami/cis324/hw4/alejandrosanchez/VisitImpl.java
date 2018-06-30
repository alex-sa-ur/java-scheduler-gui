package edu.miami.cis324.hw4.alejandrosanchez;

import java.util.Date;

/*
 * Class that implements the Visit<V,T> interface
 */

/**
 * @author alejandrosanchez
 *
 */

public class VisitImpl<V,T> implements Visit<V,T> {
	private V 		visitor;
	private T 		host;
	private Date 	date;
	
	//Constructor
	VisitImpl(V visitor, T host, Date date) {	//date as Date
		this.visitor 	= visitor;
		this.host 		= host;
		this.date 		= date;
	}
	
	VisitImpl(V visitor, T host, String date) {	//date as String
		this.visitor 	= visitor;
		this.host 		= host;
		this.date 		= DateUtil.getFromString(date);
	}

	/*
	 * Methods:
	 * compare(Visit<V, T> visit1, Visit<V, T> visit2): from Comparator interface
	 * compare(Visit<V,T> visit2): 						calls other compare(Visit<V, T> visit1, Visit<V, T> visit2) 
	 * 													with parameters (this, visit2)
	 * getters for:
	 * - Visitor:										visitor
	 * - Host:											host
	 * - Date:											date
	 * hashCode(): 										from Object [Based on visitor, host, date]
	 * equals(Object): 									from Object [Based on visitor, host, date]
	 */

	//getters
	@Override
	//from Visit
	public V getVisitor() {
		return visitor;
	}

	@Override
	//from Visitor
	public T getHost() {
		return host;
	}

	@Override
	//from Visit
	public Date getDate() {
		return date;
	}
	
	public void setHost(T newHost) {
		this.host = newHost;
	}
	
	public void setVisitor(V newVisitor) {
		this.visitor = newVisitor;
	}
	
	public void setDate(Date newDate) {
		this.date = newDate;
	}
	
	//methods
	@Override
	//from Comparator
	public int compare(Visit<V, T> visit1, Visit<V, T> visit2) {
		return (int) (visit1.getDate().getTime() - visit2.getDate().getTime());
	}
	
	@Override
	//from Visit
	public int compare(Visit<V,T> visit2) {
		return compare(this, visit2);
	}
	
	@Override
	//from Object
	public int hashCode() {
		int result = 0;
		
		result = 31 * (this.visitor 	!= null ? visitor.hashCode() : 0);
		result = 31 * (this.host 	!= null ? host.hashCode() : 0);
		result = 31 * (this.date 	!= null ? date.hashCode() : 0);
		
		return result;
	}
	
	@Override
	//from Object
	public boolean equals (Object other) {
		if (this == other) return true;
		if (other == null || this.getClass() != other.getClass()) return false;
		
		@SuppressWarnings("unchecked")
		Visit<V,T> visitOther = (Visit<V,T>) other;
		
		return ((this.visitor 	== visitOther.getVisitor()) &&
				(this.host 		== visitOther.getHost()) && 
				this.date 		== visitOther.getDate());
	}
}
