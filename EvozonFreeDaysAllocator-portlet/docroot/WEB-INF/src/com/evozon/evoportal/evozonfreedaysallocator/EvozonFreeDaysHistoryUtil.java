package com.evozon.evoportal.evozonfreedaysallocator;

import com.evozon.evoportal.evozonfreedaysallocator.model.impl.FreeDaysHistoryOperationType;
import com.evozon.evoportal.evozonfreedaysallocator.service.FreeDaysHistoryEntryLocalServiceUtil;

public class EvozonFreeDaysHistoryUtil {

	public static void saveHistory(long userId, int oldValue, int newValue, String eventType, String description) {
		int diff = newValue - oldValue;

		String operationType = FreeDaysHistoryOperationType.NONE.toString();
		if (diff > 0) {
			operationType = FreeDaysHistoryOperationType.ADD.toString();
		} else if (diff < 0) {
			operationType = FreeDaysHistoryOperationType.REMOVE.toString();
		}

		FreeDaysHistoryEntryLocalServiceUtil.saveFreeDaysHistoryEntry(userId, operationType, oldValue, Math.abs(diff),
				newValue, eventType, description);
	}
	
}
