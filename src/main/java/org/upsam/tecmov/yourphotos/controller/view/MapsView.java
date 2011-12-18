package org.upsam.tecmov.yourphotos.controller.view;

import java.io.Serializable;
import java.util.List;

public class MapsView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7329893645714753139L;
	/**
	 * Datos para las poblaciones
	 */
	private List<PoblacionWithDetailsView> markers;
	/**
	 * Coodenadas en donde debe centrase el mapa
	 */
	private CenterView center;

	/**
	 * @param markers
	 * @param center
	 */
	public MapsView(CenterView center, List<PoblacionWithDetailsView> markers) {
		super();
		this.markers = markers;
		this.center = center;
	}

	/**
	 * @return the markers
	 */
	public List<PoblacionWithDetailsView> getMarkers() {
		return markers;
	}

	/**
	 * @return the center
	 */
	public CenterView getCenter() {
		return center;
	}

}
