package com.ruiyi.askandanswer.user.model;

import java.util.List;

public class AttentionData {


    /**
     * attentions : [{"teacherGuid":"a8b1127e-5efb-11e8-8720-382c4a626a68","teacherName":"刘涛","teacherProvince":"北京","teacherTitle":"","schoolName":"北京展示中心","operateTime":"2019-01-07T16:54:45.387686","helpNumber":50},{"teacherGuid":"a8b1127e-5efb-11e8-8720-382c4a626a68","teacherName":"刘涛","teacherProvince":"北京","teacherTitle":"","schoolName":"北京展示中心","operateTime":"2019-01-07T16:54:36.832949","helpNumber":50},{"teacherGuid":"a8b1127e-5efb-11e8-8720-382c4a626a68","teacherName":"刘涛","teacherProvince":"北京","teacherTitle":"","schoolName":"北京展示中心","operateTime":"2019-01-07T16:52:19.491194","helpNumber":50}]
     * totalNumber : 3
     * currentPage : 1
     */

    private int totalNumber;
    private int currentPage;
    private List<AttentionsBean> attentions;

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

    public List<AttentionsBean> getAttentions() {
        return attentions;
    }

    public void setAttentions(List<AttentionsBean> attentions) {
        this.attentions = attentions;
    }

    public static class AttentionsBean {
        /**
         * teacherGuid : a8b1127e-5efb-11e8-8720-382c4a626a68
         * teacherName : 刘涛
         * teacherProvince : 北京
         * teacherTitle :
         * schoolName : 北京展示中心
         * operateTime : 2019-01-07T16:54:45.387686
         * helpNumber : 50
         */

        private String teacherGuid;
        private String teacherName;
        private String teacherProvince;
        private String teacherTitle;
        private String schoolName;
        private String operateTime;
        private int helpNumber;

        public String getAvatarUrl() {
            return avatarUrl;
        }

        public void setAvatarUrl(String avatarUrl) {
            this.avatarUrl = avatarUrl;
        }

        private String avatarUrl;

        public String getTeacherGuid() {
            return teacherGuid;
        }

        public void setTeacherGuid(String teacherGuid) {
            this.teacherGuid = teacherGuid;
        }

        public String getTeacherName() {
            return teacherName;
        }

        public void setTeacherName(String teacherName) {
            this.teacherName = teacherName;
        }

        public String getTeacherProvince() {
            return teacherProvince;
        }

        public void setTeacherProvince(String teacherProvince) {
            this.teacherProvince = teacherProvince;
        }

        public String getTeacherTitle() {
            return teacherTitle;
        }

        public void setTeacherTitle(String teacherTitle) {
            this.teacherTitle = teacherTitle;
        }

        public String getSchoolName() {
            return schoolName;
        }

        public void setSchoolName(String schoolName) {
            this.schoolName = schoolName;
        }

        public String getOperateTime() {
            return operateTime;
        }

        public void setOperateTime(String operateTime) {
            this.operateTime = operateTime;
        }

        public int getHelpNumber() {
            return helpNumber;
        }

        public void setHelpNumber(int helpNumber) {
            this.helpNumber = helpNumber;
        }
    }
}
