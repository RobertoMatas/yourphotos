package org.upsam.tecmov.yourphotos.controller.view.comparator;

import java.util.Comparator;

import org.upsam.tecmov.yourphotos.controller.view.PoblacionWithDetailsView;

public class DistanciaComparator implements Comparator<PoblacionWithDetailsView> {

	@Override
	public int compare(PoblacionWithDetailsView o1, PoblacionWithDetailsView o2) {
		return o1.getDistancia().compareTo(o2.getDistancia());
	}

}
