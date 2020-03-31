package org.example.controller;

import org.example.entity.Course;
import org.example.entity.User;
import org.example.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
//@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/register")
    public String register(){
        return "register";
    }

    @RequestMapping(path="/registerUser")
    public String registerUser(User user){
        System.out.println(user);
        userService.saveUser(user);
        return "login_normal";
//        return "login";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listEmployees(ModelMap map) {

        return "list";
    }


    /*
    @RequestMapping(value = "/login")
    public String login(ModelMap model) {
        System.out.println("userController login()");
        return "login";
    }

     */


    @RequestMapping(value="/login", method=RequestMethod.POST)
    public String login(User user, HttpSession session) {
        List<User> userList = userService.findUser(user);
        if(userList.size() == 1){
//            return "list";
            session.setAttribute("currentUser", userList.get(0));
            return "course";
        }else{
//            return "denied";
            return "login_failed";
        }
    }


    @RequestMapping(value="/registerCourse")
//    public String registerCourse(Course course, HttpSession session){
    public ModelAndView registerCourse(Course course, HttpSession session){
        User currentUser = (User) session.getAttribute("currentUser");
        course.setUser(currentUser);
        currentUser.getCourses().add(course);
        userService.saveUser(currentUser);
        session.setAttribute("courseList",currentUser.getCourses());

        ModelAndView mv = new ModelAndView();
        mv.setViewName("course_success");
        mv.addObject("courseName", course.getCourseName());

        return mv;

        /*
        User user = new User();
        user.setFirstName("haha");
        user.setLastName("hehe");
        user.setEmail("test@gmail.com");
        user.setPassword("123456");

        Set<Course> set = new HashSet<>();
        Course c = new Course();
        c.setCourseName("Lisp");
        c.setDepartment("CS");
        c.setUser(user);

        set.add(c);

        user.setCourses(set);
        System.out.println("-----------------user="+user);
        System.out.println("*****************course=" + c);
        userService.saveUser(user);
        */
//        return "course_success";
    }


    @RequestMapping(value = "/accessdenied", method = RequestMethod.GET)
    public String loginerror(ModelMap model) {
        model.addAttribute("error", "true");
        return "denied";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(ModelMap model) {
        return "logout";
    }
}
