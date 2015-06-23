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

import com.evozon.evoportal.evozonfreedaysallocator.NoSuchQueueException;
import com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue;
import com.evozon.evoportal.evozonfreedaysallocator.model.impl.BenefitDayQueueImpl;
import com.evozon.evoportal.evozonfreedaysallocator.model.impl.BenefitDayQueueModelImpl;

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
 * The persistence implementation for the benefit day queue service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Cristina Kiss
 * @see BenefitDayQueuePersistence
 * @see BenefitDayQueueUtil
 * @generated
 */
public class BenefitDayQueuePersistenceImpl extends BasePersistenceImpl<BenefitDayQueue>
	implements BenefitDayQueuePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link BenefitDayQueueUtil} to access the benefit day queue persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = BenefitDayQueueImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID = new FinderPath(BenefitDayQueueModelImpl.ENTITY_CACHE_ENABLED,
			BenefitDayQueueModelImpl.FINDER_CACHE_ENABLED,
			BenefitDayQueueImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUserId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID =
		new FinderPath(BenefitDayQueueModelImpl.ENTITY_CACHE_ENABLED,
			BenefitDayQueueModelImpl.FINDER_CACHE_ENABLED,
			BenefitDayQueueImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUserId",
			new String[] { Long.class.getName() },
			BenefitDayQueueModelImpl.USERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_USERID = new FinderPath(BenefitDayQueueModelImpl.ENTITY_CACHE_ENABLED,
			BenefitDayQueueModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUserId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_TYPE = new FinderPath(BenefitDayQueueModelImpl.ENTITY_CACHE_ENABLED,
			BenefitDayQueueModelImpl.FINDER_CACHE_ENABLED,
			BenefitDayQueueImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByType",
			new String[] {
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TYPE = new FinderPath(BenefitDayQueueModelImpl.ENTITY_CACHE_ENABLED,
			BenefitDayQueueModelImpl.FINDER_CACHE_ENABLED,
			BenefitDayQueueImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByType",
			new String[] { String.class.getName() },
			BenefitDayQueueModelImpl.TYPE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_TYPE = new FinderPath(BenefitDayQueueModelImpl.ENTITY_CACHE_ENABLED,
			BenefitDayQueueModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByType",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID_QUEUED =
		new FinderPath(BenefitDayQueueModelImpl.ENTITY_CACHE_ENABLED,
			BenefitDayQueueModelImpl.FINDER_CACHE_ENABLED,
			BenefitDayQueueImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUserId_Queued",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID_QUEUED =
		new FinderPath(BenefitDayQueueModelImpl.ENTITY_CACHE_ENABLED,
			BenefitDayQueueModelImpl.FINDER_CACHE_ENABLED,
			BenefitDayQueueImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUserId_Queued",
			new String[] { Long.class.getName(), Boolean.class.getName() },
			BenefitDayQueueModelImpl.USERID_COLUMN_BITMASK |
			BenefitDayQueueModelImpl.QUEUED_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_USERID_QUEUED = new FinderPath(BenefitDayQueueModelImpl.ENTITY_CACHE_ENABLED,
			BenefitDayQueueModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUserId_Queued",
			new String[] { Long.class.getName(), Boolean.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID_ALLOCATED =
		new FinderPath(BenefitDayQueueModelImpl.ENTITY_CACHE_ENABLED,
			BenefitDayQueueModelImpl.FINDER_CACHE_ENABLED,
			BenefitDayQueueImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUserId_Allocated",
			new String[] {
				Long.class.getName(), Date.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID_ALLOCATED =
		new FinderPath(BenefitDayQueueModelImpl.ENTITY_CACHE_ENABLED,
			BenefitDayQueueModelImpl.FINDER_CACHE_ENABLED,
			BenefitDayQueueImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByUserId_Allocated",
			new String[] { Long.class.getName(), Date.class.getName() },
			BenefitDayQueueModelImpl.USERID_COLUMN_BITMASK |
			BenefitDayQueueModelImpl.ALLOCATEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_USERID_ALLOCATED = new FinderPath(BenefitDayQueueModelImpl.ENTITY_CACHE_ENABLED,
			BenefitDayQueueModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByUserId_Allocated",
			new String[] { Long.class.getName(), Date.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID_TYPE =
		new FinderPath(BenefitDayQueueModelImpl.ENTITY_CACHE_ENABLED,
			BenefitDayQueueModelImpl.FINDER_CACHE_ENABLED,
			BenefitDayQueueImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUserId_Type",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID_TYPE =
		new FinderPath(BenefitDayQueueModelImpl.ENTITY_CACHE_ENABLED,
			BenefitDayQueueModelImpl.FINDER_CACHE_ENABLED,
			BenefitDayQueueImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUserId_Type",
			new String[] { Long.class.getName(), String.class.getName() },
			BenefitDayQueueModelImpl.USERID_COLUMN_BITMASK |
			BenefitDayQueueModelImpl.TYPE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_USERID_TYPE = new FinderPath(BenefitDayQueueModelImpl.ENTITY_CACHE_ENABLED,
			BenefitDayQueueModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUserId_Type",
			new String[] { Long.class.getName(), String.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(BenefitDayQueueModelImpl.ENTITY_CACHE_ENABLED,
			BenefitDayQueueModelImpl.FINDER_CACHE_ENABLED,
			BenefitDayQueueImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(BenefitDayQueueModelImpl.ENTITY_CACHE_ENABLED,
			BenefitDayQueueModelImpl.FINDER_CACHE_ENABLED,
			BenefitDayQueueImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(BenefitDayQueueModelImpl.ENTITY_CACHE_ENABLED,
			BenefitDayQueueModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the benefit day queue in the entity cache if it is enabled.
	 *
	 * @param benefitDayQueue the benefit day queue
	 */
	public void cacheResult(BenefitDayQueue benefitDayQueue) {
		EntityCacheUtil.putResult(BenefitDayQueueModelImpl.ENTITY_CACHE_ENABLED,
			BenefitDayQueueImpl.class, benefitDayQueue.getPrimaryKey(),
			benefitDayQueue);

		benefitDayQueue.resetOriginalValues();
	}

	/**
	 * Caches the benefit day queues in the entity cache if it is enabled.
	 *
	 * @param benefitDayQueues the benefit day queues
	 */
	public void cacheResult(List<BenefitDayQueue> benefitDayQueues) {
		for (BenefitDayQueue benefitDayQueue : benefitDayQueues) {
			if (EntityCacheUtil.getResult(
						BenefitDayQueueModelImpl.ENTITY_CACHE_ENABLED,
						BenefitDayQueueImpl.class,
						benefitDayQueue.getPrimaryKey()) == null) {
				cacheResult(benefitDayQueue);
			}
			else {
				benefitDayQueue.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all benefit day queues.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(BenefitDayQueueImpl.class.getName());
		}

		EntityCacheUtil.clearCache(BenefitDayQueueImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the benefit day queue.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(BenefitDayQueue benefitDayQueue) {
		EntityCacheUtil.removeResult(BenefitDayQueueModelImpl.ENTITY_CACHE_ENABLED,
			BenefitDayQueueImpl.class, benefitDayQueue.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<BenefitDayQueue> benefitDayQueues) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (BenefitDayQueue benefitDayQueue : benefitDayQueues) {
			EntityCacheUtil.removeResult(BenefitDayQueueModelImpl.ENTITY_CACHE_ENABLED,
				BenefitDayQueueImpl.class, benefitDayQueue.getPrimaryKey());
		}
	}

	/**
	 * Creates a new benefit day queue with the primary key. Does not add the benefit day queue to the database.
	 *
	 * @param entryId the primary key for the new benefit day queue
	 * @return the new benefit day queue
	 */
	public BenefitDayQueue create(long entryId) {
		BenefitDayQueue benefitDayQueue = new BenefitDayQueueImpl();

		benefitDayQueue.setNew(true);
		benefitDayQueue.setPrimaryKey(entryId);

		return benefitDayQueue;
	}

	/**
	 * Removes the benefit day queue with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param entryId the primary key of the benefit day queue
	 * @return the benefit day queue that was removed
	 * @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchQueueException if a benefit day queue with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public BenefitDayQueue remove(long entryId)
		throws NoSuchQueueException, SystemException {
		return remove(Long.valueOf(entryId));
	}

	/**
	 * Removes the benefit day queue with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the benefit day queue
	 * @return the benefit day queue that was removed
	 * @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchQueueException if a benefit day queue with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BenefitDayQueue remove(Serializable primaryKey)
		throws NoSuchQueueException, SystemException {
		Session session = null;

		try {
			session = openSession();

			BenefitDayQueue benefitDayQueue = (BenefitDayQueue)session.get(BenefitDayQueueImpl.class,
					primaryKey);

			if (benefitDayQueue == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchQueueException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(benefitDayQueue);
		}
		catch (NoSuchQueueException nsee) {
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
	protected BenefitDayQueue removeImpl(BenefitDayQueue benefitDayQueue)
		throws SystemException {
		benefitDayQueue = toUnwrappedModel(benefitDayQueue);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, benefitDayQueue);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(benefitDayQueue);

		return benefitDayQueue;
	}

	@Override
	public BenefitDayQueue updateImpl(
		com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue benefitDayQueue,
		boolean merge) throws SystemException {
		benefitDayQueue = toUnwrappedModel(benefitDayQueue);

		boolean isNew = benefitDayQueue.isNew();

		BenefitDayQueueModelImpl benefitDayQueueModelImpl = (BenefitDayQueueModelImpl)benefitDayQueue;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, benefitDayQueue, merge);

			benefitDayQueue.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !BenefitDayQueueModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((benefitDayQueueModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(benefitDayQueueModelImpl.getOriginalUserId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);

				args = new Object[] {
						Long.valueOf(benefitDayQueueModelImpl.getUserId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);
			}

			if ((benefitDayQueueModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TYPE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						benefitDayQueueModelImpl.getOriginalType()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TYPE, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TYPE,
					args);

				args = new Object[] { benefitDayQueueModelImpl.getType() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TYPE, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TYPE,
					args);
			}

			if ((benefitDayQueueModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID_QUEUED.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(benefitDayQueueModelImpl.getOriginalUserId()),
						Boolean.valueOf(benefitDayQueueModelImpl.getOriginalQueued())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID_QUEUED,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID_QUEUED,
					args);

				args = new Object[] {
						Long.valueOf(benefitDayQueueModelImpl.getUserId()),
						Boolean.valueOf(benefitDayQueueModelImpl.getQueued())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID_QUEUED,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID_QUEUED,
					args);
			}

			if ((benefitDayQueueModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID_ALLOCATED.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(benefitDayQueueModelImpl.getOriginalUserId()),
						
						benefitDayQueueModelImpl.getOriginalAllocatedDate()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID_ALLOCATED,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID_ALLOCATED,
					args);

				args = new Object[] {
						Long.valueOf(benefitDayQueueModelImpl.getUserId()),
						
						benefitDayQueueModelImpl.getAllocatedDate()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID_ALLOCATED,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID_ALLOCATED,
					args);
			}

			if ((benefitDayQueueModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID_TYPE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(benefitDayQueueModelImpl.getOriginalUserId()),
						
						benefitDayQueueModelImpl.getOriginalType()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID_TYPE,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID_TYPE,
					args);

				args = new Object[] {
						Long.valueOf(benefitDayQueueModelImpl.getUserId()),
						
						benefitDayQueueModelImpl.getType()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID_TYPE,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID_TYPE,
					args);
			}
		}

		EntityCacheUtil.putResult(BenefitDayQueueModelImpl.ENTITY_CACHE_ENABLED,
			BenefitDayQueueImpl.class, benefitDayQueue.getPrimaryKey(),
			benefitDayQueue);

		return benefitDayQueue;
	}

	protected BenefitDayQueue toUnwrappedModel(BenefitDayQueue benefitDayQueue) {
		if (benefitDayQueue instanceof BenefitDayQueueImpl) {
			return benefitDayQueue;
		}

		BenefitDayQueueImpl benefitDayQueueImpl = new BenefitDayQueueImpl();

		benefitDayQueueImpl.setNew(benefitDayQueue.isNew());
		benefitDayQueueImpl.setPrimaryKey(benefitDayQueue.getPrimaryKey());

		benefitDayQueueImpl.setEntryId(benefitDayQueue.getEntryId());
		benefitDayQueueImpl.setType(benefitDayQueue.getType());
		benefitDayQueueImpl.setUserId(benefitDayQueue.getUserId());
		benefitDayQueueImpl.setDaysNo(benefitDayQueue.getDaysNo());
		benefitDayQueueImpl.setQueued(benefitDayQueue.isQueued());
		benefitDayQueueImpl.setAddedDate(benefitDayQueue.getAddedDate());
		benefitDayQueueImpl.setAllocatedDate(benefitDayQueue.getAllocatedDate());

		return benefitDayQueueImpl;
	}

	/**
	 * Returns the benefit day queue with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the benefit day queue
	 * @return the benefit day queue
	 * @throws com.liferay.portal.NoSuchModelException if a benefit day queue with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BenefitDayQueue findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the benefit day queue with the primary key or throws a {@link com.evozon.evoportal.evozonfreedaysallocator.NoSuchQueueException} if it could not be found.
	 *
	 * @param entryId the primary key of the benefit day queue
	 * @return the benefit day queue
	 * @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchQueueException if a benefit day queue with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public BenefitDayQueue findByPrimaryKey(long entryId)
		throws NoSuchQueueException, SystemException {
		BenefitDayQueue benefitDayQueue = fetchByPrimaryKey(entryId);

		if (benefitDayQueue == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + entryId);
			}

			throw new NoSuchQueueException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				entryId);
		}

		return benefitDayQueue;
	}

	/**
	 * Returns the benefit day queue with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the benefit day queue
	 * @return the benefit day queue, or <code>null</code> if a benefit day queue with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BenefitDayQueue fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the benefit day queue with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param entryId the primary key of the benefit day queue
	 * @return the benefit day queue, or <code>null</code> if a benefit day queue with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public BenefitDayQueue fetchByPrimaryKey(long entryId)
		throws SystemException {
		BenefitDayQueue benefitDayQueue = (BenefitDayQueue)EntityCacheUtil.getResult(BenefitDayQueueModelImpl.ENTITY_CACHE_ENABLED,
				BenefitDayQueueImpl.class, entryId);

		if (benefitDayQueue == _nullBenefitDayQueue) {
			return null;
		}

		if (benefitDayQueue == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				benefitDayQueue = (BenefitDayQueue)session.get(BenefitDayQueueImpl.class,
						Long.valueOf(entryId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (benefitDayQueue != null) {
					cacheResult(benefitDayQueue);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(BenefitDayQueueModelImpl.ENTITY_CACHE_ENABLED,
						BenefitDayQueueImpl.class, entryId, _nullBenefitDayQueue);
				}

				closeSession(session);
			}
		}

		return benefitDayQueue;
	}

	/**
	 * Returns all the benefit day queues where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching benefit day queues
	 * @throws SystemException if a system exception occurred
	 */
	public List<BenefitDayQueue> findByUserId(long userId)
		throws SystemException {
		return findByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the benefit day queues where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of benefit day queues
	 * @param end the upper bound of the range of benefit day queues (not inclusive)
	 * @return the range of matching benefit day queues
	 * @throws SystemException if a system exception occurred
	 */
	public List<BenefitDayQueue> findByUserId(long userId, int start, int end)
		throws SystemException {
		return findByUserId(userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the benefit day queues where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of benefit day queues
	 * @param end the upper bound of the range of benefit day queues (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching benefit day queues
	 * @throws SystemException if a system exception occurred
	 */
	public List<BenefitDayQueue> findByUserId(long userId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
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

		List<BenefitDayQueue> list = (List<BenefitDayQueue>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (BenefitDayQueue benefitDayQueue : list) {
				if ((userId != benefitDayQueue.getUserId())) {
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

			query.append(_SQL_SELECT_BENEFITDAYQUEUE_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(BenefitDayQueueModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				list = (List<BenefitDayQueue>)QueryUtil.list(q, getDialect(),
						start, end);
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
	 * Returns the first benefit day queue in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching benefit day queue
	 * @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchQueueException if a matching benefit day queue could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public BenefitDayQueue findByUserId_First(long userId,
		OrderByComparator orderByComparator)
		throws NoSuchQueueException, SystemException {
		BenefitDayQueue benefitDayQueue = fetchByUserId_First(userId,
				orderByComparator);

		if (benefitDayQueue != null) {
			return benefitDayQueue;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchQueueException(msg.toString());
	}

	/**
	 * Returns the first benefit day queue in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching benefit day queue, or <code>null</code> if a matching benefit day queue could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public BenefitDayQueue fetchByUserId_First(long userId,
		OrderByComparator orderByComparator) throws SystemException {
		List<BenefitDayQueue> list = findByUserId(userId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last benefit day queue in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching benefit day queue
	 * @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchQueueException if a matching benefit day queue could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public BenefitDayQueue findByUserId_Last(long userId,
		OrderByComparator orderByComparator)
		throws NoSuchQueueException, SystemException {
		BenefitDayQueue benefitDayQueue = fetchByUserId_Last(userId,
				orderByComparator);

		if (benefitDayQueue != null) {
			return benefitDayQueue;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchQueueException(msg.toString());
	}

	/**
	 * Returns the last benefit day queue in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching benefit day queue, or <code>null</code> if a matching benefit day queue could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public BenefitDayQueue fetchByUserId_Last(long userId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUserId(userId);

		List<BenefitDayQueue> list = findByUserId(userId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the benefit day queues before and after the current benefit day queue in the ordered set where userId = &#63;.
	 *
	 * @param entryId the primary key of the current benefit day queue
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next benefit day queue
	 * @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchQueueException if a benefit day queue with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public BenefitDayQueue[] findByUserId_PrevAndNext(long entryId,
		long userId, OrderByComparator orderByComparator)
		throws NoSuchQueueException, SystemException {
		BenefitDayQueue benefitDayQueue = findByPrimaryKey(entryId);

		Session session = null;

		try {
			session = openSession();

			BenefitDayQueue[] array = new BenefitDayQueueImpl[3];

			array[0] = getByUserId_PrevAndNext(session, benefitDayQueue,
					userId, orderByComparator, true);

			array[1] = benefitDayQueue;

			array[2] = getByUserId_PrevAndNext(session, benefitDayQueue,
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

	protected BenefitDayQueue getByUserId_PrevAndNext(Session session,
		BenefitDayQueue benefitDayQueue, long userId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_BENEFITDAYQUEUE_WHERE);

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
			query.append(BenefitDayQueueModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(benefitDayQueue);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<BenefitDayQueue> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the benefit day queues where type = &#63;.
	 *
	 * @param type the type
	 * @return the matching benefit day queues
	 * @throws SystemException if a system exception occurred
	 */
	public List<BenefitDayQueue> findByType(String type)
		throws SystemException {
		return findByType(type, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the benefit day queues where type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param type the type
	 * @param start the lower bound of the range of benefit day queues
	 * @param end the upper bound of the range of benefit day queues (not inclusive)
	 * @return the range of matching benefit day queues
	 * @throws SystemException if a system exception occurred
	 */
	public List<BenefitDayQueue> findByType(String type, int start, int end)
		throws SystemException {
		return findByType(type, start, end, null);
	}

	/**
	 * Returns an ordered range of all the benefit day queues where type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param type the type
	 * @param start the lower bound of the range of benefit day queues
	 * @param end the upper bound of the range of benefit day queues (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching benefit day queues
	 * @throws SystemException if a system exception occurred
	 */
	public List<BenefitDayQueue> findByType(String type, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TYPE;
			finderArgs = new Object[] { type };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_TYPE;
			finderArgs = new Object[] { type, start, end, orderByComparator };
		}

		List<BenefitDayQueue> list = (List<BenefitDayQueue>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (BenefitDayQueue benefitDayQueue : list) {
				if (!Validator.equals(type, benefitDayQueue.getType())) {
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

			query.append(_SQL_SELECT_BENEFITDAYQUEUE_WHERE);

			if (type == null) {
				query.append(_FINDER_COLUMN_TYPE_TYPE_1);
			}
			else {
				if (type.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_TYPE_TYPE_3);
				}
				else {
					query.append(_FINDER_COLUMN_TYPE_TYPE_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(BenefitDayQueueModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (type != null) {
					qPos.add(type);
				}

				list = (List<BenefitDayQueue>)QueryUtil.list(q, getDialect(),
						start, end);
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
	 * Returns the first benefit day queue in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching benefit day queue
	 * @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchQueueException if a matching benefit day queue could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public BenefitDayQueue findByType_First(String type,
		OrderByComparator orderByComparator)
		throws NoSuchQueueException, SystemException {
		BenefitDayQueue benefitDayQueue = fetchByType_First(type,
				orderByComparator);

		if (benefitDayQueue != null) {
			return benefitDayQueue;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("type=");
		msg.append(type);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchQueueException(msg.toString());
	}

	/**
	 * Returns the first benefit day queue in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching benefit day queue, or <code>null</code> if a matching benefit day queue could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public BenefitDayQueue fetchByType_First(String type,
		OrderByComparator orderByComparator) throws SystemException {
		List<BenefitDayQueue> list = findByType(type, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last benefit day queue in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching benefit day queue
	 * @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchQueueException if a matching benefit day queue could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public BenefitDayQueue findByType_Last(String type,
		OrderByComparator orderByComparator)
		throws NoSuchQueueException, SystemException {
		BenefitDayQueue benefitDayQueue = fetchByType_Last(type,
				orderByComparator);

		if (benefitDayQueue != null) {
			return benefitDayQueue;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("type=");
		msg.append(type);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchQueueException(msg.toString());
	}

	/**
	 * Returns the last benefit day queue in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching benefit day queue, or <code>null</code> if a matching benefit day queue could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public BenefitDayQueue fetchByType_Last(String type,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByType(type);

		List<BenefitDayQueue> list = findByType(type, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the benefit day queues before and after the current benefit day queue in the ordered set where type = &#63;.
	 *
	 * @param entryId the primary key of the current benefit day queue
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next benefit day queue
	 * @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchQueueException if a benefit day queue with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public BenefitDayQueue[] findByType_PrevAndNext(long entryId, String type,
		OrderByComparator orderByComparator)
		throws NoSuchQueueException, SystemException {
		BenefitDayQueue benefitDayQueue = findByPrimaryKey(entryId);

		Session session = null;

		try {
			session = openSession();

			BenefitDayQueue[] array = new BenefitDayQueueImpl[3];

			array[0] = getByType_PrevAndNext(session, benefitDayQueue, type,
					orderByComparator, true);

			array[1] = benefitDayQueue;

			array[2] = getByType_PrevAndNext(session, benefitDayQueue, type,
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

	protected BenefitDayQueue getByType_PrevAndNext(Session session,
		BenefitDayQueue benefitDayQueue, String type,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_BENEFITDAYQUEUE_WHERE);

		if (type == null) {
			query.append(_FINDER_COLUMN_TYPE_TYPE_1);
		}
		else {
			if (type.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_TYPE_TYPE_3);
			}
			else {
				query.append(_FINDER_COLUMN_TYPE_TYPE_2);
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
			query.append(BenefitDayQueueModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (type != null) {
			qPos.add(type);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(benefitDayQueue);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<BenefitDayQueue> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the benefit day queues where userId = &#63; and queued = &#63;.
	 *
	 * @param userId the user ID
	 * @param queued the queued
	 * @return the matching benefit day queues
	 * @throws SystemException if a system exception occurred
	 */
	public List<BenefitDayQueue> findByUserId_Queued(long userId, boolean queued)
		throws SystemException {
		return findByUserId_Queued(userId, queued, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the benefit day queues where userId = &#63; and queued = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param queued the queued
	 * @param start the lower bound of the range of benefit day queues
	 * @param end the upper bound of the range of benefit day queues (not inclusive)
	 * @return the range of matching benefit day queues
	 * @throws SystemException if a system exception occurred
	 */
	public List<BenefitDayQueue> findByUserId_Queued(long userId,
		boolean queued, int start, int end) throws SystemException {
		return findByUserId_Queued(userId, queued, start, end, null);
	}

	/**
	 * Returns an ordered range of all the benefit day queues where userId = &#63; and queued = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param queued the queued
	 * @param start the lower bound of the range of benefit day queues
	 * @param end the upper bound of the range of benefit day queues (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching benefit day queues
	 * @throws SystemException if a system exception occurred
	 */
	public List<BenefitDayQueue> findByUserId_Queued(long userId,
		boolean queued, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID_QUEUED;
			finderArgs = new Object[] { userId, queued };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID_QUEUED;
			finderArgs = new Object[] {
					userId, queued,
					
					start, end, orderByComparator
				};
		}

		List<BenefitDayQueue> list = (List<BenefitDayQueue>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (BenefitDayQueue benefitDayQueue : list) {
				if ((userId != benefitDayQueue.getUserId()) ||
						(queued != benefitDayQueue.getQueued())) {
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

			query.append(_SQL_SELECT_BENEFITDAYQUEUE_WHERE);

			query.append(_FINDER_COLUMN_USERID_QUEUED_USERID_2);

			query.append(_FINDER_COLUMN_USERID_QUEUED_QUEUED_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(BenefitDayQueueModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(queued);

				list = (List<BenefitDayQueue>)QueryUtil.list(q, getDialect(),
						start, end);
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
	 * Returns the first benefit day queue in the ordered set where userId = &#63; and queued = &#63;.
	 *
	 * @param userId the user ID
	 * @param queued the queued
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching benefit day queue
	 * @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchQueueException if a matching benefit day queue could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public BenefitDayQueue findByUserId_Queued_First(long userId,
		boolean queued, OrderByComparator orderByComparator)
		throws NoSuchQueueException, SystemException {
		BenefitDayQueue benefitDayQueue = fetchByUserId_Queued_First(userId,
				queued, orderByComparator);

		if (benefitDayQueue != null) {
			return benefitDayQueue;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", queued=");
		msg.append(queued);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchQueueException(msg.toString());
	}

	/**
	 * Returns the first benefit day queue in the ordered set where userId = &#63; and queued = &#63;.
	 *
	 * @param userId the user ID
	 * @param queued the queued
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching benefit day queue, or <code>null</code> if a matching benefit day queue could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public BenefitDayQueue fetchByUserId_Queued_First(long userId,
		boolean queued, OrderByComparator orderByComparator)
		throws SystemException {
		List<BenefitDayQueue> list = findByUserId_Queued(userId, queued, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last benefit day queue in the ordered set where userId = &#63; and queued = &#63;.
	 *
	 * @param userId the user ID
	 * @param queued the queued
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching benefit day queue
	 * @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchQueueException if a matching benefit day queue could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public BenefitDayQueue findByUserId_Queued_Last(long userId,
		boolean queued, OrderByComparator orderByComparator)
		throws NoSuchQueueException, SystemException {
		BenefitDayQueue benefitDayQueue = fetchByUserId_Queued_Last(userId,
				queued, orderByComparator);

		if (benefitDayQueue != null) {
			return benefitDayQueue;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", queued=");
		msg.append(queued);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchQueueException(msg.toString());
	}

	/**
	 * Returns the last benefit day queue in the ordered set where userId = &#63; and queued = &#63;.
	 *
	 * @param userId the user ID
	 * @param queued the queued
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching benefit day queue, or <code>null</code> if a matching benefit day queue could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public BenefitDayQueue fetchByUserId_Queued_Last(long userId,
		boolean queued, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByUserId_Queued(userId, queued);

		List<BenefitDayQueue> list = findByUserId_Queued(userId, queued,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the benefit day queues before and after the current benefit day queue in the ordered set where userId = &#63; and queued = &#63;.
	 *
	 * @param entryId the primary key of the current benefit day queue
	 * @param userId the user ID
	 * @param queued the queued
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next benefit day queue
	 * @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchQueueException if a benefit day queue with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public BenefitDayQueue[] findByUserId_Queued_PrevAndNext(long entryId,
		long userId, boolean queued, OrderByComparator orderByComparator)
		throws NoSuchQueueException, SystemException {
		BenefitDayQueue benefitDayQueue = findByPrimaryKey(entryId);

		Session session = null;

		try {
			session = openSession();

			BenefitDayQueue[] array = new BenefitDayQueueImpl[3];

			array[0] = getByUserId_Queued_PrevAndNext(session, benefitDayQueue,
					userId, queued, orderByComparator, true);

			array[1] = benefitDayQueue;

			array[2] = getByUserId_Queued_PrevAndNext(session, benefitDayQueue,
					userId, queued, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected BenefitDayQueue getByUserId_Queued_PrevAndNext(Session session,
		BenefitDayQueue benefitDayQueue, long userId, boolean queued,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_BENEFITDAYQUEUE_WHERE);

		query.append(_FINDER_COLUMN_USERID_QUEUED_USERID_2);

		query.append(_FINDER_COLUMN_USERID_QUEUED_QUEUED_2);

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
			query.append(BenefitDayQueueModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		qPos.add(queued);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(benefitDayQueue);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<BenefitDayQueue> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the benefit day queues where userId = &#63; and allocatedDate = &#63;.
	 *
	 * @param userId the user ID
	 * @param allocatedDate the allocated date
	 * @return the matching benefit day queues
	 * @throws SystemException if a system exception occurred
	 */
	public List<BenefitDayQueue> findByUserId_Allocated(long userId,
		Date allocatedDate) throws SystemException {
		return findByUserId_Allocated(userId, allocatedDate, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the benefit day queues where userId = &#63; and allocatedDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param allocatedDate the allocated date
	 * @param start the lower bound of the range of benefit day queues
	 * @param end the upper bound of the range of benefit day queues (not inclusive)
	 * @return the range of matching benefit day queues
	 * @throws SystemException if a system exception occurred
	 */
	public List<BenefitDayQueue> findByUserId_Allocated(long userId,
		Date allocatedDate, int start, int end) throws SystemException {
		return findByUserId_Allocated(userId, allocatedDate, start, end, null);
	}

	/**
	 * Returns an ordered range of all the benefit day queues where userId = &#63; and allocatedDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param allocatedDate the allocated date
	 * @param start the lower bound of the range of benefit day queues
	 * @param end the upper bound of the range of benefit day queues (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching benefit day queues
	 * @throws SystemException if a system exception occurred
	 */
	public List<BenefitDayQueue> findByUserId_Allocated(long userId,
		Date allocatedDate, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID_ALLOCATED;
			finderArgs = new Object[] { userId, allocatedDate };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID_ALLOCATED;
			finderArgs = new Object[] {
					userId, allocatedDate,
					
					start, end, orderByComparator
				};
		}

		List<BenefitDayQueue> list = (List<BenefitDayQueue>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (BenefitDayQueue benefitDayQueue : list) {
				if ((userId != benefitDayQueue.getUserId()) ||
						!Validator.equals(allocatedDate,
							benefitDayQueue.getAllocatedDate())) {
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

			query.append(_SQL_SELECT_BENEFITDAYQUEUE_WHERE);

			query.append(_FINDER_COLUMN_USERID_ALLOCATED_USERID_2);

			if (allocatedDate == null) {
				query.append(_FINDER_COLUMN_USERID_ALLOCATED_ALLOCATEDDATE_1);
			}
			else {
				query.append(_FINDER_COLUMN_USERID_ALLOCATED_ALLOCATEDDATE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(BenefitDayQueueModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				if (allocatedDate != null) {
					qPos.add(CalendarUtil.getTimestamp(allocatedDate));
				}

				list = (List<BenefitDayQueue>)QueryUtil.list(q, getDialect(),
						start, end);
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
	 * Returns the first benefit day queue in the ordered set where userId = &#63; and allocatedDate = &#63;.
	 *
	 * @param userId the user ID
	 * @param allocatedDate the allocated date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching benefit day queue
	 * @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchQueueException if a matching benefit day queue could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public BenefitDayQueue findByUserId_Allocated_First(long userId,
		Date allocatedDate, OrderByComparator orderByComparator)
		throws NoSuchQueueException, SystemException {
		BenefitDayQueue benefitDayQueue = fetchByUserId_Allocated_First(userId,
				allocatedDate, orderByComparator);

		if (benefitDayQueue != null) {
			return benefitDayQueue;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", allocatedDate=");
		msg.append(allocatedDate);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchQueueException(msg.toString());
	}

	/**
	 * Returns the first benefit day queue in the ordered set where userId = &#63; and allocatedDate = &#63;.
	 *
	 * @param userId the user ID
	 * @param allocatedDate the allocated date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching benefit day queue, or <code>null</code> if a matching benefit day queue could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public BenefitDayQueue fetchByUserId_Allocated_First(long userId,
		Date allocatedDate, OrderByComparator orderByComparator)
		throws SystemException {
		List<BenefitDayQueue> list = findByUserId_Allocated(userId,
				allocatedDate, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last benefit day queue in the ordered set where userId = &#63; and allocatedDate = &#63;.
	 *
	 * @param userId the user ID
	 * @param allocatedDate the allocated date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching benefit day queue
	 * @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchQueueException if a matching benefit day queue could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public BenefitDayQueue findByUserId_Allocated_Last(long userId,
		Date allocatedDate, OrderByComparator orderByComparator)
		throws NoSuchQueueException, SystemException {
		BenefitDayQueue benefitDayQueue = fetchByUserId_Allocated_Last(userId,
				allocatedDate, orderByComparator);

		if (benefitDayQueue != null) {
			return benefitDayQueue;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", allocatedDate=");
		msg.append(allocatedDate);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchQueueException(msg.toString());
	}

	/**
	 * Returns the last benefit day queue in the ordered set where userId = &#63; and allocatedDate = &#63;.
	 *
	 * @param userId the user ID
	 * @param allocatedDate the allocated date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching benefit day queue, or <code>null</code> if a matching benefit day queue could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public BenefitDayQueue fetchByUserId_Allocated_Last(long userId,
		Date allocatedDate, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByUserId_Allocated(userId, allocatedDate);

		List<BenefitDayQueue> list = findByUserId_Allocated(userId,
				allocatedDate, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the benefit day queues before and after the current benefit day queue in the ordered set where userId = &#63; and allocatedDate = &#63;.
	 *
	 * @param entryId the primary key of the current benefit day queue
	 * @param userId the user ID
	 * @param allocatedDate the allocated date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next benefit day queue
	 * @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchQueueException if a benefit day queue with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public BenefitDayQueue[] findByUserId_Allocated_PrevAndNext(long entryId,
		long userId, Date allocatedDate, OrderByComparator orderByComparator)
		throws NoSuchQueueException, SystemException {
		BenefitDayQueue benefitDayQueue = findByPrimaryKey(entryId);

		Session session = null;

		try {
			session = openSession();

			BenefitDayQueue[] array = new BenefitDayQueueImpl[3];

			array[0] = getByUserId_Allocated_PrevAndNext(session,
					benefitDayQueue, userId, allocatedDate, orderByComparator,
					true);

			array[1] = benefitDayQueue;

			array[2] = getByUserId_Allocated_PrevAndNext(session,
					benefitDayQueue, userId, allocatedDate, orderByComparator,
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

	protected BenefitDayQueue getByUserId_Allocated_PrevAndNext(
		Session session, BenefitDayQueue benefitDayQueue, long userId,
		Date allocatedDate, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_BENEFITDAYQUEUE_WHERE);

		query.append(_FINDER_COLUMN_USERID_ALLOCATED_USERID_2);

		if (allocatedDate == null) {
			query.append(_FINDER_COLUMN_USERID_ALLOCATED_ALLOCATEDDATE_1);
		}
		else {
			query.append(_FINDER_COLUMN_USERID_ALLOCATED_ALLOCATEDDATE_2);
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
			query.append(BenefitDayQueueModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		if (allocatedDate != null) {
			qPos.add(CalendarUtil.getTimestamp(allocatedDate));
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(benefitDayQueue);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<BenefitDayQueue> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the benefit day queues where userId = &#63; and type = &#63;.
	 *
	 * @param userId the user ID
	 * @param type the type
	 * @return the matching benefit day queues
	 * @throws SystemException if a system exception occurred
	 */
	public List<BenefitDayQueue> findByUserId_Type(long userId, String type)
		throws SystemException {
		return findByUserId_Type(userId, type, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the benefit day queues where userId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param type the type
	 * @param start the lower bound of the range of benefit day queues
	 * @param end the upper bound of the range of benefit day queues (not inclusive)
	 * @return the range of matching benefit day queues
	 * @throws SystemException if a system exception occurred
	 */
	public List<BenefitDayQueue> findByUserId_Type(long userId, String type,
		int start, int end) throws SystemException {
		return findByUserId_Type(userId, type, start, end, null);
	}

	/**
	 * Returns an ordered range of all the benefit day queues where userId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param type the type
	 * @param start the lower bound of the range of benefit day queues
	 * @param end the upper bound of the range of benefit day queues (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching benefit day queues
	 * @throws SystemException if a system exception occurred
	 */
	public List<BenefitDayQueue> findByUserId_Type(long userId, String type,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID_TYPE;
			finderArgs = new Object[] { userId, type };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID_TYPE;
			finderArgs = new Object[] {
					userId, type,
					
					start, end, orderByComparator
				};
		}

		List<BenefitDayQueue> list = (List<BenefitDayQueue>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (BenefitDayQueue benefitDayQueue : list) {
				if ((userId != benefitDayQueue.getUserId()) ||
						!Validator.equals(type, benefitDayQueue.getType())) {
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

			query.append(_SQL_SELECT_BENEFITDAYQUEUE_WHERE);

			query.append(_FINDER_COLUMN_USERID_TYPE_USERID_2);

			if (type == null) {
				query.append(_FINDER_COLUMN_USERID_TYPE_TYPE_1);
			}
			else {
				if (type.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_USERID_TYPE_TYPE_3);
				}
				else {
					query.append(_FINDER_COLUMN_USERID_TYPE_TYPE_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(BenefitDayQueueModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				if (type != null) {
					qPos.add(type);
				}

				list = (List<BenefitDayQueue>)QueryUtil.list(q, getDialect(),
						start, end);
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
	 * Returns the first benefit day queue in the ordered set where userId = &#63; and type = &#63;.
	 *
	 * @param userId the user ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching benefit day queue
	 * @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchQueueException if a matching benefit day queue could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public BenefitDayQueue findByUserId_Type_First(long userId, String type,
		OrderByComparator orderByComparator)
		throws NoSuchQueueException, SystemException {
		BenefitDayQueue benefitDayQueue = fetchByUserId_Type_First(userId,
				type, orderByComparator);

		if (benefitDayQueue != null) {
			return benefitDayQueue;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", type=");
		msg.append(type);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchQueueException(msg.toString());
	}

	/**
	 * Returns the first benefit day queue in the ordered set where userId = &#63; and type = &#63;.
	 *
	 * @param userId the user ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching benefit day queue, or <code>null</code> if a matching benefit day queue could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public BenefitDayQueue fetchByUserId_Type_First(long userId, String type,
		OrderByComparator orderByComparator) throws SystemException {
		List<BenefitDayQueue> list = findByUserId_Type(userId, type, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last benefit day queue in the ordered set where userId = &#63; and type = &#63;.
	 *
	 * @param userId the user ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching benefit day queue
	 * @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchQueueException if a matching benefit day queue could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public BenefitDayQueue findByUserId_Type_Last(long userId, String type,
		OrderByComparator orderByComparator)
		throws NoSuchQueueException, SystemException {
		BenefitDayQueue benefitDayQueue = fetchByUserId_Type_Last(userId, type,
				orderByComparator);

		if (benefitDayQueue != null) {
			return benefitDayQueue;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", type=");
		msg.append(type);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchQueueException(msg.toString());
	}

	/**
	 * Returns the last benefit day queue in the ordered set where userId = &#63; and type = &#63;.
	 *
	 * @param userId the user ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching benefit day queue, or <code>null</code> if a matching benefit day queue could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public BenefitDayQueue fetchByUserId_Type_Last(long userId, String type,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUserId_Type(userId, type);

		List<BenefitDayQueue> list = findByUserId_Type(userId, type, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the benefit day queues before and after the current benefit day queue in the ordered set where userId = &#63; and type = &#63;.
	 *
	 * @param entryId the primary key of the current benefit day queue
	 * @param userId the user ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next benefit day queue
	 * @throws com.evozon.evoportal.evozonfreedaysallocator.NoSuchQueueException if a benefit day queue with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public BenefitDayQueue[] findByUserId_Type_PrevAndNext(long entryId,
		long userId, String type, OrderByComparator orderByComparator)
		throws NoSuchQueueException, SystemException {
		BenefitDayQueue benefitDayQueue = findByPrimaryKey(entryId);

		Session session = null;

		try {
			session = openSession();

			BenefitDayQueue[] array = new BenefitDayQueueImpl[3];

			array[0] = getByUserId_Type_PrevAndNext(session, benefitDayQueue,
					userId, type, orderByComparator, true);

			array[1] = benefitDayQueue;

			array[2] = getByUserId_Type_PrevAndNext(session, benefitDayQueue,
					userId, type, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected BenefitDayQueue getByUserId_Type_PrevAndNext(Session session,
		BenefitDayQueue benefitDayQueue, long userId, String type,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_BENEFITDAYQUEUE_WHERE);

		query.append(_FINDER_COLUMN_USERID_TYPE_USERID_2);

		if (type == null) {
			query.append(_FINDER_COLUMN_USERID_TYPE_TYPE_1);
		}
		else {
			if (type.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_USERID_TYPE_TYPE_3);
			}
			else {
				query.append(_FINDER_COLUMN_USERID_TYPE_TYPE_2);
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
			query.append(BenefitDayQueueModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		if (type != null) {
			qPos.add(type);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(benefitDayQueue);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<BenefitDayQueue> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the benefit day queues.
	 *
	 * @return the benefit day queues
	 * @throws SystemException if a system exception occurred
	 */
	public List<BenefitDayQueue> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the benefit day queues.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of benefit day queues
	 * @param end the upper bound of the range of benefit day queues (not inclusive)
	 * @return the range of benefit day queues
	 * @throws SystemException if a system exception occurred
	 */
	public List<BenefitDayQueue> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the benefit day queues.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of benefit day queues
	 * @param end the upper bound of the range of benefit day queues (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of benefit day queues
	 * @throws SystemException if a system exception occurred
	 */
	public List<BenefitDayQueue> findAll(int start, int end,
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

		List<BenefitDayQueue> list = (List<BenefitDayQueue>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_BENEFITDAYQUEUE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_BENEFITDAYQUEUE.concat(BenefitDayQueueModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<BenefitDayQueue>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<BenefitDayQueue>)QueryUtil.list(q,
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
	 * Removes all the benefit day queues where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByUserId(long userId) throws SystemException {
		for (BenefitDayQueue benefitDayQueue : findByUserId(userId)) {
			remove(benefitDayQueue);
		}
	}

	/**
	 * Removes all the benefit day queues where type = &#63; from the database.
	 *
	 * @param type the type
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByType(String type) throws SystemException {
		for (BenefitDayQueue benefitDayQueue : findByType(type)) {
			remove(benefitDayQueue);
		}
	}

	/**
	 * Removes all the benefit day queues where userId = &#63; and queued = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param queued the queued
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByUserId_Queued(long userId, boolean queued)
		throws SystemException {
		for (BenefitDayQueue benefitDayQueue : findByUserId_Queued(userId,
				queued)) {
			remove(benefitDayQueue);
		}
	}

	/**
	 * Removes all the benefit day queues where userId = &#63; and allocatedDate = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param allocatedDate the allocated date
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByUserId_Allocated(long userId, Date allocatedDate)
		throws SystemException {
		for (BenefitDayQueue benefitDayQueue : findByUserId_Allocated(userId,
				allocatedDate)) {
			remove(benefitDayQueue);
		}
	}

	/**
	 * Removes all the benefit day queues where userId = &#63; and type = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param type the type
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByUserId_Type(long userId, String type)
		throws SystemException {
		for (BenefitDayQueue benefitDayQueue : findByUserId_Type(userId, type)) {
			remove(benefitDayQueue);
		}
	}

	/**
	 * Removes all the benefit day queues from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (BenefitDayQueue benefitDayQueue : findAll()) {
			remove(benefitDayQueue);
		}
	}

	/**
	 * Returns the number of benefit day queues where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching benefit day queues
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUserId(long userId) throws SystemException {
		Object[] finderArgs = new Object[] { userId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_USERID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_BENEFITDAYQUEUE_WHERE);

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
	 * Returns the number of benefit day queues where type = &#63;.
	 *
	 * @param type the type
	 * @return the number of matching benefit day queues
	 * @throws SystemException if a system exception occurred
	 */
	public int countByType(String type) throws SystemException {
		Object[] finderArgs = new Object[] { type };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_TYPE,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_BENEFITDAYQUEUE_WHERE);

			if (type == null) {
				query.append(_FINDER_COLUMN_TYPE_TYPE_1);
			}
			else {
				if (type.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_TYPE_TYPE_3);
				}
				else {
					query.append(_FINDER_COLUMN_TYPE_TYPE_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (type != null) {
					qPos.add(type);
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_TYPE,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of benefit day queues where userId = &#63; and queued = &#63;.
	 *
	 * @param userId the user ID
	 * @param queued the queued
	 * @return the number of matching benefit day queues
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUserId_Queued(long userId, boolean queued)
		throws SystemException {
		Object[] finderArgs = new Object[] { userId, queued };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_USERID_QUEUED,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_BENEFITDAYQUEUE_WHERE);

			query.append(_FINDER_COLUMN_USERID_QUEUED_USERID_2);

			query.append(_FINDER_COLUMN_USERID_QUEUED_QUEUED_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(queued);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_USERID_QUEUED,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of benefit day queues where userId = &#63; and allocatedDate = &#63;.
	 *
	 * @param userId the user ID
	 * @param allocatedDate the allocated date
	 * @return the number of matching benefit day queues
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUserId_Allocated(long userId, Date allocatedDate)
		throws SystemException {
		Object[] finderArgs = new Object[] { userId, allocatedDate };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_USERID_ALLOCATED,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_BENEFITDAYQUEUE_WHERE);

			query.append(_FINDER_COLUMN_USERID_ALLOCATED_USERID_2);

			if (allocatedDate == null) {
				query.append(_FINDER_COLUMN_USERID_ALLOCATED_ALLOCATEDDATE_1);
			}
			else {
				query.append(_FINDER_COLUMN_USERID_ALLOCATED_ALLOCATEDDATE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				if (allocatedDate != null) {
					qPos.add(CalendarUtil.getTimestamp(allocatedDate));
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_USERID_ALLOCATED,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of benefit day queues where userId = &#63; and type = &#63;.
	 *
	 * @param userId the user ID
	 * @param type the type
	 * @return the number of matching benefit day queues
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUserId_Type(long userId, String type)
		throws SystemException {
		Object[] finderArgs = new Object[] { userId, type };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_USERID_TYPE,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_BENEFITDAYQUEUE_WHERE);

			query.append(_FINDER_COLUMN_USERID_TYPE_USERID_2);

			if (type == null) {
				query.append(_FINDER_COLUMN_USERID_TYPE_TYPE_1);
			}
			else {
				if (type.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_USERID_TYPE_TYPE_3);
				}
				else {
					query.append(_FINDER_COLUMN_USERID_TYPE_TYPE_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				if (type != null) {
					qPos.add(type);
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_USERID_TYPE,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of benefit day queues.
	 *
	 * @return the number of benefit day queues
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_BENEFITDAYQUEUE);

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
	 * Initializes the benefit day queue persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.evozon.evoportal.evozonfreedaysallocator.model.BenefitDayQueue")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<BenefitDayQueue>> listenersList = new ArrayList<ModelListener<BenefitDayQueue>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<BenefitDayQueue>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(BenefitDayQueueImpl.class.getName());
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
	private static final String _SQL_SELECT_BENEFITDAYQUEUE = "SELECT benefitDayQueue FROM BenefitDayQueue benefitDayQueue";
	private static final String _SQL_SELECT_BENEFITDAYQUEUE_WHERE = "SELECT benefitDayQueue FROM BenefitDayQueue benefitDayQueue WHERE ";
	private static final String _SQL_COUNT_BENEFITDAYQUEUE = "SELECT COUNT(benefitDayQueue) FROM BenefitDayQueue benefitDayQueue";
	private static final String _SQL_COUNT_BENEFITDAYQUEUE_WHERE = "SELECT COUNT(benefitDayQueue) FROM BenefitDayQueue benefitDayQueue WHERE ";
	private static final String _FINDER_COLUMN_USERID_USERID_2 = "benefitDayQueue.userId = ?";
	private static final String _FINDER_COLUMN_TYPE_TYPE_1 = "benefitDayQueue.type IS NULL";
	private static final String _FINDER_COLUMN_TYPE_TYPE_2 = "benefitDayQueue.type = ?";
	private static final String _FINDER_COLUMN_TYPE_TYPE_3 = "(benefitDayQueue.type IS NULL OR benefitDayQueue.type = ?)";
	private static final String _FINDER_COLUMN_USERID_QUEUED_USERID_2 = "benefitDayQueue.userId = ? AND ";
	private static final String _FINDER_COLUMN_USERID_QUEUED_QUEUED_2 = "benefitDayQueue.queued = ?";
	private static final String _FINDER_COLUMN_USERID_ALLOCATED_USERID_2 = "benefitDayQueue.userId = ? AND ";
	private static final String _FINDER_COLUMN_USERID_ALLOCATED_ALLOCATEDDATE_1 = "benefitDayQueue.allocatedDate IS NULL";
	private static final String _FINDER_COLUMN_USERID_ALLOCATED_ALLOCATEDDATE_2 = "benefitDayQueue.allocatedDate = ?";
	private static final String _FINDER_COLUMN_USERID_TYPE_USERID_2 = "benefitDayQueue.userId = ? AND ";
	private static final String _FINDER_COLUMN_USERID_TYPE_TYPE_1 = "benefitDayQueue.type IS NULL";
	private static final String _FINDER_COLUMN_USERID_TYPE_TYPE_2 = "benefitDayQueue.type = ?";
	private static final String _FINDER_COLUMN_USERID_TYPE_TYPE_3 = "(benefitDayQueue.type IS NULL OR benefitDayQueue.type = ?)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "benefitDayQueue.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No BenefitDayQueue exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No BenefitDayQueue exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(BenefitDayQueuePersistenceImpl.class);
	private static BenefitDayQueue _nullBenefitDayQueue = new BenefitDayQueueImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<BenefitDayQueue> toCacheModel() {
				return _nullBenefitDayQueueCacheModel;
			}
		};

	private static CacheModel<BenefitDayQueue> _nullBenefitDayQueueCacheModel = new CacheModel<BenefitDayQueue>() {
			public BenefitDayQueue toEntityModel() {
				return _nullBenefitDayQueue;
			}
		};
}