package com.evozon.evoportal.evozonfreedaysallocator.comparators;

import java.util.Comparator;
import java.util.Date;

import com.evozon.evoportal.vacation.model.Status;

public class VacationStatusComparator implements Comparator<Status> {

	public int compare(Status status1, Status status2) {
		Date dateSrc = status1.getUpdatedDate();
		Date dateDest = status2.getUpdatedDate();

		return dateDest.compareTo(dateSrc);
	}

}
