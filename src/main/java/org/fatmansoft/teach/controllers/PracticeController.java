package org.fatmansoft.teach.controllers;

import org.fatmansoft.teach.models.Practice;
import org.fatmansoft.teach.models.Student;
import org.fatmansoft.teach.payload.request.DataRequest;
import org.fatmansoft.teach.payload.response.DataResponse;
import org.fatmansoft.teach.repository.PracticeRepository;
import org.fatmansoft.teach.repository.StudentRepository;
import org.fatmansoft.teach.service.IntroduceService;
import org.fatmansoft.teach.util.CommonMethod;
import org.fatmansoft.teach.util.DateTimeTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/teach")

public class PracticeController {
    @Autowired
    private IntroduceService introduceService;
    @Autowired
    private PracticeRepository practiceRepository;
    @Autowired
    private StudentRepository studentRepository;


    public List getPracticeMapList(String numName){
        List dataList = new ArrayList();
        List<Practice> pList = practiceRepository.findPracticeListByNumName(numName);

        if(pList == null || pList.size() == 0)
            return dataList;

        Practice prac;
        Map m;
        for(int i = 0; i < pList.size();i++) {
            prac = pList.get(i);
            m = new HashMap();
            m.put("id", prac.getId());
            m.put("studentNum",prac.getStudentId_practice().getStudentNum());
            m.put("studentName",prac.getStudentName_practice().getStudentName());
            m.put("practiceNum",prac.getPracticeNum());
            m.put("practiceName",prac.getPracticeName());
            if("1".equals(prac.getPracticeKind())) {
                m.put("practiceKind","社会实践");
            }else if("2".equals(prac.getPracticeKind())) {
                m.put("practiceKind","学科竞赛");
            }else if("3".equals(prac.getPracticeKind())) {
                m.put("practiceKind","科研成果");
            }else if("4".equals(prac.getPracticeKind())) {
                m.put("practiceKind","培训讲座");
            }else if("5".equals(prac.getPracticeKind())) {
                m.put("practiceKind","创新创业");
            }else if("6".equals(prac.getPracticeKind())) {
                m.put("practiceKind","校外实习");
            }else if("7".equals(prac.getPracticeKind())) {
                m.put("practiceKind","其他");
            }
            m.put("practiceDate", DateTimeTool.parseDateTime(prac.getPracticeDate(),"yyyy-MM-dd"));
            dataList.add(m);
        }

        return dataList;
    }

    @PostMapping("/practiceInit")
    @PreAuthorize("hasRole('ADMIN')")
    public DataResponse practiceInit(@Valid @RequestBody DataRequest dataRequest) {
        List dataList = getPracticeMapList("");
        return CommonMethod.getReturnData(dataList);
    }

    @PostMapping("/practiceQuery")
    @PreAuthorize("hasRole('ADMIN')")
    public DataResponse practiceQuery(@Valid @RequestBody DataRequest dataRequest) {
        String numName= dataRequest.getString("numName");
        List dataList = getPracticeMapList(numName);
        return CommonMethod.getReturnData(dataList);
    }

    @PostMapping("/practiceEditInit")
    @PreAuthorize("hasRole('ADMIN')")
    public DataResponse practiceEditInit(@Valid @RequestBody DataRequest dataRequest) {
        Integer id = dataRequest.getInteger("id");
        Practice p= null;
        Optional<Practice> op;
        if(id != null) {
            op= practiceRepository.findById(id);
            if(op.isPresent()) {
                p = op.get();
            }
        }
        List kindList = new ArrayList();
        Map m;
        m = new HashMap();
        m.put("label","社会实践");
        m.put("value","1");
        kindList.add(m);
        m = new HashMap();
        m.put("label","学科竞赛");
        m.put("value","2");
        kindList.add(m);
        m = new HashMap();
        m.put("label","科研成果");
        m.put("value","3");
        kindList.add(m);
        m = new HashMap();
        m.put("label","培训讲座");
        m.put("value","4");
        kindList.add(m);
        m = new HashMap();
        m.put("label","创新创业");
        m.put("value","5");
        kindList.add(m);
        m = new HashMap();
        m.put("label","校外实习");
        m.put("value","6");
        kindList.add(m);
        m = new HashMap();
        m.put("label","其他");
        m.put("value","7");
        kindList.add(m);
        Map form = new HashMap();
        if(p != null) {
            form.put("id",p.getId());
            form.put("studentNum",p.getStudentId_practice().getStudentNum());
            form.put("studentName",p.getStudentName_practice().getStudentName());
            form.put("practiceDate", DateTimeTool.parseDateTime(p.getPracticeDate(),"yyyy-MM-dd"));
            form.put("practiceKind",p.getPracticeKind());
            form.put("practiceName",p.getPracticeName());
            form.put("practiceNum",p.getPracticeNum());
        }
        form.put("kindList",kindList);
        return CommonMethod.getReturnData(form);
    }

    public synchronized Integer getNewPracticeId(){
        Integer id = practiceRepository.getMaxId();
        if(id == null)
            id = 1;
        else
            id = id+1;
        return id;
    }

    @PostMapping("/practiceEditSubmit")
    @PreAuthorize(" hasRole('ADMIN')")
    public DataResponse practiceEditSubmit(@Valid @RequestBody DataRequest dataRequest) {
        Map form = dataRequest.getMap("form"); //参数获取Map对象
        Integer id = CommonMethod.getInteger(form,"id");
        String studentNum = CommonMethod.getString(form,"studentNum");  //Map 获取属性的值
        String studentName = CommonMethod.getString(form,"studentName");
        String practiceNum = CommonMethod.getString(form,"practiceNum");
        String practiceName = CommonMethod.getString(form,"practiceName");
        String practiceKind = CommonMethod.getString(form,"practiceKind");
        Date practiceDate = CommonMethod.getDate(form,"practiceDate");
        Optional<Student> studentId=  studentRepository.findByStudentNum(studentNum);
        Optional<Student> studentNa=  studentRepository.findByStudentName(studentName);
        Practice p= null;
        Optional<Practice> op;
        if(id != null) {
            op= practiceRepository.findById(id);
            if(op.isPresent()) {
                p = op.get();
            }
        }
        if(p == null) {
            p = new Practice();
            id = getNewPracticeId();
            p.setId(id);
        }
        if(studentId.isPresent()) {
            p.setStudentId_practice(studentId.get());
        }//设置属性\
        if(studentNa.isPresent()) {
            p.setStudentName_practice(studentNa.get());
        }
        p.setPracticeNum(practiceNum);
        p.setPracticeName(practiceName);
        p.setPracticeKind(practiceKind);
        p.setPracticeDate(practiceDate);
        practiceRepository.save(p);
        return CommonMethod.getReturnData(p.getId());
    }

    @PostMapping("/practiceDelete")
    @PreAuthorize(" hasRole('ADMIN')")
    public DataResponse practiceDelete(@Valid @RequestBody DataRequest dataRequest) {
        Integer id = dataRequest.getInteger("id");
        Practice p= null;
        Optional<Practice> op;
        if(id != null) {
            op= practiceRepository.findById(id);
            if(op.isPresent()) {
                p = op.get();
            }
        }
        if(p != null) {
            practiceRepository.delete(p);
        }
        return CommonMethod.getReturnMessageOK();
    }

    @PostMapping("/getPracticeIntroduceData")
    @PreAuthorize(" hasRole('ADMIN')")
    public DataResponse getPracticeIntroduceData(@Valid @RequestBody DataRequest dataRequest) {
        Map data = introduceService.getIntroduceDataMap();
        return CommonMethod.getReturnData(data);
    }

}
