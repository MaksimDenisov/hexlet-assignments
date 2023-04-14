package exercise.controller;

import exercise.model.Course;
import exercise.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping(path = "")
    public Iterable<Course> getCorses() {
        return courseRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Course getCourse(@PathVariable long id) {
        return courseRepository.findById(id);
    }

    @GetMapping(path = "/{id}/previous")
    public List<Course> getPrevious(@PathVariable long id) {
        Course course = courseRepository.findById(id);
        String path = course.getPath();
        if (path == null)
            return Collections.emptyList();
        String[] ids = path.split("\\.");
        List<Long> courseIds = Arrays.stream(ids).map(Long::parseLong).collect(Collectors.toList());
        return courseRepository.getPrevious(courseIds);
    }


}
