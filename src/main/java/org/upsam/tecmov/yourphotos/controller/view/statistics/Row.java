package org.upsam.tecmov.yourphotos.controller.view.statistics;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Row implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4422277241395142923L;

	private String titleColumn;

	private Integer valueColumn;

	/**
	 * @param titleColumn
	 * @param valueColumn
	 */
	public Row(String titleColumn, Integer valueColumn) {
		super();
		this.titleColumn = titleColumn;
		this.valueColumn = valueColumn;
	}

	public List<Object> toArray() {
		List<Object> list = new ArrayList<Object>(2);
		list.add(this.titleColumn);
		list.add(this.valueColumn);
		return list;
	}

}
