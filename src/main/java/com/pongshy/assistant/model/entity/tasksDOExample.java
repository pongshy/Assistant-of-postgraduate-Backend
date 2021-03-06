package com.pongshy.assistant.model.entity;

import java.util.ArrayList;
import java.util.List;

public class tasksDOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public tasksDOExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andTasknameIsNull() {
            addCriterion("taskName is null");
            return (Criteria) this;
        }

        public Criteria andTasknameIsNotNull() {
            addCriterion("taskName is not null");
            return (Criteria) this;
        }

        public Criteria andTasknameEqualTo(String value) {
            addCriterion("taskName =", value, "taskname");
            return (Criteria) this;
        }

        public Criteria andTasknameNotEqualTo(String value) {
            addCriterion("taskName <>", value, "taskname");
            return (Criteria) this;
        }

        public Criteria andTasknameGreaterThan(String value) {
            addCriterion("taskName >", value, "taskname");
            return (Criteria) this;
        }

        public Criteria andTasknameGreaterThanOrEqualTo(String value) {
            addCriterion("taskName >=", value, "taskname");
            return (Criteria) this;
        }

        public Criteria andTasknameLessThan(String value) {
            addCriterion("taskName <", value, "taskname");
            return (Criteria) this;
        }

        public Criteria andTasknameLessThanOrEqualTo(String value) {
            addCriterion("taskName <=", value, "taskname");
            return (Criteria) this;
        }

        public Criteria andTasknameLike(String value) {
            addCriterion("taskName like", value, "taskname");
            return (Criteria) this;
        }

        public Criteria andTasknameNotLike(String value) {
            addCriterion("taskName not like", value, "taskname");
            return (Criteria) this;
        }

        public Criteria andTasknameIn(List<String> values) {
            addCriterion("taskName in", values, "taskname");
            return (Criteria) this;
        }

        public Criteria andTasknameNotIn(List<String> values) {
            addCriterion("taskName not in", values, "taskname");
            return (Criteria) this;
        }

        public Criteria andTasknameBetween(String value1, String value2) {
            addCriterion("taskName between", value1, value2, "taskname");
            return (Criteria) this;
        }

        public Criteria andTasknameNotBetween(String value1, String value2) {
            addCriterion("taskName not between", value1, value2, "taskname");
            return (Criteria) this;
        }

        public Criteria andTagIsNull() {
            addCriterion("tag is null");
            return (Criteria) this;
        }

        public Criteria andTagIsNotNull() {
            addCriterion("tag is not null");
            return (Criteria) this;
        }

        public Criteria andTagEqualTo(String value) {
            addCriterion("tag =", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagNotEqualTo(String value) {
            addCriterion("tag <>", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagGreaterThan(String value) {
            addCriterion("tag >", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagGreaterThanOrEqualTo(String value) {
            addCriterion("tag >=", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagLessThan(String value) {
            addCriterion("tag <", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagLessThanOrEqualTo(String value) {
            addCriterion("tag <=", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagLike(String value) {
            addCriterion("tag like", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagNotLike(String value) {
            addCriterion("tag not like", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagIn(List<String> values) {
            addCriterion("tag in", values, "tag");
            return (Criteria) this;
        }

        public Criteria andTagNotIn(List<String> values) {
            addCriterion("tag not in", values, "tag");
            return (Criteria) this;
        }

        public Criteria andTagBetween(String value1, String value2) {
            addCriterion("tag between", value1, value2, "tag");
            return (Criteria) this;
        }

        public Criteria andTagNotBetween(String value1, String value2) {
            addCriterion("tag not between", value1, value2, "tag");
            return (Criteria) this;
        }

        public Criteria andLftIsNull() {
            addCriterion("lft is null");
            return (Criteria) this;
        }

        public Criteria andLftIsNotNull() {
            addCriterion("lft is not null");
            return (Criteria) this;
        }

        public Criteria andLftEqualTo(Integer value) {
            addCriterion("lft =", value, "lft");
            return (Criteria) this;
        }

        public Criteria andLftNotEqualTo(Integer value) {
            addCriterion("lft <>", value, "lft");
            return (Criteria) this;
        }

        public Criteria andLftGreaterThan(Integer value) {
            addCriterion("lft >", value, "lft");
            return (Criteria) this;
        }

        public Criteria andLftGreaterThanOrEqualTo(Integer value) {
            addCriterion("lft >=", value, "lft");
            return (Criteria) this;
        }

        public Criteria andLftLessThan(Integer value) {
            addCriterion("lft <", value, "lft");
            return (Criteria) this;
        }

        public Criteria andLftLessThanOrEqualTo(Integer value) {
            addCriterion("lft <=", value, "lft");
            return (Criteria) this;
        }

        public Criteria andLftIn(List<Integer> values) {
            addCriterion("lft in", values, "lft");
            return (Criteria) this;
        }

        public Criteria andLftNotIn(List<Integer> values) {
            addCriterion("lft not in", values, "lft");
            return (Criteria) this;
        }

        public Criteria andLftBetween(Integer value1, Integer value2) {
            addCriterion("lft between", value1, value2, "lft");
            return (Criteria) this;
        }

        public Criteria andLftNotBetween(Integer value1, Integer value2) {
            addCriterion("lft not between", value1, value2, "lft");
            return (Criteria) this;
        }

        public Criteria andRgtIsNull() {
            addCriterion("rgt is null");
            return (Criteria) this;
        }

        public Criteria andRgtIsNotNull() {
            addCriterion("rgt is not null");
            return (Criteria) this;
        }

        public Criteria andRgtEqualTo(Integer value) {
            addCriterion("rgt =", value, "rgt");
            return (Criteria) this;
        }

        public Criteria andRgtNotEqualTo(Integer value) {
            addCriterion("rgt <>", value, "rgt");
            return (Criteria) this;
        }

        public Criteria andRgtGreaterThan(Integer value) {
            addCriterion("rgt >", value, "rgt");
            return (Criteria) this;
        }

        public Criteria andRgtGreaterThanOrEqualTo(Integer value) {
            addCriterion("rgt >=", value, "rgt");
            return (Criteria) this;
        }

        public Criteria andRgtLessThan(Integer value) {
            addCriterion("rgt <", value, "rgt");
            return (Criteria) this;
        }

        public Criteria andRgtLessThanOrEqualTo(Integer value) {
            addCriterion("rgt <=", value, "rgt");
            return (Criteria) this;
        }

        public Criteria andRgtIn(List<Integer> values) {
            addCriterion("rgt in", values, "rgt");
            return (Criteria) this;
        }

        public Criteria andRgtNotIn(List<Integer> values) {
            addCriterion("rgt not in", values, "rgt");
            return (Criteria) this;
        }

        public Criteria andRgtBetween(Integer value1, Integer value2) {
            addCriterion("rgt between", value1, value2, "rgt");
            return (Criteria) this;
        }

        public Criteria andRgtNotBetween(Integer value1, Integer value2) {
            addCriterion("rgt not between", value1, value2, "rgt");
            return (Criteria) this;
        }

        public Criteria andMaintaskidIsNull() {
            addCriterion("mainTaskId is null");
            return (Criteria) this;
        }

        public Criteria andMaintaskidIsNotNull() {
            addCriterion("mainTaskId is not null");
            return (Criteria) this;
        }

        public Criteria andMaintaskidEqualTo(Integer value) {
            addCriterion("mainTaskId =", value, "maintaskid");
            return (Criteria) this;
        }

        public Criteria andMaintaskidNotEqualTo(Integer value) {
            addCriterion("mainTaskId <>", value, "maintaskid");
            return (Criteria) this;
        }

        public Criteria andMaintaskidGreaterThan(Integer value) {
            addCriterion("mainTaskId >", value, "maintaskid");
            return (Criteria) this;
        }

        public Criteria andMaintaskidGreaterThanOrEqualTo(Integer value) {
            addCriterion("mainTaskId >=", value, "maintaskid");
            return (Criteria) this;
        }

        public Criteria andMaintaskidLessThan(Integer value) {
            addCriterion("mainTaskId <", value, "maintaskid");
            return (Criteria) this;
        }

        public Criteria andMaintaskidLessThanOrEqualTo(Integer value) {
            addCriterion("mainTaskId <=", value, "maintaskid");
            return (Criteria) this;
        }

        public Criteria andMaintaskidIn(List<Integer> values) {
            addCriterion("mainTaskId in", values, "maintaskid");
            return (Criteria) this;
        }

        public Criteria andMaintaskidNotIn(List<Integer> values) {
            addCriterion("mainTaskId not in", values, "maintaskid");
            return (Criteria) this;
        }

        public Criteria andMaintaskidBetween(Integer value1, Integer value2) {
            addCriterion("mainTaskId between", value1, value2, "maintaskid");
            return (Criteria) this;
        }

        public Criteria andMaintaskidNotBetween(Integer value1, Integer value2) {
            addCriterion("mainTaskId not between", value1, value2, "maintaskid");
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