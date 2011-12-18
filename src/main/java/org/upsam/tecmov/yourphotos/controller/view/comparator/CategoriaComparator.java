package org.upsam.tecmov.yourphotos.controller.view.comparator;

import java.util.Comparator;

import org.upsam.tecmov.yourphotos.controller.view.PoblacionWithDetailsView;

public class CategoriaComparator implements Comparator<PoblacionWithDetailsView> {

	@Override
	public int compare(PoblacionWithDetailsView o1, PoblacionWithDetailsView o2) {
		return o1.getCategoria().compareTo(o2.getCategoria());
	}

}
