package com.ruiyi.askandanswer.ask.model.response;

import java.util.List;

public class MoreErrorData {

    private int currentPage;
    private int totalNumber;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
    }

    private List<QuestionDataBeanX> questionData;

    public List<QuestionDataBeanX> getQuestionData() {
        return questionData;
    }

    public void setQuestionData(List<QuestionDataBeanX> questionData) {
        this.questionData = questionData;
    }

    public static class QuestionDataBeanX {
        /**
         * questionGuid : 00729c9861374a0a81015e85e5c6d4d2
         * source : 问答测试
         * wrongRate : 0.0
         * operateTime : 2019-01-01T11:38:57
         * questionData : {"guid":"00729c9861374a0a81015e85e5c6d4d2","questionType":"单选题","grade":0,"answer":["B"],"analysis":"<p class=\"MsoNormal\">∵A={-1,0,1},B={x|-1≤x&lt;1},∴A∩B={-1,0},故选B.<\/p>","difficulty":0,"score":0,"content":"<p class=\"MsoNormal\">已知集合A={-1,0,1},B={x|-1≤x&lt;1},则A∩B=(　　)<\/p><p class=\"MsoNormal\">A.{0}&nbsp;&nbsp; B.{-1,0}<\/p><p class=\"MsoNormal\">C.{0,1} D.{-1,0,1}<\/p>","level":0,"attachments":[{"name":"c5f4e6ced36a4182a922f31a6cd25549.files/image001.png","base64":"iVBORw0KGgoAAAANSUhEUgAAAAwAAAAqCAMAAACwXkr+AAAAAXNSR0IArs4c6QAAAGlQTFRFAAAAAAAAAABmADqQAGa2OgAAOjpmOmZmOma2OpDbZgBmZjqQZmY6ZpDbZrbbZrb/kDoAkDo6kDpmkLb/tmYAtmY6tpCQtrbbtrb/ttuQtv//25A627Zm2////7Zm/9uQ/9vb//+2///bUhcU8QAAAAF0Uk5TAEDm2GYAAAAJcEhZcwAADsQAAA7EAZUrDhsAAAAZdEVYdFNvZnR3YXJlAE1pY3Jvc29mdCBPZmZpY2V/7TVxAAAAcUlEQVQoU2NgGDggz8rIiWS7IrcsgifNj8SGqBJhlhVhkWNkZJJiYFCUEOXhE4crkUaYIybDzqEAM0iARUmAGclUYvzKiASIUU+EGkUuoJOhQEmQDcGR5hOCc+R5lYQkhaGqRECOQASBHCMLEdbQRgkAK24EpGzTjGMAAAAASUVORK5CYII=","contentType":"image/png","encoding":"base64"}],"type":0,"subjectId":null,"options":4}
         */

        private String questionGuid;
        private String source;
        private double wrongRate;
        private String operateTime;
        private QuestionDataBean questionData;

        public String getQuestionGuid() {
            return questionGuid;
        }

        public void setQuestionGuid(String questionGuid) {
            this.questionGuid = questionGuid;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public double getWrongRate() {
            return wrongRate;
        }

        public void setWrongRate(double wrongRate) {
            this.wrongRate = wrongRate;
        }

        public String getOperateTime() {
            return operateTime;
        }

        public void setOperateTime(String operateTime) {
            this.operateTime = operateTime;
        }

        public QuestionDataBean getQuestionData() {
            return questionData;
        }

        public void setQuestionData(QuestionDataBean questionData) {
            this.questionData = questionData;
        }

        public static class QuestionDataBean {
            /**
             * guid : 00729c9861374a0a81015e85e5c6d4d2
             * questionType : 单选题
             * grade : 0
             * answer : ["B"]
             * analysis : <p class="MsoNormal">∵A={-1,0,1},B={x|-1≤x&lt;1},∴A∩B={-1,0},故选B.</p>
             * difficulty : 0
             * score : 0.0
             * content : <p class="MsoNormal">已知集合A={-1,0,1},B={x|-1≤x&lt;1},则A∩B=(　　)</p><p class="MsoNormal">A.{0}&nbsp;&nbsp; B.{-1,0}</p><p class="MsoNormal">C.{0,1} D.{-1,0,1}</p>
             * level : 0
             * attachments : [{"name":"c5f4e6ced36a4182a922f31a6cd25549.files/image001.png","base64":"iVBORw0KGgoAAAANSUhEUgAAAAwAAAAqCAMAAACwXkr+AAAAAXNSR0IArs4c6QAAAGlQTFRFAAAAAAAAAABmADqQAGa2OgAAOjpmOmZmOma2OpDbZgBmZjqQZmY6ZpDbZrbbZrb/kDoAkDo6kDpmkLb/tmYAtmY6tpCQtrbbtrb/ttuQtv//25A627Zm2////7Zm/9uQ/9vb//+2///bUhcU8QAAAAF0Uk5TAEDm2GYAAAAJcEhZcwAADsQAAA7EAZUrDhsAAAAZdEVYdFNvZnR3YXJlAE1pY3Jvc29mdCBPZmZpY2V/7TVxAAAAcUlEQVQoU2NgGDggz8rIiWS7IrcsgifNj8SGqBJhlhVhkWNkZJJiYFCUEOXhE4crkUaYIybDzqEAM0iARUmAGclUYvzKiASIUU+EGkUuoJOhQEmQDcGR5hOCc+R5lYQkhaGqRECOQASBHCMLEdbQRgkAK24EpGzTjGMAAAAASUVORK5CYII=","contentType":"image/png","encoding":"base64"}]
             * type : 0
             * subjectId : null
             * options : 4
             */

            private String guid;
            private String questionType;
            private int grade;
            private String analysis;
            private int difficulty;
            private double score;
            private String content;
            private int level;
            private int type;
            private Object subjectId;
            private int options;
            private List<String> answer;
            private List<AttachmentsBean> attachments;

            public String getGuid() {
                return guid;
            }

            public void setGuid(String guid) {
                this.guid = guid;
            }

            public String getQuestionType() {
                return questionType;
            }

            public void setQuestionType(String questionType) {
                this.questionType = questionType;
            }

            public int getGrade() {
                return grade;
            }

            public void setGrade(int grade) {
                this.grade = grade;
            }

            public String getAnalysis() {
                return analysis;
            }

            public void setAnalysis(String analysis) {
                this.analysis = analysis;
            }

            public int getDifficulty() {
                return difficulty;
            }

            public void setDifficulty(int difficulty) {
                this.difficulty = difficulty;
            }

            public double getScore() {
                return score;
            }

            public void setScore(double score) {
                this.score = score;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public Object getSubjectId() {
                return subjectId;
            }

            public void setSubjectId(Object subjectId) {
                this.subjectId = subjectId;
            }

            public int getOptions() {
                return options;
            }

            public void setOptions(int options) {
                this.options = options;
            }

            public List<String> getAnswer() {
                return answer;
            }

            public void setAnswer(List<String> answer) {
                this.answer = answer;
            }

            public List<AttachmentsBean> getAttachments() {
                return attachments;
            }

            public void setAttachments(List<AttachmentsBean> attachments) {
                this.attachments = attachments;
            }

            public static class AttachmentsBean {
                /**
                 * name : c5f4e6ced36a4182a922f31a6cd25549.files/image001.png
                 * base64 : iVBORw0KGgoAAAANSUhEUgAAAAwAAAAqCAMAAACwXkr+AAAAAXNSR0IArs4c6QAAAGlQTFRFAAAAAAAAAABmADqQAGa2OgAAOjpmOmZmOma2OpDbZgBmZjqQZmY6ZpDbZrbbZrb/kDoAkDo6kDpmkLb/tmYAtmY6tpCQtrbbtrb/ttuQtv//25A627Zm2////7Zm/9uQ/9vb//+2///bUhcU8QAAAAF0Uk5TAEDm2GYAAAAJcEhZcwAADsQAAA7EAZUrDhsAAAAZdEVYdFNvZnR3YXJlAE1pY3Jvc29mdCBPZmZpY2V/7TVxAAAAcUlEQVQoU2NgGDggz8rIiWS7IrcsgifNj8SGqBJhlhVhkWNkZJJiYFCUEOXhE4crkUaYIybDzqEAM0iARUmAGclUYvzKiASIUU+EGkUuoJOhQEmQDcGR5hOCc+R5lYQkhaGqRECOQASBHCMLEdbQRgkAK24EpGzTjGMAAAAASUVORK5CYII=
                 * contentType : image/png
                 * encoding : base64
                 */

                private String name;
                private String base64;
                private String contentType;
                private String encoding;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getBase64() {
                    return base64;
                }

                public void setBase64(String base64) {
                    this.base64 = base64;
                }

                public String getContentType() {
                    return contentType;
                }

                public void setContentType(String contentType) {
                    this.contentType = contentType;
                }

                public String getEncoding() {
                    return encoding;
                }

                public void setEncoding(String encoding) {
                    this.encoding = encoding;
                }
            }
        }
    }
}
