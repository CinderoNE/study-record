package com.cinder.mianshi;

import java.util.*;

/**
 现在程序中有一组学生成绩数据（getScoreList()返回结果），每条数据为某个学生的某科成绩。学生成绩可能不是4科都有，可能有缺科。要求对这组数据进行数据转换和计算处理，转换成学生记录列表，每条记录里面有4科成绩和总成绩，并在此基础上计算全班的平均成绩。最后用控制台输出即可，要求表格按总成绩从高到低排序。计算平均成绩时，缺课也算人次，结果保留三位小数，输出格式如下。
 需要注意的是，最终完成代码的代码质量（逻辑是否清晰，变量/方法命名是否合理等），将作为考察标准的一部分
 姓名		数学		语文		英语		物理		总成绩
 张三		 		  65	   84	     89	      316
 李四		 89		  76				 76		  313
 …	　	　	　	　	　
 平均成绩    83.5	 70.5	  78	    82.5	314.5
 */

public class ShowMeBug {
    public static void main(String[] args) {
        List<ScoreVo> scoreList = getScoreList();
        // TODO 请编写代码实现题目要求
        // 名字作为key用来对应判断对应StudentVo是否已经存在
        HashMap<String,StudentVo> studentVoMap = new HashMap<>();
        for (ScoreVo scoreVo : scoreList){
            String studentName = scoreVo.studentName;
            String courseName = scoreVo.courseName;
            double score = scoreVo.score;
            StudentVo currentStudentVo = null;
            if(!studentVoMap.containsKey(studentName)){
                StudentVo studentVo = new StudentVo();
                studentVoMap.put(studentName,studentVo);
            }
            currentStudentVo = studentVoMap.get(studentName);
            currentStudentVo.setName(studentName);
            switch (courseName){
                case "数学": currentStudentVo.setMathScore(score);break;
                case "语文": currentStudentVo.setChineseScore(score);break;
                case "英语": currentStudentVo.setEnglishScore(score);break;
                case "物理": currentStudentVo.setPhysicalScore(score);break;
                default: break;
            }
        }
        Set<Map.Entry<String, StudentVo>> studentEntries = studentVoMap.entrySet();
        List<Map.Entry<String, StudentVo>> studentList = new ArrayList<>(studentEntries) ;
        //记录每门课的总成绩
        double mathSum=0.0,chineseSum=0.0,englishSum=0.0,physicalSum=0.0;
        System.out.println("姓名\t数学\t语文\t英语\t物理\t总成绩");
        //设置总成绩
        for (Map.Entry<String, StudentVo> studentVoEntry : studentEntries){
            StudentVo studentVo = studentVoEntry.getValue();
            String name = studentVo.name;
            double mathScore = studentVo.mathScore;
            mathSum += mathScore;
            double chineseScore = studentVo.chineseScore;
            chineseSum += chineseScore;
            double englishScore = studentVo.englishScore;
            englishSum += englishScore;
            double physicalScore = studentVo.physicalScore;
            physicalSum += physicalScore;
            double totalScore = mathScore + chineseScore + englishScore + physicalScore;
            studentVo.setTotalScore(totalScore);
        }

        studentList.sort((o1, o2) -> (int) (o2.getValue().totalScore - o1.getValue().totalScore));
        for (Map.Entry<String, StudentVo> studentVoEntry:studentList){
            StudentVo studentVo = studentVoEntry.getValue();
            String name = studentVo.name;
            double mathScore = studentVo.mathScore;
            double chineseScore = studentVo.chineseScore;
            double englishScore = studentVo.englishScore;
            double physicalScore = studentVo.physicalScore;
            double totalScore = studentVo.getTotalScore();
            StringBuilder msgSb = new StringBuilder();
            msgSb.append(name).append("\t");
            //成绩为默认值的话输出空
            if(mathScore == 0.0){
                msgSb.append("    ").append("\t");
            } else {
                msgSb.append(mathScore).append("\t");
            }
            if(chineseScore == 0.0){
                msgSb.append("    ").append("\t");
            } else {
                msgSb.append(chineseScore).append("\t");
            }if(englishScore == 0.0){
                msgSb.append("    ").append("\t");
            } else {
                msgSb.append(englishScore).append("\t");
            }if(physicalScore == 0.0){
                msgSb.append("    ").append("\t");
            } else {
                msgSb.append(physicalScore).append("\t");
            }
            msgSb.append(totalScore);
            System.out.println(msgSb.toString());
        }
        int studentCount = studentVoMap.size();
        StringBuilder avgMsgSb = new StringBuilder();
        avgMsgSb.append("平均成绩").append("\t");
        avgMsgSb.append(String.format("%.3f",mathSum/studentCount)).append("\t");
        avgMsgSb.append(String.format("%.3f",chineseSum/studentCount)).append("\t");
        avgMsgSb.append(String.format("%.3f",englishSum/studentCount)).append("\t");
        avgMsgSb.append(String.format("%.3f",physicalSum/studentCount)).append("\t");
        System.out.println(avgMsgSb.toString());
        // for (ScoreVo scoreVo : scoreList) {
        //     System.out.println(scoreVo.toString());
        // }
    }

    public static List<ScoreVo> getScoreList() {
        List<ScoreVo> scoreList = new ArrayList<ScoreVo>();
        ShowMeBug showMeBug = new ShowMeBug();
        scoreList.add(showMeBug.new ScoreVo("张三", "语文", 80));
        scoreList.add(showMeBug.new ScoreVo("张三", "物理", 76));
        scoreList.add(showMeBug.new ScoreVo("李四", "语文", 78));
        scoreList.add(showMeBug.new ScoreVo("茅十八", "英语", 65));
        scoreList.add(showMeBug.new ScoreVo("李四", "数学", 88));
        scoreList.add(showMeBug.new ScoreVo("李四", "物理", 87));
        scoreList.add(showMeBug.new ScoreVo("王五", "语文", 67));
        scoreList.add(showMeBug.new ScoreVo("张三", "数学", 76));
        scoreList.add(showMeBug.new ScoreVo("李四", "英语", 89));
        scoreList.add(showMeBug.new ScoreVo("王五", "数学", 65));
        scoreList.add(showMeBug.new ScoreVo("赵六", "物理", 95));
        scoreList.add(showMeBug.new ScoreVo("王五", "英语", 78));
        scoreList.add(showMeBug.new ScoreVo("王五", "物理", 65));
        scoreList.add(showMeBug.new ScoreVo("赵六", "语文", 89));
        scoreList.add(showMeBug.new ScoreVo("赵六", "英语", 87));
        scoreList.add(showMeBug.new ScoreVo("黄七", "语文", 78));
        scoreList.add(showMeBug.new ScoreVo("黄七", "数学", 65));
        scoreList.add(showMeBug.new ScoreVo("刘八", "英语", 87));
        scoreList.add(showMeBug.new ScoreVo("张三", "英语", 56));
        scoreList.add(showMeBug.new ScoreVo("黄七", "物理", 76));
        scoreList.add(showMeBug.new ScoreVo("刘八", "数学", 89));
        scoreList.add(showMeBug.new ScoreVo("黄七", "英语", 98));
        scoreList.add(showMeBug.new ScoreVo("刘八", "语文", 56));
        scoreList.add(showMeBug.new ScoreVo("刘八", "物理", 76));
        scoreList.add(showMeBug.new ScoreVo("钱九", "语文", 88));
        scoreList.add(showMeBug.new ScoreVo("钱九", "数学", 67));
        scoreList.add(showMeBug.new ScoreVo("茅十八", "数学", 43));
        scoreList.add(showMeBug.new ScoreVo("钱九", "英语", 75));
        scoreList.add(showMeBug.new ScoreVo("茅十八", "语文", 45));
        scoreList.add(showMeBug.new ScoreVo("茅十八", "物理", 56));

        return scoreList;

    }

    private class ScoreVo {
        public String studentName;
        public String courseName;
        public double score;

        public ScoreVo(String studentName, String courseName, double score) {
            this.studentName = studentName;
            this.courseName = courseName;
            this.score = score;
        }

        public String toString() {
            return this.studentName + "\t" + this.courseName + "\t" + this.score;
        }
    }

    private static class StudentVo {
        private String name;
        private double chineseScore;
        private double mathScore;
        private double englishScore;
        private double physicalScore;
        private double totalScore;

        public StudentVo() {

        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getChineseScore() {
            return chineseScore;
        }

        public void setChineseScore(double chineseScore) {
            this.chineseScore = chineseScore;
        }

        public double getMathScore() {
            return mathScore;
        }

        public void setMathScore(double mathScore) {
            this.mathScore = mathScore;
        }

        public double getEnglishScore() {
            return englishScore;
        }

        public void setEnglishScore(double englishScore) {
            this.englishScore = englishScore;
        }

        public double getPhysicalScore() {
            return physicalScore;
        }

        public void setPhysicalScore(double physicalScore) {
            this.physicalScore = physicalScore;
        }

        public double getTotalScore() {
            return totalScore;
        }

        public void setTotalScore(double totalScore) {
            this.totalScore = totalScore;
        }
    }
}

