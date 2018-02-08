/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.paygateway.mapper.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ConfigsCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ConfigsCriteria() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andUuidIsNull() {
            addCriterion("uuid is null");
            return (Criteria) this;
        }

        public Criteria andUuidIsNotNull() {
            addCriterion("uuid is not null");
            return (Criteria) this;
        }

        public Criteria andUuidEqualTo(String value) {
            addCriterion("uuid =", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidNotEqualTo(String value) {
            addCriterion("uuid <>", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidGreaterThan(String value) {
            addCriterion("uuid >", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidGreaterThanOrEqualTo(String value) {
            addCriterion("uuid >=", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidLessThan(String value) {
            addCriterion("uuid <", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidLessThanOrEqualTo(String value) {
            addCriterion("uuid <=", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidLike(String value) {
            addCriterion("uuid like", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidNotLike(String value) {
            addCriterion("uuid not like", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidIn(List<String> values) {
            addCriterion("uuid in", values, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidNotIn(List<String> values) {
            addCriterion("uuid not in", values, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidBetween(String value1, String value2) {
            addCriterion("uuid between", value1, value2, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidNotBetween(String value1, String value2) {
            addCriterion("uuid not between", value1, value2, "uuid");
            return (Criteria) this;
        }

        public Criteria andCodeTypeUuidIsNull() {
            addCriterion("code_type_uuid is null");
            return (Criteria) this;
        }

        public Criteria andCodeTypeUuidIsNotNull() {
            addCriterion("code_type_uuid is not null");
            return (Criteria) this;
        }

        public Criteria andCodeTypeUuidEqualTo(String value) {
            addCriterion("code_type_uuid =", value, "codeTypeUuid");
            return (Criteria) this;
        }

        public Criteria andCodeTypeUuidNotEqualTo(String value) {
            addCriterion("code_type_uuid <>", value, "codeTypeUuid");
            return (Criteria) this;
        }

        public Criteria andCodeTypeUuidGreaterThan(String value) {
            addCriterion("code_type_uuid >", value, "codeTypeUuid");
            return (Criteria) this;
        }

        public Criteria andCodeTypeUuidGreaterThanOrEqualTo(String value) {
            addCriterion("code_type_uuid >=", value, "codeTypeUuid");
            return (Criteria) this;
        }

        public Criteria andCodeTypeUuidLessThan(String value) {
            addCriterion("code_type_uuid <", value, "codeTypeUuid");
            return (Criteria) this;
        }

        public Criteria andCodeTypeUuidLessThanOrEqualTo(String value) {
            addCriterion("code_type_uuid <=", value, "codeTypeUuid");
            return (Criteria) this;
        }

        public Criteria andCodeTypeUuidLike(String value) {
            addCriterion("code_type_uuid like", value, "codeTypeUuid");
            return (Criteria) this;
        }

        public Criteria andCodeTypeUuidNotLike(String value) {
            addCriterion("code_type_uuid not like", value, "codeTypeUuid");
            return (Criteria) this;
        }

        public Criteria andCodeTypeUuidIn(List<String> values) {
            addCriterion("code_type_uuid in", values, "codeTypeUuid");
            return (Criteria) this;
        }

        public Criteria andCodeTypeUuidNotIn(List<String> values) {
            addCriterion("code_type_uuid not in", values, "codeTypeUuid");
            return (Criteria) this;
        }

        public Criteria andCodeTypeUuidBetween(String value1, String value2) {
            addCriterion("code_type_uuid between", value1, value2, "codeTypeUuid");
            return (Criteria) this;
        }

        public Criteria andCodeTypeUuidNotBetween(String value1, String value2) {
            addCriterion("code_type_uuid not between", value1, value2, "codeTypeUuid");
            return (Criteria) this;
        }

        public Criteria andConfigTypeCodeIsNull() {
            addCriterion("config_type_code is null");
            return (Criteria) this;
        }

        public Criteria andConfigTypeCodeIsNotNull() {
            addCriterion("config_type_code is not null");
            return (Criteria) this;
        }

        public Criteria andConfigTypeCodeEqualTo(String value) {
            addCriterion("config_type_code =", value, "configTypeCode");
            return (Criteria) this;
        }

        public Criteria andConfigTypeCodeNotEqualTo(String value) {
            addCriterion("config_type_code <>", value, "configTypeCode");
            return (Criteria) this;
        }

        public Criteria andConfigTypeCodeGreaterThan(String value) {
            addCriterion("config_type_code >", value, "configTypeCode");
            return (Criteria) this;
        }

        public Criteria andConfigTypeCodeGreaterThanOrEqualTo(String value) {
            addCriterion("config_type_code >=", value, "configTypeCode");
            return (Criteria) this;
        }

        public Criteria andConfigTypeCodeLessThan(String value) {
            addCriterion("config_type_code <", value, "configTypeCode");
            return (Criteria) this;
        }

        public Criteria andConfigTypeCodeLessThanOrEqualTo(String value) {
            addCriterion("config_type_code <=", value, "configTypeCode");
            return (Criteria) this;
        }

        public Criteria andConfigTypeCodeLike(String value) {
            addCriterion("config_type_code like", value, "configTypeCode");
            return (Criteria) this;
        }

        public Criteria andConfigTypeCodeNotLike(String value) {
            addCriterion("config_type_code not like", value, "configTypeCode");
            return (Criteria) this;
        }

        public Criteria andConfigTypeCodeIn(List<String> values) {
            addCriterion("config_type_code in", values, "configTypeCode");
            return (Criteria) this;
        }

        public Criteria andConfigTypeCodeNotIn(List<String> values) {
            addCriterion("config_type_code not in", values, "configTypeCode");
            return (Criteria) this;
        }

        public Criteria andConfigTypeCodeBetween(String value1, String value2) {
            addCriterion("config_type_code between", value1, value2, "configTypeCode");
            return (Criteria) this;
        }

        public Criteria andConfigTypeCodeNotBetween(String value1, String value2) {
            addCriterion("config_type_code not between", value1, value2, "configTypeCode");
            return (Criteria) this;
        }

        public Criteria andConfigItemIsNull() {
            addCriterion("config_item is null");
            return (Criteria) this;
        }

        public Criteria andConfigItemIsNotNull() {
            addCriterion("config_item is not null");
            return (Criteria) this;
        }

        public Criteria andConfigItemEqualTo(String value) {
            addCriterion("config_item =", value, "configItem");
            return (Criteria) this;
        }

        public Criteria andConfigItemNotEqualTo(String value) {
            addCriterion("config_item <>", value, "configItem");
            return (Criteria) this;
        }

        public Criteria andConfigItemGreaterThan(String value) {
            addCriterion("config_item >", value, "configItem");
            return (Criteria) this;
        }

        public Criteria andConfigItemGreaterThanOrEqualTo(String value) {
            addCriterion("config_item >=", value, "configItem");
            return (Criteria) this;
        }

        public Criteria andConfigItemLessThan(String value) {
            addCriterion("config_item <", value, "configItem");
            return (Criteria) this;
        }

        public Criteria andConfigItemLessThanOrEqualTo(String value) {
            addCriterion("config_item <=", value, "configItem");
            return (Criteria) this;
        }

        public Criteria andConfigItemLike(String value) {
            addCriterion("config_item like", value, "configItem");
            return (Criteria) this;
        }

        public Criteria andConfigItemNotLike(String value) {
            addCriterion("config_item not like", value, "configItem");
            return (Criteria) this;
        }

        public Criteria andConfigItemIn(List<String> values) {
            addCriterion("config_item in", values, "configItem");
            return (Criteria) this;
        }

        public Criteria andConfigItemNotIn(List<String> values) {
            addCriterion("config_item not in", values, "configItem");
            return (Criteria) this;
        }

        public Criteria andConfigItemBetween(String value1, String value2) {
            addCriterion("config_item between", value1, value2, "configItem");
            return (Criteria) this;
        }

        public Criteria andConfigItemNotBetween(String value1, String value2) {
            addCriterion("config_item not between", value1, value2, "configItem");
            return (Criteria) this;
        }

        public Criteria andValueIsNull() {
            addCriterion("\"value\" is null");
            return (Criteria) this;
        }

        public Criteria andValueIsNotNull() {
            addCriterion("\"value\" is not null");
            return (Criteria) this;
        }

        public Criteria andValueEqualTo(String value) {
            addCriterion("\"value\" =", value, "value");
            return (Criteria) this;
        }

        public Criteria andValueNotEqualTo(String value) {
            addCriterion("\"value\" <>", value, "value");
            return (Criteria) this;
        }

        public Criteria andValueGreaterThan(String value) {
            addCriterion("\"value\" >", value, "value");
            return (Criteria) this;
        }

        public Criteria andValueGreaterThanOrEqualTo(String value) {
            addCriterion("\"value\" >=", value, "value");
            return (Criteria) this;
        }

        public Criteria andValueLessThan(String value) {
            addCriterion("\"value\" <", value, "value");
            return (Criteria) this;
        }

        public Criteria andValueLessThanOrEqualTo(String value) {
            addCriterion("\"value\" <=", value, "value");
            return (Criteria) this;
        }

        public Criteria andValueLike(String value) {
            addCriterion("\"value\" like", value, "value");
            return (Criteria) this;
        }

        public Criteria andValueNotLike(String value) {
            addCriterion("\"value\" not like", value, "value");
            return (Criteria) this;
        }

        public Criteria andValueIn(List<String> values) {
            addCriterion("\"value\" in", values, "value");
            return (Criteria) this;
        }

        public Criteria andValueNotIn(List<String> values) {
            addCriterion("\"value\" not in", values, "value");
            return (Criteria) this;
        }

        public Criteria andValueBetween(String value1, String value2) {
            addCriterion("\"value\" between", value1, value2, "value");
            return (Criteria) this;
        }

        public Criteria andValueNotBetween(String value1, String value2) {
            addCriterion("\"value\" not between", value1, value2, "value");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagIsNull() {
            addCriterion("delete_flag is null");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagIsNotNull() {
            addCriterion("delete_flag is not null");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagEqualTo(Short value) {
            addCriterion("delete_flag =", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagNotEqualTo(Short value) {
            addCriterion("delete_flag <>", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagGreaterThan(Short value) {
            addCriterion("delete_flag >", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagGreaterThanOrEqualTo(Short value) {
            addCriterion("delete_flag >=", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagLessThan(Short value) {
            addCriterion("delete_flag <", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagLessThanOrEqualTo(Short value) {
            addCriterion("delete_flag <=", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagIn(List<Short> values) {
            addCriterion("delete_flag in", values, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagNotIn(List<Short> values) {
            addCriterion("delete_flag not in", values, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagBetween(Short value1, Short value2) {
            addCriterion("delete_flag between", value1, value2, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagNotBetween(Short value1, Short value2) {
            addCriterion("delete_flag not between", value1, value2, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andCreateUserIsNull() {
            addCriterion("create_user is null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIsNotNull() {
            addCriterion("create_user is not null");
            return (Criteria) this;
        }

        public Criteria andCreateUserEqualTo(String value) {
            addCriterion("create_user =", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotEqualTo(String value) {
            addCriterion("create_user <>", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserGreaterThan(String value) {
            addCriterion("create_user >", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserGreaterThanOrEqualTo(String value) {
            addCriterion("create_user >=", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLessThan(String value) {
            addCriterion("create_user <", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLessThanOrEqualTo(String value) {
            addCriterion("create_user <=", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLike(String value) {
            addCriterion("create_user like", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotLike(String value) {
            addCriterion("create_user not like", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserIn(List<String> values) {
            addCriterion("create_user in", values, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotIn(List<String> values) {
            addCriterion("create_user not in", values, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserBetween(String value1, String value2) {
            addCriterion("create_user between", value1, value2, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotBetween(String value1, String value2) {
            addCriterion("create_user not between", value1, value2, "createUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIsNull() {
            addCriterion("update_user is null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIsNotNull() {
            addCriterion("update_user is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserEqualTo(String value) {
            addCriterion("update_user =", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotEqualTo(String value) {
            addCriterion("update_user <>", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserGreaterThan(String value) {
            addCriterion("update_user >", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserGreaterThanOrEqualTo(String value) {
            addCriterion("update_user >=", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserLessThan(String value) {
            addCriterion("update_user <", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserLessThanOrEqualTo(String value) {
            addCriterion("update_user <=", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserLike(String value) {
            addCriterion("update_user like", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotLike(String value) {
            addCriterion("update_user not like", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIn(List<String> values) {
            addCriterion("update_user in", values, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotIn(List<String> values) {
            addCriterion("update_user not in", values, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserBetween(String value1, String value2) {
            addCriterion("update_user between", value1, value2, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotBetween(String value1, String value2) {
            addCriterion("update_user not between", value1, value2, "updateUser");
            return (Criteria) this;
        }

        public Criteria andCourtUuidIsNull() {
            addCriterion("court_uuid is null");
            return (Criteria) this;
        }

        public Criteria andCourtUuidIsNotNull() {
            addCriterion("court_uuid is not null");
            return (Criteria) this;
        }

        public Criteria andCourtUuidEqualTo(String value) {
            addCriterion("court_uuid =", value, "courtUuid");
            return (Criteria) this;
        }

        public Criteria andCourtUuidNotEqualTo(String value) {
            addCriterion("court_uuid <>", value, "courtUuid");
            return (Criteria) this;
        }

        public Criteria andCourtUuidGreaterThan(String value) {
            addCriterion("court_uuid >", value, "courtUuid");
            return (Criteria) this;
        }

        public Criteria andCourtUuidGreaterThanOrEqualTo(String value) {
            addCriterion("court_uuid >=", value, "courtUuid");
            return (Criteria) this;
        }

        public Criteria andCourtUuidLessThan(String value) {
            addCriterion("court_uuid <", value, "courtUuid");
            return (Criteria) this;
        }

        public Criteria andCourtUuidLessThanOrEqualTo(String value) {
            addCriterion("court_uuid <=", value, "courtUuid");
            return (Criteria) this;
        }

        public Criteria andCourtUuidLike(String value) {
            addCriterion("court_uuid like", value, "courtUuid");
            return (Criteria) this;
        }

        public Criteria andCourtUuidNotLike(String value) {
            addCriterion("court_uuid not like", value, "courtUuid");
            return (Criteria) this;
        }

        public Criteria andCourtUuidIn(List<String> values) {
            addCriterion("court_uuid in", values, "courtUuid");
            return (Criteria) this;
        }

        public Criteria andCourtUuidNotIn(List<String> values) {
            addCriterion("court_uuid not in", values, "courtUuid");
            return (Criteria) this;
        }

        public Criteria andCourtUuidBetween(String value1, String value2) {
            addCriterion("court_uuid between", value1, value2, "courtUuid");
            return (Criteria) this;
        }

        public Criteria andCourtUuidNotBetween(String value1, String value2) {
            addCriterion("court_uuid not between", value1, value2, "courtUuid");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}