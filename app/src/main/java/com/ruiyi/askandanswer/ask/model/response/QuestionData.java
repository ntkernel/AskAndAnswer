package com.ruiyi.askandanswer.ask.model.response;

import java.util.List;

// FIXME generate failure  field _$Achments105
public class QuestionData {


    /**
     * guid : 0a6e1e12ed56499696f799cb829993a5
     * questionType : 单选题
     * grade : 0
     * answer : ["C"]
     * analysis : <p class="MsoNormal">由题可知焦点为<img width="35" height="42" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACMAAAAqCAMAAAAOCBKjAAAAAXNSR0IArs4c6QAAAGlQTFRFAAAAAAAAAAA6AABmADqQAGa2OgAAOgA6OgBmOjqQOma2OpC2OpDbZgAAZgA6ZgBmZpDbZrb/kDoAkDo6kNv/tmYAtpCQttv/tv//25A627Zm2////7Zm/7aQ/9uQ/9u2/9vb//+2///boV9NygAAAAF0Uk5TAEDm2GYAAAAJcEhZcwAADsQAAA7EAZUrDhsAAAAZdEVYdFNvZnR3YXJlAE1pY3Jvc29mdCBPZmZpY2V/7TVxAAABB0lEQVQ4T+1SXROCIBAEU/vSSlOLJEr+/4/suBMDxN56kxlnHFh295ZlbF3/TeDdT/wyaeJaamsP1P4LD7DD+Y47+rrAYg5lhhiZAo3KjzWv5opEoGtz1D2SZrzjA4UhGn2Jioki4lztwBFKGVPDyfHVck43cBfJ2FBy145Ie5WjPbTSIkZdXB1dAwkdsLYAHHIKL8ahhMvAhZjMYny3OMcMw3FZZIAxerMVaFlrHs7xjL80+7QoJTM7vTnOThkGGF3buPCOFy+MGjys3Jh20K6w4QcDkBXqxiEUJexYLtsxGamP7RijrgJTJKixq6QGXzertVvj103lnPshGNLnj6ZHVNetxQQ+x7gR+7gao/AAAAAASUVORK5CYII=">,∴直线AB的方程为y=-<img width="45" height="42" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAC0AAAAqCAMAAAAQwSIQAAAAAXNSR0IArs4c6QAAAHJQTFRFAAAAAAAAAAA6AABmADqQAGa2OgAAOgA6OgBmOjo6OjqQOma2OpC2OpDbZgAAZgA6ZgBmZma2ZpDbZrb/kDoAkDo6kNv/tmYAtpCQttv/tv//25A627Zm29uQ2////7Zm/7aQ/9uQ/9u2/9vb//+2///bKvAmEQAAAAF0Uk5TAEDm2GYAAAAJcEhZcwAADsQAAA7EAZUrDhsAAAAZdEVYdFNvZnR3YXJlAE1pY3Jvc29mdCBPZmZpY2V/7TVxAAABDklEQVRIS+1TyxKCMAxsFZ/4BkWtUqvw/79ok5SCI029eHDGHHhu0t1NIsQ/ft6BR9lK0INDRI+ZeISZdzIDadXmTH/qfawyoPSI0DphSpvxIpdZW7PO4SUUx8vgQGUVXjsC+lJUJtQScVNgzhIBUdUaddGNTghFtZJI25qBjAsWbXa+TmEJ1TmyElqOhHrvUucTlHXoaquTa1unj1gHbVWsnO9AFQMOpSdMRspAp1XBCCZ009KZSwskEGXnoEkjxMlB6k6RlJ54f3HqjmsV1yKyeIgzW3BTJRR54xnzE5s6qs0WxLZB49nNNgh+00wKYL9p/NBansfydRvvp5AlZiyltfj2yepGbf0DvurAExU+EcrH92ZjAAAAAElFTkSuQmCC">,与抛物线方程联立得<img width="95" height="62" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAF8AAAA+CAMAAAB6Ojc4AAAAAXNSR0IArs4c6QAAAJBQTFRFAAAAAAAAAAA6AABmADqQAGa2OgAAOgA6OgBmOjo6OjqQOma2OpC2OpDbZgAAZgA6ZgBmZjqQZmZmZma2ZpCQZpDbZrb/kDoAkDo6kDpmkGaQkJC2kNvbkNv/tmYAtpA6tpCQttv/tv/btv//25A625Bm27Zm29uQ2////7Zm/7aQ/9uQ/9u2/9vb//+2///b84/bIwAAAAF0Uk5TAEDm2GYAAAAJcEhZcwAADsQAAA7EAZUrDhsAAAAZdEVYdFNvZnR3YXJlAE1pY3Jvc29mdCBPZmZpY2V/7TVxAAACkElEQVRYR+1Xa1fbMAyNoS2wroHBRsYAw0bG6nlz/v+/myQriZ2HH135wDnxpx7HlqV79bgtimUtCCwIvGcEzOXjMd3/u/etmfI2x7w6iXijz/wDcp1jXn8YuDe+bK5enM08dJqvCVgq12N16r4WC0WtAu7rzcdKANieE3XoxvC5pgpx9fx68ki+104A7u+Y98WQu8GF+raod7Cnz3tMsugNwoO4WDZdTrPsh4M1pUD4gQAHxiz74cP6S4uXRJTskshvU0HVKNHvTjPRVPaEEuuiHtdZv+X4UWN+/nyAm5xWGCWt8Wts39yo1a/O1ylXHPtc74CsuZk46r3W+l+YkrPa+2zdIlD6BNUbogQyQ8X7UItruA48+9ze9PmPe+t+AJ/OL3URpKqLk3KJjprL64TOwvmpt2ECODwqA7bvPjlfxra+IOU6AibPcn259s2naN8dFOa8FwX3TImE2liau7QuSldmV81J7ZY5BkHllbTC/XmLaTjsz1njKzZfKMfd+ZLVnuFueD7qLZLkzcfcB0IIwbfn/SDEvPkL7v35PseU3gix2v/2qUxL/CTyJw+9d/umjE2Vw7HBm9yf/89IqFf4pduW+NHe8/UbFMh0gjeVgKF7wBrpt8kxhl3wMCRJPziLSvyImUyjuNcnVOJz9kH+iZ0UwJgUpy8yqmfQDtnv9IktcWxSY5VCxEhQaBgyTHqVlNgsJeb0SR+KFa2oIDAlTPl5FkgvfOY3qk+4D6J9UtERKd29wenY6xP7ZYQPT8bW/+bbU9ofh27Wh/UJjgkUeIT9mrRkojK2mizSRm04cFJeYAJAte0gFZIKzgq4NH1i+c1cpCAS9ckh9rm8krxS/A8l6fByaEFgQeBNEfgHOFo6N4R+h7wAAAAASUVORK5CYII=">消去y,得4x<sup>2</sup>-12px+p<sup>2</sup>=0,设A(x<sub>1</sub>,y<sub>1</sub>),B(x<sub>2</sub>,y<sub>2</sub>),则x<sub>1</sub>+x<sub>2</sub>=3p.∵线段AB的中点的横坐标为3,∴<img width="12" height="42" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAwAAAAqCAMAAACwXkr+AAAAAXNSR0IArs4c6QAAAEtQTFRFAAAAAAAAAAA6ADqQAGa2OgAAOjqQOma2OpC2OpDbZgAAZgA6ZpDbZrb/kDoAkDo6kNv/tpCQ25A627Zm2////7Zm/9uQ//+2///bDMHV2QAAAAF0Uk5TAEDm2GYAAAAJcEhZcwAADsQAAA7EAZUrDhsAAAAZdEVYdFNvZnR3YXJlAE1pY3Jvc29mdCBPZmZpY2V/7TVxAAAAcUlEQVQoU82QzQ6AIAyDO/EPFUWE6fs/qaARl3gzHuyx65p9A/4lrojMdRI3CCW4asfTcwbzUtjoIRyxaDgNHsC13ya79hbbmCZrJ2sGwegK+yAmoW/e4Yi0pFD+7g2ZD4iEWUH5+YqlN8idb6561bIDtxQECOnh514AAAAASUVORK5CYII=">=3,∴p=2,∴抛物线的准线方程为x=-1.</p>
     * difficulty : 0
     * score : 0.0
     * content :
     * achments  : [{"name ":"a1cffc2c023e44dba0594e7cb276b353.files / image001.png ","base64 ":"iVBORw0KGgoAAAANSUhEUgAAACMAAAAqCAMAAAAOCBKjAAAAAXNSR0IArs4c6QAAAGlQTFRFAAAAAAAAAAA6AABmADqQAGa2OgAAOgA6OgBmOjqQOma2OpC2OpDbZgAAZgA6ZgBmZpDbZrb / kDoAkDo6kNv / tmYAtpCQttv / tv //25A627Zm2////7Zm/7aQ/9uQ/9u2/9vb//+2///boV9NygAAAAF0Uk5TAEDm2GYAAAAJcEhZcwAADsQAAA7EAZUrDhsAAAAZdEVYdFNvZnR3YXJlAE1pY3Jvc29mdCBPZmZpY2V/7TVxAAABB0lEQVQ4T+1SXROCIBAEU/vSSlOLJEr+/4/suBMDxN56kxlnHFh295ZlbF3/TeDdT/wyaeJaamsP1P4LD7DD+Y47+rrAYg5lhhiZAo3KjzWv5opEoGtz1D2SZrzjA4UhGn2Jioki4lztwBFKGVPDyfHVck43cBfJ2FBy145Ie5WjPbTSIkZdXB1dAwkdsLYAHHIKL8ahhMvAhZjMYny3OMcMw3FZZIAxerMVaFlrHs7xjL80+7QoJTM7vTnOThkGGF3buPCOFy+MGjys3Jh20K6w4QcDkBXqxiEUJexYLtsxGamP7RijrgJTJKixq6QGXzertVvj103lnPshGNLnj6ZHVNetxQQ+x7gR+7gao/AAAAAASUVORK5CYII=","contentType":"image/png","encoding":"base64"},{"name":"a1cffc2c023e44dba0594e7cb276b353.files/image002.png","base64":"iVBORw0KGgoAAAANSUhEUgAAAC0AAAAqCAMAAAAQwSIQAAAAAXNSR0IArs4c6QAAAHJQTFRFAAAAAAAAAAA6AABmADqQAGa2OgAAOgA6OgBmOjo6OjqQOma2OpC2OpDbZgAAZgA6ZgBmZma2ZpDbZrb/kDoAkDo6kNv/tmYAtpCQttv/tv//25A627Zm29uQ2////7Zm/7aQ/9uQ/9u2/9vb//+2///bKvAmEQAAAAF0Uk5TAEDm2GYAAAAJcEhZcwAADsQAAA7EAZUrDhsAAAAZdEVYdFNvZnR3YXJlAE1pY3Jvc29mdCBPZmZpY2V/7TVxAAABDklEQVRIS+1TyxKCMAxsFZ/4BkWtUqvw/79ok5SCI029eHDGHHhu0t1NIsQ/ft6BR9lK0INDRI+ZeISZdzIDadXmTH/qfawyoPSI0DphSpvxIpdZW7PO4SUUx8vgQGUVXjsC+lJUJtQScVNgzhIBUdUaddGNTghFtZJI25qBjAsWbXa+TmEJ1TmyElqOhHrvUucTlHXoaquTa1unj1gHbVWsnO9AFQMOpSdMRspAp1XBCCZ009KZSwskEGXnoEkjxMlB6k6RlJ54f3HqjmsV1yKyeIgzW3BTJRR54xnzE5s6qs0WxLZB49nNNgh+00wKYL9p/NBansfydRvvp5AlZiyltfj2yepGbf0DvurAExU+EcrH92ZjAAAAAElFTkSuQmCC","contentType":"image/png","encoding":"base64"},{"name":"a1cffc2c023e44dba0594e7cb276b353.files/image003.png","base64":"iVBORw0KGgoAAAANSUhEUgAAAF8AAAA+CAMAAAB6Ojc4AAAAAXNSR0IArs4c6QAAAJBQTFRFAAAAAAAAAAA6AABmADqQAGa2OgAAOgA6OgBmOjo6OjqQOma2OpC2OpDbZgAAZgA6ZgBmZjqQZmZmZma2ZpCQZpDbZrb/kDoAkDo6kDpmkGaQkJC2kNvbkNv/tmYAtpA6tpCQttv/tv/btv//25A625Bm27Zm29uQ2////7Zm/7aQ/9uQ/9u2/9vb//+2///b84/bIwAAAAF0Uk5TAEDm2GYAAAAJcEhZcwAADsQAAA7EAZUrDhsAAAAZdEVYdFNvZnR3YXJlAE1pY3Jvc29mdCBPZmZpY2V/7TVxAAACkElEQVRYR+1Xa1fbMAyNoS2wroHBRsYAw0bG6nlz/v+/myQriZ2HH135wDnxpx7HlqV79bgtimUtCCwIvGcEzOXjMd3/u/etmfI2x7w6iXijz/wDcp1jXn8YuDe+bK5enM08dJqvCVgq12N16r4WC0WtAu7rzcdKANieE3XoxvC5pgpx9fx68ki+104A7u+Y98WQu8GF+raod7Cnz3tMsugNwoO4WDZdTrPsh4M1pUD4gQAHxiz74cP6S4uXRJTskshvU0HVKNHvTjPRVPaEEuuiHtdZv+X4UWN+/nyAm5xWGCWt8Wts39yo1a/O1ylXHPtc74CsuZk46r3W+l+YkrPa+2zdIlD6BNUbogQyQ8X7UItruA48+9ze9PmPe+t+AJ/OL3URpKqLk3KJjprL64TOwvmpt2ECODwqA7bvPjlfxra+IOU6AibPcn259s2naN8dFOa8FwX3TImE2liau7QuSldmV81J7ZY5BkHllbTC/XmLaTjsz1njKzZfKMfd+ZLVnuFueD7qLZLkzcfcB0IIwbfn/SDEvPkL7v35PseU3gix2v/2qUxL/CTyJw+9d/umjE2Vw7HBm9yf/89IqFf4pduW+NHe8/UbFMh0gjeVgKF7wBrpt8kxhl3wMCRJPziLSvyImUyjuNcnVOJz9kH+iZ0UwJgUpy8yqmfQDtnv9IktcWxSY5VCxEhQaBgyTHqVlNgsJeb0SR+KFa2oIDAlTPl5FkgvfOY3qk+4D6J9UtERKd29wenY6xP7ZYQPT8bW/+bbU9ofh27Wh/UJjgkUeIT9mrRkojK2mizSRm04cFJeYAJAte0gFZIKzgq4NH1i+c1cpCAS9ckh9rm8krxS/A8l6fByaEFgQeBNEfgHOFo6N4R+h7wAAAAASUVORK5CYII=","contentType":"image/png","encoding":"base64"},{"name":"a1cffc2c023e44dba0594e7cb276b353.files/image004.png","base64":"iVBORw0KGgoAAAANSUhEUgAAAAwAAAAqCAMAAACwXkr+AAAAAXNSR0IArs4c6QAAAEtQTFRFAAAAAAAAAAA6ADqQAGa2OgAAOjqQOma2OpC2OpDbZgAAZgA6ZpDbZrb/kDoAkDo6kNv/tpCQ25A627Zm2////7Zm/9uQ//+2///bDMHV2QAAAAF0Uk5TAEDm2GYAAAAJcEhZcwAADsQAAA7EAZUrDhsAAAAZdEVYdFNvZnR3YXJlAE1pY3Jvc29mdCBPZmZpY2V/7TVxAAAAcUlEQVQoU82QzQ6AIAyDO/EPFUWE6fs/qaARl3gzHuyx65p9A/4lrojMdRI3CCW4asfTcwbzUtjoIRyxaDgNHsC13ya79hbbmCZrJ2sGwegK+yAmoW/e4Yi0pFD+7g2ZD4iEWUH5+YqlN8idb6561bIDtxQECOnh514AAAAASUVORK5CYII=","contentType":"image/png","encoding":"base64"}]
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
    private int type;
    private Object subjectId;
    private int options;
    private List<String> answer;

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

    public static class AchmentsBean {
        /**
         * name  : a1cffc2c023e44dba0594e7cb276b353.files / image001.png
         * base64  : iVBORw0KGgoAAAANSUhEUgAAACMAAAAqCAMAAAAOCBKjAAAAAXNSR0IArs4c6QAAAGlQTFRFAAAAAAAAAAA6AABmADqQAGa2OgAAOgA6OgBmOjqQOma2OpC2OpDbZgAAZgA6ZgBmZpDbZrb / kDoAkDo6kNv / tmYAtpCQttv / tv //25A627Zm2////7Zm/7aQ/9uQ/9u2/9vb//+2///boV9NygAAAAF0Uk5TAEDm2GYAAAAJcEhZcwAADsQAAA7EAZUrDhsAAAAZdEVYdFNvZnR3YXJlAE1pY3Jvc29mdCBPZmZpY2V/7TVxAAABB0lEQVQ4T+1SXROCIBAEU/vSSlOLJEr+/4/suBMDxN56kxlnHFh295ZlbF3/TeDdT/wyaeJaamsP1P4LD7DD+Y47+rrAYg5lhhiZAo3KjzWv5opEoGtz1D2SZrzjA4UhGn2Jioki4lztwBFKGVPDyfHVck43cBfJ2FBy145Ie5WjPbTSIkZdXB1dAwkdsLYAHHIKL8ahhMvAhZjMYny3OMcMw3FZZIAxerMVaFlrHs7xjL80+7QoJTM7vTnOThkGGF3buPCOFy+MGjys3Jh20K6w4QcDkBXqxiEUJexYLtsxGamP7RijrgJTJKixq6QGXzertVvj103lnPshGNLnj6ZHVNetxQQ+x7gR+7gao/AAAAAASUVORK5CYII=
         * contentType : image/png
         * encoding : base64
         * name : a1cffc2c023e44dba0594e7cb276b353.files/image002.png
         * base64 : iVBORw0KGgoAAAANSUhEUgAAAC0AAAAqCAMAAAAQwSIQAAAAAXNSR0IArs4c6QAAAHJQTFRFAAAAAAAAAAA6AABmADqQAGa2OgAAOgA6OgBmOjo6OjqQOma2OpC2OpDbZgAAZgA6ZgBmZma2ZpDbZrb/kDoAkDo6kNv/tmYAtpCQttv/tv//25A627Zm29uQ2////7Zm/7aQ/9uQ/9u2/9vb//+2///bKvAmEQAAAAF0Uk5TAEDm2GYAAAAJcEhZcwAADsQAAA7EAZUrDhsAAAAZdEVYdFNvZnR3YXJlAE1pY3Jvc29mdCBPZmZpY2V/7TVxAAABDklEQVRIS+1TyxKCMAxsFZ/4BkWtUqvw/79ok5SCI029eHDGHHhu0t1NIsQ/ft6BR9lK0INDRI+ZeISZdzIDadXmTH/qfawyoPSI0DphSpvxIpdZW7PO4SUUx8vgQGUVXjsC+lJUJtQScVNgzhIBUdUaddGNTghFtZJI25qBjAsWbXa+TmEJ1TmyElqOhHrvUucTlHXoaquTa1unj1gHbVWsnO9AFQMOpSdMRspAp1XBCCZ009KZSwskEGXnoEkjxMlB6k6RlJ54f3HqjmsV1yKyeIgzW3BTJRR54xnzE5s6qs0WxLZB49nNNgh+00wKYL9p/NBansfydRvvp5AlZiyltfj2yepGbf0DvurAExU+EcrH92ZjAAAAAElFTkSuQmCC
         */

        private String name;
        private String base64;
        private String contentType;
        private String encoding;

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
    }
}
