package com.ruiyi.askandanswer.user.model;

import java.util.List;

public class RechargeData {


    /**
     * recharges : [{"title":"2019-02-08T00:00:00","rechargeGroup":[{"actionNumber":2,"actionType":2,"operateTime":"2019-02-08T00:00:00","source":"支付宝"}]},{"title":"2019-02-07T00:00:00","rechargeGroup":[{"actionNumber":2,"actionType":2,"operateTime":"2019-02-07T00:00:00","source":"支付宝"}]},{"title":"2019-02-04T00:00:00","rechargeGroup":[{"actionNumber":2,"actionType":2,"operateTime":"2019-02-04T00:00:00","source":"支付宝"}]},{"title":"2019-02-03T00:00:00","rechargeGroup":[{"actionNumber":2,"actionType":2,"operateTime":"2019-02-03T00:00:00","source":"支付宝"}]},{"title":"2019-02-02T00:00:00","rechargeGroup":[{"actionNumber":2,"actionType":2,"operateTime":"2019-02-02T00:00:00","source":"微信"}]},{"title":"2019-02-01T00:00:00","rechargeGroup":[{"actionNumber":2,"actionType":2,"operateTime":"2019-02-01T00:00:00","source":"微信"}]},{"title":"2019-01-31T00:00:00","rechargeGroup":[{"actionNumber":2,"actionType":2,"operateTime":"2019-01-31T00:00:00","source":"微信"}]},{"title":"2019-01-30T00:00:00","rechargeGroup":[{"actionNumber":2,"actionType":2,"operateTime":"2019-01-30T00:00:00","source":"微信"}]},{"title":"2019-01-29T00:00:00","rechargeGroup":[{"actionNumber":2,"actionType":2,"operateTime":"2019-01-29T00:00:00","source":"微信"}]},{"title":"2019-01-28T00:00:00","rechargeGroup":[{"actionNumber":2,"actionType":2,"operateTime":"2019-01-28T00:00:00","source":"微信"}]},{"title":"2019-01-27T00:00:00","rechargeGroup":[{"actionNumber":1,"actionType":2,"operateTime":"2019-01-27T00:00:00","source":"微信"}]},{"title":"2019-01-26T00:00:00","rechargeGroup":[{"actionNumber":1,"actionType":2,"operateTime":"2019-01-26T00:00:00","source":"微信"}]},{"title":"2019-01-25T00:00:00","rechargeGroup":[{"actionNumber":5,"actionType":2,"operateTime":"2019-01-25T00:00:00","source":"支付宝"}]},{"title":"2019-01-10T00:00:00","rechargeGroup":[{"actionNumber":2,"actionType":2,"operateTime":"2019-01-10T00:00:00","source":"支付宝"},{"actionNumber":2,"actionType":2,"operateTime":"2019-01-10T00:00:00","source":"支付宝"}]}]
     * totalNumber : 15
     * currentPage : 1
     */

    private int totalNumber;
    private int currentPage;
    private List<RechargesBean> recharges;

    public int getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public List<RechargesBean> getRecharges() {
        return recharges;
    }

    public void setRecharges(List<RechargesBean> recharges) {
        this.recharges = recharges;
    }

    public static class RechargesBean {
        /**
         * title : 2019-02-08T00:00:00
         * rechargeGroup : [{"actionNumber":2,"actionType":2,"operateTime":"2019-02-08T00:00:00","source":"支付宝"}]
         */

        private String title;
        private List<RechargeGroupBean> rechargeGroup;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<RechargeGroupBean> getRechargeGroup() {
            return rechargeGroup;
        }

        public void setRechargeGroup(List<RechargeGroupBean> rechargeGroup) {
            this.rechargeGroup = rechargeGroup;
        }

        public static class RechargeGroupBean {
            /**
             * actionNumber : 2
             * actionType : 2
             * operateTime : 2019-02-08T00:00:00
             * source : 支付宝
             */

            private float actionNumber;
            private int actionType;
            private String operateTime;
            private String source;

            public float getCoinNumber() {
                return coinNumber;
            }

            public void setCoinNumber(float coinNumber) {
                this.coinNumber = coinNumber;
            }

            private float coinNumber;

            public float getActionNumber() {
                return actionNumber;
            }

            public void setActionNumber(int actionNumber) {
                this.actionNumber = actionNumber;
            }

            public int getActionType() {
                return actionType;
            }

            public void setActionType(int actionType) {
                this.actionType = actionType;
            }

            public String getOperateTime() {
                return operateTime;
            }

            public void setOperateTime(String operateTime) {
                this.operateTime = operateTime;
            }

            public String getSource() {
                return source;
            }

            public void setSource(String source) {
                this.source = source;
            }
        }
    }
}
