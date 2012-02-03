package org.upsam.tecmov.yourphotos.controller.view.statistics;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Statistics implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -841551033849294048L;

	private List<String> columns;

	private List<String> columnsType;

	private List<List<Object>> table;

	/**
	 * 
	 */
	public Statistics() {
		super();
		this.table = new ArrayList<List<Object>>();
		this.columns = new ArrayList<String>(4);
		this.columnsType = new ArrayList<String>(4);
	}

	public void addColumn(String name) {
		this.columns.add(name);
	}

	public void addRow(Row row) {
		List<Object> rowArray = row.toArray();
		this.table.add(rowArray);
		if (this.columnsType.size() < 2) {
			this.columnsType.add(0, "string");
			if (Number.class.isAssignableFrom(rowArray.get(1).getClass())) {
				this.columnsType.add(1, "number");
			} else {
				this.columnsType.add(1, "string");
			}
		}
	}

	/**
	 * @return the columns
	 */
	public List<String> getColumns() {
		return columns;
	}

	/**
	 * @return the columnsType
	 */
	public List<String> getColumnsType() {
		return columnsType;
	}

	/**
	 * @return the table
	 */
	public List<List<Object>> getTable() {
		return table;
	}

}
