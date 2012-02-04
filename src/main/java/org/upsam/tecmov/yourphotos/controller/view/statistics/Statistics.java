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
		this.columns = new ArrayList<String>(5);
		this.columnsType = new ArrayList<String>(5);
	}

	public void addColumn(String name) {
		this.columns.add(name);
	}

	public void addRow(Row row) {
		List<Object> rowArray = row.toArray();
		int size = rowArray.size();
		this.table.add(rowArray);
		if (this.columnsType.size() < size) {
			for (int i = 0; i < size; i++) {
				if (Number.class.isAssignableFrom(rowArray.get(i).getClass())) {
					this.columnsType.add(i, "number");
				} else {
					this.columnsType.add(i, "string");
				}
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
