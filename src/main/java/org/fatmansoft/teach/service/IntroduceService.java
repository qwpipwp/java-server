package org.fatmansoft.teach.service;

import org.fatmansoft.teach.models.Student;
import org.fatmansoft.teach.models.Score;
import org.fatmansoft.teach.models.Course;
import org.fatmansoft.teach.repository.ScoreRepository;
import org.fatmansoft.teach.repository.StudentRepository;
import org.fatmansoft.teach.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IntroduceService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ScoreRepository scoreRepository;
    //个人简历信息数据准备方法  请同学修改这个方法，请根据自己的数据的希望展示的内容拼接成字符串，放在Map对象里， attachList 可以方多段内容，具体内容有个人决定
    public Map getIntroduceDataMap(){
        List<Student> sList1 = studentRepository.findStudentListByNumName("张平");  //数据库查询操作
        Student s1;
        s1 = sList1.get(0);
        Map data = new HashMap();
        data.put("myName", "张平");   // 学生信息
        data.put("overview","学号；"+s1.getId());  //学生基本信息综述

        List attachList = new ArrayList();

        Map m;
        List<Score> sList2 = scoreRepository.findScoreListByNumName("张平");  //数据库查询操作
        Score s2;
        s2 = sList2.get(0);
        m = new HashMap();
        m.put("title","学习成绩");   //
        m.put("content","数据库： "+s2.getScore());
        // 学生成绩综述

        attachList.add(m);

        m = new HashMap();
        m.put("title","社会实践");
        m.put("content","社会实践...");  // 社会实践综述

        attachList.add(m);

        data.put("attachList",attachList);
        return data;
    }
}
