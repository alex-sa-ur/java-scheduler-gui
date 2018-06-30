package edu.miami.cis324.hw4.alejandrosanchez;

import java.util.Comparator;
import java.util.Date;

/*
 * Interface for VisitImpl<V,T>
 * 
 * Getters for:
 * - Visitor: 	vtr
 * - Host: 		hst
 * - Date: 		date
 * 
 * Methods:
 * - compare():	[Alternative to Comparator<Visit<V,T>> interface] 
 * 				returns the difference between the object itself and another visit
 * 				by calling compare(this, visit);
 * 
 * V = Visitor
 * T = Host
 */

/**
 * @author alejandrosanchez
 *
 */

public interface Visit<V,T> extends Comparator<Visit<V,T>>{
	V getVisitor();
	T getHost();
	Date getDate();
	int compare(Visit<V,T> visit);
	void setHost(T newHost);
	void setVisitor(V newVisitor);
	void setDate(Date newDate);
}
