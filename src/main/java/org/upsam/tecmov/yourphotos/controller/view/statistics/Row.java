package org.upsam.tecmov.yourphotos.controller.view.statistics;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Row implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4422277241395142923L;

	private List<Object> row;

	/**
	 * @param titleColumn
	 * @param valueColumn
	 */
	public Row(String titleColumn, Integer valueColumn) {
		super();
		this.row = new ArrayList<Object>(2);
		this.row.add(titleColumn);
		this.row.add(valueColumn);
	}
	
	public Row(String poblacion, Integer sugerencias, Integer categoria, String provincia, String comunidad) {
		super();
		this.row = new ArrayList<Object>(5);
		this.row.add(poblacion);
		this.row.add(sugerencias);
		this.row.add(categoria);
		this.row.add(provincia);
		this.row.add(comunidad);
	}

	public List<Object> toArray() {
		return row;
	}

}
