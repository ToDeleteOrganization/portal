create table BenefitDay_BenefitDay (
	entryId LONG not null primary key,
	type_ VARCHAR(75) null,
	userId LONG,
	daysNo INTEGER,
	allocatedDate DATE null,
	comment_ VARCHAR(75) null
);

create table BenefitDay_BenefitDayQueue (
	entryId LONG not null primary key,
	type_ VARCHAR(75) null,
	userId LONG,
	daysNo INTEGER,
	queued BOOLEAN,
	addedDate DATE null,
	allocatedDate DATE null
);

create table BenefitDay_FreeDaysHistoryEntry (
	entryId LONG not null primary key,
	userId LONG,
	operation VARCHAR(75) null,
	oldValue INTEGER,
	daysNo INTEGER,
	newValue INTEGER,
	createDate DATE null,
	eventType VARCHAR(75) null,
	description VARCHAR(75) null
);