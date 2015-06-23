/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.evozon.evoportal.evozonfreedaysallocator.service.base;

import com.evozon.evoportal.evozonfreedaysallocator.service.FreeDaysHistoryEntryLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 */
public class FreeDaysHistoryEntryLocalServiceClpInvoker {
	public FreeDaysHistoryEntryLocalServiceClpInvoker() {
		_methodName0 = "addFreeDaysHistoryEntry";

		_methodParameterTypes0 = new String[] {
				"com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry"
			};

		_methodName1 = "createFreeDaysHistoryEntry";

		_methodParameterTypes1 = new String[] { "long" };

		_methodName2 = "deleteFreeDaysHistoryEntry";

		_methodParameterTypes2 = new String[] { "long" };

		_methodName3 = "deleteFreeDaysHistoryEntry";

		_methodParameterTypes3 = new String[] {
				"com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry"
			};

		_methodName4 = "dynamicQuery";

		_methodParameterTypes4 = new String[] {  };

		_methodName5 = "dynamicQuery";

		_methodParameterTypes5 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery"
			};

		_methodName6 = "dynamicQuery";

		_methodParameterTypes6 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery", "int", "int"
			};

		_methodName7 = "dynamicQuery";

		_methodParameterTypes7 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName8 = "dynamicQueryCount";

		_methodParameterTypes8 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery"
			};

		_methodName9 = "fetchFreeDaysHistoryEntry";

		_methodParameterTypes9 = new String[] { "long" };

		_methodName10 = "getFreeDaysHistoryEntry";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "getPersistedModel";

		_methodParameterTypes11 = new String[] { "java.io.Serializable" };

		_methodName12 = "getFreeDaysHistoryEntries";

		_methodParameterTypes12 = new String[] { "int", "int" };

		_methodName13 = "getFreeDaysHistoryEntriesCount";

		_methodParameterTypes13 = new String[] {  };

		_methodName14 = "updateFreeDaysHistoryEntry";

		_methodParameterTypes14 = new String[] {
				"com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry"
			};

		_methodName15 = "updateFreeDaysHistoryEntry";

		_methodParameterTypes15 = new String[] {
				"com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry",
				"boolean"
			};

		_methodName44 = "getBeanIdentifier";

		_methodParameterTypes44 = new String[] {  };

		_methodName45 = "setBeanIdentifier";

		_methodParameterTypes45 = new String[] { "java.lang.String" };

		_methodName50 = "getAllHistory";

		_methodParameterTypes50 = new String[] { "long" };

		_methodName51 = "getMyString";

		_methodParameterTypes51 = new String[] {  };

		_methodName52 = "saveFreeDaysHistoryEntry";

		_methodParameterTypes52 = new String[] {
				"long", "java.lang.String", "int", "int", "int",
				"java.lang.String", "java.lang.String"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return FreeDaysHistoryEntryLocalServiceUtil.addFreeDaysHistoryEntry((com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return FreeDaysHistoryEntryLocalServiceUtil.createFreeDaysHistoryEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return FreeDaysHistoryEntryLocalServiceUtil.deleteFreeDaysHistoryEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return FreeDaysHistoryEntryLocalServiceUtil.deleteFreeDaysHistoryEntry((com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return FreeDaysHistoryEntryLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return FreeDaysHistoryEntryLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return FreeDaysHistoryEntryLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return FreeDaysHistoryEntryLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return FreeDaysHistoryEntryLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return FreeDaysHistoryEntryLocalServiceUtil.fetchFreeDaysHistoryEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return FreeDaysHistoryEntryLocalServiceUtil.getFreeDaysHistoryEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return FreeDaysHistoryEntryLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return FreeDaysHistoryEntryLocalServiceUtil.getFreeDaysHistoryEntries(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return FreeDaysHistoryEntryLocalServiceUtil.getFreeDaysHistoryEntriesCount();
		}

		if (_methodName14.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
			return FreeDaysHistoryEntryLocalServiceUtil.updateFreeDaysHistoryEntry((com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry)arguments[0]);
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return FreeDaysHistoryEntryLocalServiceUtil.updateFreeDaysHistoryEntry((com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry)arguments[0],
				((Boolean)arguments[1]).booleanValue());
		}

		if (_methodName44.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes44, parameterTypes)) {
			return FreeDaysHistoryEntryLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName45.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes45, parameterTypes)) {
			FreeDaysHistoryEntryLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);
		}

		if (_methodName50.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes50, parameterTypes)) {
			return FreeDaysHistoryEntryLocalServiceUtil.getAllHistory(((Long)arguments[0]).longValue());
		}

		if (_methodName51.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes51, parameterTypes)) {
			return FreeDaysHistoryEntryLocalServiceUtil.getMyString();
		}

		if (_methodName52.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes52, parameterTypes)) {
			return FreeDaysHistoryEntryLocalServiceUtil.saveFreeDaysHistoryEntry(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue(),
				((Integer)arguments[4]).intValue(),
				(java.lang.String)arguments[5], (java.lang.String)arguments[6]);
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName0;
	private String[] _methodParameterTypes0;
	private String _methodName1;
	private String[] _methodParameterTypes1;
	private String _methodName2;
	private String[] _methodParameterTypes2;
	private String _methodName3;
	private String[] _methodParameterTypes3;
	private String _methodName4;
	private String[] _methodParameterTypes4;
	private String _methodName5;
	private String[] _methodParameterTypes5;
	private String _methodName6;
	private String[] _methodParameterTypes6;
	private String _methodName7;
	private String[] _methodParameterTypes7;
	private String _methodName8;
	private String[] _methodParameterTypes8;
	private String _methodName9;
	private String[] _methodParameterTypes9;
	private String _methodName10;
	private String[] _methodParameterTypes10;
	private String _methodName11;
	private String[] _methodParameterTypes11;
	private String _methodName12;
	private String[] _methodParameterTypes12;
	private String _methodName13;
	private String[] _methodParameterTypes13;
	private String _methodName14;
	private String[] _methodParameterTypes14;
	private String _methodName15;
	private String[] _methodParameterTypes15;
	private String _methodName44;
	private String[] _methodParameterTypes44;
	private String _methodName45;
	private String[] _methodParameterTypes45;
	private String _methodName50;
	private String[] _methodParameterTypes50;
	private String _methodName51;
	private String[] _methodParameterTypes51;
	private String _methodName52;
	private String[] _methodParameterTypes52;
}