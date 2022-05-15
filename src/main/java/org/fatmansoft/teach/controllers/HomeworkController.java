package org.fatmansoft.teach.controllers;

import org.fatmansoft.teach.models.Homework;
import org.fatmansoft.teach.models.Student;
import org.fatmansoft.teach.models.Course;
import org.fatmansoft.teach.payload.request.DataRequest;
import org.fatmansoft.teach.payload.response.DataResponse;
import org.fatmansoft.teach.repository.HomeworkRepository;
import org.fatmansoft.teach.repository.StudentRepository;
import org.fatmansoft.teach.repository.CourseRepository;
import org.fatmansoft.teach.service.IntroduceService;
import org.fatmansoft.teach.util.CommonMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;



// origins： 允许可访问的域列表
// maxAge:准备响应前的缓存持续的最大时间（以秒为单位）。
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/teach")


public class HomeworkController {
    //Java 对象的注入 我们定义的这下Java的操作对象都不能自己管理是由有Spring框架来管理的， TeachController 中要使用StudentRepository接口的实现类对象，
    // 需要下列方式注入，否则无法使用， studentRepository 相当于StudentRepository接口实现对象的一个引用，由框架完成对这个引用的复制，
    // TeachController中的方法可以直接使用
    @Autowired
    private IntroduceService introduceService;
    @Autowired
    private HomeworkRepository homeworkRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;//添加依赖


    //getHomeworkMapList 查询所有学号或姓名与numName相匹配的学生信息，并转换成Map的数据格式存放到List
    //
    // Map 对象是存储数据的集合类，框架会自动将Map转换程用于前后台传输数据的Json对象，Map的嵌套结构和Json的嵌套结构类似，
    //下面方法是生成前端Table数据的示例，List的每一个Map对用显示表中一行的数据
    //Map 每个键值对，对应每一个列的值，
    //按照我们测试框架的要求，每个表的主键都是id, 生成表数据是一定要用m.put("id", s.getId());将id传送前端，前端不显示，
    //但在进入编辑页面是作为参数回传到后台.
    public List getHomeworkMapList(String numName) {
        List dataList = new ArrayList();
        List<Homework> sList = homeworkRepository.findHomeworkListByNumName(numName);  //数据库查询操作
        if(sList == null || sList.size() == 0)
            return dataList;
        Homework s;
        Map m;
        for(int i = 0; i < sList.size();i++) {
            s = sList.get(i);
            m = new HashMap();
            m.put("id", s.getId());
            m.put("studentNum",s.getStudent().getStudentNum());//获取学号
            m.put("studentName",s.getStudent().getStudentName());//获取学生姓名
            m.put("courseNum",s.getCourse().getCourseNum());//获取课程号
            m.put("courseName",s.getCourse().getCourseName());//获取课程名称
            m.put("homework",s.getHomework());//获取作业
            if("1".equals(s.getHomeworkIsDone())){
                m.put("homeworkIsDone","完成");
            }else
                m.put("homeworkIsDone","未完成");
            dataList.add(m);
        }//获取作业完成情况
        return dataList;
    }
    //homework页面初始化方法
    //Table界面初始是请求列表的数据，这里缺省查出所有学生的信息，传递字符“”给方法getHomeworkMapList，返回所有学生数据，
    @PostMapping("/homeworkInit")//成绩页面的初始化方法
    @PreAuthorize("hasRole('ADMIN')")
    public DataResponse homeworkInit(@Valid @RequestBody DataRequest dataRequest)
    {
        String courseName = dataRequest.getString("courseName");//以studentName为key值检索Homework数据库中所有相关数据
        if(courseName == null)
        {
            courseName = "";//为空时传递空串，以显示数据库中所有数据
        }
        List<HashMap<String,Object>> mapList = getHomeworkMapList(courseName);
        return CommonMethod.getReturnData(mapList);//返回数据
    }

    @PostMapping("/homeworkQuery")//查询功能实现
    @PreAuthorize("hasRole('ADMIN')")
    public DataResponse homeworkQuery(@Valid@RequestBody DataRequest dataRequest)
    {
        String numName = dataRequest.getString("numName");//获取从前端返回的查询值
        List<HashMap<String,Object>> mapList = getHomeworkMapList(numName);//使用接口功能查询数据库，将数据存到list中
        return CommonMethod.getReturnData(mapList);//返回查询到的数据
    }

    //homeworkEdit初始化方法
    //homeworkEdit编辑页面进入时首先请求的一个方法， 如果是Edit,再前台会把对应要编辑的那个学生信息的id作为参数回传给后端，我们通过Integer id = dataRequest.getInteger("id")
    //获得对应学生的id， 根据id从数据库中查出数据，存在Map对象里，并返回前端，如果是添加， 则前端没有id传回，Map 对象数据为空（界面上的数据也为空白）

    @PostMapping("/homeworkEditInit")
    @PreAuthorize("hasRole('ADMIN')")
    public DataResponse homeworkEditInit(@Valid @RequestBody DataRequest dataRequest) {
        Integer id = dataRequest.getInteger("id");//获取key值id
        Homework s= null;
        Optional<Homework> op;
        if(id != null) {//添加条件提高稳定性
            op= homeworkRepository.findById(id);//找寻id相应的数据进行处理
            if(op.isPresent()) {
                s = op.get();
            }
        }
        List homeworkIsDoneList = new ArrayList();
        Map m;
        m = new HashMap();
        m.put("label","完成");
        m.put("value","1");
        homeworkIsDoneList.add(m);
        m = new HashMap();
        m.put("label","未完成");
        m.put("value","2");
        homeworkIsDoneList.add(m);//编辑页面的选项处理，每个储存的数据对应作业是否完成
        Map form = new HashMap();
        if(s != null) {
            form.put("id",s.getId());//获取id
            form.put("studentNum",s.getStudent().getStudentNum());//获取学号
            form.put("studentName",s.getStudent().getStudentName());//获取学生姓名
            form.put("courseNum",s.getCourse().getCourseNum());//获取课程号
            form.put("courseName",s.getCourse().getCourseName());//获取课程名称
            form.put("homework",s.getHomework());//作业
            form.put("homeworkIsDone",s.getHomeworkIsDone());//作业
        }
        form.put("homeworkIsDoneList",homeworkIsDoneList);
        return CommonMethod.getReturnData(form); //这里回传包含学生信息的Map对象
    }
    //  信息提交按钮方法
    //相应提交请求的方法，前端把所有数据打包成一个Json对象作为参数传回后端，后端直接可以获得对应的Map对象form, 再从form里取出所有属性，复制到
    //实体对象里，保存到数据库里即可，如果是添加一条记录， id 为空，这是先 new Homework 计算新的id， 复制相关属性，保存，如果是编辑原来的信息，
    //id 不为空。则查询出实体对象，复制相关属性，保存后修改数据库信息，永久修改
    public synchronized Integer getNewHomeworkId(){
        Integer
                id = homeworkRepository.getMaxId();  // 查询最大的id
        if(id == null)
            id = 1;
        else
            id = id+1;
        return id;
    };
    @PostMapping("/homeworkEditSubmit")//作业的存储
    @PreAuthorize(" hasRole('ADMIN')")
    public DataResponse homeworkEditSubmit(@Valid @RequestBody DataRequest dataRequest) {
        Map form = dataRequest.getMap("form"); //参数获取Map对象
        Integer id = CommonMethod.getInteger(form,"id");
        Integer studentId = CommonMethod.getInteger(form,"studentId");//获取学生的id下同课程的id
        Integer courseId = CommonMethod.getInteger(form,"courseId");
        String homework = CommonMethod.getString(form,"homework");//获取作业信息
        String homeworkIsDone = CommonMethod.getString(form,"homeworkIsDone");//获取作业完成情况
        Homework s= null;
        Optional<Homework> op;
        if(id != null) {//选项提高安全性
            op= homeworkRepository.findById(id);  //查询对应数据库中主键为id的值的实体对象
            if(op.isPresent()) {
                s = op.get();
            }
        }
        if(s == null) {
            s = new Homework();   //不存在 创建实体对象
            id = getNewHomeworkId(); //获取鑫的主键，这个是线程同步问题;
            s.setId(id);  //设置新的id
        }
        Student st;
        Course c;
        if(studentId != null) {
            st = studentRepository.findById(studentId).get();//通过接口获取学生数据库中id值对应的相关数据，下同获取课程
            c = courseRepository.findById(courseId).get();
            s.setStudent(st);  //设置属性
            s.setCourse(c);
            st.addCourse(c);//多对多的实现，将课程和学生建立联系，下同
            c.addStudent(st);
            studentRepository.save(st);
            courseRepository.save(c);
        }
        s.setHomework(homework);//获取作业信息
        s.setHomeworkIsDone(homeworkIsDone);//获取作业完成情况
        homeworkRepository.save(s);  //新建和修改都调用save方法
        return CommonMethod.getReturnData(s.getId());  // 将记录的id返回前端
    }

    //  学生信息删除方法
    //Student页面的列表里点击删除按钮则可以删除已经存在的学生信息， 前端会将该记录的id 回传到后端，方法从参数获取id，查出相关记录，调用delete方法删除
    @PostMapping("/homeworkDelete")
    @PreAuthorize(" hasRole('ADMIN')")
    public DataResponse homeworkDelete(@Valid @RequestBody DataRequest dataRequest) {
        Integer id = dataRequest.getInteger("id");  //获取id值
        Homework s= null;
        Optional<Homework> op;
        if(id != null) {
            op= homeworkRepository.findById(id);   //查询获得实体对象
            if(op.isPresent()) {
                s = op.get();//在保证id存在的情况下进行id的获取
            }
        }
        if(s != null) {
            homeworkRepository.delete(s);    //数据库永久删除
        }
        return CommonMethod.getReturnMessageOK();  //通知前端操作正常
    }


}
