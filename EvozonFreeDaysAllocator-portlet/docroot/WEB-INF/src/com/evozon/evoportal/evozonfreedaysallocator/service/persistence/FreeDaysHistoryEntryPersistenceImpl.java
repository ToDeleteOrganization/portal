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

package com.evozon.evoportal.evozonfreedaysallocator.service.persistence;

import com.evozon.evoportal.evozonfreedaysallocator.NoSuchFreeDaysHistoryEntryException;
import com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry;
import com.evozon.evoportal.evozonfreedaysallocator.model.impl.FreeDaysHistoryEntryImpl;
import com.evozon.evoportal.evozonfreedaysallocator.model.impl.FreeDaysHistoryEntryModelImpl;

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.CalendarUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * The persistence implementation for the free days history entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Cristina Kiss
 * @see FreeDaysHistoryEntryPersistence
 * @see FreeDaysHistoryEntryUtil
 * @generated
 */
public class FreeDaysHistoryEntryPersistenceImpl extends BasePersistenceImpl<FreeDaysHistoryEntry>
	implements FreeDaysHistoryEntryPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link FreeDaysHistoryEntryUtil} to access the free days history entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = FreeDaysHistoryEntryImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID = new FinderPath(FreeDaysHistoryEntryModelImpl.ENTITY_CACHE_ENABLED,
			FreeDaysHistoryEntryModelImpl.FINDER_CACHE_ENABLED,
			FreeDaysHistoryEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUserId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID =
		new FinderPath(FreeDaysHistoryEntryModelImpl.ENTITY_CACHE_ENABLED,
			FreeDaysHistoryEntryModelImpl.FINDER_CACHE_ENABLED,
			FreeDaysHistoryEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUserId",
			new String[] { Long.class.getName() },
			FreeDaysHistoryEntryModelImpl.USERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_USERID = new FinderPath(FreeDaysHistoryEntryModelImpl.ENTITY_CACHE_ENABLED,
			FreeDaysHistoryEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUserId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID_OPERATION =
		new FinderPath(FreeDaysHistoryEntryModelImpl.ENTITY_CACHE_ENABLED,
			FreeDaysHistoryEntryModelImpl.FINDER_CACHE_ENABLED,
			FreeDaysHistoryEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUserId_Operation",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID_OPERATION =
		new FinderPath(FreeDaysHistoryEntryModelImpl.ENTITY_CACHE_ENABLED,
			FreeDaysHistoryEntryModelImpl.FINDER_CACHE_ENABLED,
			FreeDaysHistoryEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByUserId_Operation",
			new String[] { Long.class.getName(), String.class.getName() },
			FreeDaysHistoryEntryModelImpl.USERID_COLUMN_BITMASK |
			FreeDaysHistoryEntryModelImpl.OPERATION_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_USERID_OPERATION = new FinderPath(FreeDaysHistoryEntryModelImpl.ENTITY_CACHE_ENABLED,
			FreeDaysHistoryEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByUserId_Operation",
			new String[] { Long.class.getName(), String.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID_EVENTTYPE =
		new FinderPath(FreeDaysHistoryEntryModelImpl.ENTITY_CACHE_ENABLED,
			FreeDaysHistoryEntryModelImpl.FINDER_CACHE_ENABLED,
			FreeDaysHistoryEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUserId_EventType",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID_EVENTTYPE =
		new FinderPath(FreeDaysHistoryEntryModelImpl.ENTITY_CACHE_ENABLED,
			FreeDaysHistoryEntryModelImpl.FINDER_CACHE_ENABLED,
			FreeDaysHistoryEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByUserId_EventType",
			new String[] { Long.class.getName(), String.class.getName() },
			FreeDaysHistoryEntryModelImpl.USERID_COLUMN_BITMASK |
			FreeDaysHistoryEntryModelImpl.EVENTTYPE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_USERID_EVENTTYPE = new FinderPath(FreeDaysHistoryEntryModelImpl.ENTITY_CACHE_ENABLED,
			FreeDaysHistoryEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByUserId_EventType",
			new String[] { Long.class.getName(), String.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID_CREATEDATE =
		new FinderPath(FreeDaysHistoryEntryModelImpl.ENTITY_CACHE_ENABLED,
			FreeDaysHistoryEntryModelImpl.FINDER_CACHE_ENABLED,
			FreeDaysHistoryEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUserId_CreateDate",
			new String[] {
				Long.class.getName(), Date.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID_CREATEDATE =
		new FinderPath(FreeDaysHistoryEntryModelImpl.ENTITY_CACHE_ENABLED,
			FreeDaysHistoryEntryModelImpl.FINDER_CACHE_ENABLED,
			FreeDaysHistoryEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByUserId_CreateDate",
			new String[] { Long.class.getName(), Date.class.getName() },
			FreeDaysHistoryEntryModelImpl.USERID_COLUMN_BITMASK |
			FreeDaysHistoryEntryModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_USERID_CREATEDATE = new FinderPath(FreeDaysHistoryEntryModelImpl.ENTITY_CACHE_ENABLED,
			FreeDaysHistoryEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByUserId_CreateDate",
			new String[] { Long.class.getName(), Date.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CREATEDATE =
		new FinderPath(FreeDaysHistoryEntryModelImpl.ENTITY_CACHE_ENABLED,
			FreeDaysHistoryEntryModelImpl.FINDER_CACHE_ENABLED,
			FreeDaysHistoryEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCreateDate",
			new String[] {
				Date.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CREATEDATE =
		new FinderPath(FreeDaysHistoryEntryModelImpl.ENTITY_CACHE_ENABLED,
			FreeDaysHistoryEntryModelImpl.FINDER_CACHE_ENABLED,
			FreeDaysHistoryEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCreateDate",
			new String[] { Date.class.getName() },
			FreeDaysHistoryEntryModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CREATEDATE = new FinderPath(FreeDaysHistoryEntryModelImpl.ENTITY_CACHE_ENABLED,
			FreeDaysHistoryEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCreateDate",
			new String[] { Date.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(FreeDaysHistoryEntryModelImpl.ENTITY_CACHE_ENABLED,
			FreeDaysHistoryEntryModelImpl.FINDER_CACHE_ENABLED,
			FreeDaysHistoryEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(FreeDaysHistoryEntryModelImpl.ENTITY_CACHE_ENABLED,
			FreeDaysHistoryEntryModelImpl.FINDER_CACHE_ENABLED,
			FreeDaysHistoryEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(FreeDaysHistoryEntryModelImpl.ENTITY_CACHE_ENABLED,
			FreeDaysHistoryEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the free days history entry in the entity cache if it is enabled.
	 *
	 * @param freeDaysHistoryEntry the free days history entry
	 */
	public void cacheResult(FreeDaysHistoryEntry freeDaysHistoryEntry) {
		EntityCacheUtil.putResult(FreeDaysHistoryEntryModelImpl.ENTITY_CACHE_ENABLED,
			FreeDaysHistoryEntryImpl.class,
			freeDaysHistoryEntry.getPrimaryKey(), freeDaysHistoryEntry);

		freeDaysHistoryEntry.resetOriginalValues();
	}

	/**
	 * Caches the free days history entries in the entity cache if it is enabled.
	 *
	 * @param freeDaysHistoryEntries the free days history entries
	 */
	public void cacheResult(List<FreeDaysHistoryEntry> freeDaysHistoryEntries) {
		for (FreeDaysHistoryEntry freeDaysHistoryEntry : freeDaysHistoryEntries) {
			if (EntityCacheUtil.getResult(
						FreeDaysHistoryEntryModelImpl.ENTITY_CACHE_ENABLED,
						FreeDaysHistoryEntryImpl.class,
						freeDaysHistoryEntry.getPrimaryKey()) == null) {
				cacheResult(freeDaysHistoryEntry);
			}
			else {
				freeDaysHistoryEntry.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all free days history entries.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(FreeDaysHistoryEntryImpl.class.getName());
		}

		EntityCacheUtil.clearCache(FreeDaysHistoryEntryImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the free days history entry.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(FreeDaysHistoryEntry freeDaysHistoryEntry) {
		EntityCacheUtil.removeResult(FreeDaysHistoryEntryModelImpl.ENTITY_CACHE_ENABLED,
			FreeDaysHistoryEntryImpl.class, freeDaysHistoryEntry.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<FreeDaysHistoryEntry> freeDaysHistoryEntries) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (FreeDaysHistoryEntry freeDaysHistoryEntry : freeDaysHistoryEntries) {
			EntityCacheUtil.removeResult(FreeDaysHistoryEntryModelImpl.ENTITY_CACHE_ENABLED,
				FreeDaysHistoryEntryImpl.class,
				freeDaysHistoryEntry.getPrimaryKey());
		}
	}

	/**
	 * Creates a new free days history entry with the primary key. Does not add the free days history entry to the database.
	 *
	 * @param entryId the primary key for the new free days history entry
	 * @return the new free days history entry
	 */
	public FreeDaysHistoryEntry create(long entryId) {
		FreeDaysHistoryEntry freeDaysHistoryEntry = new FreeDaysHistoryEntryImpl();

		freeDaysHistoryEntry.setNew(true);
		freeDaysHistoryEntry.setPrimaryKey(entryId);

		return freeDaysHistoryEntry;
	}

	/**
	 * Removes the free days history entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param entryId the primary key of the free days history entry
	 * @return the free days history entry that was removed
	 * @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchFreeDaysHistoryEntryException if a free days history entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public FreeDaysHistoryEntry remove(long entryId)
		throws NoSuchFreeDaysHistoryEntryException, SystemException {
		return remove(Long.valueOf(entryId));
	}

	/**
	 * Removes the free days history entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the free days history entry
	 * @return the free days history entry that was removed
	 * @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchFreeDaysHistoryEntryException if a free days history entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public FreeDaysHistoryEntry remove(Serializable primaryKey)
		throws NoSuchFreeDaysHistoryEntryException, SystemException {
		Session session = null;

		try {
			session = openSession();

			FreeDaysHistoryEntry freeDaysHistoryEntry = (FreeDaysHistoryEntry)session.get(FreeDaysHistoryEntryImpl.class,
					primaryKey);

			if (freeDaysHistoryEntry == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchFreeDaysHistoryEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(freeDaysHistoryEntry);
		}
		catch (NoSuchFreeDaysHistoryEntryException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected FreeDaysHistoryEntry removeImpl(
		FreeDaysHistoryEntry freeDaysHistoryEntry) throws SystemException {
		freeDaysHistoryEntry = toUnwrappedModel(freeDaysHistoryEntry);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, freeDaysHistoryEntry);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(freeDaysHistoryEntry);

		return freeDaysHistoryEntry;
	}

	@Override
	public FreeDaysHistoryEntry updateImpl(
		com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry freeDaysHistoryEntry,
		boolean merge) throws SystemException {
		freeDaysHistoryEntry = toUnwrappedModel(freeDaysHistoryEntry);

		boolean isNew = freeDaysHistoryEntry.isNew();

		FreeDaysHistoryEntryModelImpl freeDaysHistoryEntryModelImpl = (FreeDaysHistoryEntryModelImpl)freeDaysHistoryEntry;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, freeDaysHistoryEntry, merge);

			freeDaysHistoryEntry.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !FreeDaysHistoryEntryModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((freeDaysHistoryEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(freeDaysHistoryEntryModelImpl.getOriginalUserId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);

				args = new Object[] {
						Long.valueOf(freeDaysHistoryEntryModelImpl.getUserId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);
			}

			if ((freeDaysHistoryEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID_OPERATION.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(freeDaysHistoryEntryModelImpl.getOriginalUserId()),
						
						freeDaysHistoryEntryModelImpl.getOriginalOperation()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID_OPERATION,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID_OPERATION,
					args);

				args = new Object[] {
						Long.valueOf(freeDaysHistoryEntryModelImpl.getUserId()),
						
						freeDaysHistoryEntryModelImpl.getOperation()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID_OPERATION,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID_OPERATION,
					args);
			}

			if ((freeDaysHistoryEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID_EVENTTYPE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(freeDaysHistoryEntryModelImpl.getOriginalUserId()),
						
						freeDaysHistoryEntryModelImpl.getOriginalEventType()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID_EVENTTYPE,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID_EVENTTYPE,
					args);

				args = new Object[] {
						Long.valueOf(freeDaysHistoryEntryModelImpl.getUserId()),
						
						freeDaysHistoryEntryModelImpl.getEventType()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID_EVENTTYPE,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID_EVENTTYPE,
					args);
			}

			if ((freeDaysHistoryEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID_CREATEDATE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(freeDaysHistoryEntryModelImpl.getOriginalUserId()),
						
						freeDaysHistoryEntryModelImpl.getOriginalCreateDate()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID_CREATEDATE,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID_CREATEDATE,
					args);

				args = new Object[] {
						Long.valueOf(freeDaysHistoryEntryModelImpl.getUserId()),
						
						freeDaysHistoryEntryModelImpl.getCreateDate()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID_CREATEDATE,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID_CREATEDATE,
					args);
			}

			if ((freeDaysHistoryEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CREATEDATE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						freeDaysHistoryEntryModelImpl.getOriginalCreateDate()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CREATEDATE,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CREATEDATE,
					args);

				args = new Object[] {
						freeDaysHistoryEntryModelImpl.getCreateDate()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CREATEDATE,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CREATEDATE,
					args);
			}
		}

		EntityCacheUtil.putResult(FreeDaysHistoryEntryModelImpl.ENTITY_CACHE_ENABLED,
			FreeDaysHistoryEntryImpl.class,
			freeDaysHistoryEntry.getPrimaryKey(), freeDaysHistoryEntry);

		return freeDaysHistoryEntry;
	}

	protected FreeDaysHistoryEntry toUnwrappedModel(
		FreeDaysHistoryEntry freeDaysHistoryEntry) {
		if (freeDaysHistoryEntry instanceof FreeDaysHistoryEntryImpl) {
			return freeDaysHistoryEntry;
		}

		FreeDaysHistoryEntryImpl freeDaysHistoryEntryImpl = new FreeDaysHistoryEntryImpl();

		freeDaysHistoryEntryImpl.setNew(freeDaysHistoryEntry.isNew());
		freeDaysHistoryEntryImpl.setPrimaryKey(freeDaysHistoryEntry.getPrimaryKey());

		freeDaysHistoryEntryImpl.setEntryId(freeDaysHistoryEntry.getEntryId());
		freeDaysHistoryEntryImpl.setUserId(freeDaysHistoryEntry.getUserId());
		freeDaysHistoryEntryImpl.setOperation(freeDaysHistoryEntry.getOperation());
		freeDaysHistoryEntryImpl.setOldValue(freeDaysHistoryEntry.getOldValue());
		freeDaysHistoryEntryImpl.setDaysNo(freeDaysHistoryEntry.getDaysNo());
		freeDaysHistoryEntryImpl.setNewValue(freeDaysHistoryEntry.getNewValue());
		freeDaysHistoryEntryImpl.setCreateDate(freeDaysHistoryEntry.getCreateDate());
		freeDaysHistoryEntryImpl.setEventType(freeDaysHistoryEntry.getEventType());
		freeDaysHistoryEntryImpl.setDescription(freeDaysHistoryEntry.getDescription());

		return freeDaysHistoryEntryImpl;
	}

	/**
	 * Returns the free days history entry with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the free days history entry
	 * @return the free days history entry
	 * @throws com.liferay.portal.NoSuchModelException if a free days history entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public FreeDaysHistoryEntry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the free days history entry with the primary key or throws a {@link com.evozon.evoportal.evozonfreedaysallocator.NoSuchFreeDaysHistoryEntryException} if it could not be found.
	 *
	 * @param entryId the primary key of the free days history entry
	 * @return the free days history entry
	 * @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchFreeDaysHistoryEntryException if a free days history entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public FreeDaysHistoryEntry findByPrimaryKey(long entryId)
		throws NoSuchFreeDaysHistoryEntryException, SystemException {
		FreeDaysHistoryEntry freeDaysHistoryEntry = fetchByPrimaryKey(entryId);

		if (freeDaysHistoryEntry == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + entryId);
			}

			throw new NoSuchFreeDaysHistoryEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				entryId);
		}

		return freeDaysHistoryEntry;
	}

	/**
	 * Returns the free days history entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the free days history entry
	 * @return the free days history entry, or <code>null</code> if a free days history entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public FreeDaysHistoryEntry fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the free days history entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param entryId the primary key of the free days history entry
	 * @return the free days history entry, or <code>null</code> if a free days history entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public FreeDaysHistoryEntry fetchByPrimaryKey(long entryId)
		throws SystemException {
		FreeDaysHistoryEntry freeDaysHistoryEntry = (FreeDaysHistoryEntry)EntityCacheUtil.getResult(FreeDaysHistoryEntryModelImpl.ENTITY_CACHE_ENABLED,
				FreeDaysHistoryEntryImpl.class, entryId);

		if (freeDaysHistoryEntry == _nullFreeDaysHistoryEntry) {
			return null;
		}

		if (freeDaysHistoryEntry == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				freeDaysHistoryEntry = (FreeDaysHistoryEntry)session.get(FreeDaysHistoryEntryImpl.class,
						Long.valueOf(entryId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (freeDaysHistoryEntry != null) {
					cacheResult(freeDaysHistoryEntry);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(FreeDaysHistoryEntryModelImpl.ENTITY_CACHE_ENABLED,
						FreeDaysHistoryEntryImpl.class, entryId,
						_nullFreeDaysHistoryEntry);
				}

				closeSession(session);
			}
		}

		return freeDaysHistoryEntry;
	}

	/**
	 * Returns all the free days history entries where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching free days history entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<FreeDaysHistoryEntry> findByUserId(long userId)
		throws SystemException {
		return findByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the free days history entries where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of free days history entries
	 * @param end the upper bound of the range of free days history entries (not inclusive)
	 * @return the range of matching free days history entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<FreeDaysHistoryEntry> findByUserId(long userId, int start,
		int end) throws SystemException {
		return findByUserId(userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the free days history entries where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of free days history entries
	 * @param end the upper bound of the range of free days history entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching free days history entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<FreeDaysHistoryEntry> findByUserId(long userId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID;
			finderArgs = new Object[] { userId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID;
			finderArgs = new Object[] { userId, start, end, orderByComparator };
		}

		List<FreeDaysHistoryEntry> list = (List<FreeDaysHistoryEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (FreeDaysHistoryEntry freeDaysHistoryEntry : list) {
				if ((userId != freeDaysHistoryEntry.getUserId())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_FREEDAYSHISTORYENTRY_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(FreeDaysHistoryEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				list = (List<FreeDaysHistoryEntry>)QueryUtil.list(q,
						getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first free days history entry in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching free days history entry
	 * @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchFreeDaysHistoryEntryException if a matching free days history entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public FreeDaysHistoryEntry findByUserId_First(long userId,
		OrderByComparator orderByComparator)
		throws NoSuchFreeDaysHistoryEntryException, SystemException {
		FreeDaysHistoryEntry freeDaysHistoryEntry = fetchByUserId_First(userId,
				orderByComparator);

		if (freeDaysHistoryEntry != null) {
			return freeDaysHistoryEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchFreeDaysHistoryEntryException(msg.toString());
	}

	/**
	 * Returns the first free days history entry in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching free days history entry, or <code>null</code> if a matching free days history entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public FreeDaysHistoryEntry fetchByUserId_First(long userId,
		OrderByComparator orderByComparator) throws SystemException {
		List<FreeDaysHistoryEntry> list = findByUserId(userId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last free days history entry in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching free days history entry
	 * @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchFreeDaysHistoryEntryException if a matching free days history entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public FreeDaysHistoryEntry findByUserId_Last(long userId,
		OrderByComparator orderByComparator)
		throws NoSuchFreeDaysHistoryEntryException, SystemException {
		FreeDaysHistoryEntry freeDaysHistoryEntry = fetchByUserId_Last(userId,
				orderByComparator);

		if (freeDaysHistoryEntry != null) {
			return freeDaysHistoryEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchFreeDaysHistoryEntryException(msg.toString());
	}

	/**
	 * Returns the last free days history entry in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching free days history entry, or <code>null</code> if a matching free days history entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public FreeDaysHistoryEntry fetchByUserId_Last(long userId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUserId(userId);

		List<FreeDaysHistoryEntry> list = findByUserId(userId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the free days history entries before and after the current free days history entry in the ordered set where userId = &#63;.
	 *
	 * @param entryId the primary key of the current free days history entry
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next free days history entry
	 * @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchFreeDaysHistoryEntryException if a free days history entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public FreeDaysHistoryEntry[] findByUserId_PrevAndNext(long entryId,
		long userId, OrderByComparator orderByComparator)
		throws NoSuchFreeDaysHistoryEntryException, SystemException {
		FreeDaysHistoryEntry freeDaysHistoryEntry = findByPrimaryKey(entryId);

		Session session = null;

		try {
			session = openSession();

			FreeDaysHistoryEntry[] array = new FreeDaysHistoryEntryImpl[3];

			array[0] = getByUserId_PrevAndNext(session, freeDaysHistoryEntry,
					userId, orderByComparator, true);

			array[1] = freeDaysHistoryEntry;

			array[2] = getByUserId_PrevAndNext(session, freeDaysHistoryEntry,
					userId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected FreeDaysHistoryEntry getByUserId_PrevAndNext(Session session,
		FreeDaysHistoryEntry freeDaysHistoryEntry, long userId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_FREEDAYSHISTORYENTRY_WHERE);

		query.append(_FINDER_COLUMN_USERID_USERID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		else {
			query.append(FreeDaysHistoryEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(freeDaysHistoryEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<FreeDaysHistoryEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the free days history entries where userId = &#63; and operation = &#63;.
	 *
	 * @param userId the user ID
	 * @param operation the operation
	 * @return the matching free days history entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<FreeDaysHistoryEntry> findByUserId_Operation(long userId,
		String operation) throws SystemException {
		return findByUserId_Operation(userId, operation, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the free days history entries where userId = &#63; and operation = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param operation the operation
	 * @param start the lower bound of the range of free days history entries
	 * @param end the upper bound of the range of free days history entries (not inclusive)
	 * @return the range of matching free days history entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<FreeDaysHistoryEntry> findByUserId_Operation(long userId,
		String operation, int start, int end) throws SystemException {
		return findByUserId_Operation(userId, operation, start, end, null);
	}

	/**
	 * Returns an ordered range of all the free days history entries where userId = &#63; and operation = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param operation the operation
	 * @param start the lower bound of the range of free days history entries
	 * @param end the upper bound of the range of free days history entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching free days history entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<FreeDaysHistoryEntry> findByUserId_Operation(long userId,
		String operation, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID_OPERATION;
			finderArgs = new Object[] { userId, operation };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID_OPERATION;
			finderArgs = new Object[] {
					userId, operation,
					
					start, end, orderByComparator
				};
		}

		List<FreeDaysHistoryEntry> list = (List<FreeDaysHistoryEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (FreeDaysHistoryEntry freeDaysHistoryEntry : list) {
				if ((userId != freeDaysHistoryEntry.getUserId()) ||
						!Validator.equals(operation,
							freeDaysHistoryEntry.getOperation())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_FREEDAYSHISTORYENTRY_WHERE);

			query.append(_FINDER_COLUMN_USERID_OPERATION_USERID_2);

			if (operation == null) {
				query.append(_FINDER_COLUMN_USERID_OPERATION_OPERATION_1);
			}
			else {
				if (operation.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_USERID_OPERATION_OPERATION_3);
				}
				else {
					query.append(_FINDER_COLUMN_USERID_OPERATION_OPERATION_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(FreeDaysHistoryEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				if (operation != null) {
					qPos.add(operation);
				}

				list = (List<FreeDaysHistoryEntry>)QueryUtil.list(q,
						getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first free days history entry in the ordered set where userId = &#63; and operation = &#63;.
	 *
	 * @param userId the user ID
	 * @param operation the operation
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching free days history entry
	 * @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchFreeDaysHistoryEntryException if a matching free days history entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public FreeDaysHistoryEntry findByUserId_Operation_First(long userId,
		String operation, OrderByComparator orderByComparator)
		throws NoSuchFreeDaysHistoryEntryException, SystemException {
		FreeDaysHistoryEntry freeDaysHistoryEntry = fetchByUserId_Operation_First(userId,
				operation, orderByComparator);

		if (freeDaysHistoryEntry != null) {
			return freeDaysHistoryEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", operation=");
		msg.append(operation);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchFreeDaysHistoryEntryException(msg.toString());
	}

	/**
	 * Returns the first free days history entry in the ordered set where userId = &#63; and operation = &#63;.
	 *
	 * @param userId the user ID
	 * @param operation the operation
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching free days history entry, or <code>null</code> if a matching free days history entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public FreeDaysHistoryEntry fetchByUserId_Operation_First(long userId,
		String operation, OrderByComparator orderByComparator)
		throws SystemException {
		List<FreeDaysHistoryEntry> list = findByUserId_Operation(userId,
				operation, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last free days history entry in the ordered set where userId = &#63; and operation = &#63;.
	 *
	 * @param userId the user ID
	 * @param operation the operation
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching free days history entry
	 * @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchFreeDaysHistoryEntryException if a matching free days history entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public FreeDaysHistoryEntry findByUserId_Operation_Last(long userId,
		String operation, OrderByComparator orderByComparator)
		throws NoSuchFreeDaysHistoryEntryException, SystemException {
		FreeDaysHistoryEntry freeDaysHistoryEntry = fetchByUserId_Operation_Last(userId,
				operation, orderByComparator);

		if (freeDaysHistoryEntry != null) {
			return freeDaysHistoryEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", operation=");
		msg.append(operation);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchFreeDaysHistoryEntryException(msg.toString());
	}

	/**
	 * Returns the last free days history entry in the ordered set where userId = &#63; and operation = &#63;.
	 *
	 * @param userId the user ID
	 * @param operation the operation
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching free days history entry, or <code>null</code> if a matching free days history entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public FreeDaysHistoryEntry fetchByUserId_Operation_Last(long userId,
		String operation, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByUserId_Operation(userId, operation);

		List<FreeDaysHistoryEntry> list = findByUserId_Operation(userId,
				operation, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the free days history entries before and after the current free days history entry in the ordered set where userId = &#63; and operation = &#63;.
	 *
	 * @param entryId the primary key of the current free days history entry
	 * @param userId the user ID
	 * @param operation the operation
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next free days history entry
	 * @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchFreeDaysHistoryEntryException if a free days history entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public FreeDaysHistoryEntry[] findByUserId_Operation_PrevAndNext(
		long entryId, long userId, String operation,
		OrderByComparator orderByComparator)
		throws NoSuchFreeDaysHistoryEntryException, SystemException {
		FreeDaysHistoryEntry freeDaysHistoryEntry = findByPrimaryKey(entryId);

		Session session = null;

		try {
			session = openSession();

			FreeDaysHistoryEntry[] array = new FreeDaysHistoryEntryImpl[3];

			array[0] = getByUserId_Operation_PrevAndNext(session,
					freeDaysHistoryEntry, userId, operation, orderByComparator,
					true);

			array[1] = freeDaysHistoryEntry;

			array[2] = getByUserId_Operation_PrevAndNext(session,
					freeDaysHistoryEntry, userId, operation, orderByComparator,
					false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected FreeDaysHistoryEntry getByUserId_Operation_PrevAndNext(
		Session session, FreeDaysHistoryEntry freeDaysHistoryEntry,
		long userId, String operation, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_FREEDAYSHISTORYENTRY_WHERE);

		query.append(_FINDER_COLUMN_USERID_OPERATION_USERID_2);

		if (operation == null) {
			query.append(_FINDER_COLUMN_USERID_OPERATION_OPERATION_1);
		}
		else {
			if (operation.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_USERID_OPERATION_OPERATION_3);
			}
			else {
				query.append(_FINDER_COLUMN_USERID_OPERATION_OPERATION_2);
			}
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		else {
			query.append(FreeDaysHistoryEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		if (operation != null) {
			qPos.add(operation);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(freeDaysHistoryEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<FreeDaysHistoryEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the free days history entries where userId = &#63; and eventType = &#63;.
	 *
	 * @param userId the user ID
	 * @param eventType the event type
	 * @return the matching free days history entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<FreeDaysHistoryEntry> findByUserId_EventType(long userId,
		String eventType) throws SystemException {
		return findByUserId_EventType(userId, eventType, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the free days history entries where userId = &#63; and eventType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param eventType the event type
	 * @param start the lower bound of the range of free days history entries
	 * @param end the upper bound of the range of free days history entries (not inclusive)
	 * @return the range of matching free days history entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<FreeDaysHistoryEntry> findByUserId_EventType(long userId,
		String eventType, int start, int end) throws SystemException {
		return findByUserId_EventType(userId, eventType, start, end, null);
	}

	/**
	 * Returns an ordered range of all the free days history entries where userId = &#63; and eventType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param eventType the event type
	 * @param start the lower bound of the range of free days history entries
	 * @param end the upper bound of the range of free days history entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching free days history entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<FreeDaysHistoryEntry> findByUserId_EventType(long userId,
		String eventType, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID_EVENTTYPE;
			finderArgs = new Object[] { userId, eventType };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID_EVENTTYPE;
			finderArgs = new Object[] {
					userId, eventType,
					
					start, end, orderByComparator
				};
		}

		List<FreeDaysHistoryEntry> list = (List<FreeDaysHistoryEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (FreeDaysHistoryEntry freeDaysHistoryEntry : list) {
				if ((userId != freeDaysHistoryEntry.getUserId()) ||
						!Validator.equals(eventType,
							freeDaysHistoryEntry.getEventType())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_FREEDAYSHISTORYENTRY_WHERE);

			query.append(_FINDER_COLUMN_USERID_EVENTTYPE_USERID_2);

			if (eventType == null) {
				query.append(_FINDER_COLUMN_USERID_EVENTTYPE_EVENTTYPE_1);
			}
			else {
				if (eventType.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_USERID_EVENTTYPE_EVENTTYPE_3);
				}
				else {
					query.append(_FINDER_COLUMN_USERID_EVENTTYPE_EVENTTYPE_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(FreeDaysHistoryEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				if (eventType != null) {
					qPos.add(eventType);
				}

				list = (List<FreeDaysHistoryEntry>)QueryUtil.list(q,
						getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first free days history entry in the ordered set where userId = &#63; and eventType = &#63;.
	 *
	 * @param userId the user ID
	 * @param eventType the event type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching free days history entry
	 * @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchFreeDaysHistoryEntryException if a matching free days history entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public FreeDaysHistoryEntry findByUserId_EventType_First(long userId,
		String eventType, OrderByComparator orderByComparator)
		throws NoSuchFreeDaysHistoryEntryException, SystemException {
		FreeDaysHistoryEntry freeDaysHistoryEntry = fetchByUserId_EventType_First(userId,
				eventType, orderByComparator);

		if (freeDaysHistoryEntry != null) {
			return freeDaysHistoryEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", eventType=");
		msg.append(eventType);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchFreeDaysHistoryEntryException(msg.toString());
	}

	/**
	 * Returns the first free days history entry in the ordered set where userId = &#63; and eventType = &#63;.
	 *
	 * @param userId the user ID
	 * @param eventType the event type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching free days history entry, or <code>null</code> if a matching free days history entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public FreeDaysHistoryEntry fetchByUserId_EventType_First(long userId,
		String eventType, OrderByComparator orderByComparator)
		throws SystemException {
		List<FreeDaysHistoryEntry> list = findByUserId_EventType(userId,
				eventType, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last free days history entry in the ordered set where userId = &#63; and eventType = &#63;.
	 *
	 * @param userId the user ID
	 * @param eventType the event type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching free days history entry
	 * @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchFreeDaysHistoryEntryException if a matching free days history entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public FreeDaysHistoryEntry findByUserId_EventType_Last(long userId,
		String eventType, OrderByComparator orderByComparator)
		throws NoSuchFreeDaysHistoryEntryException, SystemException {
		FreeDaysHistoryEntry freeDaysHistoryEntry = fetchByUserId_EventType_Last(userId,
				eventType, orderByComparator);

		if (freeDaysHistoryEntry != null) {
			return freeDaysHistoryEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", eventType=");
		msg.append(eventType);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchFreeDaysHistoryEntryException(msg.toString());
	}

	/**
	 * Returns the last free days history entry in the ordered set where userId = &#63; and eventType = &#63;.
	 *
	 * @param userId the user ID
	 * @param eventType the event type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching free days history entry, or <code>null</code> if a matching free days history entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public FreeDaysHistoryEntry fetchByUserId_EventType_Last(long userId,
		String eventType, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByUserId_EventType(userId, eventType);

		List<FreeDaysHistoryEntry> list = findByUserId_EventType(userId,
				eventType, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the free days history entries before and after the current free days history entry in the ordered set where userId = &#63; and eventType = &#63;.
	 *
	 * @param entryId the primary key of the current free days history entry
	 * @param userId the user ID
	 * @param eventType the event type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next free days history entry
	 * @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchFreeDaysHistoryEntryException if a free days history entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public FreeDaysHistoryEntry[] findByUserId_EventType_PrevAndNext(
		long entryId, long userId, String eventType,
		OrderByComparator orderByComparator)
		throws NoSuchFreeDaysHistoryEntryException, SystemException {
		FreeDaysHistoryEntry freeDaysHistoryEntry = findByPrimaryKey(entryId);

		Session session = null;

		try {
			session = openSession();

			FreeDaysHistoryEntry[] array = new FreeDaysHistoryEntryImpl[3];

			array[0] = getByUserId_EventType_PrevAndNext(session,
					freeDaysHistoryEntry, userId, eventType, orderByComparator,
					true);

			array[1] = freeDaysHistoryEntry;

			array[2] = getByUserId_EventType_PrevAndNext(session,
					freeDaysHistoryEntry, userId, eventType, orderByComparator,
					false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected FreeDaysHistoryEntry getByUserId_EventType_PrevAndNext(
		Session session, FreeDaysHistoryEntry freeDaysHistoryEntry,
		long userId, String eventType, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_FREEDAYSHISTORYENTRY_WHERE);

		query.append(_FINDER_COLUMN_USERID_EVENTTYPE_USERID_2);

		if (eventType == null) {
			query.append(_FINDER_COLUMN_USERID_EVENTTYPE_EVENTTYPE_1);
		}
		else {
			if (eventType.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_USERID_EVENTTYPE_EVENTTYPE_3);
			}
			else {
				query.append(_FINDER_COLUMN_USERID_EVENTTYPE_EVENTTYPE_2);
			}
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		else {
			query.append(FreeDaysHistoryEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		if (eventType != null) {
			qPos.add(eventType);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(freeDaysHistoryEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<FreeDaysHistoryEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the free days history entries where userId = &#63; and createDate = &#63;.
	 *
	 * @param userId the user ID
	 * @param createDate the create date
	 * @return the matching free days history entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<FreeDaysHistoryEntry> findByUserId_CreateDate(long userId,
		Date createDate) throws SystemException {
		return findByUserId_CreateDate(userId, createDate, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the free days history entries where userId = &#63; and createDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param createDate the create date
	 * @param start the lower bound of the range of free days history entries
	 * @param end the upper bound of the range of free days history entries (not inclusive)
	 * @return the range of matching free days history entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<FreeDaysHistoryEntry> findByUserId_CreateDate(long userId,
		Date createDate, int start, int end) throws SystemException {
		return findByUserId_CreateDate(userId, createDate, start, end, null);
	}

	/**
	 * Returns an ordered range of all the free days history entries where userId = &#63; and createDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param createDate the create date
	 * @param start the lower bound of the range of free days history entries
	 * @param end the upper bound of the range of free days history entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching free days history entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<FreeDaysHistoryEntry> findByUserId_CreateDate(long userId,
		Date createDate, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID_CREATEDATE;
			finderArgs = new Object[] { userId, createDate };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID_CREATEDATE;
			finderArgs = new Object[] {
					userId, createDate,
					
					start, end, orderByComparator
				};
		}

		List<FreeDaysHistoryEntry> list = (List<FreeDaysHistoryEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (FreeDaysHistoryEntry freeDaysHistoryEntry : list) {
				if ((userId != freeDaysHistoryEntry.getUserId()) ||
						!Validator.equals(createDate,
							freeDaysHistoryEntry.getCreateDate())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_FREEDAYSHISTORYENTRY_WHERE);

			query.append(_FINDER_COLUMN_USERID_CREATEDATE_USERID_2);

			if (createDate == null) {
				query.append(_FINDER_COLUMN_USERID_CREATEDATE_CREATEDATE_1);
			}
			else {
				query.append(_FINDER_COLUMN_USERID_CREATEDATE_CREATEDATE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(FreeDaysHistoryEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				if (createDate != null) {
					qPos.add(CalendarUtil.getTimestamp(createDate));
				}

				list = (List<FreeDaysHistoryEntry>)QueryUtil.list(q,
						getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first free days history entry in the ordered set where userId = &#63; and createDate = &#63;.
	 *
	 * @param userId the user ID
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching free days history entry
	 * @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchFreeDaysHistoryEntryException if a matching free days history entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public FreeDaysHistoryEntry findByUserId_CreateDate_First(long userId,
		Date createDate, OrderByComparator orderByComparator)
		throws NoSuchFreeDaysHistoryEntryException, SystemException {
		FreeDaysHistoryEntry freeDaysHistoryEntry = fetchByUserId_CreateDate_First(userId,
				createDate, orderByComparator);

		if (freeDaysHistoryEntry != null) {
			return freeDaysHistoryEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", createDate=");
		msg.append(createDate);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchFreeDaysHistoryEntryException(msg.toString());
	}

	/**
	 * Returns the first free days history entry in the ordered set where userId = &#63; and createDate = &#63;.
	 *
	 * @param userId the user ID
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching free days history entry, or <code>null</code> if a matching free days history entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public FreeDaysHistoryEntry fetchByUserId_CreateDate_First(long userId,
		Date createDate, OrderByComparator orderByComparator)
		throws SystemException {
		List<FreeDaysHistoryEntry> list = findByUserId_CreateDate(userId,
				createDate, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last free days history entry in the ordered set where userId = &#63; and createDate = &#63;.
	 *
	 * @param userId the user ID
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching free days history entry
	 * @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchFreeDaysHistoryEntryException if a matching free days history entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public FreeDaysHistoryEntry findByUserId_CreateDate_Last(long userId,
		Date createDate, OrderByComparator orderByComparator)
		throws NoSuchFreeDaysHistoryEntryException, SystemException {
		FreeDaysHistoryEntry freeDaysHistoryEntry = fetchByUserId_CreateDate_Last(userId,
				createDate, orderByComparator);

		if (freeDaysHistoryEntry != null) {
			return freeDaysHistoryEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", createDate=");
		msg.append(createDate);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchFreeDaysHistoryEntryException(msg.toString());
	}

	/**
	 * Returns the last free days history entry in the ordered set where userId = &#63; and createDate = &#63;.
	 *
	 * @param userId the user ID
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching free days history entry, or <code>null</code> if a matching free days history entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public FreeDaysHistoryEntry fetchByUserId_CreateDate_Last(long userId,
		Date createDate, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByUserId_CreateDate(userId, createDate);

		List<FreeDaysHistoryEntry> list = findByUserId_CreateDate(userId,
				createDate, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the free days history entries before and after the current free days history entry in the ordered set where userId = &#63; and createDate = &#63;.
	 *
	 * @param entryId the primary key of the current free days history entry
	 * @param userId the user ID
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next free days history entry
	 * @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchFreeDaysHistoryEntryException if a free days history entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public FreeDaysHistoryEntry[] findByUserId_CreateDate_PrevAndNext(
		long entryId, long userId, Date createDate,
		OrderByComparator orderByComparator)
		throws NoSuchFreeDaysHistoryEntryException, SystemException {
		FreeDaysHistoryEntry freeDaysHistoryEntry = findByPrimaryKey(entryId);

		Session session = null;

		try {
			session = openSession();

			FreeDaysHistoryEntry[] array = new FreeDaysHistoryEntryImpl[3];

			array[0] = getByUserId_CreateDate_PrevAndNext(session,
					freeDaysHistoryEntry, userId, createDate,
					orderByComparator, true);

			array[1] = freeDaysHistoryEntry;

			array[2] = getByUserId_CreateDate_PrevAndNext(session,
					freeDaysHistoryEntry, userId, createDate,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected FreeDaysHistoryEntry getByUserId_CreateDate_PrevAndNext(
		Session session, FreeDaysHistoryEntry freeDaysHistoryEntry,
		long userId, Date createDate, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_FREEDAYSHISTORYENTRY_WHERE);

		query.append(_FINDER_COLUMN_USERID_CREATEDATE_USERID_2);

		if (createDate == null) {
			query.append(_FINDER_COLUMN_USERID_CREATEDATE_CREATEDATE_1);
		}
		else {
			query.append(_FINDER_COLUMN_USERID_CREATEDATE_CREATEDATE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		else {
			query.append(FreeDaysHistoryEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		if (createDate != null) {
			qPos.add(CalendarUtil.getTimestamp(createDate));
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(freeDaysHistoryEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<FreeDaysHistoryEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the free days history entries where createDate = &#63;.
	 *
	 * @param createDate the create date
	 * @return the matching free days history entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<FreeDaysHistoryEntry> findByCreateDate(Date createDate)
		throws SystemException {
		return findByCreateDate(createDate, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the free days history entries where createDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param createDate the create date
	 * @param start the lower bound of the range of free days history entries
	 * @param end the upper bound of the range of free days history entries (not inclusive)
	 * @return the range of matching free days history entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<FreeDaysHistoryEntry> findByCreateDate(Date createDate,
		int start, int end) throws SystemException {
		return findByCreateDate(createDate, start, end, null);
	}

	/**
	 * Returns an ordered range of all the free days history entries where createDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param createDate the create date
	 * @param start the lower bound of the range of free days history entries
	 * @param end the upper bound of the range of free days history entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching free days history entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<FreeDaysHistoryEntry> findByCreateDate(Date createDate,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CREATEDATE;
			finderArgs = new Object[] { createDate };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CREATEDATE;
			finderArgs = new Object[] { createDate, start, end, orderByComparator };
		}

		List<FreeDaysHistoryEntry> list = (List<FreeDaysHistoryEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (FreeDaysHistoryEntry freeDaysHistoryEntry : list) {
				if (!Validator.equals(createDate,
							freeDaysHistoryEntry.getCreateDate())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_FREEDAYSHISTORYENTRY_WHERE);

			if (createDate == null) {
				query.append(_FINDER_COLUMN_CREATEDATE_CREATEDATE_1);
			}
			else {
				query.append(_FINDER_COLUMN_CREATEDATE_CREATEDATE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(FreeDaysHistoryEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (createDate != null) {
					qPos.add(CalendarUtil.getTimestamp(createDate));
				}

				list = (List<FreeDaysHistoryEntry>)QueryUtil.list(q,
						getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first free days history entry in the ordered set where createDate = &#63;.
	 *
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching free days history entry
	 * @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchFreeDaysHistoryEntryException if a matching free days history entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public FreeDaysHistoryEntry findByCreateDate_First(Date createDate,
		OrderByComparator orderByComparator)
		throws NoSuchFreeDaysHistoryEntryException, SystemException {
		FreeDaysHistoryEntry freeDaysHistoryEntry = fetchByCreateDate_First(createDate,
				orderByComparator);

		if (freeDaysHistoryEntry != null) {
			return freeDaysHistoryEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("createDate=");
		msg.append(createDate);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchFreeDaysHistoryEntryException(msg.toString());
	}

	/**
	 * Returns the first free days history entry in the ordered set where createDate = &#63;.
	 *
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching free days history entry, or <code>null</code> if a matching free days history entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public FreeDaysHistoryEntry fetchByCreateDate_First(Date createDate,
		OrderByComparator orderByComparator) throws SystemException {
		List<FreeDaysHistoryEntry> list = findByCreateDate(createDate, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last free days history entry in the ordered set where createDate = &#63;.
	 *
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching free days history entry
	 * @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchFreeDaysHistoryEntryException if a matching free days history entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public FreeDaysHistoryEntry findByCreateDate_Last(Date createDate,
		OrderByComparator orderByComparator)
		throws NoSuchFreeDaysHistoryEntryException, SystemException {
		FreeDaysHistoryEntry freeDaysHistoryEntry = fetchByCreateDate_Last(createDate,
				orderByComparator);

		if (freeDaysHistoryEntry != null) {
			return freeDaysHistoryEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("createDate=");
		msg.append(createDate);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchFreeDaysHistoryEntryException(msg.toString());
	}

	/**
	 * Returns the last free days history entry in the ordered set where createDate = &#63;.
	 *
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching free days history entry, or <code>null</code> if a matching free days history entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public FreeDaysHistoryEntry fetchByCreateDate_Last(Date createDate,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByCreateDate(createDate);

		List<FreeDaysHistoryEntry> list = findByCreateDate(createDate,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the free days history entries before and after the current free days history entry in the ordered set where createDate = &#63;.
	 *
	 * @param entryId the primary key of the current free days history entry
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next free days history entry
	 * @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchFreeDaysHistoryEntryException if a free days history entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public FreeDaysHistoryEntry[] findByCreateDate_PrevAndNext(long entryId,
		Date createDate, OrderByComparator orderByComparator)
		throws NoSuchFreeDaysHistoryEntryException, SystemException {
		FreeDaysHistoryEntry freeDaysHistoryEntry = findByPrimaryKey(entryId);

		Session session = null;

		try {
			session = openSession();

			FreeDaysHistoryEntry[] array = new FreeDaysHistoryEntryImpl[3];

			array[0] = getByCreateDate_PrevAndNext(session,
					freeDaysHistoryEntry, createDate, orderByComparator, true);

			array[1] = freeDaysHistoryEntry;

			array[2] = getByCreateDate_PrevAndNext(session,
					freeDaysHistoryEntry, createDate, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected FreeDaysHistoryEntry getByCreateDate_PrevAndNext(
		Session session, FreeDaysHistoryEntry freeDaysHistoryEntry,
		Date createDate, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_FREEDAYSHISTORYENTRY_WHERE);

		if (createDate == null) {
			query.append(_FINDER_COLUMN_CREATEDATE_CREATEDATE_1);
		}
		else {
			query.append(_FINDER_COLUMN_CREATEDATE_CREATEDATE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		else {
			query.append(FreeDaysHistoryEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (createDate != null) {
			qPos.add(CalendarUtil.getTimestamp(createDate));
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(freeDaysHistoryEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<FreeDaysHistoryEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the free days history entries.
	 *
	 * @return the free days history entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<FreeDaysHistoryEntry> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the free days history entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of free days history entries
	 * @param end the upper bound of the range of free days history entries (not inclusive)
	 * @return the range of free days history entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<FreeDaysHistoryEntry> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the free days history entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of free days history entries
	 * @param end the upper bound of the range of free days history entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of free days history entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<FreeDaysHistoryEntry> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = new Object[] { start, end, orderByComparator };

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<FreeDaysHistoryEntry> list = (List<FreeDaysHistoryEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_FREEDAYSHISTORYENTRY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_FREEDAYSHISTORYENTRY.concat(FreeDaysHistoryEntryModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<FreeDaysHistoryEntry>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<FreeDaysHistoryEntry>)QueryUtil.list(q,
							getDialect(), start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the free days history entries where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByUserId(long userId) throws SystemException {
		for (FreeDaysHistoryEntry freeDaysHistoryEntry : findByUserId(userId)) {
			remove(freeDaysHistoryEntry);
		}
	}

	/**
	 * Removes all the free days history entries where userId = &#63; and operation = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param operation the operation
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByUserId_Operation(long userId, String operation)
		throws SystemException {
		for (FreeDaysHistoryEntry freeDaysHistoryEntry : findByUserId_Operation(
				userId, operation)) {
			remove(freeDaysHistoryEntry);
		}
	}

	/**
	 * Removes all the free days history entries where userId = &#63; and eventType = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param eventType the event type
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByUserId_EventType(long userId, String eventType)
		throws SystemException {
		for (FreeDaysHistoryEntry freeDaysHistoryEntry : findByUserId_EventType(
				userId, eventType)) {
			remove(freeDaysHistoryEntry);
		}
	}

	/**
	 * Removes all the free days history entries where userId = &#63; and createDate = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param createDate the create date
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByUserId_CreateDate(long userId, Date createDate)
		throws SystemException {
		for (FreeDaysHistoryEntry freeDaysHistoryEntry : findByUserId_CreateDate(
				userId, createDate)) {
			remove(freeDaysHistoryEntry);
		}
	}

	/**
	 * Removes all the free days history entries where createDate = &#63; from the database.
	 *
	 * @param createDate the create date
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByCreateDate(Date createDate) throws SystemException {
		for (FreeDaysHistoryEntry freeDaysHistoryEntry : findByCreateDate(
				createDate)) {
			remove(freeDaysHistoryEntry);
		}
	}

	/**
	 * Removes all the free days history entries from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (FreeDaysHistoryEntry freeDaysHistoryEntry : findAll()) {
			remove(freeDaysHistoryEntry);
		}
	}

	/**
	 * Returns the number of free days history entries where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching free days history entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUserId(long userId) throws SystemException {
		Object[] finderArgs = new Object[] { userId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_USERID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_FREEDAYSHISTORYENTRY_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_USERID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of free days history entries where userId = &#63; and operation = &#63;.
	 *
	 * @param userId the user ID
	 * @param operation the operation
	 * @return the number of matching free days history entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUserId_Operation(long userId, String operation)
		throws SystemException {
		Object[] finderArgs = new Object[] { userId, operation };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_USERID_OPERATION,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_FREEDAYSHISTORYENTRY_WHERE);

			query.append(_FINDER_COLUMN_USERID_OPERATION_USERID_2);

			if (operation == null) {
				query.append(_FINDER_COLUMN_USERID_OPERATION_OPERATION_1);
			}
			else {
				if (operation.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_USERID_OPERATION_OPERATION_3);
				}
				else {
					query.append(_FINDER_COLUMN_USERID_OPERATION_OPERATION_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				if (operation != null) {
					qPos.add(operation);
				}

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_USERID_OPERATION,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of free days history entries where userId = &#63; and eventType = &#63;.
	 *
	 * @param userId the user ID
	 * @param eventType the event type
	 * @return the number of matching free days history entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUserId_EventType(long userId, String eventType)
		throws SystemException {
		Object[] finderArgs = new Object[] { userId, eventType };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_USERID_EVENTTYPE,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_FREEDAYSHISTORYENTRY_WHERE);

			query.append(_FINDER_COLUMN_USERID_EVENTTYPE_USERID_2);

			if (eventType == null) {
				query.append(_FINDER_COLUMN_USERID_EVENTTYPE_EVENTTYPE_1);
			}
			else {
				if (eventType.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_USERID_EVENTTYPE_EVENTTYPE_3);
				}
				else {
					query.append(_FINDER_COLUMN_USERID_EVENTTYPE_EVENTTYPE_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				if (eventType != null) {
					qPos.add(eventType);
				}

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_USERID_EVENTTYPE,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of free days history entries where userId = &#63; and createDate = &#63;.
	 *
	 * @param userId the user ID
	 * @param createDate the create date
	 * @return the number of matching free days history entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUserId_CreateDate(long userId, Date createDate)
		throws SystemException {
		Object[] finderArgs = new Object[] { userId, createDate };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_USERID_CREATEDATE,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_FREEDAYSHISTORYENTRY_WHERE);

			query.append(_FINDER_COLUMN_USERID_CREATEDATE_USERID_2);

			if (createDate == null) {
				query.append(_FINDER_COLUMN_USERID_CREATEDATE_CREATEDATE_1);
			}
			else {
				query.append(_FINDER_COLUMN_USERID_CREATEDATE_CREATEDATE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				if (createDate != null) {
					qPos.add(CalendarUtil.getTimestamp(createDate));
				}

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_USERID_CREATEDATE,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of free days history entries where createDate = &#63;.
	 *
	 * @param createDate the create date
	 * @return the number of matching free days history entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByCreateDate(Date createDate) throws SystemException {
		Object[] finderArgs = new Object[] { createDate };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_CREATEDATE,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_FREEDAYSHISTORYENTRY_WHERE);

			if (createDate == null) {
				query.append(_FINDER_COLUMN_CREATEDATE_CREATEDATE_1);
			}
			else {
				query.append(_FINDER_COLUMN_CREATEDATE_CREATEDATE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (createDate != null) {
					qPos.add(CalendarUtil.getTimestamp(createDate));
				}

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_CREATEDATE,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of free days history entries.
	 *
	 * @return the number of free days history entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_FREEDAYSHISTORYENTRY);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Initializes the free days history entry persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.evozon.evoportal.evozonfreedaysallocator.model.FreeDaysHistoryEntry")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<FreeDaysHistoryEntry>> listenersList = new ArrayList<ModelListener<FreeDaysHistoryEntry>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<FreeDaysHistoryEntry>)InstanceFactory.newInstance(
							listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	public void destroy() {
		EntityCacheUtil.removeCache(FreeDaysHistoryEntryImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@BeanReference(type = BenefitDayPersistence.class)
	protected BenefitDayPersistence benefitDayPersistence;
	@BeanReference(type = BenefitDayQueuePersistence.class)
	protected BenefitDayQueuePersistence benefitDayQueuePersistence;
	@BeanReference(type = FreeDaysHistoryEntryPersistence.class)
	protected FreeDaysHistoryEntryPersistence freeDaysHistoryEntryPersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_FREEDAYSHISTORYENTRY = "SELECT freeDaysHistoryEntry FROM FreeDaysHistoryEntry freeDaysHistoryEntry";
	private static final String _SQL_SELECT_FREEDAYSHISTORYENTRY_WHERE = "SELECT freeDaysHistoryEntry FROM FreeDaysHistoryEntry freeDaysHistoryEntry WHERE ";
	private static final String _SQL_COUNT_FREEDAYSHISTORYENTRY = "SELECT COUNT(freeDaysHistoryEntry) FROM FreeDaysHistoryEntry freeDaysHistoryEntry";
	private static final String _SQL_COUNT_FREEDAYSHISTORYENTRY_WHERE = "SELECT COUNT(freeDaysHistoryEntry) FROM FreeDaysHistoryEntry freeDaysHistoryEntry WHERE ";
	private static final String _FINDER_COLUMN_USERID_USERID_2 = "freeDaysHistoryEntry.userId = ?";
	private static final String _FINDER_COLUMN_USERID_OPERATION_USERID_2 = "freeDaysHistoryEntry.userId = ? AND ";
	private static final String _FINDER_COLUMN_USERID_OPERATION_OPERATION_1 = "freeDaysHistoryEntry.operation IS NULL";
	private static final String _FINDER_COLUMN_USERID_OPERATION_OPERATION_2 = "freeDaysHistoryEntry.operation = ?";
	private static final String _FINDER_COLUMN_USERID_OPERATION_OPERATION_3 = "(freeDaysHistoryEntry.operation IS NULL OR freeDaysHistoryEntry.operation = ?)";
	private static final String _FINDER_COLUMN_USERID_EVENTTYPE_USERID_2 = "freeDaysHistoryEntry.userId = ? AND ";
	private static final String _FINDER_COLUMN_USERID_EVENTTYPE_EVENTTYPE_1 = "freeDaysHistoryEntry.eventType IS NULL";
	private static final String _FINDER_COLUMN_USERID_EVENTTYPE_EVENTTYPE_2 = "freeDaysHistoryEntry.eventType = ?";
	private static final String _FINDER_COLUMN_USERID_EVENTTYPE_EVENTTYPE_3 = "(freeDaysHistoryEntry.eventType IS NULL OR freeDaysHistoryEntry.eventType = ?)";
	private static final String _FINDER_COLUMN_USERID_CREATEDATE_USERID_2 = "freeDaysHistoryEntry.userId = ? AND ";
	private static final String _FINDER_COLUMN_USERID_CREATEDATE_CREATEDATE_1 = "freeDaysHistoryEntry.createDate IS NULL";
	private static final String _FINDER_COLUMN_USERID_CREATEDATE_CREATEDATE_2 = "freeDaysHistoryEntry.createDate = ?";
	private static final String _FINDER_COLUMN_CREATEDATE_CREATEDATE_1 = "freeDaysHistoryEntry.createDate IS NULL";
	private static final String _FINDER_COLUMN_CREATEDATE_CREATEDATE_2 = "freeDaysHistoryEntry.createDate = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "freeDaysHistoryEntry.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No FreeDaysHistoryEntry exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No FreeDaysHistoryEntry exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(FreeDaysHistoryEntryPersistenceImpl.class);
	private static FreeDaysHistoryEntry _nullFreeDaysHistoryEntry = new FreeDaysHistoryEntryImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<FreeDaysHistoryEntry> toCacheModel() {
				return _nullFreeDaysHistoryEntryCacheModel;
			}
		};

	private static CacheModel<FreeDaysHistoryEntry> _nullFreeDaysHistoryEntryCacheModel =
		new CacheModel<FreeDaysHistoryEntry>() {
			public FreeDaysHistoryEntry toEntityModel() {
				return _nullFreeDaysHistoryEntry;
			}
		};
}