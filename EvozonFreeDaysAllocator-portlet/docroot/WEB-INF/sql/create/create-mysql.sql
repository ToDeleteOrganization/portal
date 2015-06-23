drop database if exists lportal;
create database lportal character set utf8;
use lportal;



create index IX_895ECDB0 on BenefitDay_BenefitDay (type_);
create index IX_BADF6FBF on BenefitDay_BenefitDay (userId);
create index IX_D4EA9536 on BenefitDay_BenefitDay (userId, type_);

create index IX_F9AE53BD on BenefitDay_BenefitDayQueue (type_);
create index IX_5480AB52 on BenefitDay_BenefitDayQueue (userId);
create index IX_B0ADCEF3 on BenefitDay_BenefitDayQueue (userId, allocatedDate);
create index IX_FCCD5579 on BenefitDay_BenefitDayQueue (userId, queued);
create index IX_DFCDBE43 on BenefitDay_BenefitDayQueue (userId, type_);

create index IX_B02BA37F on BenefitDay_FreeDaysHistoryEntry (createDate);
create index IX_94DD357B on BenefitDay_FreeDaysHistoryEntry (userId);
create index IX_A95823B9 on BenefitDay_FreeDaysHistoryEntry (userId, createDate);
create index IX_2CC3F349 on BenefitDay_FreeDaysHistoryEntry (userId, eventType);
create index IX_2C21DEFC on BenefitDay_FreeDaysHistoryEntry (userId, operation);


