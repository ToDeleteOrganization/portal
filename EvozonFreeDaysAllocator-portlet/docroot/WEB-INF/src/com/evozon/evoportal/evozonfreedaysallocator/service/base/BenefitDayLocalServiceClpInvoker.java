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

import com.evozon.evoportal.evozonfreedaysallocator.service.BenefitDayLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 */
public class BenefitDayLocalServiceClpInvoker {
	public BenefitDayLocalServiceClpInvoker() {
		_methodName0 = "addBenefitDay";

		_methodParameterTypes0 = new String[] {
				"com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay"
			};

		_methodName1 = "createBenefitDay";

		_methodParameterTypes1 = new String[] { "long" };

		_methodName2 = "deleteBenefitDay";

		_methodParameterTypes2 = new String[] { "long" };

		_methodName3 = "deleteBenefitDay";

		_methodParameterTypes3 = new String[] {
				"com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay"
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

		_methodName9 = "fetchBenefitDay";

		_methodParameterTypes9 = new String[] { "long" };

		_methodName10 = "getBenefitDay";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "getPersistedModel";

		_methodParameterTypes11 = new String[] { "java.io.Serializable" };

		_methodName12 = "getBenefitDaies";

		_methodParameterTypes12 = new String[] { "int", "int" };

		_methodName13 = "getBenefitDaiesCount";

		_methodParameterTypes13 = new String[] {  };

		_methodName14 = "updateBenefitDay";

		_methodParameterTypes14 = new String[] {
				"com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay"
			};

		_methodName15 = "updateBenefitDay";

		_methodParameterTypes15 = new String[] {
				"com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay",
				"boolean"
			};

		_methodName44 = "getBeanIdentifier";

		_methodParameterTypes44 = new String[] {  };

		_methodName45 = "setBeanIdentifier";

		_methodParameterTypes45 = new String[] { "java.lang.String" };

		_methodName50 = "saveBenefitDay";

		_methodParameterTypes50 = new String[] { "long", "java.lang.String", "int" };

		_methodName51 = "addExtraValue";

		_methodParameterTypes51 = new String[] { "long", "java.lang.String", "int" };

		_methodName52 = "saveExtraDay";

		_methodParameterTypes52 = new String[] {
				"long", "java.lang.String", "int", "java.lang.String"
			};

		_methodName53 = "getBenefitDaysByTypeInYear";

		_methodParameterTypes53 = new String[] { "int", "long", "java.lang.String" };

		_methodName54 = "getTotalExtraDaysReceivedInYear";

		_methodParameterTypes54 = new String[] { "int", "long" };

		_methodName55 = "getTotalExtraDaysInCurrentYear";

		_methodParameterTypes55 = new String[] { "long" };

		_methodName56 = "getBenefitDays";

		_methodParameterTypes56 = new String[] { "long" };

		_methodName57 = "getBenefitDayOfType";

		_methodParameterTypes57 = new String[] { "long", "java.lang.String" };

		_methodName58 = "getUserSelectedDate";

		_methodParameterTypes58 = new String[] {  };

		_methodName59 = "hasSimulateMode";

		_methodParameterTypes59 = new String[] {  };

		_methodName60 = "getProperty";

		_methodParameterTypes60 = new String[] {
				"java.lang.String", "java.lang.String"
			};

		_methodName61 = "blankMethod";

		_methodParameterTypes61 = new String[] {  };
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return BenefitDayLocalServiceUtil.addBenefitDay((com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return BenefitDayLocalServiceUtil.createBenefitDay(((Long)arguments[0]).longValue());
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return BenefitDayLocalServiceUtil.deleteBenefitDay(((Long)arguments[0]).longValue());
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return BenefitDayLocalServiceUtil.deleteBenefitDay((com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return BenefitDayLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return BenefitDayLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return BenefitDayLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return BenefitDayLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return BenefitDayLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return BenefitDayLocalServiceUtil.fetchBenefitDay(((Long)arguments[0]).longValue());
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return BenefitDayLocalServiceUtil.getBenefitDay(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return BenefitDayLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return BenefitDayLocalServiceUtil.getBenefitDaies(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return BenefitDayLocalServiceUtil.getBenefitDaiesCount();
		}

		if (_methodName14.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
			return BenefitDayLocalServiceUtil.updateBenefitDay((com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay)arguments[0]);
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return BenefitDayLocalServiceUtil.updateBenefitDay((com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDay)arguments[0],
				((Boolean)arguments[1]).booleanValue());
		}

		if (_methodName44.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes44, parameterTypes)) {
			return BenefitDayLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName45.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes45, parameterTypes)) {
			BenefitDayLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);
		}

		if (_methodName50.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes50, parameterTypes)) {
			return BenefitDayLocalServiceUtil.saveBenefitDay(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				((Integer)arguments[2]).intValue());
		}

		if (_methodName51.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes51, parameterTypes)) {
			BenefitDayLocalServiceUtil.addExtraValue(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				((Integer)arguments[2]).intValue());
		}

		if (_methodName52.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes52, parameterTypes)) {
			return BenefitDayLocalServiceUtil.saveExtraDay(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				((Integer)arguments[2]).intValue(),
				(java.lang.String)arguments[3]);
		}

		if (_methodName53.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes53, parameterTypes)) {
			return BenefitDayLocalServiceUtil.getBenefitDaysByTypeInYear(((Integer)arguments[0]).intValue(),
				((Long)arguments[1]).longValue(), (java.lang.String)arguments[2]);
		}

		if (_methodName54.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes54, parameterTypes)) {
			return BenefitDayLocalServiceUtil.getTotalExtraDaysReceivedInYear(((Integer)arguments[0]).intValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName55.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes55, parameterTypes)) {
			return BenefitDayLocalServiceUtil.getTotalExtraDaysInCurrentYear(((Long)arguments[0]).longValue());
		}

		if (_methodName56.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes56, parameterTypes)) {
			return BenefitDayLocalServiceUtil.getBenefitDays(((Long)arguments[0]).longValue());
		}

		if (_methodName57.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes57, parameterTypes)) {
			return BenefitDayLocalServiceUtil.getBenefitDayOfType(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);
		}

		if (_methodName58.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes58, parameterTypes)) {
			return BenefitDayLocalServiceUtil.getUserSelectedDate();
		}

		if (_methodName59.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes59, parameterTypes)) {
			return BenefitDayLocalServiceUtil.hasSimulateMode();
		}

		if (_methodName60.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes60, parameterTypes)) {
			return BenefitDayLocalServiceUtil.getProperty((java.lang.String)arguments[0],
				(java.lang.String)arguments[1]);
		}

		if (_methodName61.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes61, parameterTypes)) {
			BenefitDayLocalServiceUtil.blankMethod();
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
	private String _methodName53;
	private String[] _methodParameterTypes53;
	private String _methodName54;
	private String[] _methodParameterTypes54;
	private String _methodName55;
	private String[] _methodParameterTypes55;
	private String _methodName56;
	private String[] _methodParameterTypes56;
	private String _methodName57;
	private String[] _methodParameterTypes57;
	private String _methodName58;
	private String[] _methodParameterTypes58;
	private String _methodName59;
	private String[] _methodParameterTypes59;
	private String _methodName60;
	private String[] _methodParameterTypes60;
	private String _methodName61;
	private String[] _methodParameterTypes61;
}